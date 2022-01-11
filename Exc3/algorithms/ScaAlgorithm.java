package algorithms ;
import computer.* ;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ScaAlgorithm {
    
    public void run(Memory memory, ArrayList<PageIdentifier> requestsList){
        Queue<Frame> framesQueue = new LinkedList<Frame>() ;
        for(PageIdentifier page : requestsList){
            if(memory.isPageInMemory(page)){
                memory.setRecallByteForFrame(page, true) ;
                continue ;
            }
            else if(memory.isFreeFrame()){
                useFreeFrameForPage(memory, framesQueue, page);
            }
            else{
                useNotFreeFrameForPage(memory, framesQueue, page);
            }
        }
    }

    
    private void useFreeFrameForPage(Memory memory, Queue<Frame> framesQueue, PageIdentifier page) {
        Frame freeFrame = memory.getFreeFrame() ;
        framesQueue.add(freeFrame) ;
        memory.setPageForFrame(freeFrame, page) ;
        memory.setRecallByteForFrame(page, true) ;
    }

    private void useNotFreeFrameForPage(Memory memory, Queue<Frame> framesQueue, PageIdentifier page) {
        while(framesQueue.peek().getRecallByte()){
            framesQueue.peek().setRecallByte(false) ;
            framesQueue.add(framesQueue.remove()) ;
        }

        framesQueue.remove().setPage(null) ;
        Frame freeFrame = memory.getFreeFrame() ;
        framesQueue.add(freeFrame) ;
        memory.setPageForFrame(freeFrame, page) ;
        memory.setRecallByteForFrame(page, true) ;
    }
}