package com.hust.demo2;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class RunGeneral {
    public Square[][] squares;;
    public static Map mapGame;
    public ArrayList<Run> robotRun=new ArrayList<>();

    public RunGeneral(GridPane map, int mapWeight, int mapHeight, int numOfWalls, ArrayList<Robot> robotList) {
        mapGame = new Map(map, mapWeight, mapHeight, numOfWalls, robotList);
        this.squares= mapGame.squares;
        for(int m=0;m<robotList.size();m++){
            robotRun.add(new Run(mapWeight,mapHeight,robotList.get(m).posXRobotStart, robotList.get(m).posYRobotStart,squares,mapGame));
        }
        this.squares= mapGame.squares;

    }
}
