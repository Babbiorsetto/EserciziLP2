package faella.esercizisegnatura;

class A {
    public String f(Object a, A b) {
        return "A1";
    }
    public String f(A a,C b) {
        return "A2";
    }
}
class B extends A {
    @Override
    public String f(Object a, A b) {
        System.out.println(this.getClass());
        return "B1 + " + this.f(null, new B());
    }
    // @NotOverridable
    private String f(A a, B b) {
        return "B2";
    }
}
class C extends B {
    public String f(Object a, B b) {
        return"C1";
    }
    // @NotOverride
    public String f(A a, B b) {
        return"C2";
    }
}
public class Tricky1 {
    public static void main(String[]args) {
        C gamma = new C();
        B beta = gamma;
        A alfa = gamma;
        System.out.println(alfa.f(beta, gamma));
        System.out.println(beta.f(beta, beta));
        System.out.println(gamma.f(alfa, null));
        System.out.println(beta instanceof A);
    }
}
