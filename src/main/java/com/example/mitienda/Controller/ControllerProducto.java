package com.example.mitienda.Controller;

import com.example.mitienda.ClassAux.*;
import com.example.mitienda.Controller.Cell.CellProducto;
import com.example.mitienda.Controller.Forms.FormProducto;
import com.example.mitienda.Data.DataLote;
import com.example.mitienda.Data.DataProducto;
import com.example.mitienda.Data.DataProductoInventario;
import com.example.mitienda.Model.Lote;
import com.example.mitienda.Model.Producto;
import com.example.mitienda.Model.ProductoInvetario;
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

public class ControllerProducto implements Initializable {
    public ListView<Producto> listProducto;
    public TextField txtBuscar;





    static ObservableList<Producto> productos ;
    static FilteredList<Producto> proData;
    public Button btnInventario;
    public Button btnIngresarNuevo;
    public TableView<Producto> tblProducto;
    public TableColumn<Producto, String> cellCodigo;
    public TableColumn<Producto, String> cellNombre;
    public TableColumn<Producto, String> cellModelo;
    public TableColumn<Producto, String> cellEspecificacion;
    public TableColumn<Producto, String> cellColocacio;
    public TableColumn<Producto, String> cellPrecio;
    public TableColumn<Producto, String> cellMayorista;
    public TableColumn<Producto, String> cellStock;
    public TableColumn<Producto, String> cellMinima;
    public TableColumn<Producto, String> cellMaxima;
    public TableColumn<Producto, String> cellEstado;
    public TableColumn<Producto, String> cellopciones;
    SizeColumnTable size_tabla=new SizeColumnTable();
    AlertDialog alertDialog=new AlertDialog();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //initLista(listProducto);
        initTabla();
        llenarListaProducto();




    }
public void initTabla(){
    cellCodigo=new TableColumn<>("Código");
    cellNombre=new TableColumn<>("Nombre");
    cellModelo=new TableColumn<>("Modelo");
    cellEspecificacion=new TableColumn<>("Descripción");
    cellColocacio=new TableColumn<>("Colocación");
    cellPrecio=new TableColumn<>("P/Unidad");
    cellMayorista=new TableColumn<>("P/Mayorista");
    cellStock=new TableColumn<>("C/Disponible");
    cellMinima=new TableColumn<>("Minima");
    cellMaxima=new TableColumn<>("Maxima");
    cellopciones=new TableColumn<>("Opciones");
    cellEstado=new TableColumn<>("Estado");
    /*
    *     int codigo, stock, minimo,maximo, idcolocacion,idproveedor,idlote,cantidad;
    String nombre, modelo,especificacion,estado,proveedor,colocacion;
    Float precio_compra, precio_mayorista,precio_mayor,precio_unidad;

    * */
    cellCodigo.setCellValueFactory(new PropertyValueFactory<Producto,String>("codigo"));
    cellNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("nombre"));
    cellModelo.setCellValueFactory(new PropertyValueFactory<Producto,String>("modelo"));
    cellEspecificacion.setCellValueFactory(new PropertyValueFactory<Producto,String>("especificacion"));
    cellColocacio.setCellValueFactory(new PropertyValueFactory<Producto,String>("colocacion"));
    cellPrecio.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio_unidad"));
    cellMayorista.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio_mayorista"));
    cellStock.setCellValueFactory(new PropertyValueFactory<Producto,String>("stock"));
    cellMinima.setCellValueFactory(new PropertyValueFactory<Producto,String>("minimo"));
    cellMaxima.setCellValueFactory(new PropertyValueFactory<Producto,String>("maximo"));
    cellEstado.setCellValueFactory(new PropertyValueFactory<Producto,String>("estado"));

    tblProducto.getColumns().addAll(cellCodigo,cellNombre,cellModelo,cellEspecificacion,cellColocacio,cellStock,cellMinima,cellMaxima,cellPrecio,cellMayorista,cellEstado,cellopciones);
    Platform.runLater(()->size_tabla.ajustarColumna(tblProducto));

