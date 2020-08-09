
public class Baz {
    
    private  int x;
    
    private  static  int y = 2;
    
    public  Baz(int x) {
        this.x = x;
    }
    
    public  void  doStuff () {
        x -= y;
        System.out.println(x);
        System.out.println(y);
        y++;
    }
    
    public  static  void  main(String [] args) {
        Baz b1 = new Baz (20);
        b1.doStuff ();
    
        
        Baz b2 = new Baz (15);
        b2.doStuff ();
    
        b1.doStuff ();
        b2.doStuff ();
        b1.doStuff ();
    }
}
