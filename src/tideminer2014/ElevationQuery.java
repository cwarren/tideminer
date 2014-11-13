/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tideminer2014;

import java.time.Duration;
/**
 * This class handles a single elevation about which a flooding query is being made
 *
 * @author Chris
 */
public class ElevationQuery {
    private double height;
    private int floodCount;
    private Duration floodDuration;
    private boolean isFlooded;
    private int numIntervals;

    public ElevationQuery(double height) {
        this.height = height;
        this.floodCount = 0;
        this.floodDuration = Duration.ofSeconds(0);
        this.isFlooded = false;
        this.numIntervals = 0;
    }

    public void processInterval(TideInterval ti) {
//        System.out.println("TODO: elevation query . process interval");
        // branch points: current flooded or not, elev is between beginning and end interval heights, elev below lowest height, elev above highest height
        this.numIntervals++;
        if (this.isFlooded) {
            if ((ti.getBeginHeight() >= this.height) && (ti.getEndHeight() > this.height)) {
                this.floodDuration = this.floodDuration.plus(ti.getDuration().abs());
            } else if ((ti.getBeginHeight() < this.height) && (ti.getEndHeight() <= this.height)) {
                this.isFlooded = false;
            } else { // our height falls somewhere in between; in theory beginHeigh will be higher, but don't want to rely on that

                // NOTE: code duped in section below!
                double intervalSpan = Math.abs(ti.getBeginHeight() - ti.getEndHeight());
                double interpSpan = 0;
                if (ti.getBeginHeight() >= this.height) {
                    interpSpan = ti.getBeginHeight() - this.height;
                } else {
                    interpSpan = ti.getEndHeight() - this.height;
                }
                if (interpSpan > intervalSpan) { interpSpan = intervalSpan; }
                double interpFrac = interpSpan / intervalSpan;
                double interpSeconds = (double)(ti.getDuration().getSeconds()) * interpFrac;
                this.isFlooded = false;
                this.floodDuration = this.floodDuration.plusSeconds((long)interpSeconds);
            }
        } else { // not flooded at the moment
            if (! ((ti.getBeginHeight() < this.height) && (ti.getEndHeight() <= this.height))) {
                this.isFlooded = true;
                this.floodCount++;
                
                // NOTE: code duped in section above!
                double intervalSpan = Math.abs(ti.getBeginHeight() - ti.getEndHeight());
                double interpSpan = 0;
                if (ti.getBeginHeight() >= this.height) {
                    interpSpan = ti.getBeginHeight() - this.height;
                } else {
                    interpSpan = ti.getEndHeight() - this.height;
                }
                if (interpSpan > intervalSpan) { interpSpan = intervalSpan; }
                double interpFrac = interpSpan / intervalSpan;
                double interpSeconds = (double)(ti.getDuration().getSeconds()) * interpFrac;
                this.floodDuration = this.floodDuration.plusSeconds((long)interpSeconds);
            }
        }
    }
    
    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the floodCount
     */
    public int getFloodCount() {
        return floodCount;
    }

    /**
     * @param floodCount the floodCount to set
     */
    public void setFloodCount(int floodCount) {
        this.floodCount = floodCount;
    }

    /**
     * @return the floodDurationSeconds
     */
    public Duration getFloodDuration() {
        return floodDuration;
    }

    /**
     * @param floodDuration
     */
    public void setFloodDuration(Duration floodDuration) {
        this.floodDuration = floodDuration;
    }

    /**
     * @return the isFlooded
     */
    public boolean isIsFlooded() {
        return isFlooded;
    }

    /**
     * @param isFlooded the isFlooded to set
     */
    public void setIsFlooded(boolean isFlooded) {
        this.isFlooded = isFlooded;
    }
}
