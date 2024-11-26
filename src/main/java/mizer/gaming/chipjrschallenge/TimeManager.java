package mizer.gaming.chipjrschallenge;

import javafx.scene.control.Label;

public class TimeManager {
    
    private long startTime;
    private long frameStamp;
    private long currentTime;
    private long secondsLeft = 200;

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
        frameStamp = currentTime - this.currentTime;  //new time - old time
        this.currentTime = currentTime;  //set new time to .this
    }
    
    public long getCurrentTime() {
        return System.nanoTime();
    }
    
}
