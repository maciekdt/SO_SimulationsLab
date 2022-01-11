public class Test {
    public static void main(String[] args) {
        ProcessesDataBase base = new ProcessesDataBase() ;
        base.generateNewList(100) ;
        Algorithms a = new Algorithms() ;

        double FCFS_sum = 0.0 ;
        double SJF_sum = 0.0 ;
        double RR_sum = 0.0 ;

        int numberOfSeries = 1 ;
        for(int i=0; i<numberOfSeries; i++){
            base.generateNewList(100) ;
            FCFS_sum += a.avarageWaitingTime(a.FCFS(base.getProcessesList())) ;
            SJF_sum += a.avarageWaitingTime(a.SJF(base.getProcessesList())) ;
            RR_sum += a.avarageWaitingTime(a.RR(base.getProcessesList(), 10.0)) ;
        }

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("FCFS algorithm avarage waiting time : " + FCFS_sum/numberOfSeries) ;
        System.out.println("SJF algorithm avarage waiting time : " + SJF_sum/numberOfSeries) ;
        System.out.println("RR algorithm avarage waiting time : " + RR_sum/numberOfSeries) ;
        System.out.println("---------------------------------------------------------------------------");


    }
    
}
