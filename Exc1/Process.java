public class Process{
    private int number ;
    private double arrivalTime ;
    private double burstTime ;
    private double admissionTime ;
    private double completeStatus ;
    
    public Process(int number, double arrivalTime, double burstTime){
        this.number = number ;
        this.arrivalTime = arrivalTime ;
        this.burstTime = burstTime ;
        completeStatus = 0 ;
    }

    
    public int getNumber(){
        return number ;
    }
    public double getArrivalTime(){
        return arrivalTime ;
    }
    public double getBurstTime(){
        return burstTime ;
    }
    public double getAdmissionTime(){
        return admissionTime ;
    }
    public double getCompleteStatus(){
        return completeStatus ;
    }

    
    public void setAdmissionTime(double time){
        admissionTime = time ;
    }
    public void setCompleteStatus(double time){
        completeStatus = time ;
    }

    @Override
    public String toString(){
        return ("P" + String.valueOf(number) + "  arrival:" + 
        String.valueOf(arrivalTime) + "  burst:" + String.valueOf(burstTime)) ;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false ;
        if(obj instanceof Process){
            Process p = (Process) obj ;
            if(number == p.number) return true ;
        }
        return false ;
    }

}