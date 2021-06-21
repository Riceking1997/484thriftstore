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
    Label lblExpName = new Label("Expense Type: ");
    Label lblExpAmount = new Label("Expense Amount: ");
    Label lblExpDueDate = new Label("Due Date: ");
    Label lblExpStore = new Label("Store: ");
    TextField txtExpID = new TextField();
    TextField txtExpName = new TextField();
    TextField txtExpAmount = new TextField();
    DatePicker txtExpDueDate = new DatePicker();
    ComboBox txtExpStore = new ComboBox();
    Button btnAddExpense = new Button("Add ->");
    
    ArrayList<Expense> expData = new ArrayList<>();
    
    String lastExpID;
    int idDigits;
    String newExpID;
    
    public AddExpenses(ThriftyStoreGUI parentReference){
        mainReference = parentReference;
        primaryPane = new GridPane();
        
        txtExpStore.getItems().addAll("sto22221","sto22222","sto22223","sto22224","sto22225","sto22226");
        
        primaryPane.add(lblAdd,0,0);
        //primaryPane.add(lblExpID,0,1);
        primaryPane.add(lblExpName,0,1);
        primaryPane.add(lblExpAmount,0,2);
        primaryPane.add(lblExpDueDate,0,3);
        primaryPane.add(lblExpStore,0,4);
        
        //primaryPane.add(txtExpID,1,1);
        primaryPane.add(txtExpName,1,1);
        primaryPane.add(txtExpAmount,1,2);
        primaryPane.add(txtExpDueDate,1,3);
        primaryPane.add(txtExpStore,1,4);
        primaryPane.add(btnAddExpense,1,5);
        
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryScene = new Scene(primaryPane, 700, 500);
        primaryStage = new Stage();
        
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Add New Expense");
        primaryStage.show();
        
        lastExpID = mainReference.ExpenseID().getBillID();
        idDigits = Integer.parseInt(lastExpID.substring(4));
        idDigits++;
        newExpID = "bill" + idDigits;
        System.out.println(newExpID);
        
        
        
        btnAddExpense.setOnAction(e -> {
            expData.add(new Expense(newExpID, txtExpStore.getSelectionModel().getSelectedItem().toString()
                    ,txtExpName.getText(),Double.valueOf(txtExpAmount.getText()), txtExpDueDate.getValue().toString()));
            mainReference.AddExpense(expData.get(0));
            
            String day = String.valueOf(txtExpDueDate.getValue().getDayOfMonth()); //Day in int
            String month = txtExpDueDate.getValue().getMonth().toString();
            String year = String.valueOf(txtExpDueDate.getValue().getYear());
            
           
            
            System.out.println(day + "/" + month + "/" + year);
            

            String date = day + "/" + month + "/" + year;
            //System.out.println(txtExpDueDate.getValue().toString());
            //System.out.println(expData.get(0).getBillID());
            
            //String alterSession = "alter session set nls_date_format = 'mm/dd/yy'";
            String sql = "INSERT INTO JAVAUSER.EXPENSEBILL (BILLID,STOREID,EXPENSETYPE,EXPENSETOTAL,DUEDATE) VALUES ('";
            sql += newExpID + "', '";
            sql += txtExpStore.getSelectionModel().getSelectedItem().toString() + "', '";
            sql += txtExpName.getText() + "', '";
            sql += Double.valueOf(txtExpAmount.getText()) + "', '";
            sql += date + "')";
            
            
            //mainReference.sendDBCommand(alterSession);
            mainReference.sendDBCommand(sql);
            primaryStage.close();
        });
        
        
        
        
    }
    
}
