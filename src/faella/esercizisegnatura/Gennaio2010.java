package faella.esercizisegnatura;

class A {
    public String f(int[] a, int l){return"A1";}
    public String f(int[] a, double d){return"A2";}
    public String f(Object o, int l){return"A3";}
}
class B extends A {
    public String f(double[] a, double d){return"B1";}
}
class C extends B {
    public final String f(int[] a, int l){return"C1";}
}
public class Gennaio2010 {

    /* Un array di S e' sottotipo di un array di T
    * se e solo se S e' sottotipo di T.
    * E' chiaro che un array di interi non e' sottotipo
    * di un array di double perche' int non e' sottotipo
    * di double, ma solo assegnabile a double.
    */
    public static void main(String[] args) {
        C gamma = new C();
        B beta = new B();
        A alfa = gamma;
        int[] x = new int[10];
        System.out.println(alfa.f(x, 10));
        System.out.println(beta.f(x, x[1]));
        // System.out.println(gamma.f(null, 10));
        System.out.println(gamma.f(x, 3.0));
        System.out.println(alfa instanceof C);
    }

}
