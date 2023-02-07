package com.hust.demo2;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    Random random = new Random();
    String colorMap = "#EEEEEE";
    String colorWall = "#e69138";
    String colorVisited="#019A66";
    String colorStart = "#00BFF7";

    GridPane map;
    int mapHeight;
    int mapWeight;
    int numOfWalls;
    int posXRobotStart, posYRobotStart;
    int imgSize;
    boolean runStatus = true;
    int step = 0, index = 0;
    public Square[][] squares = new Square[1000][1000];
    ArrayList<Robot> robotList = new ArrayList<>();


    ArrayList<Wall> walls = new ArrayList<>();

    //    void in(){
//        System.out.println(mapHeight);
//        System.out.println(mapWeight);
//        System.out.println(numOfWalls);
//        System.out.println(posXRobotStart);
//        System.out.println(posYRobotStart);
//
//    }
    public Map(GridPane map, int mapWeight, int mapHeight, int numOfWalls, ArrayList<Robot> robotList) {
        this.robotList = robotList;
        this.map = map;
        this.mapHeight = mapHeight;
        this.mapWeight = mapWeight;
        this.numOfWalls = numOfWalls;
        setImgSize();
        makeMap(this.map);
    }

    public void setImgSize() {
        int max;
        if (mapWeight < mapHeight) {
            max = mapHeight;
        } else max = mapWeight;
        this.imgSize = 800 / max;
    }

    public void makeMap(GridPane map) {
        System.out.println("makeMap here");
        initWall();
        for (int k = 0; k < walls.size(); k++) {
            System.out.println(walls.get(k));
        }
        System.out.println();
        for (int i = 0; i < mapWeight; i++) {
            for (int j = 0; j < mapHeight; j++) {
                Square square = new Square(i, j);

                square.setPrefHeight(imgSize);
                square.setPrefWidth(imgSize);

                square.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setSquareStatus(square, i, j);
//                square.visted=false;
//                System.out.println(square);
                map.add(square, i, j, 1, 1);
                squares[i][j] = square;
                //System.out.println(squares[i][j]);

            }
        }
        for (int k = 0; k < robotList.size(); k++) {
            squares[robotList.get(k).posXRobotStart][robotList.get(k).posYRobotStart].setBackground(new Background(new BackgroundFill(Color.web(colorStart), CornerRadii.EMPTY, Insets.EMPTY)));
            addRobot(squares[robotList.get(k).posXRobotStart][robotList.get(k).posYRobotStart]);
        }

//        removeObjectAndSetSquare(squares[posYRobotStart][posXRobotStart]);

//        run();
//        for (int k = 0; k < backTrackList.size(); k++) {
//            System.out.println(backTrackList.get(k));
//        }
    }


    public void setSquareStatus(Square square, int posX, int posY) {
        if (isWall(square, posX, posY)) {
            //String colorWall = String.format("#%06x", random.nextInt(0xffffff + 1));
            square.setStatus(-1);
            square.setBackground(new Background(new BackgroundFill(Color.web(colorWall), CornerRadii.EMPTY, Insets.EMPTY)));

        } else {
            square.setStatus(0);
            square.setBackground(new Background(new BackgroundFill(Color.web(colorMap), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private boolean isWall(Square square, int posX, int posY) {
        boolean check = false;
        for (int m = 0; m < numOfWalls; m++) {
            if (posX >= walls.get(m).getPosX() && posX <= (walls.get(m).getPosX() + walls.get(m).getdX())
                    && posY >= walls.get(m).getPosY() && posY <= (walls.get(m).getPosY() + walls.get(m).getdY())) {
                check = true;
                break;
            }
        }
        return check;
    }

    private void initWall() {
        int w = 0;
        whileLoop:
        while (w < numOfWalls) {
            boolean checkAllInitWall = false;
//            System.out.println();
//            System.out.println("chi so w: "+w);

            int wallX = (ThreadLocalRandom.current().nextInt(0, mapWeight - 2));
            int wallY = (ThreadLocalRandom.current().nextInt(0, mapHeight - 2));
            int wallDx = random.nextInt(3);
            int wallDy = random.nextInt(3);


            if (walls.size() == 0) {
                checkAllInitWall = true;
            }
            if (walls.size() > 0) {
//                System.out.println("số lượng phần tử trong mảng walls: "+walls.size());
                wallLoop:
                for (int a = 0; a < walls.size(); a++) {
//                    System.out.println("chi so a: "+a);
                    if ((wallX < walls.get(a).getPosX() || wallX > (walls.get(a).getPosX() + walls.get(a).getdX()))
                            && (wallY < walls.get(a).getPosY() || wallY > (walls.get(a).getPosY() + walls.get(a).getdY()))) {
//                        System.out.println(wallX < walls.get(a).getPosX());
//                        System.out.println(wallX > (walls.get(a).getPosX()+walls.get(a).getdX()));
//                        System.out.println(wallY < walls.get(a).getPosY() );
//                        System.out.println(wallY > (walls.get(a).getPosY() + walls.get(a).getdY()));
                        checkAllInitWall = true;
                    } else {
//                        System.out.println(wallX < walls.get(a).getPosX());
//                        System.out.println(wallX > (walls.get(a).getPosX()+walls.get(a).getdX()));
//                        System.out.println(wallY < walls.get(a).getPosY() );
//                        System.out.println(wallY > (walls.get(a).getPosY() + walls.get(a).getdY()));
//                        System.out.println("false: Loại tường ");
                        checkAllInitWall = false;
                        break wallLoop;

                    }
                }
            }
//            if (wallX == posXRobotStart && wallY == posYRobotStart) {
//                checkAllInitWall = false;
//            }
            checkDuplicateWallWithStartPos:
            for (int i = wallX; i <= (wallX + wallDx); i++) {
                for (int j = wallY; j <= wallY + wallDy; j++) {
                    for (int k = 0; k < robotList.size(); k++) {
                        if (i == robotList.get(k).posXRobotStart && j == robotList.get(k).posYRobotStart) {
                            checkAllInitWall = false;
                            break checkDuplicateWallWithStartPos;
                        }
                    }
                }
            }
            if (checkAllInitWall) {
                Wall wallI = new Wall(wallX, wallY, wallDx, wallDy);
                walls.add(wallI);
                w++;
//                System.out.println("Create wall success");
//                for (int k=0;k<walls.size();k++){
//                    System.out.println(walls.get(k));
//                }
            }
        }
    }


//    private void addObject(Square square, Object object) {
//        squares[square.x][square.y].getChildren().add(object);
//        square.status = 1;
//    }

    public void addRobot(Square square) {
        squares[square.x][square.y].getChildren().add(new Robot(square.x, square.y, imgSize - 5));
//        addObject(square, new Robot(square.x, square.y, imgSize - 5));
        squares[square.x][square.y].status = 1;
    }
//
//    public void removeObjectAndSetSquare(Square squareI) {
//        squares[squareI.x][squareI.y].getChildren().clear();
//        for (int k = 0; k < robotList.size(); k++) {
//
//            if (squareI.x == robotList.get(k).posXRobotStart && squareI.y == robotList.get(k).posYRobotStart) {
//                    squares[squareI.x][squareI.y].setBackground(new Background(new BackgroundFill(Color.web(colorStart), CornerRadii.EMPTY, Insets.EMPTY)));
//                System.out.println(robotList.get(k).posXRobotStart + "  " + robotList.get(k).posYRobotStart);
//            } else
//                squares[squareI.x][squareI.y].setBackground(new Background(new BackgroundFill(Color.web(colorVisited), CornerRadii.EMPTY, Insets.EMPTY)));
//        }
//        System.out.println("thay doi vi tri");
//    }


    //

}









