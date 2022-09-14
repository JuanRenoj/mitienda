module com.example.mitienda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires jasperreports;
    requires org.controlsfx.controls;



    opens com.example.mitienda to javafx.fxml;
    opens Css to javafx.fxml;
    opens View to javafx.fxml;
    opens Img to javafx.fxml;
    opens View.Cliente to javafx.fxml;
    opens View.Colocacion to javafx.fxml;
    opens View.Informe to javafx.fxml;
    opens View.Login to javafx.fxml;
    opens  View.Producto to javafx.fxml;
    opens View.Menu to javafx.fxml;
    opens View.Proveedor to javafx.fxml;
    opens View.Venta to javafx.fxml;

    exports com.example.mitienda;
    exports com.example.mitienda.Model;
    exports com.example.mitienda.Controller;
    exports com.example.mitienda.Controller.Cell;
    exports com.example.mitienda.Controller.Forms;
    exports com.example.mitienda.Controller.Row;
    exports com.example.mitienda.Data;
    exports com.example.mitienda.ClassAux;
}