package faella.esercizithread;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class GuessTheNumber extends Thread {

	private int secret;
	private long duration;
	private boolean isActive;
	private Thread winner;
	private List<Guess> guesses = new LinkedList<>();

	public GuessTheNumber(int secret, long duration) {
		this.secret = secret;
		this.duration = duration;
		start();
	}

	@Override
	public void run() {
		synchronized (guesses) {
			isActive = true;
		}
		try {sleep(duration);} catch (InterruptedException e) {return;};
		synchronized (guesses) {
            Thread closest = null;
            int minDistance = 0;

            isActive = false;
			if (!guesses.isEmpty()) {
				closest = guesses.get(0).getVoter();
				minDistance = Math.abs(guesses.get(0).getVote() - secret);
			}
			for (Guess g : guesses) {
				if (Math.abs(g.getVote() - secret) < minDistance) {
					closest = g.getVoter();
					minDistance = Math.abs(g.getVote() - secret);
				}
			}
			winner = closest;
		}
	}

	public boolean guessAndWait(int guess) throws InterruptedException {
		if (isActive) {
			synchronized (guesses) {
				if (isActive) {
					guesses.add(new Guess(currentThread(), guess));
				}
			}
			join();
		}
		return winner == currentThread();
	}

	private static class Guess {
		private Thread voter;
		private int vote;

		public Guess(Thread t, int v) {
			voter = t;
			vote = v;
		}

		public Thread getVoter() {
			return voter;
		}

		public int getVote() {
			return vote;
		}


	}

    public static void main(String[] args) {

		final int n_thread = 20;
		final int durata = 300;
		final int MAX = 20;
		final int numero = new Random().nextInt(MAX);
		final int tSleep = 10;

		Thread[] threads = new Thread[n_thread];
		GuessTheNumber gn = new GuessTheNumber(numero, durata);

		class MyThread extends Thread {

			public MyThread(String name) {
				super(name);
			}

			@Override
			public void run() {
				int guess = new Random().nextInt(MAX);
				boolean response = false;
				try {
					sleep(tSleep);
					response = gn.guessAndWait(guess);
				} catch (InterruptedException e) {

				}
				System.out.println(currentThread() + " " + guess + " " + response );
			}

		}

		for (int i = 0; i < n_thread; i++) {
			threads[i] = new MyThread(i + "");
			threads[i].start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Finito! Il numero era: " + numero);
	}
}
