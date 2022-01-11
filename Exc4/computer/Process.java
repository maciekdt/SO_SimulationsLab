package computer;
import java.util.ArrayList;
import java.util.List;


public class Process {
    private int id ;
    private List<PageIdentifier> pagesSet ;
    private List<Frame> framesList ;

    public Process(int id){
        this.id = id ;
        pagesSet = new ArrayList<PageIdentifier>() ;
        framesList = new ArrayList<Frame>() ;
    }

    public int getId(){
        return id ;
    }

    public Process clone(){
        Process p = new Process(this.id) ;
        for(PageIdentifier page : pagesSet){
            p.addPage(new PageIdentifier(page.getId())) ;
        }
        return p ;
    }

    public void restart(){
        for(Frame f : framesList){
            f.setPage(null) ;
        }
    }

    public void addPage(PageIdentifier page){
        pagesSet.add(page) ;
    }

    public void addFrame(Frame frame){
        framesList.add(frame) ;
    }

    public void setPageForFrame(Frame frame, PageIdentifier page){
        frame.setPage(page) ; 
    }
    
    public int getNumberOfPages(){
        return pagesSet.size() ;
    }

    public boolean isFreeFrame(){
        for(Frame frame : framesList){
            if(frame.getPage() == null) return true ;
        }
        return false ;
    }


    public boolean isPageInMemory(PageIdentifier page){
        for(Frame frame : framesList){
            if(page.equals(frame.getPage())) return true ;
        }
        return false ;
    }

    public boolean isPageForProcess(PageIdentifier page){
        return pagesSet.contains(page) ;
    }

    public Frame getFreeFrame(){
        for(Frame frame : framesList){
            if(frame.getPage() == null) return frame ;
        }
        return null ;
    }

    public Frame removeFrame(int currentRequestIndex, ArrayList<PageIdentifier> requestsList){
        Frame frameToRemove = findMostNotUsedFrame(currentRequestIndex, requestsList) ;
        frameToRemove.restart() ;
        framesList.remove(frameToRemove) ;
        return frameToRemove ;
    }

    //LRU algorithm
    public Frame findMostNotUsedFrame(int currentRequestIndex, ArrayList<PageIdentifier> requestsList){
        Frame mostNotUsedFrame = null ;
        int longestTime = 0 ;
        for(Frame frame : framesList){
            int time = 0 ;
            for(int i=currentRequestIndex-1; i>=0; i--, time++){
                if(requestsList.get(i).equals(frame.getPage())) break ;
            }
            if(time>=longestTime){
                mostNotUsedFrame = frame ;
                longestTime = time ;
            }
        }
        return mostNotUsedFrame ;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null) return false ;
        if(obj instanceof Process){
            Process f = (Process) obj ;
            if(f.id == id) return true ; 
        }
        return false ;
    }
}
