package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.awt.*;
import javafx.geometry.Insets;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("hello");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("丑图秀秀");


        primaryStage.setResizable(false);//设置不能窗口改变大小
        Scene scene = new Scene(root,1023,610);
        primaryStage.setScene(scene);
        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
