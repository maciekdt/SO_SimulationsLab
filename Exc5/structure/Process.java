package structure ;

public class Process{
    private int requiredPower ;
    private int duration ;
    private int timeToEnd ;
    private Processor initialProcessor ;

    public Process(int requiredPower, int duration, Processor initialProcessor){
        this.setRequiredPower(requiredPower) ;
        this.setDuration(duration) ;
        this.setInitialProcessor(initialProcessor) ;
        setTimeToEnd(duration) ;
    }

    public Processor getInitialProcessor() {
        return initialProcessor ;
    }

    public void setInitialProcessor(Processor initialProcessor) {
        this.initialProcessor = initialProcessor ;
    }

    public int getTimeToEnd() {
        return timeToEnd;
    }

    public void setTimeToEnd(int timeToEnd) {
        this.timeToEnd = timeToEnd;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRequiredPower() {
        return requiredPower;
    }

    public void setRequiredPower(int requiredPower) {
        this.requiredPower = requiredPower;
    }

    public void restart(){
        setTimeToEnd(duration) ;
    }
}