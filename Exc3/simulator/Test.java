package simulator ;

public class Test {
    public static void main(String[] args) {
        //frames number -- requests number -- pages number -- loops number
        Simulator s1 = new Simulator(3, 1000, 20, 1000) ;
        Simulator s2 = new Simulator(5, 1000, 20, 1000) ; 
        Simulator s3 = new Simulator(10, 1000, 20, 1000) ;  
        s1.simulate() ;
        s2.simulate() ;
        s3.simulate() ; 
    }
}
