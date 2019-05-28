package faella.esercizisegnatura;

class A {
    public String f(Object a, B b){return"A1";}
    public String f(C a, B b){return"A2";}
}
class B extends A {
    public String f(Object a, B b){return"B1 + "+ f(b, null);}
    public String f(A a, B b){return"B2";}}
class C extends B {
    public String f(Object a, B b){return"C1 + "+ f(this,b);}
    private String f(B a, B b){return"C2";}
}
public class Novembre2014 {
    public static void main(String[] args){
        C gamma = new C();
        B beta = gamma;
        A alfa = gamma;

        System.out.println(gamma.f(beta,beta));
        System.out.println(alfa.f(beta,gamma));
        System.out.println(9 & 12);
    }
}