/*
    Callback<TableColumn<Producto,String>,TableCell<Producto,String>> cellFactory=(TableColumn<Producto,String> param)->{
        final TableCell<Producto,String> cell =new TableCell<>(){
            @Override
            public void updateItem(String item, boolean empty) {
                if (empty){
                    setGraphic(null);
                }else{
                    Button btnEdit=new Button();
                    btnEdit.setGraphic(ButtonStyle.iconoEdit());
                    btnEdit.setStyle(ButtonStyle.style());
                    Button btnDelete=new Button();
                    btnDelete.setGraphic(ButtonStyle.iconDelete());
                    btnDelete.setStyle(ButtonStyle.style());
                    Button btnEditLote=new Button();
                    btnEditLote.setGraphic(ButtonStyle.iconoNew());
                    btnEditLote.setStyle(ButtonStyle.style());
                    btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EditarProducto(producto);
                        }
                    });
                    btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EliminarProducto(producto);
                        }
                    });
                    btnEditLote.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EditarLote(producto);
                        }
                    });
                    HBox containButton=new HBox(btnDelete,btnEditLote,btnEdit);
                    containButton.setAlignment(Pos.CENTER);
                    containButton.setSpacing(2);
                    setGraphic(containButton);

                }
                setText(null);
            }
        };
        return  cell;
    };
    cellopciones.setCellFactory(cellFactory);

*/

cellopciones.setCellFactory(new Callback<TableColumn<Producto, String>, TableCell<Producto, String>>() {
    @Override
    public TableCell<Producto, String> call(TableColumn<Producto, String> tableColumn) {
        return new TableCell<Producto, String>(){
            @Override
            protected  void updateItem(String item, boolean empty){
                super.updateItem(item, empty);
                if (empty){
                    setGraphic(null);
                }
                else{
                    Button btnEdit=new Button();
                    btnEdit.setGraphic(ButtonStyle.iconoEdit());
                    btnEdit.setStyle(ButtonStyle.style());
                    Button btnDelete=new Button();
                    btnDelete.setGraphic(ButtonStyle.iconDelete());
                    btnDelete.setStyle(ButtonStyle.style());
                    Button btnEditLote=new Button();
                    btnEditLote.setGraphic(ButtonStyle.iconoNew());
                    btnEditLote.setStyle(ButtonStyle.style());
                    btnEdit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EditarProducto(producto);
                        }
                    });
                    btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EliminarProducto(producto);
                        }
                    });
                    btnEditLote.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Producto producto=getTableView().getItems().get(getIndex());
                            EditarLote(producto);
                        }
                    });
                    HBox containButton=new HBox(btnDelete,btnEditLote,btnEdit);
                    containButton.setAlignment(Pos.CENTER);
                    containButton.setSpacing(1);
                    setGraphic(containButton);

                }
           setText(null);
            }
        };
    }
});



cellEstado.setCellFactory(new Callback<TableColumn<Producto, String>, TableCell<Producto, String>>() {
    @Override
    public TableCell<Producto, String> call(TableColumn<Producto, String> tableColumn) {
        return new TableCell<Producto, String>(){
            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item,empty);
                if (!empty){
                    Label label=new Label();
                    if (item.equals("Activo")){

                        label.setStyle(ButtonStyle.Activo());
                    }else{
                       label.setStyle(ButtonStyle.NoActivo());
                    }
                    label.setText(item);
                    HBox containLabel=new HBox(label);
                    containLabel.setAlignment(Pos.CENTER);
                    containLabel.setSpacing(2);
                    setGraphic(containLabel);
                    //setText(item);
                }else{
                    setGraphic(null);
                    setText("");
                }
            }
        };
    }
});
tblProducto.setRowFactory(new Callback<TableView<Producto>, TableRow<Producto>>() {
    @Override
    public TableRow<Producto> call(TableView<Producto> param) {
        return new TableRow<Producto>(){
            @Override
            protected void updateItem(Producto item, boolean empty){
                super.updateItem(item, empty);
                if (item !=null){
                    if (item.getStock() == 0){
                        setStyle(ButtonStyle.Menor());
                    } else if (item.getStock() <= item.getMinimo()) {
                        setStyle(ButtonStyle.Igual());
                    } else if (item.getStock() > item.getMinimo()) {
                        setStyle(null);
                    } /*else if (item.getStock() == 0 && item.getMinimo()==0) {
                        setStyle(null);
                    }*/
                }else {
                    setStyle(null);
                }
            }
        };
    }
});
}
    //inixcair las listas para el ListView y combobox
    public void initLista(ListView<Producto> listview){
        DataProducto datos=new DataProducto();
        productos = FXCollections.observableArrayList(datos.viewProducto("viewall"));
        proData=new FilteredList<Producto>(productos,s->true);
        listview.setItems(proData);
        //para llenar las filas personalizadas

        listview.setCellFactory(new Callback<ListView<Producto>, ListCell<Producto>>() {
            @Override
            public ListCell<Producto> call(ListView<Producto> param) {
                CellProducto proCell=new CellProducto();
                return proCell;
            }
        });

    }

    //combobox ordenar por


// combobox categoria


