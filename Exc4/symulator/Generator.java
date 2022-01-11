package symulator;
import java.util.ArrayList;
import java.util.Random;
import computer.* ;
import computer.Process ;


public class Generator {
    private ArrayList<Process> processList ;
    private ArrayList<PageIdentifier> requestsList ;
    private Random r ;
    private int pagesNumber ;

    public Generator(){
        processList = new ArrayList<Process>() ;
        requestsList = new ArrayList<PageIdentifier>() ;
        r = new Random() ;
    }

    public void generate(int processNumber, int requestsNumber, int pagesNumber){
        int processStart = 0 ;
        int processEnd = 0 ;
        int maxDelta = pagesNumber*2/processNumber ;
        for(int i=0; i<processNumber; i++){
            Process process = new Process(i) ;
            processList.add(process) ;
            processEnd += (r.nextInt(maxDelta)+2) ;
            for(int j=processStart; j<=processEnd; j++){
                PageIdentifier page = new PageIdentifier(j) ;
                process.addPage(page);
            }
            pagesNumber = processEnd ;
            processStart = processEnd + 1 ;
            this.pagesNumber = pagesNumber ;
            
        }

        for(int i=0; i<requestsNumber; i++){
            requestsList.add(new PageIdentifier(r.nextInt(pagesNumber))) ;
        }
    }

    public ArrayList<Process> getProcessList(){
        ArrayList<Process> list = new ArrayList<Process>() ;
        for(Process process : processList){
            list.add(process.clone()) ;
        }
        return list ;
    }

    public ArrayList<PageIdentifier> getReqestList(){
        return requestsList ;
    }

    public int getPagesNumber(){
        return pagesNumber ;
    }


}
