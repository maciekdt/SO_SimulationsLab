package algorithms ;
import java.util.ArrayList;
import java.util.Random;
import computer.*;

public class RandAlgorithm {
    private Random rand = new Random() ;
    
    public void run(Memory memory, ArrayList<PageIdentifier> requestsList){
        for(PageIdentifier page : requestsList){
            if(memory.isPageInMemory(page)) continue ;
            else if(memory.isFreeFrame()){
                useFreeFrameForPage(memory, page) ;
            }
            else{
                useNotFreeFrameForPage(memory, page);
            }
        }
    }

    private void useFreeFrameForPage(Memory memory, PageIdentifier page) {
        Frame freeFrame = memory.getFreeFrame() ;
        memory.setPageForFrame(freeFrame, page) ;
    }

    private void useNotFreeFrameForPage(Memory memory, PageIdentifier page) {
        int randomFrameIndex = rand.nextInt(memory.getFramesList().size())  ;
        memory.getFramesList().get(randomFrameIndex).setPage(null) ;
        memory.setPageForFrame(memory.getFreeFrame(), page) ;
    }
}
