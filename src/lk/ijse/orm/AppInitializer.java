package lk.ijse.orm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"))));
        primaryStage.setTitle("Royal Institute");
        primaryStage.getIcons().add(new Image("lk/ijse/orm/asserts/logo.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
