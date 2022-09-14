package com.example.mitienda.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    public Button btnIniciar;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnIniciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AbrirMenu();
            }
        });

    }
    public  void AbrirMenu(){
        try{
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/View/Menu/Menu.fxml"));
            Parent root= fxmlLoader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu principal");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            stage.setMaximized(true);
            stage.show();
            Stage cerrar=(Stage) btnIniciar.getScene().getWindow();
            cerrar.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
