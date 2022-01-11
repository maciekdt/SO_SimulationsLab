package structure;
import java.util.ArrayList;
import java.util.Random;

public class CpuSystem {
    private ArrayList<Processor> processorList ;

    public CpuSystem(int processorNumber){
        processorList = new ArrayList<Processor>() ;
        for(int i=0; i<processorNumber; i++){
            processorList.add(new Processor()) ;
        }
    }

    public void addProcessor(Processor processor){
        processorList.add(processor) ;
    }


    public Processor getRandomProcessor(){
        int index = new Random().nextInt(processorList.size()) ;
        return processorList.get(index) ;
    }

    public double getAvarageProcessorsLoad(){
        double sum = 0 ;
        for(Processor p : processorList){
            sum+=p.getCpuLoad() ;
        }
        return sum/processorList.size() ;
    }

    public int getProcessorsNumber(){
        return processorList.size() ;
    }

    public void update(){
        for(Processor p : processorList){
            p.update();
        }
    }

    public void restart(){
        for(Processor p : processorList){
            p.restart();
        }
    }

    public ArrayList<Integer> getLoadList(){
        ArrayList<Integer> list = new ArrayList<Integer>() ;
        for(Processor p : processorList){
            list.add(p.getCpuLoad()) ;
        }
        return list ;
    }

}
