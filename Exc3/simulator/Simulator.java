package simulator ;
import algorithms.* ;
import computer.* ;

public class Simulator {
    private Memory memory ;
    private RequestsGenerator requestsGenerator ;
    private int loopsNumber ;
    private int framesNumber ;
    private int requestsNumber ;
    private int pagesTypesNumber ;

    public Simulator(int framesNumber, int requestsNumber, int pagesTypesNumber, int loopsNumber){
        this.loopsNumber = loopsNumber ;
        this.framesNumber = framesNumber ;
        this.requestsNumber = requestsNumber ;
        this.pagesTypesNumber = pagesTypesNumber ;
        memory = new Memory(framesNumber) ;
        requestsGenerator = new RequestsGenerator(requestsNumber, pagesTypesNumber) ;
    }

    public void simulate(){
        FifoAlgorithm fifo = new FifoAlgorithm() ;
        double fifoSum = 0 ;

        LruAlgorithm lru = new LruAlgorithm() ;
        double lruSum = 0 ;

        OptAlgorithm opt = new OptAlgorithm() ;
        double optSum = 0 ;

        RandAlgorithm rand = new RandAlgorithm() ;
        double randSum = 0 ;

        ScaAlgorithm sca = new ScaAlgorithm() ;
        double scaSum = 0 ;
        
        for(int i=0; i<loopsNumber; i++){
            requestsGenerator.generateRequestsList();

            memory.restart() ;
            fifo.run(memory, requestsGenerator.getRequestsList()) ;
            fifoSum += memory.getErrorsNumber();

            memory.restart() ;
            lru.run(memory, requestsGenerator.getRequestsList()) ;
            lruSum += memory.getErrorsNumber();

            memory.restart() ;
            opt.run(memory, requestsGenerator.getRequestsList()) ;
            optSum += memory.getErrorsNumber();

            memory.restart() ;
            rand.run(memory, requestsGenerator.getRequestsList()) ;
            randSum += memory.getErrorsNumber();

            memory.restart() ;
            sca.run(memory, requestsGenerator.getRequestsList()) ;
            scaSum += memory.getErrorsNumber();
        }

        System.out.println("-----------------------------");
        System.out.println("Simulation results for :");
        System.out.println("Memory size : " + framesNumber + " frames") ;
        System.out.println("Number of requests : " + requestsNumber) ;
        System.out.println("Number of page types : " + pagesTypesNumber) ;
        System.out.println("Number of simulation loops : " + loopsNumber) ;
        System.out.println("\nAvarage number of errors for each algorithm : ");
        System.out.println("\nFIFO algorithm : " + fifoSum/loopsNumber) ;
        System.out.println("LRU algorithm : " + lruSum/loopsNumber) ;
        System.out.println("OPT algorithm : " + optSum/loopsNumber) ;
        System.out.println("RAND algorithm : " + randSum/loopsNumber) ;
        System.out.println("SCA algorithm : " + scaSum/loopsNumber) ;
        System.out.println("-----------------------------");

    }
}
