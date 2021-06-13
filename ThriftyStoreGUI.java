  
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;
import project.UpdateGUI;

/**
 *
 * @author Diallo
 */
public class ThriftyStoreGUI extends Application{
    
// This is a tester
// Another push test

    // TabPane and its Tabs
    TabPane tbPane = new TabPane();
    Tab tabinv = new Tab("Inventory");
    Tab tabemp = new Tab("Employees");
    Tab tabsup = new Tab("Suppliers");
    Tab tabexp = new Tab("Expenses");
    Tab tabsale = new Tab("Sales");
    Tab tabpay = new Tab("Payroll");
    Tab tabpos = new Tab("POS");
    
    // GridPanes for each Tab
    GridPane overallPane = new GridPane();
    GridPane invPane = new GridPane();
    GridPane empPane = new GridPane();
    GridPane supPane = new GridPane();
    GridPane expPane = new GridPane();
    GridPane salPane = new GridPane();
    GridPane payPane = new GridPane();
    GridPane posPane = new GridPane();
    
    //Controls for InvPane
    Label lblinvsearchtxt = new Label("Search");
    TextField txtinvsearch = new TextField();
    Button btninvadd = new Button("Add Product");
    Button btninvdel = new Button("Delete Product");
    Button btninvedit = new Button("Edit Product");
    TableView<String> InvTable;
    ObservableList<String> InvData;
    
    //Controls for EmpPane
    Label lblempsearchtxt = new Label("Store");
    ComboBox cboxempstore = new ComboBox();
    Button btnempadd = new Button("Add New Employee");
    Button btnempdel = new Button("Delete Employee");
    Button btnempedit = new Button("Edit Employee");
    ArrayList<Employee> EmpData = new ArrayList<>();
    TableView<Employee> EmpTable;
    ObservableList<Employee> EmpTableData;
    
    //Controls for SupPane
    Label lblsupsearchtxt = new Label("Search");
    TextField txtsupsearch = new TextField();
    Button btnsupadd = new Button("Add Supplier");
    Button btnsupedit = new Button("Edit Supplier");
    Button btnsupdel = new Button("Delete Supplier");
    Button btnsupvs = new Button("View Shipments");
    Button btnsupvp = new Button("View Products");
    Button btnsupsrch = new Button("Search");
    TableView<String> SupTable;
    ObservableList<String> SupData;
    
    //Controls for ExpPane
    ComboBox cboxexpmonth = new ComboBox();
    ComboBox cboxexpyr = new ComboBox();
    Button btnexpdate = new Button("Change Date");
    ComboBox cboxexpstore = new ComboBox();
    Button btnexpstore = new Button("Change Store");
    Button btnexpadd = new Button("Add Expense");
    TableView<String> ExpTable;
    ObservableList<String> ExpData;
    
    //Controls for SalPane
    ComboBox cboxsalday = new ComboBox();
    ComboBox cboxsalmonth = new ComboBox();
    ComboBox cboxsalyr = new ComboBox();
    Button btnsaldate = new Button("Change Date");
    Label lblsalmonth = new Label("Month");
    Label lblsalday = new Label("Day");
    Label lblsalyr = new Label("Year");
    TableView<String> SalTable;
    ObservableList<String> SalData;
    Button btnsalYPER = new Button("View Yearly Profit Expense Report");
    Button btnsalYTDPER = new Button("View Year To Date Profit Expense Report");
    Button btnsalPOSR = new Button("View POS Sales Report");
    
    //Controls for PayrollPane
    Label lblpaysearch = new Label("Search Employee");
    Label lblpaystore = new Label("Store:");
    Label lblpaydate = new Label("Date:");
    ComboBox cboxpaystore = new ComboBox();
    ComboBox cboxpayday = new ComboBox();
    ComboBox cboxpaymonth = new ComboBox();
    ComboBox cboxpayyr = new ComboBox();
    Button btnpayemp = new Button("Search Employee");
    Button btnpayedit = new Button("Edit Payroll Item");
    Button btnpaycreate = new Button("Create Payroll Item");
    Button btnpayER = new Button("View Employee Report");
    TableView<String> PayrollTable;
    ObservableList<String> PayrollData;
    
