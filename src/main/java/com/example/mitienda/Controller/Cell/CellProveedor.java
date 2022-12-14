package com.example.mitienda.Controller.Cell;

import com.example.mitienda.ClassAux.AlertDialog;
import com.example.mitienda.ClassAux.Formato;
import com.example.mitienda.ClassAux.Util;
import com.example.mitienda.Controller.ControllerProveedor;
import com.example.mitienda.Controller.Forms.FormProveedor;
import com.example.mitienda.Controller.Row.RowProveedor;
import com.example.mitienda.Data.DataProveedor;
import com.example.mitienda.Model.Proveedor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CellProveedor  extends ListCell<Proveedor> {
    //declaramos un nodo y un objeto de rowproducto
    AlertDialog alertDialog=new AlertDialog();
    private Node graphic;
    private RowProveedor rowProveedor;
    // el constructor donde llamamos el el rowproducto
    public CellProveedor(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/Proveedor/RowProveedor.fxml"));
        try {
            graphic=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        rowProveedor=loader.getController();
        //el evento del botono eliminar para eliminar productos
        rowProveedor.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (alertDialog.alertConfirm("Proveedor", "esta seguro de elliminar al proveedor")){
                    Proveedor pro=new Proveedor(Integer.parseInt(rowProveedor.codigo.getText()),"x","x",0,0,"x","x","x","x");
                    DataProveedor datos=new DataProveedor();
                    datos.crudProveedor(pro,"delete");
                    ControllerProveedor proveedorController=new ControllerProveedor();
                    proveedorController.initLista(getListView());
                    getListView().refresh();

                }
            }
        });
        //aquii dejas los eventos del boton donde los tenes te dar problema por el costructor
        //evento del boton modificar _______________________________________________________________________________________//

        rowProveedor.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Proveedor proveedor = new Proveedor();
                for (int i = 0; i < getListView().getItems().size(); i++) {
                    if (Integer.parseInt(rowProveedor.codigo.getText()) == getListView().getItems().get(i).getIdProveedor()) {
                        proveedor.setIdProveedor(getListView().getItems().get(i).getIdProveedor());
                        proveedor.setNombre(getListView().getItems().get(i).getNombre());
                        proveedor.setApellido(getListView().getItems().get(i).getApellido());
                        proveedor.setTelefonoUno(getListView().getItems().get(i).getTelefonoUno());
                        proveedor.setTelefonoDos(getListView().getItems().get(i).getTelefonoDos());
                        proveedor.setCompania(getListView().getItems().get(i).getCompania());
                        proveedor.setDireccion(getListView().getItems().get(i).getDireccion());
                        proveedor.setSexo(getListView().getItems().get(i).getSexo());
                        proveedor.setEstado(getListView().getItems().get(i).getEstado());


                    }
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Proveedor/FormProveedor.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Modificar producto");
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
                    stage.setScene(new Scene(parent));
                    FormProveedor formProveedor = loader.<FormProveedor>getController();
                    formProveedor.pasarRegistro(proveedor);
                    stage.show();
                    stage.setOnHiding((event -> {
                        ControllerProveedor proveedorController = new ControllerProveedor();
                        proveedorController.initLista(getListView());
                        getListView().refresh();
                    }));

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        });
        //final del evento modificar


    }
    //aqui llenas la lista con  el rowProducto
    @Override
    protected void updateItem(Proveedor proveedor, boolean empty){
        super.updateItem(proveedor, empty);
        if (empty){
            clearContent();
        }else{
            addContent(proveedor);
            //  rowProducto.pasar(getItem());
        }
    }


    //para limpiar contenido
    private void clearContent(){
        setGraphic(null);
    }
    //agregamos contenido a cada label creado en el rowproducto
    private void addContent(Proveedor proveedor){
        setText(null);
        //  rowProducto.setAncho(getListView().getWidth()-16);
        rowProveedor.setCodigo(proveedor.getIdProveedor());
        rowProveedor.setNombre(proveedor.getNombre()+"  "+proveedor.getApellido());
        rowProveedor.setTelefonouno(proveedor.getTelefonoUno());
        rowProveedor.setTelefonodos(proveedor.getTelefonoDos());

        rowProveedor.setSexo(proveedor.getSexo());
        rowProveedor.setCompania(proveedor.getCompania());
        rowProveedor.setDireccion(proveedor.getDireccion());
        rowProveedor.setEstado(proveedor.getEstado());

        setGraphic(graphic);
    }

}
