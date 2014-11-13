/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tideminer2014;

import java.time.*;

/**
 *
 * @author Chris
 */
public class TideInterval {
    private double beginHeight;
    private double endHeight;
    private double heightDelta;
    private double heightDeltaMagnitude;
    
    private Instant beginTime;
    private Instant endTime;
    private Duration duration;
    
    private boolean isValid;
    
    public TideInterval(double beginHeight, double endHeight, Instant beginTime, Instant endTime) {
        this.beginHeight = beginHeight;
        this.endHeight = endHeight;
        this.heightDelta = endHeight - beginHeight;
        this.heightDeltaMagnitude = Math.abs(this.heightDelta);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.duration =  Duration.between(beginTime, endTime).abs();
        this.isValid = false;
    }

    public static TideInterval buildFromNoaaLines(String noaaFileLine1, String noaaFileLine2) {
        // validate the lines here? Yes, other places don't know the reqs... or don't bother validating since that gets really messy & this is a quick and dirty program
        // get the relevant data from the noaa line & call the above constructor with it
        try {
            String[] line1Data = noaaFileLine1.split(",");
            String[] line2Data = noaaFileLine2.split(",");
            Instant time1 = Instant.parse(line1Data[0].replace(' ', 'T')+":00.00Z");
            Instant time2 = Instant.parse(line2Data[0].replace(' ', 'T')+":00.00Z");
            double level1 = Double.parseDouble(line1Data[1]);
            double level2 = Double.parseDouble(line2Data[1]);
            TideInterval ti = new TideInterval(level1,level2,time1,time2);
            ti.setValid(true);
            return ti;
        }
        catch (Exception exc) {
            System.out.println(exc.toString());
//            System.exit(1);
//            exc.printStackTrace();
            return new TideInterval(0,0,Instant.now(),Instant.now());
        }
    }
   
    /**
     * @return the beginHeight
     */
    public double getBeginHeight() {
        return beginHeight;
    }

    /**
     * @param beginHeight the beginHeight to set
     */
    public void setBeginHeight(double beginHeight) {
        this.beginHeight = beginHeight;
    }

    /**
     * @return the endHeight
     */
    public double getEndHeight() {
        return endHeight;
    }

    /**
     * @param endHeight the endHeight to set
     */
    public void setEndHeight(double endHeight) {
        this.endHeight = endHeight;
    }

    /**
     * @return the heightDelta
     */
    public double getHeightDelta() {
        return heightDelta;
    }

    /**
     * @param heightDelta the heightDelta to set
     */
    public void setHeightDelta(double heightDelta) {
        this.heightDelta = heightDelta;
    }

    /**
     * @return the heightDeltaMagnitude
     */
    public double getHeightDeltaMagnitude() {
        return heightDeltaMagnitude;
    }

    /**
     * @param heightDeltaMagnitude the heightDeltaMagnitude to set
     */
    public void setHeightDeltaMagnitude(double heightDeltaMagnitude) {
        this.heightDeltaMagnitude = heightDeltaMagnitude;
    }

    /**
     * @return the beginTime
     */
    public Instant getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime the beginTime to set
     */
    public void setBeginTime(Instant beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the endTime
     */
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * @return the isValid
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
