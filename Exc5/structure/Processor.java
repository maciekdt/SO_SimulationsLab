package structure;
import java.util.ArrayList;
import java.util.Iterator;

public class Processor {

    private int cpuLoad ;
    private ArrayList<Process> processList ;

    public Processor(){
        cpuLoad = 0 ;
        processList = new ArrayList<Process>() ;
    }

    public void handleProcess(Process process){
        cpuLoad += process.getRequiredPower() ;
        processList.add(process) ;
    }

    public void update(){
        Iterator<Process> iterator = processList.iterator() ;
        while(iterator.hasNext()){
            Process process = iterator.next() ;
            process.setTimeToEnd(process.getTimeToEnd()-1);
            if(process.getTimeToEnd() <= 0){
                iterator.remove() ; 
                cpuLoad -= process.getRequiredPower() ;
            }
        }
    }

    public int getCpuLoad(){
        return cpuLoad ;
    }

    public void restart(){
        cpuLoad = 0 ;
        processList = new ArrayList<Process>() ;
    }

    public void transferProcesses(Processor processor){
        int size = processList.size()/2 ;
        for(int i=0; i<size; i++){
            Process p = processList.remove(0) ;
            cpuLoad -= p.getRequiredPower() ;
            processor.handleProcess(p);
        }
    }
}
