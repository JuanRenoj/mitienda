package com.example.mitienda.Controller;

import com.example.mitienda.ClassAux.AlertDialog;
import com.example.mitienda.ClassAux.ButtonStyle;
import com.example.mitienda.ClassAux.SizeColumnTable;
import com.example.mitienda.Controller.Cell.CellProveedor;
import com.example.mitienda.Controller.Forms.FormProveedor;
import com.example.mitienda.Controller.Row.RowProveedor;
import com.example.mitienda.Data.DataProveedor;
import com.example.mitienda.Model.Proveedor;
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

public class ControllerProveedor implements Initializable {
    public ListView<Proveedor> listProveedor;
    public Button btnNuevo;
    public TextField txtBuscar;
    public TableView<Proveedor> tblProveedor;
    TableColumn<Proveedor,String> cellCodigo;
    TableColumn<Proveedor,String> cellNombre;
    TableColumn<Proveedor,String> cellApellido;
    TableColumn<Proveedor,String> cellDireccion;
    TableColumn<Proveedor,String> cellTelefonouno;
    TableColumn<Proveedor,String> cellTelelefonodos;
    TableColumn<Proveedor,String> cellCompania;
    TableColumn<Proveedor,String> cellEstado;
    TableColumn<Proveedor,String> cellSexo;
    TableColumn<Proveedor,String> cellOpciones;


    String hcambio="";
    RowProveedor rowCliente=new RowProveedor();

    static ObservableList<Proveedor> proveedores;
    static FilteredList<Proveedor> proveedordata;
    SizeColumnTable sizeColumnTable=new SizeColumnTable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initLista(listProveedor);
        initTabla();
        llenarListaProveedor();



    }
    public void initTabla(){
        cellCodigo=new TableColumn<>("Código");
        cellNombre=new TableColumn<>("Nombre");
        cellApellido=new TableColumn<>("Apellido");
        cellDireccion=new TableColumn<>("Dirección");
        cellTelefonouno=new TableColumn<>("Telefono ");
        cellTelelefonodos=new TableColumn<>("Telefono");
        cellCompania=new TableColumn<>("Compania");
        cellEstado=new TableColumn<>("Estado");
        cellSexo=new TableColumn<>("Sexo");
        cellOpciones=new TableColumn<>("Opciones");

        cellCodigo.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("idProveedor"));
        cellNombre.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("nombre"));
        cellApellido.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("apellido"));
        cellDireccion.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("direccion"));
        cellTelefonouno.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("telefonoUno"));
        cellTelelefonodos.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("telefonoDos"));
        cellCompania.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("compania"));
        cellEstado.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("estado"));
        cellSexo.setCellValueFactory(new PropertyValueFactory<Proveedor,String>("sexo"));
        //cellOpciones.setCellValueFactory(new PropertyValueFactory<Proveedor,String>(""));
        tblProveedor.getColumns().addAll(cellCodigo,cellNombre,cellApellido,cellDireccion,cellTelefonouno,cellTelelefonodos,cellCompania,cellSexo,cellEstado,cellOpciones);
        Platform.runLater(()->sizeColumnTable.ajustarColumna(tblProveedor));
        cellOpciones.setCellFactory(new Callback<TableColumn<Proveedor, String>, TableCell<Proveedor, String>>() {
            @Override
            public TableCell<Proveedor, String> call(TableColumn<Proveedor, String> proveedorStringTableColumn) {
                return new TableCell<Proveedor,String>(){
                    @Override
                    protected  void updateItem(String item, boolean empty){
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
                                    Proveedor proveedor=getTableView().getItems().get(getIndex());
                                    EditarProveedor(proveedor);
                                }
                            });
                            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Proveedor proveedor=getTableView().getItems().get(getIndex());
                                    EliminarProveedor(proveedor);
                                }
                            });
                            HBox containButton=new HBox(btnDelete,btnEdit);
                            containButton.setAlignment(Pos.CENTER);
                            containButton.setSpacing(2);
                            setGraphic(containButton);
                        }
                        setText(null);
                    }
                };
            }
        });
        cellEstado.setCellFactory(new Callback<TableColumn<Proveedor, String>, TableCell<Proveedor, String>>() {
            @Override
            public TableCell<Proveedor, String> call(TableColumn<Proveedor, String> proveedorStringTableColumn) {
                return new TableCell<Proveedor,String>(){
                    @Override
                    protected  void updateItem(String item, boolean empty){
                        if (empty){
                            setGraphic(null);
                            setText(null);
                        }else{
                            Label label=new Label();
                            if (item.equals("Activo")){
                                label.setStyle(ButtonStyle.Activo());
                            }else{
                                label.setStyle(ButtonStyle.NoActivo());
                            }
                            label.setText(item);
                            HBox containLabel=new HBox(label);
                            containLabel.setSpacing(2);
                            containLabel.setAlignment(Pos.CENTER);
                            setGraphic(containLabel);
                        }
                    }
                };
            }
        });

    }

    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Proveedor> listView){
        DataProveedor datos=new DataProveedor();
        proveedores = FXCollections.observableArrayList(datos.viewProveedor("viewall"));
        proveedordata=new FilteredList<Proveedor>(proveedores,s->true);
        listView.setItems(proveedordata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Proveedor>, ListCell<Proveedor>>() {
            @Override
            public ListCell<Proveedor> call(ListView<Proveedor> param) {
                CellProveedor proveedorCell=new CellProveedor();
                return proveedorCell;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaProveedor(){
        DataProveedor datos=new DataProveedor();
        proveedores = FXCollections.observableArrayList(datos.viewProveedor("viewall"));
        proveedordata=new FilteredList<Proveedor>(proveedores,s->true);
        tblProveedor.setItems(proveedordata);
        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            proveedordata.setPredicate(proveedor ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(proveedor.getIdProveedor()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(proveedor.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(proveedor.getApellido().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevoProveedor(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Proveedor/FormProveedor.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            stage.setOnHiding((event ->{
//                initLista(listProveedor);
                llenarListaProveedor();
                tblProveedor.refresh();
                // llenarListaProducto();
                //listProveedor.refresh();
            }));


        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public  void EditarProveedor(Proveedor proveedor){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/Proveedor/FormProveedor.fxml"));
            Parent parent = loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            FormProveedor formProveedor=loader.getController();
            formProveedor.pasarRegistro(proveedor);
            stage.setTitle("Modificar Proveedor");
            stage.show();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            stage.setOnHiding((event ->{
//                initLista(listProveedor);
                llenarListaProveedor();
                tblProveedor.refresh();
                // llenarListaProducto();
                //listProveedor.refresh();
            }));


        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public void EliminarProveedor(Proveedor proveedor){
        AlertDialog alertDialog=new AlertDialog();
        if (alertDialog.alertConfirm("Proveedor", "esta seguro de elliminar al proveedor")){
            DataProveedor datos=new DataProveedor();
            datos.crudProveedor(proveedor,"delete");
            llenarListaProveedor();
            tblProveedor.refresh();
        }
    }
}
