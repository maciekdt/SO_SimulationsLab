public class Disc {
    private int startPosition ;
    private int position ;
    private int time ;
    private int size ;


    public Disc(int size){
        this.size = size ;
        position = (int) (Math.random() * size) ;
        startPosition = position ;
        time = 0 ;
    }

    public int getTime(){
        return time ;
    }

    public int getSize(){
        return size ;
    }

    public int getPosition(){
        return position ;
    }

    public void changePosition(int nextPosition){
        time += Math.abs(position - nextPosition) ;
        position = nextPosition ;
    }

    public int distance(int nextPosition){
        return Math.abs(position - nextPosition) ;
    }

    public void reset(){
        time = 0 ;
        position = startPosition ;
    }

    public void restart(){
        position = (int) (Math.random() * size) ;
        startPosition = position ;
        time = 0 ;
    }

}
