package algorithms;
import java.util.ArrayList;
import java.util.List;
import simulator.RandomDataCreator;
import structure.CpuSystem;
import structure.Process;
import structure.Processor;


public class Strategy2 {
    private int requestsNumber ;
    private int migrationNumber ;
    private double time ;
    private double avarageLoadSum ;
    private ArrayList<ArrayList<Integer>> loadList ;

    public Strategy2(){
        requestsNumber = 0 ;
        migrationNumber = 0 ;
        time = 0 ;
        avarageLoadSum = 0 ;
        loadList = new ArrayList<ArrayList<Integer>>() ;
    }

    public void simulate(CpuSystem system, RandomDataCreator dataCreator, int border, int z){
        List<Process> processList = dataCreator.getProcessList() ;
        for(Process process : processList){

            time++ ;
            avarageLoadSum += system.getAvarageProcessorsLoad() ;
            system.update() ;
            loadList.add(system.getLoadList()) ;

            if(process==null) continue ;

            if(process.getInitialProcessor().getCpuLoad() <= border){
                process.getInitialProcessor().handleProcess(process) ;
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
        System.out.print("\n\nStrategy 2");
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
}