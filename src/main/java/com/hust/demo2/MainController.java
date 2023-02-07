package com.hust.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class MainController {
    @FXML
    private AnchorPane container;
    @FXML
    private Label AlertSuccess1;
    @FXML
    private Label AlertSuccess2;

    @FXML
    private GridPane Map;
    @FXML
    private TextField MapWeight;

    int mapWeight;
    @FXML
    private TextField MapHeight;
    int mapHeight;

    @FXML
    private TextField NumOfWalls;
    int numOfWalls;

    @FXML
    private TextField RobotStartX1;
    int posXRobotStart1;

    @FXML
    private TextField RobotStartY1;
    int posYRobotStart1;

    @FXML
    private TextField RobotStartX2;
    int posXRobotStart2;

    @FXML
    private TextField RobotStartY2;
    int posYRobotStart2;

    @FXML
    Button NextStep;
    @FXML
    Button StartButton;
    @FXML
    Button StopButton;
    RunGeneral run;
    @FXML
    TextField NumOfRobot;
    int numOfRobot = 2;

    ArrayList<Robot> robotList = new ArrayList<>();
    boolean tempCheckDone = false;

    public void initialize(ActionEvent event) {
        run=null;robotList=new ArrayList<>();
        Map.getChildren().clear();
        NextStep.setVisible(false);
        StartButton.setVisible(true);
        StopButton.setVisible(true);
        NextStep.setVisible(false);
        AlertSuccess1.setVisible(false);
        Map.getChildren().removeAll();
        mapWeight = Integer.parseInt(MapWeight.getText());
        mapHeight = Integer.parseInt(MapHeight.getText());
        numOfWalls = Integer.parseInt(NumOfWalls.getText());
        posXRobotStart1 = Integer.parseInt(RobotStartX1.getText());
        posYRobotStart1 = Integer.parseInt(RobotStartY1.getText());
        posXRobotStart2 = Integer.parseInt(RobotStartX2.getText());
        posYRobotStart2 = Integer.parseInt(RobotStartY2.getText());
        robotList.add(new Robot(posXRobotStart1, posYRobotStart1, posXRobotStart1, posYRobotStart1));
        robotList.add(new Robot(posXRobotStart2, posYRobotStart2, posXRobotStart2, posYRobotStart2));
        run = new RunGeneral(Map, mapWeight, mapHeight, numOfWalls, robotList);
    }


    public void start(ActionEvent event) {
        for (int m = 0; m < robotList.size(); m++) {
            run.robotRun.get(m).clean();
        }
        StartButton.setVisible(false);
        NextStep.setVisible(true);


//        run.clean();
//        do {
//            run.clean();
//        }
//        while (run.index > 0);
//
//        if (run.index < 0) System.out.println("Quét dọn thành công: ");
    }

    public void nextStep(ActionEvent event) {
        for (int m = 0; m < robotList.size(); m++) {
            run.robotRun.get(m).clean();
        }

        for (int m = 0; m < robotList.size(); m++) {
            if (run.robotRun.get(m).index >= 0) {
                tempCheckDone = false;
                break;
            }
            System.out.println(run.robotRun.get(m).index);
            if (run.robotRun.get(m).index < 0) {
                tempCheckDone = true;
            }
        }
        if (tempCheckDone) {
            System.out.println("Quét dọn thành công: ");
            AlertSuccess1.setVisible(true);
            NextStep.setVisible(false);
        }


    }


    public void stop(ActionEvent event) {

    }
}