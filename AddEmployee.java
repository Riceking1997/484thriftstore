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


public class AddEmployee  {
    
    //Data Fields
    Label lblAdd = new Label("Add New Employee");
    ThriftyStoreGUI mainReference;
    GridPane primaryPane;
    Scene primaryScene;
    Stage primaryStage;
    Label lblEmpID = new Label("Employee ID: ");
    Label lblEmpName = new Label("Employee Name: ");
    Label lblEmpPhone = new Label("Phone Number: ");
    Label lblEmpAddress = new Label("Address: ");
    Label lblEmpPeriodSalary = new Label("Salary: ");
    Label lblEmpType = new Label("Type: ");
    Label lblEmpStore = new Label("Store: ");
    Label lblEmpUsername = new Label("Username: ");
    Label lblEmpPassword = new Label("Password: ");
    Label lblEmpEmail = new Label("Email: ");
    TextField txtEmpID = new TextField();
    TextField txtEmpName = new TextField();
    TextField txtEmpPhone = new TextField();
    TextField txtEmpAddress = new TextField();
    TextField txtEmpPeriodSalary = new TextField();
    TextField txtEmpType = new TextField();
    TextField txtEmpStore = new TextField();
    TextField txtEmpUsername = new TextField();
    TextField txtEmpPassword = new TextField();
    Button btnAddEmployee = new Button("Add ->");
    TextField txtEmpEmail = new TextField();
    ArrayList<Employee> empData = new ArrayList<>();
    
    
    public AddEmployee(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        
        primaryPane.add(lblAdd,0,0);
        primaryPane.add(lblEmpID,0,1);
        primaryPane.add(txtEmpID,1,1);
        primaryPane.add(lblEmpStore,0,2);
        primaryPane.add(txtEmpStore,1,2);
        primaryPane.add(lblEmpName,0,3);
        primaryPane.add(txtEmpName,1,3);
        primaryPane.add(lblEmpAddress,0,4);
        primaryPane.add(txtEmpAddress,1,4);
        primaryPane.add(lblEmpPhone,0,5);
        primaryPane.add(txtEmpPhone,1,5);
        primaryPane.add(lblEmpEmail, 0, 6);
        primaryPane.add(txtEmpEmail, 1, 6);
        primaryPane.add(lblEmpPeriodSalary,0,7);
        primaryPane.add(txtEmpPeriodSalary,1,7);
        primaryPane.add(lblEmpUsername,0,8);
        primaryPane.add(lblEmpPassword,0,9);
        primaryPane.add(txtEmpUsername,1,8);
        primaryPane.add(txtEmpPassword,1,9);
        primaryPane.add(lblEmpType,0,10);
        primaryPane.add(txtEmpType,1,10);
        primaryPane.add(btnAddEmployee,1,11);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Employee");
        primaryStage.show();
        
        
        
        
        
        btnAddEmployee.setOnAction(e -> {
            empData.add(new Employee(txtEmpID.getText(),txtEmpStore.getText(),txtEmpName.getText(),txtEmpAddress.getText(),
                                 txtEmpPhone.getText(), txtEmpEmail.getText(), Double.valueOf(txtEmpPeriodSalary.getText()), txtEmpUsername.getText(),
                                 txtEmpPassword.getText(), txtEmpType.getText()));
            mainReference.AddEmployee(empData.get(0));
//            txtEmpID.clear();
//            txtEmpStore.clear();
//            txtEmpName.clear();
//            txtEmpAddress.clear();
//            txtEmpPhone.clear();
//            txtEmpEmail.clear();
//            txtEmpPeriodSalary.clear();
//            txtEmpUsername.clear();
//            txtEmpPassword.clear();
//            txtEmpType.clear();

            //Add Notification
            
            primaryStage.close();
            
            
        });
        
        
        
        
    }
    
}
