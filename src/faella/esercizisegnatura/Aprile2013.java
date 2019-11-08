package faella.esercizisegnatura;

class A {
    public String f(A x, Object y){return"A1";}
    public String f(Object x, B y){return"A2";}
}
class B extends A {
    private String f(B x, A y){return"B1 + "+ f(null,new B());}
    public String f(Object x, B y){return"B2";}}
class C extends B {
    public String f(B x, A y){return"C1 + "+ f(this,b);}
    public String f(A x, Object y){return"C2";}
}
public class Aprile2013 {
    public static void main(String[] args){
        C gamma = new C();
        B beta = gamma;
        A alfa = gamma;
        System.out.println(beta.f(beta, alfa ));
        System.out.println(gamma.f(gamma, alfa));
        //System.out.println(gamma.f(null, gamma));
        System.out.println(8 | 3);
    }
}
