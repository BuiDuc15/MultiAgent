package com.hust.demo2;

public class BackTrack {
    int x, y, index;

    public BackTrack(int index,int x, int y) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "BackTrack{" +
                "x=" + x +
                ", y=" + y +
                ", index=" + index +
                '}';
    }
}
