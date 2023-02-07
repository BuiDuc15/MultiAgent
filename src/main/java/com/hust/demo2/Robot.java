package com.hust.demo2;

public class Robot extends Object {
    int posXRobotStart, posYRobotStart;
    public Robot(int posX, int posY) {
        super(posX, posY);
        this.type = "robot";
    }

    public Robot(int posX, int posY, int posXRobotStart, int getPosYRobotStart) {
        super(posX, posY);
        this.posXRobotStart = posXRobotStart;
        this.posYRobotStart = getPosYRobotStart;
    }

    public Robot(int posX, int posY, int size) {
        super(posX, posY);
        this.type = "robot";
        setImage(size);
    }
}


