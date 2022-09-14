package com.example.mitienda.ClassAux;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Util {
    public static  void Error(String titulo,String mensaje){
        try {

             ImageView icon=new ImageView(new Image(Util.class.getResourceAsStream("/Img/exclamatiom.png")));
            Notifications notifications=Notifications.create()
                    .title(titulo)
                    .text(mensaje)
                    .graphic(icon)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .threshold(2,Notifications.create().title(titulo).text(mensaje).graphic(icon).darkStyle().hideCloseButton())
                    .hideCloseButton()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {

                        }
                    });
            notifications.darkStyle();
            notifications.show();

        }catch (Exception e){

        }


    }
    public static  void Advertencia(String titulo,String mensaje){
        try {

            ImageView icon=new ImageView(new Image(Util.class.getResourceAsStream("/Img/exclamation.png")));

            Notifications notifications=Notifications.create()
                    .title(titulo)
                    .text(mensaje)
                    .graphic(icon)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .threshold(2,Notifications.create().title(titulo).text(mensaje).graphic(icon).darkStyle().hideCloseButton())
                    .hideCloseButton()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {

                        }
                    });
            notifications.darkStyle();
            notifications.show();

        }catch (Exception e){

        }



    }
    public static  void Exito(String titulo,String mensaje){
        try {
            //    Node ld= FXMLLoader.load(HelloController.class.getResource("Noti.fxml"));
//            Image image=new Image(HelloController.class.getResourceAsStream("img/check.png"));
            ImageView icon=new ImageView(new Image(Util.class.getResourceAsStream("/Img/check.png")));
            Notifications notifications=Notifications.create()
                    .title(titulo)
                    .text(mensaje)
                    .graphic(icon)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .threshold(2,Notifications.create().title(titulo).text(mensaje).graphic(icon).darkStyle().hideCloseButton())
                    .hideCloseButton()
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {

                        }
                    });
            notifications.darkStyle();
            notifications.show();

        }catch (Exception e){

        }

    }
}
