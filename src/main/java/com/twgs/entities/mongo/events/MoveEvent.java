package com.twgs.entities.mongo.events;

import com.twgs.entities.Path;

/**
 * Created by alirezaghias on 1/6/2017 AD.
 */
public class MoveEvent extends BaseEvent {
    private String targetTowerId;
    private double[] targetTowerLocation; //move destination
    private Path path;
    private boolean attackMode;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getTargetTowerId() {
        return targetTowerId;
    }

    public void setTargetTowerId(String targetTowerId) {
        this.targetTowerId = targetTowerId;
    }

    public double[] getTargetTowerLocation() {
        return targetTowerLocation;
    }

    public void setTargetTowerLocation(double[] targetTowerLocation) {
        this.targetTowerLocation = targetTowerLocation;
    }

    public void setAttackMode(boolean attackMode) {
        this.attackMode = attackMode;
    }

    public boolean isAttackMode() {
        return attackMode;
    }
}
