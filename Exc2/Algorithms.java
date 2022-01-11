import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorithms{


    //FCFS
    public int FCFS(ArrayList<Request> rqList, Disc disc){
        for(Request r : rqList){
            disc.changePosition(r.getBlock()) ;
        }
        return disc.getTime() ;
    }




    //SSTF
    public int SSTF(ArrayList<Request> rqList, Disc disc){
        ArrayList<Request> rqLeftList = new ArrayList<Request>(rqList) ;

        while(!rqLeftList.isEmpty()){
            Request closestRequest = findClosest(rqLeftList, disc) ;
            rqLeftList.remove(closestRequest) ;
            disc.changePosition(closestRequest.getBlock());
        }
        return disc.getTime() ;
    }


    public Request findClosest(ArrayList<Request> rqLeftList, Disc disc){
        Request closestRequest = rqLeftList.get(0) ;
        for(Request r : rqLeftList){
            if(disc.distance(r.getBlock()) < disc.distance(closestRequest.getBlock())){
                closestRequest = r ;
            }
        }
        return closestRequest ;
    }




    //SCAN
    public int SCAN(ArrayList<Request> rqList, Disc disc){
        ArrayList<Request> rqLeftList = new ArrayList<Request>(rqList) ;
        for(int i=disc.getPosition(); i>=0; i--){
            disc.changePosition(i) ;
            deleteRequestByBlock(rqLeftList, i) ;
            if(rqLeftList.isEmpty()) return disc.getTime() ;
        }
        for(int i=0; i<=disc.getSize(); i++){
            disc.changePosition(i) ;
            deleteRequestByBlock(rqLeftList, i) ;
            if(rqLeftList.isEmpty()) return disc.getTime() ;
        }
        return disc.getTime() ;
    }


    

    
    //C-SCAN
    public int CSCAN(ArrayList<Request> rqList, Disc disc){
        ArrayList<Request> rqLeftList = new ArrayList<Request>(rqList) ;
        for(int i=disc.getPosition(); i>=0; i--){
            disc.changePosition(i) ;
            deleteRequestByBlock(rqLeftList, i) ;
            if(rqLeftList.isEmpty()) return disc.getTime() ;
            if(i==0) i=disc.getSize();
        }
        return disc.getTime() ;
    }



    //EDF
    public int EDF(ArrayList<Request> rqList, Disc disc){
        ArrayList<Request> rqCopyList = new ArrayList<Request>(rqList) ;
        Collections.sort(rqCopyList, new ComparatorDeadline()) ;

        for(Request r : rqCopyList){
            disc.changePosition(r.getBlock()) ;
        }
        return disc.getTime() ;
    }

    



    //FD-SCAN
    public int FDSCAN(ArrayList<Request> rqList, Disc disc){
        ArrayList<Request> rqCopyList = new ArrayList<Request>(rqList) ;
        while(!rqCopyList.isEmpty()){
            Request nextRequest = findNext(rqCopyList) ;

            if(nextRequest.getBlock() > disc.getPosition()){
                for(int i=disc.getPosition(); i<=nextRequest.getBlock(); i++){
                    disc.changePosition(i) ;
                    deleteRequestByBlock(rqCopyList, i) ;
                }
            }
            if(nextRequest.getBlock() < disc.getPosition()){
                for(int i=disc.getPosition(); i>=nextRequest.getBlock(); i--){
                    disc.changePosition(i) ;
                    deleteRequestByBlock(rqCopyList, i) ;
                }
            }

            if(nextRequest.getBlock() == disc.getPosition()) deleteRequestByBlock(rqCopyList, disc.getPosition()) ;
            
        }

        return disc.getTime() ;
        
    }




    //Other methods and classes
    public Request findNext(ArrayList<Request> rqList){
        Request rMax = rqList.get(0) ;
        for(Request r : rqList){
            if(r.getDeadline() > rMax.getDeadline()) rMax = r ;
        }
        return rMax ;
    }



    public void deleteRequestByBlock(ArrayList<Request> rqLeftList, int block){
        ArrayList<Request> tempList = new ArrayList<Request>() ;
        for(Request r : rqLeftList){
            if(r.getBlock() == block) tempList.add(r) ;
        }
        for(Request r : tempList){
            rqLeftList.remove(r) ;
        }
    }

    private class ComparatorDeadline implements Comparator<Request>{
        @Override
        public int compare(Request r1, Request r2) {
            if(r1.getDeadline() > r2.getDeadline()) return 1 ;
            if(r1.getDeadline() < r2.getDeadline()) return -1 ;
            return 0 ;
        }
    }
}