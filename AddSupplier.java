/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capstone;

import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;


public class AddSupplier  {
    
    //Data Fields
    Label lblAdd = new Label("Add New Supplier");
    ThriftyStoreGUI mainReference;
    GridPane primaryPane;
    Scene primaryScene;
    Stage primaryStage;
    Label lblSupID = new Label("SupplierID");
    Label lblSupplier = new Label("Supplier Name: ");
    Label lblSupAddress = new Label("Address: ");
    Label lblSupStartDate = new Label("Start Date: ");
    Label lblSupContactName = new Label("Contact Name: ");
    Label lblSupContactPhone = new Label("Contact Phone: ");
    Label lblSupContactEmail = new Label("Contact Email: ");
    
    TextField txtSupID = new TextField();
    TextField txtSupplier = new TextField();
    TextField txtSupAddress = new TextField();
    DatePicker txtSupStartDate = new DatePicker();
    TextField txtSupContactName = new TextField();
    TextField txtSupContactPhone = new TextField();
    TextField txtSupContactEmail = new TextField();
    
    Button btnAddSupplier = new Button("Add ->");
    ArrayList<Supplier> SupData = new ArrayList<>();
    
    String lastSupID;
    int idDigits;
    String newSupID;
    
    public AddSupplier(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        
        primaryPane.add(lblAdd,0,0);
        //primaryPane.add(lblSupID,0,1);
        //primaryPane.add(txtSupID,1,1);
        primaryPane.add(lblSupplier,0,1);
        primaryPane.add(txtSupplier,1,1);
        primaryPane.add(lblSupAddress,0,2);
        primaryPane.add(txtSupAddress,1,2);
        primaryPane.add(lblSupStartDate,0,3);
        primaryPane.add(txtSupStartDate,1,3);
        primaryPane.add(lblSupContactName,0,4);
        primaryPane.add(txtSupContactName,1,4);
        primaryPane.add(lblSupContactPhone,0,5);
        primaryPane.add(txtSupContactPhone,1,5);
        primaryPane.add(lblSupContactEmail,0,6);
        primaryPane.add(txtSupContactEmail,1,6);
        primaryPane.add(btnAddSupplier,1,7);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Supplier");
        primaryStage.show();
        
        lastSupID = mainReference.SupplierID().getSupplierID();
        idDigits = Integer.parseInt(lastSupID.substring(3));
        idDigits++;
        newSupID = "sup" + idDigits;
        System.out.println(newSupID);
        
        btnAddSupplier.setOnAction(e -> {
            SupData.add(new Supplier(newSupID,txtSupplier.getText(),txtSupAddress.getText()
                    ,txtSupStartDate.getValue().toString(),txtSupContactName.getText(),txtSupContactPhone.getText()
                    ,txtSupContactEmail.getText()));
            System.out.println(SupData.get(0).getStartDate());
            mainReference.AddSupplier(SupData.get(0));
            
            String day = String.valueOf(txtSupStartDate.getValue().getDayOfMonth()); //Day in int
            String month = txtSupStartDate.getValue().getMonth().toString();
            String year = String.valueOf(txtSupStartDate.getValue().getYear());
            String date = day + "/" + month + "/" + year;
            
            String sql = "INSERT INTO JAVAUSER.SUPPLIER (SUPPLIERID,NAME,PHONE,EMAIL,ADDRESS,CONTACTNAME,STARTDATE) VALUES ('";
            sql += newSupID + "', '";
            sql += txtSupplier.getText() + "', '";
            sql += txtSupContactPhone.getText() + "', '";
            sql += txtSupContactEmail.getText() + "', '";
            sql += txtSupAddress.getText() + "', '";
            sql += txtSupContactName.getText() + "', '";
            sql += date + "')";
            
            mainReference.sendDBCommand(sql);
            
            primaryStage.close();
        });
        
        
        
        
    }
    
}
