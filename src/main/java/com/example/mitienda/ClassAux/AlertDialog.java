package com.example.mitienda.ClassAux;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class AlertDialog {

    public  Boolean alertConfirm(String titulo, String mensaje){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("gsaccesorios");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Img/question.png"))));
        DialogPane dialogPane=alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Css/styleAlert.css").toExternalForm());
        Stage stage=(Stage)alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
        Optional<ButtonType> result=alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            return true;
        }else {
            return false;
        }
    }
    public Float SelectPrecion(ArrayList<Float> list){


        ChoiceDialog<Float> dialog=new ChoiceDialog<Float>(list.get(0),list.get(1),list.get(2));
        dialog.setTitle("Precios");
        dialog.setHeaderText("Selecione del producto: ");
        dialog.setContentText("Precios del producto: ");
        dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/exclamation.png"))));
        DialogPane dp = dialog.getDialogPane();
        dp.getStylesheets().add(getClass().getResource("/Css/styleAlert.css").toExternalForm());
        Stage stage=(Stage)dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
        Optional<Float> result =dialog.showAndWait();
        if (result.isPresent()){
            return Float.parseFloat(result.get().toString());
        }else {
            return null;
        }



    }
    public  Boolean alert(String titulo, String mensaje){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("gsaccesorios");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/img/question.png"))));
        DialogPane dialogPane=alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Css/styleAlert.css").toExternalForm());
        Stage stage=(Stage)alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
        Optional<ButtonType> result=alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            return true;
        }else {
            return false;
        }
    }
}
