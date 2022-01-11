package algorithms ;
import java.util.ArrayList;
import computer.Process ;
import computer.* ;

public class EqualAllocation {
    
    public int run(ArrayList<PageIdentifier> requestsList, Memory memory){
        allocation(memory);

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
                process.findMostNotUsedFrame(pageIndex, requestsList).setPage(page) ;
                errorsCounter++ ; 
            }
            pageIndex++ ;
        }
        return errorsCounter ;
    }

    private void allocation(Memory memory) {
        int framesForProcess = memory.getFramesNumber()/memory.getProcessNumber() ;
        for(Process p : memory.getProcessList()){
            for(int i=0; i<framesForProcess; i++){
                memory.addFreeFrameForProcess(p) ;
            }
        }
    }
}
