package algorithms ;
import java.util.ArrayList;
import computer.Process ;
import computer.* ;

public class ProportionalAllocation {
    
    public int run(ArrayList<PageIdentifier> requestsList, Memory memory, int pagesNumber){
        allocation(memory, pagesNumber);

        int errorsCounter = 0 ;
        int pageIndex = 0 ;
        for(PageIdentifier page : requestsList){
            Process process = memory.getProcess(page) ;
            if(process==null) continue ;
            if(process.isPageInMemory(page)) ;
            else if(process.isFreeFrame()){
                process.getFreeFrame().setPage(page);
                errorsCounter++ ;
            }
            else{
                //process.findMostNotUsedFrame(pageIndex, requestsList).setPage(page) ;
                Frame f = process.findMostNotUsedFrame(pageIndex, requestsList) ;
                if(f == null) continue ;
                f.setPage(page) ;
                errorsCounter++ ; 
            }
            pageIndex++ ;
        }
        return errorsCounter ;
    }

    private void allocation(Memory memory, int pagesNumber) {
        for(Process p : memory.getProcessList()){
            double h = memory.getFramesNumber() ;
            double y = Double.valueOf(pagesNumber) ;
            double u = Double.valueOf(p.getNumberOfPages()) ;
            int framesForProcess = (int) (h * u/y) ;
            for(int i=0; i<framesForProcess; i++){
                memory.addFreeFrameForProcess(p) ;
            }
        }
    }
}
