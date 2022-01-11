package computer ;
public class Frame {
    private int number ;
    private PageIdentifier page ;
    private boolean recallByte ;

    public Frame(int number){
        this.number = number ;
        page = null ;
        recallByte = true ;
    }

    public int getNumber(){
        return number ;
    }
    public PageIdentifier getPage() {
        return page;
    }
    public boolean getRecallByte() {
        return recallByte;
    }
    public void setRecallByte(boolean recallByte) {
        this.recallByte = recallByte;
    }
    public void setPage(PageIdentifier page) {
        this.page = page;
    }

    public void restart(){
        page = null ;
        recallByte = true ;
    }
}
