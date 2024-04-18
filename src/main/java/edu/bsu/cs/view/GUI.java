package edu.bsu.cs.view;

import edu.bsu.cs.model.API_Requests;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class GUI extends Application {
    public static final int[] WINDOW_SIZE = {800, 600};

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private final Button artistBTN = new Button("Artist");
    private final Button albumButton = new Button("Album");
    private final Button trackButton = new Button("Track");
    @FXML
    private final Button homeBTN = new Button("");
    @FXML
    private final TextArea outputField = new TextArea();


    @Override
    public void start(Stage stage){
        try {
            outputField.setMinHeight(400);
            configure(stage);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GUI.fxml")));
            Scene scene = new Scene(root, WINDOW_SIZE[0], WINDOW_SIZE[1]);
            stage.setScene(scene);
            stage.show();
            homeBTN.setOnAction(this::configureHomeButton);
            albumButton.setOnAction(this::handleAlbumButtonAction);
        }catch(Exception e){
            e.printStackTrace();
        }



    }
    private void configure(Stage stage) {
        stage.setScene(new Scene(createRoot()));
        stage.sizeToScene();
        stage.show();


    }

    private VBox createRoot() {
        VBox root = new VBox();
        root.getChildren().addAll(
                artistBTN,
                albumButton,
                trackButton,
                homeBTN,
                new Label("Search Results:"),//
                outputField);
        return root;
    }


    private void configureHomeButton(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GUI.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_SIZE[0], WINDOW_SIZE[1]));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleAlbumButtonAction(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("albumScene.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_SIZE[0], WINDOW_SIZE[1]));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}








