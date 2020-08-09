import java.util.Collection;
import java.util.Set;

public class Foo {
    protected  int a = 10;
    
    
    protected  String b = "hello";
    
    public  Foo() {
        this (251, "CS");
    }
   
    public  Foo(String a) {
        this (2017, a);
    }
    
    public  Foo(int a, String b) {
        this.a = b.length ();
        this.b = b + a;
    }
    
    public  void  doStuff(String c) {
        b = c;
        doStuff ();
    }
    
    public  void  doStuff () {
        System.out.println(a);
        System.out.println(b);
    }
}