    //Controls for POSPane
    Label lblPOSclub = new Label("Is Customer a club member");
    ComboBox cboxPOSclub = new ComboBox();
    Button btnPOSemp = new Button("Search Employee");
    Label lblPOSEID = new Label("Employee ID");
    TextField txtPOSEID = new TextField();
    Label lblPOSTOT = new Label("Total Price");
    TextField txtPOSTOT = new TextField();
    Label lblPOSSAV = new Label("Total Saved");
    TextField txtPOSSAV = new TextField();
    Button btnPOSprod = new Button("Add Product");
    ComboBox cboxPOSprod = new ComboBox();
    Button btnCheckout = new Button("Checkout");
    Button btnPrintReceipt = new Button("Print Receipt");
    TableView<String> POSTable;
    ObservableList<String> POSData;
    
    //Creating the Menu Bar
    MenuBar mnuBar = new MenuBar();
    
    //Setting up Login Pane portions
    GridPane LoginPane = new GridPane();
    Label lblloginusr = new Label("Username ");
    Label lblloginpass = new Label("Password ");
    TextField txtloginusr = new TextField();
    TextField txtloginpass = new TextField();
    Button btnlogin = new Button("Login");
    
    //Employee Lists for checking who has logged in
    ArrayList<Employee> WhoLoggedIn = new ArrayList<>();
    String UserNameTyped;
    String PasswordTyped;
    String UserStatus;
    Employee CurrentUser;
    Boolean validLogin;
    
