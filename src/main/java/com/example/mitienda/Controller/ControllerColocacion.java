package com.example.mitienda.Controller;

import com.example.mitienda.ClassAux.AlertDialog;
import com.example.mitienda.ClassAux.ButtonStyle;
import com.example.mitienda.ClassAux.SizeColumnTable;
import com.example.mitienda.Controller.Cell.CellColocacion;
import com.example.mitienda.Controller.Forms.FormColocacion;
import com.example.mitienda.Controller.Row.RowColocacion;
import com.example.mitienda.Data.DataColocacion;
import com.example.mitienda.Data.DataProducto;
import com.example.mitienda.Model.Colocacion;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerColocacion implements Initializable {
    public Button btnIngresarNuevo;
    public TextField txtBuscar;


    public ListView<Colocacion> listColocacion;
    public ListView<Producto>listProducto;
    public TableView<Colocacion> tblColocacion;
    public  TableColumn<Colocacion,String> cellCodigo;
    public  TableColumn<Colocacion,String> cellNombre;
    public  TableColumn<Colocacion,String> cellEstado;
    public  TableColumn<Colocacion,String> cellOpciones;
    public TableView<Producto> tblDetalle;
    public  TableColumn<Producto,String> cellDes;
    public  TableColumn<Producto,String> cellModelo;
    public  TableColumn<Producto,String> cellEspecificacion;

SizeColumnTable sizeColumnTable=new SizeColumnTable();
    String hcambio="";
    RowColocacion rowColocacion=new RowColocacion();

    static ObservableList<Colocacion> colocaciones ;
    static FilteredList<Colocacion> colocaciondata;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
initTablaColocacion();
initTablaDetalle();
        llenarListaColocacion();
        //initLista(listColocacion);


    /*    listColocacion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent mouseEvent) {
                int codigo = listColocacion.getSelectionModel().getSelectedItem().getIdColocacion();
                Producto producto = new Producto(0, "", "", "", 0, 0, 0, 0, 0, 0, 0, codigo, 0, "", 0, 0, "", "");
                DataProducto dataProducto=new DataProducto();
                ObservableList <Producto> lista= FXCollections.observableArrayList(dataProducto.viewProductoXCol(producto,"viewxcol"));
                listProducto.setItems(lista);
            }
        });
*/


    }

    public  void  initTablaColocacion(){
        cellCodigo=new TableColumn<>("Código");
        cellNombre=new TableColumn<>("Nombre");
        cellEstado=new TableColumn<>("Estado");
        cellOpciones=new TableColumn<>("Opciones");
        cellCodigo.setCellValueFactory(new PropertyValueFactory<Colocacion,String>("idColocacion"));
        cellNombre.setCellValueFactory(new PropertyValueFactory<Colocacion,String>("nombre"));
        cellEstado.setCellValueFactory(new PropertyValueFactory<Colocacion,String>("estado"));
        //cellOpciones.setCellValueFactory(new PropertyValueFactory<Colocacion,String>("opciones"));
        tblColocacion.setEditable(true);
        tblColocacion.getColumns().addAll(cellCodigo,cellNombre,cellEstado,cellOpciones);
        Platform.runLater(()-> sizeColumnTable.ajustarColumna(tblColocacion) );

        cellOpciones.setCellFactory(new Callback<TableColumn<Colocacion, String>, TableCell<Colocacion, String>>() {
            @Override
            public TableCell<Colocacion, String> call(TableColumn<Colocacion, String> colocacionStringTableColumn) {
                return new TableCell<Colocacion,String>(){
                    @Override
                    protected  void updateItem(String item,boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setGraphic(null);
                        }else{
                            Button btnEdit=new Button();
                            btnEdit.setGraphic(ButtonStyle.iconoEdit());
                            btnEdit.setStyle(ButtonStyle.style());
                            Button btnDelete=new Button();
                            btnDelete.setGraphic(ButtonStyle.iconDelete());
                            btnDelete.setStyle(ButtonStyle.style());
                            btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Colocacion colocacion=getTableView().getItems().get(getIndex());
                                    EditarColocaion(colocacion);
                                }
                            });
                            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Colocacion colocacion=getTableView().getItems().get(getIndex());
                                    EliminarColocacion(colocacion);
                                }
                            });
                            HBox containBotton=new HBox(btnDelete,btnEdit);
                            containBotton.setAlignment(Pos.CENTER);
                            containBotton.setSpacing(2);
                            setGraphic(containBotton);
                        }
                        setText(null);
                    }
                };
            }
        });

        cellEstado.setCellFactory(new Callback<TableColumn<Colocacion, String>, TableCell<Colocacion, String>>() {
            @Override
            public TableCell<Colocacion, String> call(TableColumn<Colocacion, String> colocacionStringTableColumn) {
                return new TableCell<Colocacion,String>(){
                    @Override
                    protected void updateItem(String item, boolean empty){
                        super.updateItem(item,empty);
                        if (empty){
                            setText("");
                            setGraphic(null);
                        }else {
                            Label label=new Label();
                            if (item.equals("Activo")){
                                label.setStyle(ButtonStyle.Activo());
                            }else{
                                label.setStyle(ButtonStyle.NoActivo());
                            }
                            label.setText(item);
                            HBox containLabel=new HBox(label);
                            containLabel.setSpacing(1);
                            containLabel.setAlignment(Pos.CENTER);
                            setGraphic(containLabel);
                        }

                    }
                };
            }
        });

        tblColocacion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==1  && event.getButton()==MouseButton.PRIMARY) {
                    Colocacion colocacion=tblColocacion.getSelectionModel().getSelectedItem();
                    if (colocacion !=null) {
                        Producto producto = new Producto(0, "", "", "", 0, 0, 0, 0, 0, 0, 0, colocacion.getIdColocacion(), 0, "", 0, 0, "", "");
                        initListaDetalle(producto);
                    }
            }
            }
        });

    }
    public  void initTablaDetalle(){
        cellDes=new TableColumn<>("Nombre del producto");
        cellModelo=new TableColumn<>("Modelo");
        cellEspecificacion=new TableColumn<>("Especificación");
        cellDes.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
        cellModelo.setCellValueFactory(new PropertyValueFactory<Producto,String>("Modelo"));
        cellEspecificacion.setCellValueFactory(new PropertyValueFactory<Producto,String>("especificacion"));
        tblDetalle.getColumns().addAll(cellDes,cellModelo,cellEspecificacion);
        Platform.runLater(()->sizeColumnTable.ajustarColumna(tblDetalle));
    }
    //inixcair l
    // as listas para el ListView y combobox
    public void initListaDetalle(Producto producto){
        DataProducto dataProducto=new DataProducto();
        ObservableList <Producto> lista= FXCollections.observableArrayList(dataProducto.viewProductoXCol(producto,"viewxcol"));
        tblDetalle.setItems(lista);
        //listProducto.setItems(lista);
    }
    public void initLista(ListView<Colocacion> listView){
        DataColocacion datos=new DataColocacion();
        colocaciones = FXCollections.observableArrayList(datos.viewColocacion("viewall"));
        colocaciondata=new FilteredList<Colocacion>(colocaciones, s->true);
        listView.setItems(colocaciondata);
        //para llenar las filas personalizadas

        listView.setCellFactory(new Callback<ListView<Colocacion>, ListCell<Colocacion>>() {
            @Override
            public ListCell<Colocacion> call(ListView<Colocacion> param) {
                CellColocacion cellColocacion=new CellColocacion();
                return cellColocacion;
            }
        });

    }

    //combobox ordenar por


