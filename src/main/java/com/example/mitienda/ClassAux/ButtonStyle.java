package com.example.mitienda.ClassAux;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonStyle {
    public static  int height=25;
    public static int width=25;
    public  static ImageView icono(String url){
        ImageView imageView=new ImageView(new Image(ButtonStyle.class.getResourceAsStream(url)));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }
    public  static ImageView iconDelete(){
        ImageView imageView=new ImageView(new Image(ButtonStyle.class.getResourceAsStream("/Img/delete.png")));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }
    public  static ImageView iconoEdit(){
        ImageView imageView=new ImageView(new Image(ButtonStyle.class.getResourceAsStream("/Img/edit.png")));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }
    public  static ImageView iconoNew(){
        ImageView imageView=new ImageView(new Image(ButtonStyle.class.getResourceAsStream("/Img/plus.png")));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        return imageView;
    }
    public  static String style(){
        String a="-fx-cursor:hand; " +
                "-fx-background-color: transparent;";
        return a;
    }
    public  static String Activo(){
        String Activo ="" +
                "-fx-background-color:#54dea7;" +
                "-fx-font-size:14px;"+
                "-fx-text-fill:#ffffff;"+
                "-fx-font-weight: bold;"+
                "-fx-background-radius:10px;"+
                "-fx-padding:5px 10px;";
        return Activo;
    }
    public  static String NoActivo(){
        String Noactivo ="" +
                "-fx-background-color:#F03F37;" +
                "-fx-font-size:14px;"+
                "-fx-text-fill:#ffffff;"+
                "-fx-font-weight: bold;"+
                "-fx-background-radius:10px;"+
                "-fx-padding:5px 10px;";
        return Noactivo;
    }
    public  static String Menor(){

        return "-fx-background-color: #FF5F42; -fx-table-cell-border-color: transparent;";
    }
    public  static String Igual(){

        return "-fx-background-color: #FFF075; -fx-table-cell-border-color: transparent;";
    }
    public  static String Superior(){

        return "-fx-background-color: transparent; -fx-table-cell-border-color: transparent;";
    }
}
