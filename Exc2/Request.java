public class Request{
    private int block ;
    private int deadline ;
    private int id ;

    public Request(int block, int deadline, int id){
        this.block = block ;
        this.deadline = deadline ;
        this.id = id ;
    }

    public int getBlock(){
        return block ;
    }
    public int getDeadline(){
        return deadline ;
    }

   @Override
    public boolean equals(Object obj){
        if(obj == null) return false ;
        if(obj instanceof Request){
            Request r = (Request) obj ;
            if(r.id == id) return true ;
        }
        return false ;
    }

}