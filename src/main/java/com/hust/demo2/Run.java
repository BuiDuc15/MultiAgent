package com.hust.demo2;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Run extends Pane {
    String colorVisited;
    String colorStart = "#00BFF7";

    int posXRobotStart, posYRobotStart;
    int mapWeight, mapHeight;
    public static Map mapGame;
    List<BackTrack> backTrackList = new ArrayList<BackTrack>();
    public Square[][] squares;
    int step = 0, index = 0;
    int dir = -1;
    Object robot;

    public Run(int mapWeight, int mapHeight, int posXRobotStart, int posYRobotStart, Square[][] squares, Map mapGame) {
        this.mapGame = mapGame;
        this.squares = squares;
        this.mapHeight = mapHeight;
        this.mapWeight = mapWeight;
        robot = new Robot(posXRobotStart, posYRobotStart);
        this.posXRobotStart = posXRobotStart;
        this.posYRobotStart = posYRobotStart;
        randomColor();

    }

    public void clean() {
        if (index >= 0) {
            //Set trạng thái: Go up=0; Turn right =1; Go down=2; Turn left=3
            switch (dir) {
                case 0:
                    if (checkGoUp(robot)) {
//                        System.out.println("continue go up");
                        goUp(robot);
                    } else {
//                        System.out.println("checkNewStep");
                        checkNewStep(robot);
                    }
                    break;
                case 1:
                    if (checkTurnRight(robot)) {
//                        System.out.println("continue right");
                        turnRight(robot);
                    } else {
//                        System.out.println("checkNewStep");
                        checkNewStep(robot);
                    }
                    break;
                case 2:
                    if (checkGoDown(robot)) {
//                        System.out.println("continue down");
                        goDown(robot);
                    } else {
//                        System.out.println("checkNewStep");
                        checkNewStep(robot);
                    }
                    break;
                case 3:
                    if (checkTurnLeft(robot)) {
//                        System.out.println("continue left");
                        turnLeft(robot);
                    } else {
//                        System.out.println("checkNewStep");
                        checkNewStep(robot);
                    }
                    break;
                default: {
                    //start position
                    backTrackList.add(new BackTrack(0, robot.posX, robot.posY));
                    checkNewStep(robot);

                    break;
                }
            }
        }
    }

    public void randomColor() {
        Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        colorVisited = String.format("#%06x", nextInt);
    }

    public void moveRobot(Square oldSquare, Square newSquare) {

//        mapGame.removeObjectAndSetSquare(oldSquare);
//
//        mapGame.addRobot(newSquare);
        //test
        squares[oldSquare.x][oldSquare.y].getChildren().clear();
        if (squares[oldSquare.x][oldSquare.y].x == posXRobotStart && squares[oldSquare.x][oldSquare.y].y == posYRobotStart) {
            squares[oldSquare.x][oldSquare.y].setBackground(new Background(new BackgroundFill(Color.web(colorStart), CornerRadii.EMPTY, Insets.EMPTY)));
        } else
            squares[oldSquare.x][oldSquare.y].setBackground(new Background(new BackgroundFill(Color.web(colorVisited), CornerRadii.EMPTY, Insets.EMPTY)));
        squares[newSquare.x][newSquare.y].getChildren().add(new Robot(robot.posX, robot.posY, mapGame.imgSize - 5));
//        addObject(square, new Robot(square.x, square.y, imgSize - 5));
        squares[newSquare.x][newSquare.y].status = 1;
        //

    }

    public void checkNewStep(Object robotI) {
        if (checkGoUp(robotI)) {
//            System.out.println("Check up true");
            goUp(robotI);
        } else if (checkTurnRight(robotI)) {
//            System.out.println("Check right true");
            turnRight(robotI);
        } else if (checkGoDown(robotI)) {
//            System.out.println("Check down true");
            goDown(robotI);
        } else if (checkTurnLeft(robotI)) {
//            System.out.println("Check left true");
            turnLeft(robotI);
        } else {
//            System.out.println("Backtrack");
            backTrack();
        }
    }

    public void backTrack() {
        step += 1;
        index = index - 1;
        if (index >= 0) {
            backTrackList.remove(index + 1);
            System.out.println(step + ":Turn Back. Index= " + index);
            int tempX = backTrackList.get(index).x;
            int tempY = backTrackList.get(index).y;
//            System.out.println(tempX + " " + tempY);
            moveRobot(convertRobotToSquare(robot), convertRobotToSquare(new Object(tempX, tempY)));
            robot = new Object(tempX, tempY);

//            checkNewStep();
        }
//        else runStatus=false;
    }

    boolean checkGoUp(Object robotI) {
        boolean check = false;
//        System.out.println("In vi tri " + robotI);
        if (robotI.posY - 1 >= 0) {
            if (squares[robotI.posX][robotI.posY - 1].status != -1 && squares[robotI.posX][robotI.posY - 1].status != 1) {
                check = true;
            } else {

                check = false;
            }
        }
//        else System.out.println("check up false");
        return check;
    }

    public void goUp(Object robotI) {
        step += 1;
        index += 1;
        squares[robotI.posX][robotI.posY - 1].status = 1;
        System.out.println(step + ": Go Up. Index= " + (index));
        backTrackList.add(new BackTrack(index, robotI.posX, robotI.posY - 1));
//        System.out.println("Vi tri x= " + robotI.posX + " Vi tri y= " + (robotI.posY - 1));
        robot = new Object(robotI.posX, robotI.posY - 1);
        dir = 0;
        moveRobot(convertRobotToSquare(robotI), convertRobotToSquare(robot));
    }

    //Turn Right
    public boolean checkTurnRight(Object robotI) {
//        System.out.println("In vi tri " + robotI);
        boolean check = false;
        if (robotI.posX + 2 <= mapWeight) {
            if (squares[robotI.posX + 1][robotI.posY].status != -1 && squares[robotI.posX + 1][robotI.posY].status != 1) {
                check = true;
            } else {

                check = false;
            }
        }
//        else System.out.println("check right false");
        return check;
    }

    public void turnRight(Object robotI) {
        step += 1;
        index += 1;
        squares[robotI.posX + 1][robotI.posY].status = 1;
        System.out.println(step + ": Turn Right. Index= " + (index));
        backTrackList.add(new BackTrack(index, robotI.posX + 1, robotI.posY));
//        System.out.println("Vi tri x= " + (robotI.posX + 1) + " Vi tri y= " + robotI.posY);
        robot = new Object(robotI.posX + 1, robotI.posY);
        dir = 1;
        moveRobot(convertRobotToSquare(robotI), convertRobotToSquare(robot));
    }

    //Go Down
    public boolean checkGoDown(Object robotI) {
//        System.out.println("In vi tri " + robotI);
        boolean check = false;
        if (robotI.posY + 2 <= mapHeight) {
            if (squares[robotI.posX][robotI.posY + 1].status != -1 && squares[robotI.posX][robotI.posY + 1].status != 1) {
                check = true;
            } else {

                check = false;
            }
        }
//        else System.out.println("check down false");
        return check;
    }

    public void goDown(Object robotI) {
        step += 1;
        index += 1;
        squares[robotI.posX][robotI.posY + 1].status = 1;
        System.out.println(step + ": Turn Down. Index= " + index);
        backTrackList.add(new BackTrack(index, robotI.posX, robotI.posY + 1));
//        System.out.println("Vi tri x= " + robotI.posX + " Vi tri y= " + (robotI.posY + 1));
        robot = new Object(robotI.posX, robotI.posY + 1);
        dir = 2;
        moveRobot(convertRobotToSquare(robotI), convertRobotToSquare(robot));
    }

    //Turn Left
    public boolean checkTurnLeft(Object robotI) {
        boolean check = false;
//        System.out.println("In vi tri " + robotI);
        if (robotI.posX - 1 >= 0) {
            if (squares[robotI.posX - 1][robotI.posY].status != -1 && squares[robotI.posX - 1][robotI.posY].status != 1) {
                check = true;
            } else {

                check = false;
            }
        }
//        else System.out.println("check left false");
        return check;
    }

    public void turnLeft(Object robotI) {
        step += 1;
        index += 1;
        squares[robotI.posX - 1][robotI.posY].status = 1;
        System.out.println(step + ": Turn Left. Index= " + index);
//        System.out.println("Vi tri x= " + (robotI.posX - 1) + " Vi tri y= " + robotI.posY);
        robot = new Object(robotI.posX - 1, robotI.posY);
        backTrackList.add(new BackTrack(index, robotI.posX - 1, robotI.posY));
        dir = 3;
        moveRobot(convertRobotToSquare(robotI), convertRobotToSquare(robot));
    }

    public Square convertRobotToSquare(Object robotI) {
        return new Square(robotI.posX, robotI.posY);
    }
//    public void addRobot(Square square) {
//        squares[square.x][square.y].getChildren().add(new Robot(square.x, square.y, imgSize - 5));
////        addObject(square, new Robot(square.x, square.y, imgSize - 5));
//        squares[square.x][square.y].status = 1;
//    }
//
//    public void removeObjectAndSetSquare(Square squareI) {
//        squares[squareI.x][squareI.y].getChildren().clear();
//        for (int k = 0; k < robotList.size(); k++) {
//
//            if (squareI.x == robotList.get(k).posXRobotStart && squareI.y == robotList.get(k).posYRobotStart) {
//                squares[squareI.x][squareI.y].setBackground(new Background(new BackgroundFill(Color.web(colorStart), CornerRadii.EMPTY, Insets.EMPTY)));
//                System.out.println(robotList.get(k).posXRobotStart + "  " + robotList.get(k).posYRobotStart);
//            } else
//                squares[squareI.x][squareI.y].setBackground(new Background(new BackgroundFill(Color.web(colorVisited), CornerRadii.EMPTY, Insets.EMPTY)));
//        }

}
