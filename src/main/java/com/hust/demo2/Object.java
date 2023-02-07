package com.hust.demo2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

//using set img
public class Object extends ImageView {
    int status;
    String type;

    int posX, posY;
    public Object(int posX,int posY){
        this.posX=posX;
        this.posY=posY;
    }

    String direction;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void setObjectImage(Image image){
        this.setImage(image);
    }

    public void setImage(int size){
        Image img=new Image( new File("image/"+this.type+"Img.png").toString(),size,size,false,false);
        this.setObjectImage(img);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Object{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
