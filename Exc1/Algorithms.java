import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class Algorithms {
    

    //FCFS
    public ArrayList<Process> FCFS(ArrayList<Process> list){

        ArrayList<Process> pList = new ArrayList<Process>(list) ;
        double time = 0 ;

        pList.get(0).setAdmissionTime(pList.get(0).getArrivalTime()) ;
        time += pList.get(0).getArrivalTime() + pList.get(0).getBurstTime() ;

        for(int i=1; i<pList.size(); i++){
            Process p = pList.get(i) ;
            if(time >= p.getArrivalTime()){
                p.setAdmissionTime(time) ;
            }
            else{
                time = p.getArrivalTime() ;
                p.setAdmissionTime(time) ;
            }
            time += p.getBurstTime() ;
        }

        return pList ;
    }


    //SJF
    public ArrayList<Process> SJF(ArrayList<Process> list){

        ArrayList<Process> pList = new ArrayList<Process>(list) ;
        Set<Process> pSet = new HashSet<Process>(pList) ;
        double time = 0 ;

        pList.get(0).setAdmissionTime(pList.get(0).getArrivalTime()) ;
        time += pList.get(0).getArrivalTime() + pList.get(0).getBurstTime() ;
        pSet.remove(pList.get(0)) ;


        while(pSet.size() > 0){
            Process nextProcess = findProcessSJF(pSet, pList, time) ;
            pSet.remove(nextProcess) ;
            nextProcess.setAdmissionTime(time) ;
            time += nextProcess.getBurstTime() ;
        }

        return pList ;
    }

    private Process findProcessSJF(Set<Process> pSet, ArrayList<Process> pList, double time){
        ArrayList<Process> waitingProcesses = new ArrayList<Process>() ;
        for(Process p : pList){
            if(pSet.contains(p) && p.getArrivalTime()<=time) waitingProcesses.add(p) ;
        }
        if(waitingProcesses.size() == 0){
            for(Process p : pList){
                if(p.getArrivalTime()>time) return p ;
            }
        }
        Process pMin = waitingProcesses.get(0) ;
        for(Process p : waitingProcesses){
            if(p.getBurstTime() < pMin.getBurstTime()) pMin = p ;
        }
        return pMin ;
    }




    //SRTF
    public ArrayList<Process> SRTF(ArrayList<Process> list){

        ArrayList<Process> pList = new ArrayList<Process>(list) ;
        Set<Process> pSet = new HashSet<Process>(pList) ;
        Stack<Process> pStack = new Stack<Process>() ;
        double time = 0 ;

        pList.get(0).setAdmissionTime(pList.get(0).getArrivalTime()) ;
        pSet.remove(pList.get(0)) ;
        pStack.push(pList.get(0)) ;
        time = pList.get(0).getArrivalTime() ;

        
        while(pSet.size() > 0){
            Process currentProcess = pStack.peek() ;
            Process nextProcess = findNextProcessSRTF(pSet, pList, time, time + currentProcess.getBurstTime() - currentProcess.getCompleteStatus()) ;
            if(nextProcess == null){
                nextProcess = findNextProcess(pSet, pList, currentProcess) ;
                pStack.pop() ;
                pSet.remove(currentProcess) ;
                time = time + currentProcess.getBurstTime() - currentProcess.getCompleteStatus() ;
                pStack.push(nextProcess) ;
                nextProcess.setAdmissionTime(time) ;
            }
            else{
                time = nextProcess.getArrivalTime() ;
                nextProcess.setAdmissionTime(time) ;
                pStack.push(nextProcess) ;
            }
        }
        return pList ;
    }

    private Process findNextProcessSRTF(Set<Process> pSet, ArrayList<Process> pList, double start, double end){
        for(Process p : pList){
            if(pSet.contains(p)){
                if(p.getArrivalTime() > start && (p.getArrivalTime() + p.getBurstTime()) < end){
                    return p ;
                }
            }     
        }
        return null ;
    }

    private Process findNextProcess(Set<Process> pSet, ArrayList<Process> pList, Process lastProcess){
        for(Process p : pList){
            if(pSet.contains(p) && p.getArrivalTime() > lastProcess.getArrivalTime()){
                return p ;
            }
        }
        return null ;
    }



    //RR
    public ArrayList<Process> RR(ArrayList<Process> list, double period){

        ArrayList<Process> pList = new ArrayList<Process>(list) ;
        ArrayList<Process> pQueue = new ArrayList<Process>() ;

        double time = pList.get(0).getArrivalTime() ;
        pQueue.add(pList.get(0)) ;

        int i = 1 ;
        while(i < pList.size() ){
            while(time < pList.get(i).getArrivalTime()){
                for(int j=0; j<pQueue.size(); j++){
                    Process p = pQueue.get(j) ;
                    double timeToComplete = p.getBurstTime() - p.getCompleteStatus() ;
                    if(timeToComplete < period){
                        p.setAdmissionTime(time) ;
                        time += timeToComplete ;
                        pQueue.remove(p) ;
                    }
                    else{
                        p.setCompleteStatus(p.getCompleteStatus() + period) ;
                        time += period ;
                    }
                }
                time += period ;
            }
            pQueue.add(pList.get(i)) ;
            i++ ;
        }

        while(pQueue.size() > 0){
            for(int j=0; j<pQueue.size(); j++){
                Process p = pQueue.get(j) ;
                double timeToComplete = p.getBurstTime() - p.getCompleteStatus() ;
                if(timeToComplete < period){
                    p.setAdmissionTime(time) ;
                    time += timeToComplete ;
                    pQueue.remove(p) ;
                }
                else{
                    p.setCompleteStatus(p.getCompleteStatus() + period) ;
                    time += period ;
                }
            }
        }

        return pList ;
    }





    public double avarageWaitingTime(ArrayList<Process> prcsList){
        double sum = 0.0 ;
        for(Process p : prcsList){
            sum += (p.getAdmissionTime() - p.getArrivalTime()) ;
        }
        return sum/Double.valueOf(prcsList.size()) ;
    }

}