    //Database connection variables
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    
    @Override
    public void start(Stage primaryStage) {
        
        validLogin = false;

        //Creating the Scene
        Scene primaryScene = new Scene(overallPane,800,800);

        Scene loginScene = new Scene(LoginPane, 800, 800);
        LoginPane.setAlignment(Pos.CENTER);
        LoginPane.add(lblloginusr, 0, 0);
        LoginPane.add(txtloginusr, 0, 1);
        LoginPane.add(lblloginpass, 1, 0);
        LoginPane.add(txtloginpass, 1, 1);
        LoginPane.add(btnlogin, 2, 1);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Thrifty Store");
        primaryStage.show();

        btnlogin.setOnAction(e -> { 

            //Filling in employee test list
            testEmpLogin();

            //Pulling typed in username and password from textboxes
            UserNameTyped = txtloginusr.getText();
            PasswordTyped = txtloginpass.getText();

            //Looping through Employee list to identify user
            for (Employee y : WhoLoggedIn) {
                    if(y.getUsername().equals(UserNameTyped)) {
                        UserStatus = y.getEmployeeType();
                        CurrentUser = y;
                    }
                }




            // Adding the Tabs to the TabPane
            if(UserStatus.equals("executive")) {
                if(CurrentUser.password.equals(PasswordTyped)) {

                    // Setting the alignment for our "overall" pane
                    // and adding the MenuBar and TabPane to it.
                    overallPane.setAlignment(Pos.TOP_CENTER);
                    overallPane.add(mnuBar, 0, 0);
                    overallPane.add(tbPane, 0, 1);

                    // Set the width and Height of the TabPane
                    tbPane.setMinWidth(primaryScene.getWidth());
                    tbPane.setMinHeight(primaryScene.getHeight());

                    validLogin = true;
                    tbPane.getTabs().add(tabinv);
                    tbPane.getTabs().add(tabemp);
                    tbPane.getTabs().add(tabsup);
                    tbPane.getTabs().add(tabexp);
                    tbPane.getTabs().add(tabsale);
                    tbPane.getTabs().add(tabpay);
                    tbPane.getTabs().add(tabpos);
                } else {
                    validLogin = false;
                    System.out.println("Invalid Log In");
                }
            } else if(UserStatus.equals("manager")) {
                if(CurrentUser.password.equals(PasswordTyped)) {
                    // Setting the alignment for our "overall" pane
                    // and adding the MenuBar and TabPane to it.
                    overallPane.setAlignment(Pos.TOP_CENTER);
                    overallPane.add(mnuBar, 0, 0);
                    overallPane.add(tbPane, 0, 1);

                    // Set the width and Height of the TabPane
                    tbPane.setMinWidth(primaryScene.getWidth());
                    tbPane.setMinHeight(primaryScene.getHeight());

                    validLogin = true;
                    tbPane.getTabs().add(tabinv);
                    tbPane.getTabs().add(tabemp);
                    tbPane.getTabs().add(tabsup);
                    tbPane.getTabs().add(tabexp);
                    tbPane.getTabs().add(tabsale);
                    tbPane.getTabs().add(tabpay);
                    tbPane.getTabs().add(tabpos);
                } else {
                    validLogin = false;
                    System.out.println("Invalid Log In");
                }
            } else if (UserStatus.equals("cashier")) {
                if(CurrentUser.password.equals(PasswordTyped)) {
                    // Setting the alignment for our "overall" pane
                    // and adding the MenuBar and TabPane to it.
                    overallPane.setAlignment(Pos.TOP_CENTER);
                    overallPane.add(mnuBar, 0, 0);
                    overallPane.add(tbPane, 0, 1);

                    // Set the width and Height of the TabPane
                    tbPane.setMinWidth(primaryScene.getWidth());
                    tbPane.setMinHeight(primaryScene.getHeight());

                    validLogin = true;
                    tbPane.getTabs().add(tabpay);
                    tbPane.getTabs().add(tabpos);
                } else {
                    validLogin = false;
                    System.out.println("Invalid Log In");
                }
            } else {
                validLogin = false;
                System.out.println("Invalid Log In");
            }


            if(validLogin = true)
            {
                // To not allow the tab to be closed
                tabinv.setClosable(false);
                tabemp.setClosable(false);
                tabsup.setClosable(false);
                tabexp.setClosable(false);
                tabsale.setClosable(false);
                tabpay.setClosable(false);
                tabpos.setClosable(false);

                // Adding our GridPanes to each tab.
                tabinv.setContent(invPane);
                tabemp.setContent(empPane);
                tabsup.setContent(supPane);
                tabexp.setContent(expPane);
                tabsale.setContent(salPane);
                tabpay.setContent(payPane);
                tabpos.setContent(posPane);

                // Setting Alignment for our panes
                invPane.setAlignment(Pos.CENTER);
                empPane.setAlignment(Pos.CENTER);
                supPane.setAlignment(Pos.CENTER);
                expPane.setAlignment(Pos.CENTER);
                salPane.setAlignment(Pos.CENTER);
                payPane.setAlignment(Pos.CENTER);
                posPane.setAlignment(Pos.CENTER);

                // Adding controls to InvPane
                invPane.add(lblinvsearchtxt, 0, 0);
                invPane.add(txtinvsearch, 1, 0);
                invPane.add(btninvadd, 6, 0);
                invPane.add(btninvdel, 8, 0);
                invPane.add(btninvedit, 10, 0);

                //Adding Inventory Table
                InvTable = new TableView<>();
                InvTable.setItems(InvData);
                TableColumn tblcinvprod = new TableColumn("Product");
                TableColumn tblcinvpic = new TableColumn("Picture");
                TableColumn tblcinvprice = new TableColumn("Price");
                TableColumn tblcinvqty = new TableColumn("Quantity");
                TableColumn tblcinvstat = new TableColumn("Status");
                TableColumn tblcinvexp = new TableColumn("Exp. Date");
                TableColumn tblcinvdep = new TableColumn("Department");
                TableColumn tblcinvstore = new TableColumn("Store");
                //InvTable.setMinWidth(primaryScene.getWidth());
                InvTable.getColumns().addAll(tblcinvprod, tblcinvpic, tblcinvprice, tblcinvqty, tblcinvstat,
                    tblcinvexp, tblcinvdep, tblcinvstore);
                invPane.add(InvTable, 0, 1, 10, 1);

                // Adding controls to EmpPane
                empPane.add(lblempsearchtxt, 0, 0);
                empPane.add(cboxempstore, 1, 0);
                empPane.add(btnempadd, 1, 1);
                empPane.add(btnempedit, 2, 1);
                empPane.add(btnempdel, 3, 1);

                //Adding Employee Table
                /*EmpTable = new TableView<>();
                EmpTable.setItems(EmpData);*/
                
            EmpTable = new TableView<Employee>();
            EmpTableData = FXCollections.observableArrayList(EmpData);
            EmpTable.setItems(EmpTableData);
            //send query to oracle database to retrieve employee info
            sendDBCommand("select * from Employee");
            try{
            while (dbResults.next()) {
            EmpData.add(new Employee(dbResults.getString(1), dbResults.getString(3), Double.valueOf(dbResults.getString(7)), dbResults.getString(4), dbResults.getString(6), dbResults.getString(5), dbResults.getString(11), dbResults.getString(8), dbResults.getString(9)));
            }
            }
            catch (SQLException ex) {
            Logger.getLogger(UpdateGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (Employee emp: EmpData) {
                EmpTableData.add(emp);
            }
                TableColumn tblcempeid = new TableColumn("Employee ID");
                TableColumn tblcempname = new TableColumn("Name");
                TableColumn tblcempphone = new TableColumn("Phone");
                TableColumn tblcempaddy = new TableColumn("Address");
                TableColumn tblcempsal = new TableColumn("Salary");
                TableColumn tblcemptype = new TableColumn("Type");
                TableColumn tblcempstore = new TableColumn("Store");
                TableColumn tblcempdepartment = new TableColumn("Department");
                //EmpTable.setMinWidth(primaryScene.getWidth());
                
            tblcempeid.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeID"));
            tblcempname.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeName"));
            tblcempphone.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeePhone"));
            tblcempaddy.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeAddress"));
            tblcempsal.setCellValueFactory(new PropertyValueFactory<Employee, Double>("employeeSalary"));
            tblcemptype.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeType"));
            //tblcempstore.setCellValueFactory(new PropertyValueFactory<Employee, String>("emptype"));
            //tblcempdepartment.setCellValueFactory(new PropertyValueFactory<Employee, String>(""));
                EmpTable.getColumns().addAll(tblcempeid, tblcempname, tblcempphone, tblcempaddy, tblcempsal,
                    tblcemptype, tblcempstore, tblcempdepartment);
                empPane.add(EmpTable, 0, 2, 10, 1);


                // Adding controls to SupPane
                supPane.add(txtsupsearch, 0, 0);
                supPane.add(btnsupsrch, 1, 0);
                supPane.add(btnsupadd, 0, 1);
                supPane.add(btnsupedit, 1, 1);
                supPane.add(btnsupdel, 2, 1);
                supPane.add(btnsupvs, 0, 2);
                supPane.add(btnsupvp, 1, 2);

                //Adding Supplier Table
                SupTable = new TableView<>();
                SupTable.setItems(SupData);
                TableColumn tblcsupsup = new TableColumn("Supplier");
                TableColumn tblcsupprod = new TableColumn("Products");
                TableColumn tblcsupaddy = new TableColumn("Address");
                TableColumn tblcsupcname = new TableColumn("Contact Name");
                TableColumn tblcsupphone = new TableColumn("Phone Number");
                TableColumn tblcsupemail = new TableColumn("Email");
                //SupTable.setMinWidth(primaryScene.getWidth());
                SupTable.getColumns().addAll(tblcsupsup, tblcsupprod, tblcsupaddy, tblcsupcname, tblcsupphone,
                    tblcsupemail);
                supPane.add(SupTable, 0, 3, 10, 1);

                // Adding controls to ExpPane
                expPane.add(cboxexpmonth, 0, 0);
                expPane.add(cboxexpyr, 1, 0);
                expPane.add(btnexpdate, 6, 0);
                expPane.add(cboxexpstore, 7, 0);
                expPane.add(btnexpstore, 8, 0);
                expPane.add(btnexpadd, 9, 0);

                //Adding Expense Table
                ExpTable = new TableView<>();
                ExpTable.setItems(SupData);
                TableColumn tblcexpexp = new TableColumn("Expenses");
                TableColumn tblcexpam = new TableColumn("Expense Amount");
                TableColumn tblcexpdue = new TableColumn("Due Date");
                TableColumn tblcexpsname = new TableColumn("Store Name");
                //SupTable.setMinWidth(primaryScene.getWidth());
                ExpTable.getColumns().addAll(tblcexpexp, tblcexpam, tblcexpdue, tblcexpsname);
                expPane.add(ExpTable, 0, 1, 10, 1);

                // Adding controls to SalPane
                salPane.add(lblsalyr, 2, 0);
                salPane.add(cboxsalyr, 3, 0);
                salPane.add(lblsalmonth, 4, 0);
                salPane.add(cboxsalmonth, 5, 0);
                salPane.add(lblsalday, 6, 0);
                salPane.add(cboxsalday, 7, 0);
                salPane.add(btnsaldate, 8, 0);

                //Adding Salary Table
                SalTable = new TableView<>();
                SalTable.setItems(SalData);
                TableColumn tblcsalsale = new TableColumn("Sales");
                TableColumn tblcsalsm = new TableColumn("Sales Amount");
                TableColumn tblcsalstore = new TableColumn("Store");
                //SupTable.setMinWidth(primaryScene.getWidth());
                SalTable.getColumns().addAll(tblcsalsale, tblcsalsm, tblcsalstore);
                salPane.add(SalTable, 0, 1, 10, 1);
                salPane.add(btnsalYPER, 0, 2);
                salPane.add(btnsalPOSR, 1, 2);

                // Adding controls to PayPane
                payPane.add(btnpayemp, 0, 0);
                payPane.add(lblpaystore, 0, 1);
                payPane.add(cboxpaystore, 1, 1);
                payPane.add(lblpaydate, 0, 2);
                payPane.add(cboxpayday, 1, 2);
                payPane.add(cboxpaymonth, 2, 2);
                payPane.add(cboxpayyr, 3, 2);
                payPane.add(btnpayedit, 1, 3);
                payPane.add(btnpaycreate, 2, 3);
                payPane.add(btnpayER, 3, 3);

                //Adding Payroll Table
                PayrollTable = new TableView<>();
                PayrollTable.setItems(PayrollData);
                TableColumn tblcpayEID = new TableColumn("Employee ID");
                TableColumn tblcpayname = new TableColumn("Name");
                TableColumn tblcpaycit = new TableColumn("Clock-in Time");
                TableColumn tblcpaycot = new TableColumn("Clock-out Time");
                TableColumn tblcpayhw = new TableColumn("Hours Worked");
                PayrollTable.getColumns().addAll(tblcpayEID, tblcpayname, tblcpaycit, tblcpaycot, tblcpayhw);
                //SupTable.setMinWidth(primaryScene.getWidth());
                payPane.add(PayrollTable, 0, 4, 10, 1);

                // Adding controls to POS Pane
                posPane.add(lblPOSclub, 0, 0);
                posPane.add(cboxPOSclub, 1, 0);
                posPane.add(btnPOSemp, 0, 1);
                posPane.add(btnPOSprod, 1, 1);

                //Adding POS Table
                POSTable = new TableView<>();
                POSTable.setItems(POSData);
                TableColumn tblcposprod = new TableColumn("Product");
                TableColumn tblcposprice = new TableColumn("Price");
                POSTable.getColumns().addAll(tblcposprod, tblcposprice);
                //SupTable.setMinWidth(primaryScene.getWidth());
                posPane.add(POSTable, 0, 3, 10, 1);

                //final POS Pane controls
                posPane.add(lblPOSEID, 0, 2);
                posPane.add(txtPOSEID, 1, 2);
                posPane.add(lblPOSTOT, 0, 4);
                posPane.add(txtPOSTOT, 1, 4);
                posPane.add(lblPOSSAV, 0, 5);
                posPane.add(txtPOSSAV, 1, 5);
                posPane.add(btnCheckout, 0, 6);
                posPane.add(btnPrintReceipt, 1, 6);


                primaryStage.setScene(primaryScene);
                primaryStage.setTitle("Thrifty Store");
                primaryStage.show();

            } else {

                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Thrifty Store");
                primaryStage.show();
            }
        });

        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void sendDBCommand(String sqlQuery)
    {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "moneyuser"; // Change to YOUR Oracle username
        String userPASS = "moneypass"; // Change to YOUR Oracle password
        OracleDataSource ds;
        
        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);
        
        // Lets try to connect
        try
        {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID,userPASS);
            // When we get results
            //  -TYPE_SCROLL_SENSITIVE means if the database data changes we
            //   will see our resultset update in real time.
            //  -CONCUR_READ_ONLY means that we cannot accidentally change the
            //   data in our database by using the .update____() methods of
            //   the ResultSet class - TableView controls are impacted by
            //   this setting as well.
            commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // We send the query to the DB. A ResultSet object is instantiated
            //  and we are returned a reference to it, that we point to from
            // dbResults.
            // Because we declared dbResults at the datafield level
            // we can see the results from anywhere in our Class.
            dbResults = commStmt.executeQuery(sqlQuery); // Sends the Query to the DB
            // The results are stored in a ResultSet structure object
            // pointed to by the reference variable dbResults
            // Because we declared this variable globally above, we can use
            // the results in any method in the class.
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    
    public void testEmpLogin() {
        
        Employee emp1 = new Employee("1a", "Rick", 100.00,  "Grace st.", "a@aol.com", "999-999-9999", "cashier", "Rick1", "123");
        Employee emp2 = new Employee("3b", "Jim", 100.00,  "Grace st.", "a@aol.com", "999-999-9999", "executive", "Jim2", "123");
        Employee emp3 = new Employee("4b", "James", 100.00,  "Grace st.", "a@aol.com", "999-999-9999", "manager", "James", "123");
        
        WhoLoggedIn.add(emp1);
        WhoLoggedIn.add(emp2);
        WhoLoggedIn.add(emp3);
    }
    
}
