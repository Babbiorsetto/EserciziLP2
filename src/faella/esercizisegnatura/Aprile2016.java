package faella.esercizisegnatura;

class A {
    public String f(Object a, A b){return"A1";}
    private String f(B a, C b){return"A2";}
}
class B extends A {
    public String f(Object a, A b){return"B1 + "+ f(null,new B());}
    public String f(A a, B b){return"B2";}}
class C extends B {
    public String f(Object a, B b){return"C1 + "+ f(this,b);}
    public String f(B a, C b){return"C2";}
}
public class Aprile2016 {
    public static void main(String[] args){
        C gamma = new C();
        B beta = gamma;
        A alfa = gamma;

        System.out.println(alfa.f(beta,gamma));
        System.out.println(gamma.f(beta,beta));
        System.out.println(gamma.f(beta,null));
        System.out.println(8 & 4);
    }
}
