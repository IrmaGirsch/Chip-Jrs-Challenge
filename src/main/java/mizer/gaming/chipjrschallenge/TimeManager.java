package mizer.gaming.chipjrschallenge;

public class TimeManager {
    
    private long startTime;
    private long frameStamp;
    private long currentTime;

    public TimeManager() {
        startTime = System.nanoTime();
        frameStamp = 0;
        currentTime = startTime;
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public double getStampSec() {
        return frameStamp / 1000000000.0;
    }
    
    public void stamp(long currentTime) {
        frameStamp = currentTime - this.currentTime;
        this.currentTime = currentTime;
    }
    
}
