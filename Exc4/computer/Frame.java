package computer ;
public class Frame {
    private int number ;
    private PageIdentifier page ;

    public Frame(int number){
        this.number = number ;
        page = null ;
    }

    public int getNumber(){
        return number ;
    }
    public PageIdentifier getPage() {
        return page;
    }
    public void setPage(PageIdentifier page) {
        this.page = page;
    }

    public void restart(){
        page = null ;
    }
}
