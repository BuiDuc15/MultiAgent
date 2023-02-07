package com.hust.demo2;

import javafx.scene.image.Image;

public class Wall extends  Object{
    private int dX,dY;
    public Wall( int posX, int posY, int dX,int dY ) {
        super(posX, posY);
        this.dX=dX;
        this.dY=dY;
    }
//    public void setObject(Image image){
//        this.setImage(image);
//    }
//    public void setImage() {
//        this.setObject(new Image("image/Brick_Image.png"));
//    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    @Override
    public String toString() {
        return "Wall{" +
                " posX=" + posX +
                ", posY=" + posY +
                ", dX=" + dX +
                ", dY=" + dY +

                '}';
    }
}
