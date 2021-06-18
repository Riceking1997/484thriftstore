/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capstone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import oracle.jdbc.pool.OracleDataSource;


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
    Label lblEmpPeriodSalary = new Label("Hourly Pay: ");
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
    ComboBox txtEmpType = new ComboBox();
    ComboBox txtEmpStore = new ComboBox();
    TextField txtEmpUsername = new TextField();
    TextField txtEmpPassword = new TextField();
    Button btnAddEmployee = new Button("Add ->");
    TextField txtEmpEmail = new TextField();
    ArrayList<Employee> empData = new ArrayList<>();
    String lastEmpID;
    int idDigits;
    String newEmpID;
    
    
    public AddEmployee(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        txtEmpStore.getItems().addAll("sto22221","sto22222","sto22223","sto22224","sto22225","sto22226");
        txtEmpType.getItems().addAll("cashier","stocker","manager");
        primaryPane.add(lblAdd,0,0);
//        primaryPane.add(lblEmpID,0,1);
//        primaryPane.add(txtEmpID,1,1);
        primaryPane.add(lblEmpStore,0,1);
        primaryPane.add(txtEmpStore,1,1);
        primaryPane.add(lblEmpName,0,2);
        primaryPane.add(txtEmpName,1,2);
        primaryPane.add(lblEmpAddress,0,3);
        primaryPane.add(txtEmpAddress,1,3);
        primaryPane.add(lblEmpPhone,0,4);
        primaryPane.add(txtEmpPhone,1,4);
        primaryPane.add(lblEmpEmail, 0, 5);
        primaryPane.add(txtEmpEmail, 1, 5);
        primaryPane.add(lblEmpPeriodSalary,0,6);
        primaryPane.add(txtEmpPeriodSalary,1,6);
        primaryPane.add(lblEmpUsername,0,7);
        primaryPane.add(lblEmpPassword,0,8);
        primaryPane.add(txtEmpUsername,1,7);
        primaryPane.add(txtEmpPassword,1,8);
        primaryPane.add(lblEmpType,0,9);
        primaryPane.add(txtEmpType,1,9);
        primaryPane.add(btnAddEmployee,1,10);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Employee");
        primaryStage.show();
        lastEmpID = mainReference.employeeID().getEmployeeID();
        idDigits = Integer.parseInt(lastEmpID.substring(3));
        idDigits++;
        newEmpID = "emp" + idDigits;
        System.out.println(newEmpID);
        
        
        btnAddEmployee.setOnAction(e -> {
            empData.add(new Employee(newEmpID,txtEmpStore.getSelectionModel().getSelectedItem().toString(),txtEmpName.getText(),txtEmpAddress.getText(),
                                 txtEmpPhone.getText(), txtEmpEmail.getText(), Double.valueOf(txtEmpPeriodSalary.getText()), txtEmpUsername.getText(),
                                 txtEmpPassword.getText(), txtEmpType.getSelectionModel().getSelectedItem().toString()));
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

            //*******Add Success Notification*****
            
            
            String sql = "INSERT INTO JAVAUSER.EMPLOYEE (EMPLOYEEID,STOREID,NAME,ADDRESS,PHONE,EMAIL,PERIODSALARY,USERNAME,PASSWORD,HOURLYPAY,TYPE) VALUES ('";
            sql += newEmpID + "', '";
                    sql += txtEmpStore.getSelectionModel().getSelectedItem().toString() + "', '";
                    sql += txtEmpName.getText() + "', '"+ txtEmpAddress.getText()+ "', '"+ txtEmpPhone.getText();
                    sql += "', '"+ txtEmpEmail.getText()+ "', null, '" + txtEmpUsername.getText()+ "', '";
                    sql += txtEmpPassword.getText()+ "', '"+ txtEmpPeriodSalary.getText();
                    sql += "', '" + txtEmpType.getSelectionModel().getSelectedItem().toString() + "')";
            
//            mainReference.sendDBCommand("INSERT INTO EMPLOYEE  VALUES ('" + newEmpID + "', '"
//                    + txtEmpStore.getSelectionModel().getSelectedItem().toString() + "', '"
//                    + txtEmpName.getText() + "', '"+ txtEmpAddress.getText()+ "', '"+ txtEmpPhone.getText() 
//                    + "', '"+ txtEmpEmail.getText()+ "', null, '" + txtEmpUsername.getText()+ "', '"
//                    + txtEmpPassword.getText()+ "', '"+ Double.valueOf(txtEmpPeriodSalary.getText()) 
//                    + "', '" + txtEmpType.getSelectionModel().getSelectedItem().toString() + "')");
            
            System.out.println(sql);
            
            mainReference.sendDBCommand(sql);
            
            primaryStage.close();
            
            
        });
        
        
        
        
    }
    
//    public String getSql()
//    {
//        return sql;
//    }

    
}
