package com.example.android.covidapp;

import com.google.firebase.database.DatabaseReference;

public class Complains {

    String Comp;
    String loc;


    public Complains() {
    }

    public String getComp() {
        return Comp;
    }

    public void setComp(String comp) {
        Comp = comp;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
