package com.target.barrenlandanalysis;

import java.util.*;

public class LandCoordinate {
    
    private Integer x;
    private Integer y;
    private boolean isBarren;
    private boolean visited = false;
    private String text = new String();

    public LandCoordinate() {
        
    }
    
    public LandCoordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
//        text = "-";
    }
    
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isIsBarren() {
        return isBarren;
    }

    public void setIsBarren(boolean isBarren) {
        this.isBarren = isBarren;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
        if(visited) {
            text ="*";
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}