package com.infinitequarks.tgz.attenote;

import java.io.Serializable;

/**
 * Created by m on 10/17/2016.
 */

public class Subject implements Serializable{
    private String name;
    private boolean isTheory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTheory() {
        return isTheory;
    }

    public void setTheory(boolean theory) {
        isTheory = theory;
    }

}
