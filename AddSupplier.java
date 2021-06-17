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
    TextField txtSupStartDate = new TextField();
    TextField txtSupContactName = new TextField();
    TextField txtSupContactPhone = new TextField();
    TextField txtSupContactEmail = new TextField();
    
    Button btnAddSupplier = new Button("Add ->");
    ArrayList<Supplier> SupData = new ArrayList<>();
    
    
    public AddSupplier(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        
        primaryPane.add(lblAdd,0,0);
        primaryPane.add(lblSupID,0,1);
        primaryPane.add(txtSupID,1,1);
        primaryPane.add(lblSupplier,0,2);
        primaryPane.add(txtSupplier,1,2);
        primaryPane.add(lblSupAddress,0,3);
        primaryPane.add(txtSupAddress,1,3);
        primaryPane.add(lblSupStartDate,0,4);
        primaryPane.add(txtSupStartDate,1,4);
        primaryPane.add(lblSupContactName,0,5);
        primaryPane.add(txtSupContactName,1,5);
        primaryPane.add(lblSupContactPhone,0,6);
        primaryPane.add(txtSupContactPhone,1,6);
        primaryPane.add(lblSupContactEmail,0,7);
        primaryPane.add(txtSupContactEmail,1,7);
        primaryPane.add(btnAddSupplier,1,8);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Supplier");
        primaryStage.show();
        
        btnAddSupplier.setOnAction(e -> {
            SupData.add(new Supplier(txtSupID.getText(),txtSupplier.getText(),txtSupAddress.getText()
                    ,txtSupStartDate.getText(),txtSupContactName.getText(),txtSupContactPhone.getText()
                    ,txtSupContactEmail.getText()));
            
            mainReference.AddSupplier(SupData.get(0));
        });
        
        
        
        
    }
    
}
