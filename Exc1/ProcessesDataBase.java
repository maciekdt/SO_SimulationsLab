import java.util.ArrayList;
import java.util.Random;

public class ProcessesDataBase {
    private ArrayList<Process> processesList ;
    private Random rand = new Random();

    public void generateNewList(int size){
        processesList = new ArrayList<Process>() ;
        double burstTime = 10*rand.nextDouble() ;
        double arrivalTime = 10*rand.nextDouble() ;
        processesList.add(new Process(1, arrivalTime, burstTime)) ;

        for(int i=1 ; i<size; i++){
            burstTime = 10*rand.nextDouble() ;
            arrivalTime += 10*rand.nextDouble() ;
            processesList.add(new Process(i+1, arrivalTime, burstTime)) ;
        }
    }

    public void printList(){
        for(Process p : processesList){
            System.out.println(p) ;
        }
    }
    public void restart(){
        for(Process p : processesList){
            p.setCompleteStatus(0.0) ;
        }
    }

    public ArrayList<Process> getProcessesList(){
        return processesList ;
    }
}
