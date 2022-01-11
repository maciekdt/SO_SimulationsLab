import java.util.ArrayList;

public class Simulator {

    private Disc disc ;
    private ArrayList<Request> rqList ;
    private int discSize ;
    private Algorithms algorithms ;
    private int maxRequestsNumber ;
    private int loopsNumber ;

    public Simulator(int discSize, int maxRequestsNumber, int loopsNumber){
        this.discSize = discSize ;
        this.maxRequestsNumber = maxRequestsNumber ;
        this.loopsNumber = loopsNumber ;

        disc = new Disc(discSize) ;
        disc.restart() ; 

        rqList = new ArrayList<Request>() ;
        algorithms = new Algorithms() ;

    }

    public void createRandomRQlist(){
        ArrayList<Request> list = new ArrayList<Request>() ;
        int number = (int) (Math.random() * maxRequestsNumber) + 2 ;
        for(int i=0; i<number; i++){
            int randomBlock = (int) (Math.random() * discSize) ;
            int randomDeadline = 1 ;//(int) (Math.random() * 10) ;
            list.add(new Request(randomBlock, randomDeadline, i)) ;
        }
        rqList = list ;
    }

    public void simulation(){

        int fcfs = 0 ;
        int sstf = 0 ;
        int scan = 0 ;
        int cscan = 0 ;
        int edf = 0 ;
        int fdscan = 0 ;

        for(int i=0; i<loopsNumber; i++){
            disc.restart() ;
            createRandomRQlist() ;

            disc.reset() ;
            fcfs += algorithms.FCFS(rqList, disc) ;

            disc.reset() ;
            sstf += algorithms.SSTF(rqList, disc) ;

            disc.reset() ;
            scan += algorithms.SCAN(rqList, disc) ;

            disc.reset() ;
            cscan += algorithms.CSCAN(rqList, disc) ;

            disc.reset() ;
            edf += algorithms.EDF(rqList, disc) ;

            disc.reset() ;
            fdscan += algorithms.FDSCAN(rqList, disc) ;
        }





        disc.restart() ;
        createRandomRQlist() ;
        
        System.out.println("-------------------------") ;
        System.out.println("FCFS    --  time : " + Double.valueOf(fcfs)/Double.valueOf(loopsNumber));
        System.out.println("SSTF    --  time : " + Double.valueOf(sstf)/Double.valueOf(loopsNumber));
        System.out.println("SCAN    --  time : " + Double.valueOf(scan)/Double.valueOf(loopsNumber));
        System.out.println("C-SCAN  --  time : " + Double.valueOf(cscan)/Double.valueOf(loopsNumber));
        System.out.println("") ;
        System.out.println("EDF     --  time : " + Double.valueOf(edf)/Double.valueOf(loopsNumber));
        System.out.println("FD-SCAN --  time : " + Double.valueOf(fdscan)/Double.valueOf(loopsNumber));
        System.out.println("-------------------------") ;

        
    }
}
