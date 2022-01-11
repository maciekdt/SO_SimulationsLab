package simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import structure.Process;
import structure.CpuSystem;

public class RandomDataCreator {
    private List<Process> processList ;

    public void createRandomProcessList(int time, int maxProcessDuration, int maxPower, int frequency, CpuSystem system){
        processList = new ArrayList<Process>() ;
        for(int i=0; i<time; i++){
            int processDuration = new Random().nextInt(maxProcessDuration) + 1 ;
            int processRequiredPower = new Random().nextInt(maxPower) + 1 ;
            int random = new Random().nextInt() ;
            if(random%frequency == 0){
                processList.add(null) ;
            }
            else {
                processList.add(new Process(processRequiredPower, processDuration, system.getRandomProcessor())) ;
            }
        }
    }

    public List<Process> getProcessList(){
        List<Process> processListCopy = new ArrayList<Process>() ;
        for(Process p : processList){
            if(p != null) p.restart() ;
            processListCopy.add(p) ;
        }
        return processListCopy ;
    }
}
