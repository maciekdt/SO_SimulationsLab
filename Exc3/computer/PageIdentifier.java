package computer ;
public class PageIdentifier{
    private int id ;

    public PageIdentifier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null) return false ;
        if(obj instanceof PageIdentifier){
            PageIdentifier f = (PageIdentifier) obj ;
            if(f.id == id) return true ; 
        }
        return false ;
    }
}