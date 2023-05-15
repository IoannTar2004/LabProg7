package org.example.collections;

import java.io.Serializable;

/**
 * Dragon's cave
 */
public class DragonCave implements Serializable {
    private double depth;

    public DragonCave(double death) {
        this.depth = death;
    }

    public Double getDepth() {
        return depth;
    }

}
