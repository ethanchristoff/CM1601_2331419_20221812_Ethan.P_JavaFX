package com.example.cm1601_2331419_20221812;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

public class RapidRun_App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RapidRun_App.class.getResource("Welcome_Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        // Attaches the icon to the stage/window
        stage.getIcons().add(new Image(RapidRun_App.class.getResourceAsStream("/ICON.jpg")));
        stage.setTitle("Rapid Races");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);// Disables the ability to adjust the size of the stage
    }

    public static void main(String[] args) {
        file_manip f_m = new file_manip();
        String horseDataPath = f_m.find_f_path("horse_data.txt");
        File horseDataFile = new File(horseDataPath);

        if (horseDataFile.exists() && !horseDataFile.isDirectory()) {
            //A file will always be made by default so if it exists nothing happens
        } else {
            f_m.write_to_file(horseDataPath,f_m.return_data());
        }
        launch();
    }
}