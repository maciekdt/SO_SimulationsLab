package algorithms;
import java.util.ArrayList;
import java.util.List;
import simulator.RandomDataCreator;
import structure.CpuSystem;
import structure.Process;
import structure.Processor;


public class Strategy3 {
    private int requestsNumber ;
    private int migrationNumber ;
    private double time ;
    private double avarageLoadSum ;
    private ArrayList<ArrayList<Integer>> loadList ;

    public Strategy3(){
        requestsNumber = 0 ;
        migrationNumber = 0 ;
        time = 0 ;
        avarageLoadSum = 0 ;
        loadList = new ArrayList<ArrayList<Integer>>() ;
    }

    public void simulate(CpuSystem system, RandomDataCreator dataCreator, int border, int z, int min){
        List<Process> processList = dataCreator.getProcessList() ;
        for(Process process : processList){

            time++ ;
            avarageLoadSum += system.getAvarageProcessorsLoad() ;
            loadList.add(system.getLoadList()) ;
            system.update() ;

            if(process==null) continue ;

            if(process.getInitialProcessor().getCpuLoad() <= border){
                process.getInitialProcessor().handleProcess(process) ;
                if(process.getInitialProcessor().getCpuLoad() >= border) balancing(process.getInitialProcessor(), system, min) ;
                continue ;
            }
            boolean isProcessHandled = false ;
            for(int i=0; i<z; i++){
                Processor otherProcessor = system.getRandomProcessor() ;
                requestsNumber++ ;
                if(otherProcessor.getCpuLoad() <= border){
                    otherProcessor.handleProcess(process) ;
                    migrationNumber++ ;
                    isProcessHandled = true ;
                    break ;
                }
            }
            if(!isProcessHandled){
                process.getInitialProcessor().handleProcess(process) ;
            }
        }
    }

    public void printRaport(){
        System.out.print("\n\nStrategy 3");
        System.out.print("\nRequests : " + requestsNumber) ;
        System.out.print("\nMigration : " + migrationNumber) ;
        System.out.print("\nAvarage processor load : ") ;
        System.out.printf("%5.2f", avarageLoadSum/time);
        System.out.print("\nAvarage deviation : ") ;
        System.out.printf("%5.2f", getAvarageDeviation());
    }

    public double getAvarageDeviation(){
        double sum = 0;
        for(int i=0; i<loadList.size(); i++){
            for(int j=0; j<loadList.get(i).size(); j++){
                sum+=Math.abs(avarageLoadSum/time - loadList.get(i).get(j)) ;
            }
        }
        return sum/(loadList.size()*loadList.get(0).size()) ;
    }

    public void balancing(Processor processor, CpuSystem system, int min){
        for(int i=0; i<system.getProcessorsNumber()/3; i++){
            Processor p = system.getRandomProcessor() ;
            requestsNumber++ ;
            if(p.getCpuLoad() <= min){
                processor.transferProcesses(p) ;
                migrationNumber++ ;
                break ;
            }
        }
    }
}
