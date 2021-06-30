package Capstone;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AddInventory {

    //Data Fields 
    Label lblAdd = new Label("Add Inventory");
    ThriftyStoreGUI mainReference;
    GridPane primaryPane;
    Scene primaryScene;
    Stage primaryStage;
    Label lblInvProd = new Label("Product: ");
    Label lblInvImage = new Label("Image: ");
    Label lblInvStore = new Label("Store: ");
    Label lblInvDep = new Label("Department: ");
    Label lblInvQuantity = new Label("Quantity: ");
    Label lblInvStatus = new Label("Status: ");
    Label lblInvExpiration = new Label("Exp. Date: ");
    Label lblInvSalesPrice = new Label("Sales Price: ");
    ComboBox cbInvProd = new ComboBox();
    ComboBox cbInvStore = new ComboBox();
    ComboBox cbInvDep = new ComboBox();
    ComboBox cbInvImage = new ComboBox();
    TextField txtInvQuantity = new TextField();
    TextField txtInvStatus = new TextField();
    DatePicker dpInvExpiration = new DatePicker();
    Button btnAddInventory = new Button("Add ->");
    Button btnAddProduct = new Button("Add New Product");
    Label lblUnitCost = new Label("Unit Cost: ");
    TextField txtUnitCost = new TextField();
    TextField txtInvSalesPrice = new TextField();
    ArrayList<Product> cbProducts = new ArrayList<>();
    ArrayList<Inventory> addedInventory = new ArrayList<>();

    public AddInventory(ThriftyStoreGUI parentReference) {
        mainReference = parentReference;
        primaryPane = new GridPane();

        mainReference.sendDBCommand("Select * from Product");
        
        // adds database results into product arraylist
        try {
            while (mainReference.dbResults.next()) {
                cbProducts.add(new Product(mainReference.dbResults.getString(1), mainReference.dbResults.getString(2), mainReference.dbResults.getString(3), mainReference.dbResults.getString(4), Double.valueOf(mainReference.dbResults.getString(5)), Double.valueOf(mainReference.dbResults.getString(6))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // adds product names into combobox
        for (int i = 0; i < cbProducts.size(); i++) {
            cbInvProd.getItems().add(cbProducts.get(i).getProductName());
        }
        
        // adds stores into combobox
        cbInvStore.getItems().addAll("sto22221", "sto22222", "sto22223", "sto22224", "sto22225", "sto22226");

        
        // adds departments into combobox
        cbInvStore.setOnAction(eb -> {
            
            String choice = cbInvStore.getSelectionModel().getSelectedItem().toString();
            
            if (choice.equals("sto22221")) {
                cbInvDep.getItems().add("dep55554");
                cbInvDep.getItems().add("dep55555");
                cbInvDep.getItems().add("dep55556");
            } else if (choice.equals("sto22222")) {
                cbInvDep.getItems().add("dep55557");
                cbInvDep.getItems().add("dep55558");
            } else if (choice.equals("sto22223")) {
                cbInvDep.getItems().add("dep55559");
            } else if (choice.equals("sto22224")) {
                cbInvDep.getItems().add("dep55553");
            }
        });
        
        // adds image names into combobox
        cbInvImage.getItems().addAll("eggs","flour","fruit","orange juice","rice","cheese","honey","soup","butter","steak","chicken","no picture");
        cbInvProd.setMinWidth(180);
        cbInvDep.setMinWidth(180);
        dpInvExpiration.setMinWidth(180);
        cbInvImage.setMinWidth(180);
        cbInvStore.setMinWidth(180);
        primaryPane.add(lblAdd, 0, 0);
        primaryPane.add(lblInvStore, 0, 2);
        primaryPane.add(lblInvProd, 0, 1);
        primaryPane.add(lblInvDep, 0, 3);
        primaryPane.add(lblInvQuantity, 0, 4);
        primaryPane.add(lblInvStatus, 0, 5);
        primaryPane.add(lblInvExpiration, 0, 6);
        primaryPane.add(lblInvSalesPrice, 0, 7);
        primaryPane.add(lblUnitCost, 0, 8);
        primaryPane.add(lblInvImage, 0, 9);
        primaryPane.add(cbInvProd, 1, 1);
        primaryPane.add(btnAddProduct, 2, 1);
        primaryPane.add(cbInvStore, 1, 2);
        primaryPane.add(cbInvDep, 1, 3);
        primaryPane.add(txtInvQuantity, 1, 4);
        primaryPane.add(txtInvStatus, 1, 5);
        primaryPane.add(dpInvExpiration, 1, 6);
        primaryPane.add(txtInvSalesPrice, 1, 7);
        primaryPane.add(txtUnitCost, 1, 8);
        primaryPane.add(cbInvImage, 1, 9);
        primaryPane.add(btnAddInventory, 1, 10);

        primaryPane.setAlignment(Pos.CENTER);

        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();

        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Inventory");
        primaryStage.show();

        btnAddInventory.setOnAction(e -> {
            ArrayList<Product> prodIDList = new ArrayList<>();
            String prodID = new String();
            String unitCost = new String();
            mainReference.sendDBCommand("Select * from Product");
            try {
                while (mainReference.dbResults.next()) {
                    prodIDList.add(new Product(mainReference.dbResults.getString(1), mainReference.dbResults.getString(2), mainReference.dbResults.getString(3), mainReference.dbResults.getString(4), Double.valueOf(mainReference.dbResults.getString(5)), Double.valueOf(mainReference.dbResults.getString(6))));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(int i = 0; i < prodIDList.size(); i++)
            {
                if(prodIDList.get(i).getProductName().equals(cbInvProd.getSelectionModel().getSelectedItem().toString()))
                {
                    prodID = prodIDList.get(i).getProductID();
                    
                }
            }
            
            String imgName = "images\\" + cbInvImage.getSelectionModel().getSelectedItem().toString() + ".jpg";
                        ImageView invImg = new ImageView(new Image(imgName));
                        invImg.setFitHeight(50);
                        invImg.setFitWidth(50);
                        
            addedInventory.add(new Inventory(prodID,cbInvProd.getSelectionModel().getSelectedItem().toString(),invImg,
                    cbInvDep.getSelectionModel().getSelectedItem().toString(),
                    cbInvStore.getSelectionModel().getSelectedItem().toString(),
                    Integer.valueOf(txtInvQuantity.getText()),
                    txtInvStatus.getText(), dpInvExpiration.getValue().toString(),
                    Double.valueOf(txtInvSalesPrice.getText()), Double.valueOf(txtUnitCost.getText()))
                    );
             mainReference.AddInventory(addedInventory.get(0));
             
            String day = String.valueOf(dpInvExpiration.getValue().getDayOfMonth()); //Day in int
            String month = dpInvExpiration.getValue().getMonth().toString();
            String year = String.valueOf(dpInvExpiration.getValue().getYear());
            
            System.out.println(day + "/" + month + "/" + year);
            
            String date = day + "/" + month + "/" + year;
             
             String sql = "INSERT INTO JAVAUSER.INVENTORYITEM (PRODUCTID,DEPARTMENTID,QUANTITYINSTOCK,STATUS,EXPIRATIONDATE) VALUES ('";
             sql += prodID + "', '";
             sql += cbInvDep.getSelectionModel().getSelectedItem().toString() + "', '";
             sql += Integer.valueOf(txtInvQuantity.getText()) + "', '";
             sql += txtInvStatus.getText() + "', '";
             sql += date + "')";
             
             mainReference.sendDBCommand(sql);
             
             primaryStage.close();
            
            
            
        });

        btnAddProduct.setOnAction(e -> {
            Label lblAddProd = new Label("Add New Product: ");
            Label lblProdName = new Label("Product Name: ");
            Label lblProdDesc = new Label("Product Description: ");
            Label lblProdUnitCost = new Label("Unit Cost: ");
            Label lblProdSalesPrice = new Label("Sales Price: ");
            Label lblProdSupplier = new Label("Supplier: ");
            TextField txtProdName = new TextField();
            TextField txtProdDesc = new TextField();
            TextField txtProdUnitCost = new TextField();
            TextField txtProdSalesPrice = new TextField();
            ComboBox cbProdSupplier = new ComboBox();
            Button btnAddNewProduct = new Button("Add ->");
            mainReference.sendDBCommand("Select * from Supplier");
            try {
                while (mainReference.dbResults.next()) {
                    cbProdSupplier.getItems().add(mainReference.dbResults.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            GridPane pPane = new GridPane();
            ArrayList<Product> prodData = new ArrayList<>();
            String lastProdID;
            int idDigits;
            String newProdID;

            pPane.add(lblAddProd, 0, 0);
            pPane.add(lblProdName, 0, 1);
            pPane.add(lblProdDesc, 0, 2);
            pPane.add(lblProdUnitCost, 0, 3);
            pPane.add(lblProdSalesPrice, 0, 4);
            pPane.add(lblProdSupplier, 0, 5);
            pPane.add(txtProdName, 1, 1);
            pPane.add(txtProdDesc, 1, 2);
            pPane.add(txtProdUnitCost, 1, 3);
            pPane.add(txtProdSalesPrice, 1, 4);
            pPane.add(cbProdSupplier, 1, 5);
            pPane.add(btnAddNewProduct, 1, 6);
            pPane.setAlignment(Pos.CENTER);
            Scene pScene = new Scene(pPane, 700, 500);
            Stage pStage = new Stage();

            mainReference.sendDBCommand("Select * from Product");

            try {
                while (mainReference.dbResults.next()) {
                    prodData.add(new Product(mainReference.dbResults.getString(1), mainReference.dbResults.getString(2), mainReference.dbResults.getString(3), mainReference.dbResults.getString(4), Double.valueOf(mainReference.dbResults.getString(5)), Double.valueOf(mainReference.dbResults.getString(6))));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            pStage.setScene(pScene);
            pStage.setTitle("Add New Product");
            pStage.show();

            lastProdID = prodData.get(prodData.size() - 1).getProductID();
            idDigits = Integer.parseInt(lastProdID.substring(3));
            idDigits++;
            newProdID = "pro" + idDigits;
            btnAddNewProduct.setOnAction(x -> {
                String sql = "INSERT INTO JAVAUSER.PRODUCT (PRODUCTID,SUPPLIERID,PRODUCTNAME,PRODUCTDESCRIPTION,UNITCOST,SALESPRICE) VALUES ('";
                sql += newProdID + "', '";
                sql += cbProdSupplier.getSelectionModel().getSelectedItem().toString() + "', '";
                sql += txtProdName.getText() + "', '";
                sql += txtProdDesc.getText() + "', '";
                sql += Double.valueOf(txtProdUnitCost.getText()) + "', '";
                sql += Double.valueOf(txtProdSalesPrice.getText()) + "')";

                mainReference.sendDBCommand(sql);
                cbInvProd.getItems().add(txtProdName.getText());
                pStage.close();

            });
        });

    }
}
