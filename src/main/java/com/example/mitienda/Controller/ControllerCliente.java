package com.example.mitienda.Controller;

import com.example.mitienda.ClassAux.AlertDialog;
import com.example.mitienda.ClassAux.ButtonStyle;
import com.example.mitienda.ClassAux.SizeColumnTable;
import com.example.mitienda.Controller.Cell.CellCliente;
import com.example.mitienda.Controller.Forms.FormCliente;
import com.example.mitienda.Controller.Row.RowCliente;
import com.example.mitienda.Data.DataCliente;
import com.example.mitienda.Model.Cliente;
import com.example.mitienda.Model.Producto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCliente implements Initializable {

    public ListView<Cliente> listCliente;
    public Button btnNuevo;
    public TextField txtBuscar;
    public TableView <Cliente> tblCliente;
    public TableColumn<Cliente,String> cellCodigo;
    public TableColumn<Cliente,String> cellNombre;
    public TableColumn<Cliente,String> cellApellido;
    public TableColumn<Cliente,String> cellTelefonouno;
    public TableColumn<Cliente,String> cellTelefonodos;
    public TableColumn<Cliente,String> cellNit;
    public TableColumn<Cliente,String> cellSexo;
    public TableColumn<Cliente,String> cellOpciones;


    String hcambio="";
    RowCliente rowCliente=new RowCliente();

    static ObservableList<Cliente> clientes ;
    static FilteredList<Cliente> clientedata;
    SizeColumnTable sizeColumnTable=new SizeColumnTable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      //  initLista(listCliente);
        initTable();
        llenarListaCliente();



    }
    public  void initTable(){
        cellCodigo=new TableColumn<>("Código");
        cellNombre=new TableColumn<>("Nombre");
        cellApellido=new TableColumn<>("Apellido");
        cellTelefonouno=new TableColumn<>("Telefono 1");
        cellTelefonodos=new TableColumn<>("Telefono 2");
        cellNit=new TableColumn<>("Nit");
        cellSexo=new TableColumn<>("Sexo");
        cellOpciones=new TableColumn<>("Opciones");
        /*
          int codigo, telefonoUno, telefonoDos;
    String nombre, apellido, sexo, nit;
        * */
        cellCodigo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("codigo"));
        cellNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        cellApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
        cellTelefonouno.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefonoUno"));
        cellTelefonodos.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefonoDos"));
        cellNit.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nit"));
        cellSexo.setCellValueFactory(new PropertyValueFactory<Cliente,String>("sexo"));
        //cellOpciones.setCellValueFactory(new PropertyValueFactory<Cliente,String>(""));
        tblCliente.getColumns().addAll(cellCodigo,cellNombre,cellApellido,cellTelefonouno,cellTelefonodos,cellNit,cellSexo,cellOpciones);
        Platform.runLater(()->sizeColumnTable.ajustarColumna(tblCliente));
        cellOpciones.setCellFactory(new Callback<TableColumn<Cliente, String>, TableCell<Cliente, String>>() {
            @Override
            public TableCell<Cliente, String> call(TableColumn<Cliente, String> clienteStringTableColumn) {
                return new TableCell<Cliente,String>(){
                    @Override
                    protected void updateItem(String item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                        }else {
                            Button btnEdit=new Button();
                            btnEdit.setGraphic(ButtonStyle.iconoEdit());
                            btnEdit.setStyle(ButtonStyle.style());
                            Button btnDelete=new Button();
                            btnDelete.setGraphic(ButtonStyle.iconDelete());
                            btnDelete.setStyle(ButtonStyle.style());

                            btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Cliente cliente=getTableView().getItems().get(getIndex());
                                    EditarCliente(cliente);
                                }
                            });
                            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Cliente cliente=getTableView().getItems().get(getIndex());
                                    EliminarCliete(cliente);
                                }
                            });

                            HBox containButton=new HBox(btnDelete,btnEdit);
                            containButton.setAlignment(Pos.CENTER);
                            containButton.setSpacing(1);
                            setGraphic(containButton);
                        }
                        setText(null);
                    }
                };
            }
        });
    }
    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Cliente> listView){
        DataCliente datos=new DataCliente();
        clientes = FXCollections.observableArrayList(datos.viewCliente("viewall"));
        clientedata=new FilteredList<Cliente>(clientes, s->true);
        listView.setItems(clientedata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Cliente>, ListCell<Cliente>>() {
            @Override
            public ListCell<Cliente> call(ListView<Cliente> param) {
                CellCliente clienteCell=new CellCliente();
                return clienteCell;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaCliente(){
        DataCliente datos=new DataCliente();
        clientes = FXCollections.observableArrayList(datos.viewCliente("viewall"));
        clientedata=new FilteredList<Cliente>(clientes, s->true);
        tblCliente.setItems(clientedata);

        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            clientedata.setPredicate(cliente ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(cliente.getCodigo()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(cliente.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(cliente.getApellido().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevoCliente(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Cliente/FormCliente.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            stage.show();
            stage.setOnHiding((event -> {
               // initLista(listCliente);
                llenarListaCliente();
                tblCliente.refresh();
            }));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void EditarCliente(Cliente cliente) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Cliente/FormCliente.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            FormCliente formCliente=fxmlLoader.getController();
            formCliente.pasarRegistro(cliente);
            stage.show();
            stage.setOnHiding((event -> {

                llenarListaCliente();
                tblCliente.refresh();
            }));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void EliminarCliete(Cliente cliente) {
        AlertDialog alertDialog=new AlertDialog();
        if (alertDialog.alertConfirm("Cliente", "esta seguro de elliminar el cliente")){

            DataCliente datos=new DataCliente();
            datos.crudCliente(cliente,"delete");
         llenarListaCliente();
         tblCliente.refresh();

        }

    }
}