//llenarel list View

    public void llenarListaProducto(){
        DataProducto datos=new DataProducto();
        productos = FXCollections.observableArrayList(datos.viewProducto("viewall"));
        proData=new FilteredList<Producto>(productos,s->true);
        tblProducto.setItems(proData);

        txtBuscar.textProperty().addListener((prop,old,text) ->{
            proData.setPredicate(producto ->{
                if (text==null || text.isEmpty()){
                    return  true;
                }
                String texto=text.toLowerCase();
                if(String.valueOf(producto.getCodigo()).toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getNombre().toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getModelo().toLowerCase().contains(texto)){
                    return true;
                }
                else if(producto.getEspecificacion().toLowerCase().contains(texto)){
                    return true;
                }
                return false;
            });
        });


    }

    public void nuevoProducto(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Producto/FormProducto.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.  show();
            stage.setTitle("Ingresar nuevo producto");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            stage.setOnHiding((event ->{
               // initLista(listProducto);
                //initTabla();
                 llenarListaProducto();
                tblProducto.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public void EditarProducto(Producto producto) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/Producto/FormProducto.fxml"));
            //Parent parent = FXMLLoader.load(getClass().getResource("/View/Producto/FormProducto.fxml"));
            Parent parent=loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Modificar nuevo producto");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
            FormProducto formProducto=loader.getController();
            formProducto.pasarRegistro(producto);
            stage.  show();
            stage.setOnHiding((event ->{
               // initLista(listProducto);
                //listProducto.refresh();
                 llenarListaProducto();
                tblProducto.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }
    public  void EliminarProducto(Producto producto){
        Lote lote=new Lote(producto.getIdlote(),producto.getCodigo(),0,0,0,0,0,producto.getEstado());
        if (lote.getEstado().equals("Activo")) {
            if (lote.getIdlote() > 0) {

                if (alertDialog.alertConfirm("Lote", "Esta seguro de emilinar este lote")) {
                    DataLote dataLote = new DataLote();
                    dataLote.crudLote(lote, "delete");
                    llenarListaProducto();
                    tblProducto.refresh();

                }
            }
            else{
                if (alertDialog.alertConfirm("Producto", "Está seguro de desactivar este producto.")) {
                    Producto pro = new Producto(producto.getCodigo(), "x", "x", "x", 0, 0, 0, 0, 0, 0, 0, 0, 0, "No Activo", 0, 0, "", "");
                    DataProducto datos = new DataProducto();
                    datos.crudProducto(pro, "delete");
                    llenarListaProducto();
                    tblProducto.refresh();

                }
            }
        }else {
            if (alertDialog.alertConfirm("Producto", "Desea  activar este producto.")){
                Producto pro=new Producto(producto.getCodigo(),"x","x","x",0,0,0,0,0,0,0,0,0,"Activo",0,0,"","");
                DataProducto datos=new DataProducto();
                datos.crudProducto(pro,"delete");
                llenarListaProducto();
                tblProducto.refresh();

            }
        }

    }
    public void EditarLote(Producto producto){
        if (producto.getEstado().equals("No Activo")) {
            Util.Error("Producto", "Por favor Active el estado del producto para pode agregar al inventario");

        } else {

            Lote lote =new Lote(producto.getIdlote(),producto.getCodigo(),producto.getCantidad(),producto.getPrecio_compra(),producto.getPrecio_mayorista(),producto.getPrecio_mayor(),producto.getPrecio_unidad(),producto.getEstado());
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/View/Producto/Lote.fxml"));
                Parent parent = loader.load();
                Stage stage=new Stage();
                stage.setTitle("Modificar producto");
                stage.setScene(new Scene(parent));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/Img/icon.png")));
                ControllerLote nuevoLoteController=loader.<ControllerLote>getController();
                nuevoLoteController.pasarRegistro(lote);
                stage.show();
                stage.setOnHiding((event ->{

                    llenarListaProducto();
                    tblProducto.refresh();
                }));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void verInvetario(ActionEvent actionEvent) {
        int total_prodcuto=0;
        float costo_total=0;
        DataProductoInventario invetario=new DataProductoInventario();
        ObservableList<ProductoInvetario> list=FXCollections.observableArrayList(invetario.productoInventario());
        for (int i=0; i<list.size();i++){
            costo_total= costo_total+ list.get(i).getSubtotal();
            total_prodcuto=total_prodcuto+list.get(i).getCantidad();
        }
        ImprimirVale imprimirVale=new ImprimirVale();
        imprimirVale.InventarioProducto(list,total_prodcuto,costo_total,false);


    }
}
