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


public class AddExpenses  {
    
    //Data Fields
    Label lblAdd = new Label("Add New Expense");
    ThriftyStoreGUI mainReference;
    GridPane primaryPane;
    Scene primaryScene;
    Stage primaryStage;
    Label lblExpID = new Label("ExpenseID: ");
    Label lblExpName = new Label("Expense Name: ");
    Label lblExpAmount = new Label("Expense Amount: ");
    Label lblExpDueDate = new Label("Due Date: ");
    Label lblExpStore = new Label("Store: ");
    TextField txtExpID = new TextField();
    TextField txtExpName = new TextField();
    TextField txtExpAmount = new TextField();
    TextField txtExpDueDate = new TextField();
    TextField txtExpStore = new TextField();
    Button btnAddExpense = new Button("Add ->");
    
    
    
    public AddExpenses(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        primaryPane.add(lblAdd,0,0);
        primaryPane.add(lblExpID,0,1);
        primaryPane.add(lblExpName,0,2);
        primaryPane.add(lblExpAmount,0,3);
        primaryPane.add(lblExpDueDate,0,4);
        primaryPane.add(lblExpStore,0,5);
        
        primaryPane.add(txtExpID,1,1);
        primaryPane.add(txtExpName,1,2);
        primaryPane.add(txtExpAmount,1,3);
        primaryPane.add(txtExpDueDate,1,4);
        primaryPane.add(txtExpStore,1,5);
        primaryPane.add(btnAddExpense,1,6);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Expense");
        primaryStage.show();
        
        btnAddExpense.setOnAction(e -> {
            
        });
        
        
        
        
    }
    
}
