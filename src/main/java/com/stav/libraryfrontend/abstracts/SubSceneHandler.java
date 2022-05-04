package com.stav.libraryfrontend.abstracts;

import com.stav.libraryfrontend.Library;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SubSceneHandler {

    private static SubSceneHandler instance = new SubSceneHandler();

    private Stage stage;
    private Scene scene;
    private BorderPane root;

    private SubSceneHandler(){
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.initModality(Modality.NONE);
        stage.initOwner(Library.inst().getStage());
        root = new BorderPane();
        scene = new Scene(root);
        stage.setScene(scene);
        addCloseEvent();
        stage.show();
        centerContent();
    }

    public static SubSceneHandler inst(){
        return instance;
    }

    private void addCloseEvent(){
        stage.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(!newValue){
                stage.hide();
                Library.inst().getRoot().getCenter().setOpacity(1);
            }
        }));
    }

    private void centerContent(){
        stage.setX(Library.inst().getStage().getX() + (Library.inst().getStage().getWidth()/2 - stage.getWidth()/2));
        stage.setY(Library.inst().getStage().getY() + (Library.inst().getStage().getHeight()/2 - stage.getHeight()/2));
    }

    public void show(Node node){
        root.setCenter(node);
        stage.sizeToScene();
        stage.show();
        centerContent();
        Library.inst().getRoot().getCenter().setOpacity(.3);
    }

    public void hide(){
        stage.close();
    }

}
