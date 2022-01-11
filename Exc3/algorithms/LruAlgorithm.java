package algorithms;

import java.util.ArrayList;
import computer.*;

public class LruAlgorithm {

    public void run(Memory memory, ArrayList<PageIdentifier> requestsList){
        int index = 0 ;
        for(PageIdentifier page : requestsList){
            if(memory.isPageInMemory(page)) continue ;
            else if(memory.isFreeFrame()){
                useFreeFrameForPage(memory, page);
            }
            else{
                useNotFreeFrameForPage(memory, page, index, requestsList) ;
            }
            index++ ;
        }
    }


    private void useFreeFrameForPage(Memory memory, PageIdentifier page) {
        Frame freeFrame = memory.getFreeFrame() ;
        memory.setPageForFrame(freeFrame, page) ;
    }

    private void useNotFreeFrameForPage(Memory memory, PageIdentifier page, int index, ArrayList<PageIdentifier> requestsList){
        findMostNotUsedFrame(memory, index, requestsList).setPage(null) ;
        Frame freeFrame = memory.getFreeFrame() ;
        memory.setPageForFrame(freeFrame, page) ;
    }

    private Frame findMostNotUsedFrame(Memory memory, int index, ArrayList<PageIdentifier> requestsList){
        Frame mostNotUsedFrame = memory.getFramesList().get(0) ;
        int longestTime = 0 ;
        for(Frame frame : memory.getFramesList()){
            int time = 0 ;
            for(int i=index-1; i>=0; i--, time++){
                if(requestsList.get(i).equals(frame.getPage())) break ;
            }
            if(time>=longestTime){
                mostNotUsedFrame = frame ;
                longestTime = time ;
            }
        }
        return mostNotUsedFrame ;
    }
}
