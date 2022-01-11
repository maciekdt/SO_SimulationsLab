package computer ;
import java.util.ArrayList;

public class Memory {
    private ArrayList<Frame> framesList ;
    private int errorsCounter ;


    public Memory(int framesNumber){
        errorsCounter = 0 ;
        framesList = new ArrayList<Frame>() ;
        for(int i=0; i<framesNumber; i++){
            framesList.add(new Frame(i)) ;
        }
    }

    public boolean isPageInMemory(PageIdentifier page){
        for(Frame frame : framesList){
            if(page.equals(frame.getPage())) return true ;
        }
        return false ;
    }

    public boolean isFreeFrame(){
        for(Frame frame : framesList){
            if(frame.getPage() == null) return true ;
        }
        return false ;
    }

    public Frame getFreeFrame(){
        for(Frame frame : framesList){
            if(frame.getPage() == null) return frame ;
        }
        return null ;
    }

    public void setPageForFrame(Frame frame, PageIdentifier page){
        frame.setPage(page) ; 
        errorsCounter ++ ;
    }

    public ArrayList<Frame> getFramesList(){
        return framesList ;
    }

    public int getErrorsNumber(){
        return errorsCounter ;
    }

    public void restart(){
        errorsCounter = 0 ;
        for(Frame frame : framesList){
            frame.restart() ;
        }
    }

    public void setRecallByteForFrame(PageIdentifier page, boolean newValue){
        for(Frame frame : framesList){
            if(page.equals(frame.getPage())){
                frame.setRecallByte(newValue) ;
                break ;
            }
        }
    }
}
