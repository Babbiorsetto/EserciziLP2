package faella.esercizisegnatura;

class A {
    public String f(Object x, A y, A z){return"A1";}
    private String f(B x, C y, B z){return"A2";}
}
class B extends A {
    public String f(Object x, A y, B z){return"B1 + "+ f(null,new B(),z);}
    public String f(A x, B y, C z){return"B2";}}
class C extends B {
    public String f(Object x, A y, B z){return"C1 + "+ f(this,this,z);}
    public String f(B x, C y, B z){return"C2";}
}
public class Aprile2017 {
    public static void main(String[] args){
        C gamma = new C();
        B beta = gamma;
        A alfa = gamma;
        System.out.println(alfa.f(alfa,beta,gamma));
        System.out.println(gamma.f(beta,beta,beta));
        System.out.println(gamma.f(beta,beta,null));
        System.out.println(128 & 4);
    }
}
