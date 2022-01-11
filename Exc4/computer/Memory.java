package computer ;
import java.util.ArrayList;
import java.util.LinkedList;

public class Memory {
    private ArrayList<Process> processList ;
    private LinkedList<Frame> freeFramesList ;
    private int errorsCounter ;
    private int framesNumber ;


    public Memory(int framesNumber, ArrayList<Process> processList){
        errorsCounter = 0 ;
        this.framesNumber = framesNumber ;
        freeFramesList = new LinkedList<Frame>() ;
        for(int i=0; i<framesNumber; i++){
            freeFramesList.add(new Frame(i)) ;
        }
        this.processList = processList ;
    }

    public int getErrorsNumber(){
        return errorsCounter ;
    }

    public int getFramesNumber(){
        return framesNumber ;
    }

    public int getProcessNumber(){
        return processList.size() ;
    }

    public ArrayList<Process> getProcessList(){
        return processList ;
    }

    public Process getProcess(PageIdentifier page){
        for(Process p : processList){
            if(p.isPageForProcess(page)) return p ;
        }
        return null ;
    }

    public void addFreeFrameForProcess(Process process){
        process.addFrame(freeFramesList.remove()) ; 
    }
}