//llenarel list View

    public void llenarListaColocacion(){
        DataColocacion datos=new DataColocacion();
        colocaciones = FXCollections.observableArrayList(datos.viewColocacion("viewall"));
        colocaciondata=new FilteredList<Colocacion>(colocaciones, s->true);
        tblColocacion.setItems(colocaciondata);
        //capturar el texto y filtrar
        txtBuscar.textProperty().addListener((prop,old,text) ->{
            colocaciondata.setPredicate(colocacion ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(colocacion.getIdColocacion()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(colocacion.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(colocacion.getEstado().toLowerCase().contains(texto)){
                    return true;
                }

                return false;
            });
        });



    }


    public void nuevaColocacion(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Colocacion/FormColocacion.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding((event ->{

                llenarListaColocacion();
                //initLista(listColocacion);
                tblColocacion.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public void EditarColocaion(Colocacion colocacion) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/Colocacion/FormColocacion.fxml"));
            Parent parent = loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            FormColocacion formColocacion=loader.getController();
            formColocacion.pasarRegistro(colocacion);
            stage.setTitle("Actualizar colocación");
            stage.show();
            stage.setOnHiding((event ->{

                llenarListaColocacion();
                tblColocacion.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public  void EliminarColocacion(Colocacion colocacion){
        AlertDialog alertDialog=new AlertDialog();
        if (alertDialog.alertConfirm("Colocación", "esta seguro de elliminar la colocación")){
            DataColocacion datos=new DataColocacion();
            datos.crudColocacion(colocacion,"delete");
            llenarListaColocacion();
            tblColocacion.refresh();
        }
    }
}
