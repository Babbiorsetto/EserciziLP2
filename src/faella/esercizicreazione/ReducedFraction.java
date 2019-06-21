package faella.esercizicreazione;

import java.math.BigInteger;

public class ReducedFraction extends Fraction {

    public ReducedFraction(int n, int d) {
        super(
            n / ( BigInteger.valueOf(n).gcd(BigInteger.valueOf(d)) ).intValue(),
            d / ( BigInteger.valueOf(n).gcd(BigInteger.valueOf(d)) ).intValue());
    }

    public ReducedFraction(Fraction f) {
        this(f.n, f.d);
    }

    @Override
    public Fraction times(Fraction other) {
        if (other instanceof ReducedFraction) {
            return new ReducedFraction(n * other.n, d * other.d);
        }
        return super.times(other);
    }

}
