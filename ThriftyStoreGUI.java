/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty;


import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Diallo
 */
public class ThriftyStoreGUI extends Application {
    AddEmployee mainReference;
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

    // GridPane to edit a product in Inventory
    // Also textfields and buttons for the edit a product window
    GridPane editProductPane = new GridPane();
    TextField editProductName = new TextField();
    TextField editProductUnitCost = new TextField();
    TextField editProductSalesPrice = new TextField();
    TextField editQuantityStock = new TextField();
    TextField editProductStatus = new TextField();
    TextField editProductExpDate = new TextField();
    TextField editProductStoreLocation = new TextField();
    TextField editProductDepartment = new TextField();
    Button editSaveBut = new Button("Save Changes->");
    Scene ItemScene = new Scene(editProductPane, 800, 800);

    // Gridpane to add a product in Inventory
    // Also textfields and buttons for the add a product window
    GridPane addProductPane = new GridPane();
    TextField addProductName = new TextField();
    TextField addProductUnitCost = new TextField();
    TextField addProductSalesPrice = new TextField();
    TextField addQuantityStock = new TextField();
    TextField addProductStatus = new TextField();
    TextField addProductExpDate = new TextField();
    TextField addProductStoreLocation = new TextField();
    TextField addProductDepartment = new TextField();
    Button addSaveBut = new Button("Save Changes->");
    Scene AddInvScene = new Scene(addProductPane, 800, 800);
    Stage AddItemStage = new Stage();

    //Controls for InvPane
    Label lblinvsearchtxt = new Label("Search");
    TextField txtinvsearch = new TextField();
    Button btninvadd = new Button("Add Product");
    Button btninvdel = new Button("Delete Product");
    Button btninvedit = new Button("Edit Product");
    ArrayList<Inventory> InvData = new ArrayList<>();
    TableView<Inventory> InvTable;
    ObservableList<Inventory> InvTableData;
    Stage ItemStage = new Stage();

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
    ArrayList<Supplier> SupData = new ArrayList<>();
    TableView<Supplier> SupTable;
    ObservableList<Supplier> SupTableData;

    //Controls for ExpPane
    Label lblexpmonth = new Label("Month");
    Label lblexpyear = new Label("Year");
    ComboBox cboxexpmonth = new ComboBox();
    ComboBox cboxexpyr = new ComboBox();
    Button btnexpdate = new Button("View Date");
    ComboBox cboxexpstore = new ComboBox();
    Label lblexpstore = new Label("Store");
    Button btnexpstore = new Button("View Store");
    Button btnexpadd = new Button("Add Expense");
    ArrayList<Expense> ExpData = new ArrayList<>();
    TableView<Expense> ExpTable;
    ObservableList<Expense> ExpTableData;
    ArrayList<String> ExpStoreData = new ArrayList<>();

    //Controls for SalPane
    ComboBox cboxsalday = new ComboBox();
    ComboBox cboxsalmonth = new ComboBox();
    ComboBox cboxsalyr = new ComboBox();
    Button btnsaldate = new Button("View Date");
    Label lblsalmonth = new Label("Month");
    Label lblsalday = new Label("Day");
    Label lblsalyr = new Label("Year");
    Button btnsalYPER = new Button("View Yearly Profit Expense Report");
    Button btnsalYTDPER = new Button("View Year To Date Profit Expense Report");
    Button btnsalPOSR = new Button("View POS Sales Report");
    ArrayList<Receipt> SalData = new ArrayList<>();
    TableView<Receipt> SalTable;
    ObservableList<Receipt> SalTableData;
    

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
    Button btnPOScust = new Button("Search Club Member");
    Label lblPOSEID = new Label("Club Member ID");
    TextField txtPOSEID = new TextField();
    Label lblPOSTOT = new Label("Total Price");
    TextField txtPOSTOT = new TextField();
    Label lblPOSSAV = new Label("Total Saved");
    TextField txtPOSSAV = new TextField();
    Button btnPOSprod = new Button("Add Product");
    ComboBox cboxPOSprod = new ComboBox();
    Button btnPOSdelprod = new Button("Delete Product");
    Button btnCheckout = new Button("Checkout");
    Button btnPrintReceipt = new Button("Print Receipt");
    ArrayList<Inventory> POSDataArr = new ArrayList<>();
    ArrayList<ClubMember> POSCustArr = new ArrayList<>();
    TableView<Inventory> POSTable;
    ObservableList<Inventory> POSData;
    Stage POSaddprodStage = new Stage();
    Stage POSsrchcustStage = new Stage();
    
    //Controls for POSAddProdPane
    GridPane AddPOSProdPane = new GridPane();
    TextField txtPOSaddprod = new TextField();
    Button btnPOSaddprodsrch = new Button("Search Product");
    Button btnPOSaddprod = new Button("Add Product");
    ListView POSaddprodlst = new ListView();
    ObservableList<Inventory> POSInvProducts;
    Scene POSProdScene = new Scene(AddPOSProdPane, 800, 800);
    
    //Controls POSSrchCustPane
    GridPane POSSrchCustPane = new GridPane();
    TextField txtPOSCustSrch = new TextField();
    Button butPOSCustSrch =  new Button("Search Club Members");
    Button butPOSCustSel =  new Button("Select Club Member");
    ListView lstPOScust = new ListView();
    ObservableList<ClubMember> POSCustData;
    Scene POSCustScene = new Scene(POSSrchCustPane, 800, 800);

    //Creating the Menu Bar
    MenuBar mnuBar = new MenuBar();

    //Setting up Login Pane portions
    GridPane LoginPane = new GridPane();
    ImageView loginImage = new ImageView("images\\ThriftyShopper.png");
    ImageView loginImage2 = new ImageView("images\\thriftyman.jpg");
    Label lblloginusr = new Label("Username ");
    Label lblloginpass = new Label("Password ");
    TextField txtloginusr = new TextField();
    TextField txtloginpass = new TextField();
    Button btnlogin = new Button("Login");

    String UserNameTyped;
    String PasswordTyped;
    String UserStatus;
    Employee CurrentUser;
    Boolean validLogin;
    
    //combobox variables
    String[] months = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String[] years = {"None", "2019", "2020", "2021"};
    String[] days = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    ObservableList monthItems = FXCollections.observableArrayList(months);
    ObservableList yearItems = FXCollections.observableArrayList(years);
    ObservableList dayItems = FXCollections.observableArrayList(days);
    String strday = "";
    String strmonth = "";
    String stryear = "";
    String strstore = "";
    
    //Database connection variables
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;

    @Override
    public void start(Stage primaryStage) {

        validLogin = false;
        txtloginusr.setPrefWidth(50);
        //txtloginusr.setMaxWidth(50);
        txtloginpass.setPrefWidth(50);
        //txtloginpass.setMaxWidth(50);
        loginImage.setFitHeight(200);
        loginImage.setFitWidth(300);
        loginImage2.setFitHeight(200);
        loginImage2.setFitWidth(300);

        //Creating the Scene
        Scene primaryScene = new Scene(overallPane, 800, 800);

        Scene loginScene = new Scene(LoginPane, 800, 800);
        LoginPane.setAlignment(Pos.CENTER);
        LoginPane.add(loginImage, 0, 0);
        LoginPane.add(loginImage2, 1, 0);
        LoginPane.add(lblloginusr, 0, 1);
        LoginPane.add(txtloginusr, 0, 2);
        LoginPane.add(lblloginpass, 1, 1);
        LoginPane.add(txtloginpass, 1, 2);
        LoginPane.add(btnlogin, 2, 2);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Thrifty Store");
        primaryStage.show();

        btnlogin.setOnAction(e -> {

            sendDBCommand("select * from Employee");
            try {
                while (dbResults.next()) {
                    EmpData.add(new Employee(dbResults.getString(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4), dbResults.getString(5), dbResults.getString(6), Double.valueOf(dbResults.getString(10)), dbResults.getString(8), dbResults.getString(9), dbResults.getString(11)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Pulling typed in username and password from textboxes
            UserNameTyped = txtloginusr.getText();
            PasswordTyped = txtloginpass.getText();

            //Looping through Employee list to identify user
            for (Employee y : EmpData) {
                if (y.getUsername().equals(UserNameTyped) && y.getPassword().equals(PasswordTyped)) {
                    UserStatus = y.getEmployeeType();
                    CurrentUser = y;
                }
            }

            if (UserStatus == null && CurrentUser == null) {

                Alert invalid = new Alert(Alert.AlertType.INFORMATION, "Username and Password dont exist", ButtonType.OK);
                invalid.show();
                txtloginusr.clear();
                txtloginpass.clear();

            }

            // Adding the Tabs to the TabPane
            if (UserStatus.equals("executive")) {
                if (CurrentUser.password.equals(PasswordTyped)) {

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
            } else if (UserStatus.equals("manager")) {
                if (CurrentUser.password.equals(PasswordTyped)) {
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
                if (CurrentUser.password.equals(PasswordTyped)) {
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

            if (validLogin = true) {
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
                InvTable = new TableView<Inventory>();
                InvTableData = FXCollections.observableArrayList(InvData);
                InvTable.setItems(InvTableData);
                sendDBCommand("select Product.*, InventoryItem.*, Department.*from Product join InventoryItem on Product.productID = InventoryItem.productID join Department on InventoryItem.departmentID = Department.departmentID");
                try {
                    while (dbResults.next()) {
                        String imgName = "images\\" + dbResults.getString(2) + ".jpg";
                        ImageView invImg = new ImageView(new Image(imgName));
                        invImg.setFitHeight(50);
                        invImg.setFitWidth(50);
                        InvData.add(new Inventory(dbResults.getString(1), dbResults.getString(2), invImg, dbResults.getString(7), dbResults.getString(12), Integer.valueOf(dbResults.getString(8)), dbResults.getString(9), dbResults.getString(10), Double.valueOf(dbResults.getString(5)), Double.valueOf(dbResults.getString(4))));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Inventory i : InvData) {
                    InvTableData.add(i);
                }

                TableColumn tblcinvprod = new TableColumn("Product");
                TableColumn tblcinvpic = new TableColumn("Picture");
                TableColumn tblcucost = new TableColumn("Unit Cost");
                TableColumn tblcinvprice = new TableColumn("Sales Price");
                TableColumn tblcinvqty = new TableColumn("Quantity");
                TableColumn tblcinvstat = new TableColumn("Status");
                TableColumn tblcinvexp = new TableColumn("Expiration Date");
                TableColumn tblcinvstr = new TableColumn("Store");
                TableColumn tblcinvdep = new TableColumn("Department");

                //TableColumn tblcinvstore = new TableColumn("Store");
                //InvTable.setMinWidth(primaryScene.getWidth());
                tblcinvprod.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
                tblcinvpic.setCellValueFactory(new PropertyValueFactory<Inventory, ImageView>("image"));
                tblcinvdep.setCellValueFactory(new PropertyValueFactory<Inventory, String>("deptID"));
                tblcinvqty.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("QIS"));
                tblcinvstr.setCellValueFactory(new PropertyValueFactory<Inventory, String>("storeID"));
                tblcinvstat.setCellValueFactory(new PropertyValueFactory<Inventory, String>("status"));
                tblcinvexp.setCellValueFactory(new PropertyValueFactory<Inventory, String>("expDate"));
                tblcucost.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("unitCost"));
                tblcinvprice.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("salesPrice"));

                //tblcinvpic.setPrefWidth(60);
                InvTable.getColumns().addAll(tblcinvprod, tblcinvpic, tblcucost, tblcinvprice, tblcinvqty, tblcinvstat, tblcinvexp,
                        tblcinvstr, tblcinvdep);
                invPane.add(InvTable, 0, 1, 10, 1);

                // setting up gridpane for edit inventory window
                editProductPane.setAlignment(Pos.CENTER);
                addProductPane.setAlignment(Pos.CENTER);

                Label[] editI = {new Label("Product Name:"), new Label("Unit Cost:"),
                    new Label("Sales Price:"), new Label("QIS:"),
                    new Label("Status:"), new Label("Exp Date:"), new Label("Store:"), new Label("Department:")};

                for (int i = 0; i < 8; i++) {
                    addProductPane.add(editI[i], 0, i);
                }

                addProductPane.add(addProductName, 1, 0);
                addProductPane.add(addProductUnitCost, 1, 1);
                addProductPane.add(addProductSalesPrice, 1, 2);
                addProductPane.add(addQuantityStock, 1, 3);
                addProductPane.add(addProductStatus, 1, 4);
                addProductPane.add(addProductExpDate, 1, 5);
                addProductPane.add(addProductStoreLocation, 1, 6);
                addProductPane.add(addProductDepartment, 1, 7);
                addProductPane.add(addSaveBut, 2, 7);

                for (int i = 0; i < 8; i++) {
                    editProductPane.add(editI[i], 0, i);
                }

                editProductPane.add(editProductName, 1, 0);
                editProductPane.add(editProductUnitCost, 1, 1);
                editProductPane.add(editProductSalesPrice, 1, 2);
                editProductPane.add(editQuantityStock, 1, 3);
                editProductPane.add(editProductStatus, 1, 4);
                editProductPane.add(editProductExpDate, 1, 5);
                editProductPane.add(editProductStoreLocation, 1, 6);
                editProductPane.add(editProductDepartment, 1, 7);
                editProductPane.add(editSaveBut, 2, 7);

                // event handlers for the inventory pane
                btninvadd.setOnAction(eB -> {
                    try {

                        Inventory addInv = new Inventory();

                        AddInventory(AddItemStage, addInv);
                        // create new statement to execute the update query once a user enters new data
                        Statement queryUpdate = dbConn.createStatement();

                    } catch (SQLException ex) {
                        Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                btninvdel.setOnAction(eB -> {

                    Inventory deleteInv;
                    deleteInv = InvTable.getSelectionModel().getSelectedItem();

                    InvTable.getItems().clear();

                    InvData.remove(deleteInv);
                    String byeByeP = deleteInv.getProductID();
                    sendDBCommand("DELETE FROM InventoryItem WHERE productID = " + "'" + byeByeP + "'");
                    InvTable.getItems().clear();
                    for (Inventory td : InvData) {
                        InvTable.getItems().add(td);
                    }

                });
                btninvedit.setOnAction(eB -> {

                    Inventory editInv = new Inventory();
                    for (Inventory td : InvData) {
                        if (td.equals(InvTable.getSelectionModel().getSelectedItem())) {
                            editInv = td;
                        }
                        
                    }
                    EditInventory(ItemStage, editInv);

                });

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
                //sendDBCommand("select * from Employee");
                //try {
                //    while (dbResults.next()) {
                //        EmpData.add(new Employee(dbResults.getString(1), dbResults.getString(3), Double.valueOf(dbResults.getString(7)), dbResults.getString(4), dbResults.getString(6), dbResults.getString(5), dbResults.getString(11), dbResults.getString(8), dbResults.getString(9)));
                //    }
                //} catch (SQLException ex) {
                //    Logger.getLogger(UpdateGUI.class.getName()).log(Level.SEVERE, null, ex);
                //}

                /*for (Employee emp : EmpData) {
                    EmpTableData.add(emp);
                }*/
                TableColumn tblcempeid = new TableColumn("Employee ID");
                TableColumn tblcempname = new TableColumn("Name");
                TableColumn tblcempphone = new TableColumn("Phone");
                TableColumn tblcempaddy = new TableColumn("Address");
                TableColumn tblcempsal = new TableColumn("HourlyPay");
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
                tblcempstore.setCellValueFactory(new PropertyValueFactory<Employee, String>("storeID"));
                //tblcempdepartment.setCellValueFactory(new PropertyValueFactory<Employee, String>(""));
                EmpTable.getColumns().addAll(tblcempeid, tblcempname, tblcempphone, tblcempaddy, tblcempsal,
                        tblcemptype, tblcempstore);
                empPane.add(EmpTable, 0, 2, 10, 1);

                btnempadd.setOnAction(eB -> {
                    AddEmployee tempWindow = new AddEmployee(this);
                    
                });

                btnempdel.setOnAction(eB -> {

                    Employee deleteEmp;
                    deleteEmp = EmpTable.getSelectionModel().getSelectedItem();

                    EmpTable.getItems().clear();

                    EmpData.remove(deleteEmp);
                    String byeByeE = deleteEmp.getEmployeeID();
                    sendDBCommand("DELETE FROM STOREEMPLOYEE WHERE employeeID = " + "'" + byeByeE + "'");
                    sendDBCommand("DELETE FROM EMPLOYEEWORKLOG WHERE employeeID = " + "'" + byeByeE + "'");
                    sendDBCommand("DELETE FROM EMPLOYEEASSIGNMENT WHERE employeeID = " + "'" + byeByeE + "'");
                    sendDBCommand("DELETE FROM EMPLOYEE WHERE employeeID = " + "'" + byeByeE + "'");
                    EmpTable.getItems().clear();
                    for (Employee td : EmpData) {
                        EmpTable.getItems().add(td);
                    }

                });
                btnempedit.setOnAction(eB -> {

                    Employee editEmp = new Employee();
                    for (Employee td : EmpData) {
                        if (td.equals(EmpTable.getSelectionModel().getSelectedItem())) {
                            editEmp = td;
                        }
                    }
                    // EditInventory(ItemStage, editEmp);

                });

                // Adding controls to SupPane
                supPane.add(txtsupsearch, 0, 0);
                supPane.add(btnsupsrch, 1, 0);
                supPane.add(btnsupadd, 0, 1);
                supPane.add(btnsupedit, 1, 1);
                supPane.add(btnsupdel, 2, 1);
                supPane.add(btnsupvs, 0, 2);
                supPane.add(btnsupvp, 1, 2);

                //Adding Supplier Table
                SupTable = new TableView<Supplier>();
                SupTableData = FXCollections.observableArrayList(SupData);
                SupTable.setItems(SupTableData);

                sendDBCommand("select * from Supplier");
                try {
                    while (dbResults.next()) {
                        SupData.add(new Supplier(dbResults.getString(1), dbResults.getString(2), dbResults.getString(5), dbResults.getString(7), dbResults.getString(6), dbResults.getString(3), dbResults.getString(4)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Supplier s : SupData) {
                    SupTableData.add(s);
                }

                TableColumn tblcsupsup = new TableColumn("Supplier");
                //TableColumn tblcsupprod = new TableColumn("Products");
                TableColumn tblcsupaddy = new TableColumn("Address");
                TableColumn tblcsupcname = new TableColumn("Contact Name");
                TableColumn tblcsupphone = new TableColumn("Phone Number");
                TableColumn tblcsupemail = new TableColumn("Email");
                //SupTable.setMinWidth(primaryScene.getWidth());

                tblcsupsup.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierName"));
                //tblcsupprod.setCellValueFactory(new PropertyValueFactory<Supplier, String>("employeeName"));
                tblcsupaddy.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierAddress"));
                tblcsupcname.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierName"));
                tblcsupphone.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactPhone"));
                tblcsupemail.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactEmail"));

                //Button btnsupadd = new Button("Add Supplier");
                //Button btnsupedit = new Button("Edit Supplier");
                //Button btnsupdel = new Button("Delete Supplier");
                //Button btnsupvs = new Button("View Shipments");
                //Button btnsupvp = new Button("View Products");
                //Button btnsupsrch = new Button("Search");
                
                // Event handler to add a supplier to the table
                btnsupadd.setOnAction(eB -> {
                    AddSupplier tempWindow = new AddSupplier(this);
                });

                // Event handler to delete a supplier selected
                btnsupdel.setOnAction(eB -> {

                    Supplier deleteSup;
                    deleteSup = SupTable.getSelectionModel().getSelectedItem();

                    SupTable.getItems().clear();

                    SupData.remove(deleteSup);
                    String byeByeS = deleteSup.getSupplierID();
                    sendDBCommand("DELETE FROM Supplier WHERE supplierID = " + "'" + byeByeS + "'");
                    SupTable.getItems().clear();
                    for (Supplier td : SupData) {
                        SupTable.getItems().add(td);
                    }

                });
                
                // Event handler to edit suppliers information selected
                btnsupedit.setOnAction(eB -> {

                    //Employee editEmp = new Employee();
                    //for (Employee td : EmpData) {
                    //    if (td.toString().equals(EmpTable.getSelectionModel().getSelectedItem())) {
                    //        editEmp = td;
                    //    }
                    //}
                    // EditInventory(ItemStage, editEmp);

                });

                // Event handler to see incoming shipments
                btnsupvs.setOnAction(eB -> {

                });

                // Event handler to see what products certain suppliers carry/distribute
                btnsupvp.setOnAction(eB -> {

                

                });
                
                // Event handler to search for specified supplier
                btnsupsrch.setOnAction(eB -> {

                 
                });

                SupTable.getColumns().addAll(tblcsupsup, tblcsupaddy, tblcsupcname, tblcsupphone,
                        tblcsupemail);
                supPane.add(SupTable, 0, 3, 10, 1);

                // Adding controls to ExpPane
                expPane.add(lblexpmonth, 0, 0);
                expPane.add(lblexpyear, 1, 0);
                expPane.add(cboxexpmonth, 0, 1);
                expPane.add(cboxexpyr, 1, 1);
                expPane.add(btnexpdate, 6, 1);
                expPane.add(lblexpstore, 7, 0);
                expPane.add(cboxexpstore, 7, 1);
                expPane.add(btnexpstore, 8, 1);
                expPane.add(btnexpadd, 9, 1);

                //Adding Expense Table
                sendDBCommand("select * from Expensebill");
                try {
                    while (dbResults.next()) {
                        ExpData.add(new Expense(dbResults.getString(1), dbResults.getString(2), dbResults.getString(3), Double.valueOf(dbResults.getString(4)), dbResults.getString(5).substring(0, 10)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                ExpStoreData.add("None");
                //Query for store IDs
                sendDBCommand("select *from Store");
                try {
                    while (dbResults.next()) {
                        ExpStoreData.add(dbResults.getString(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ObservableList storeItems = FXCollections.observableArrayList(ExpStoreData);
                cboxexpstore.setItems(storeItems);
                cboxexpstore.setValue("None");
                //Adding Expense Table
                ExpTable = new TableView<>();
                ExpTableData = FXCollections.observableList(ExpData);
                ExpTable.setItems(ExpTableData);
                

                //ExpTable.setItems(SupTableData);
                TableColumn tblcexpid = new TableColumn("Bill ID");
                TableColumn tblcexpname = new TableColumn("Expenses");
                TableColumn tblcexpamt = new TableColumn("Expense Amount");
                TableColumn tblcexpdue = new TableColumn("Due Date");
                TableColumn tblcexpstore = new TableColumn("Store");
                //SupTable.setMinWidth(primaryScene.getWidth());

                tblcexpid.setCellValueFactory(new PropertyValueFactory<Expense, String>("billID"));
                tblcexpname.setCellValueFactory(new PropertyValueFactory<Expense, String>("expenseType"));
                tblcexpamt.setCellValueFactory(new PropertyValueFactory<Expense, Double>("expenseTotal"));
                tblcexpdue.setCellValueFactory(new PropertyValueFactory<Expense, String>("dueDate"));
                tblcexpstore.setCellValueFactory(new PropertyValueFactory<Expense, String>("storeID"));
                
                //String[] months = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                //ObservableList monthItems = FXCollections.observableArrayList(months);
                cboxexpmonth.getItems().addAll(monthItems);
                cboxexpmonth.setValue("None");
                //String[] years = {"None", "2019", "2020", "2021"};
                //ObservableList yearItems = FXCollections.observableArrayList(years);
                cboxexpyr.getItems().addAll(yearItems);
                cboxexpyr.setValue("None");
                
                btnexpdate.setOnAction(eE -> {
                    strmonth = String.valueOf(cboxexpmonth.getValue());
                    
                    
                    stryear = String.valueOf(cboxexpyr.getValue());
                    
                    strstore = String.valueOf(cboxexpstore.getValue());
                    System.out.println(strday);
                    System.out.println(strmonth);
                    System.out.println(stryear);
                    ExpTableData.removeAll();
                    ArrayList<Expense> dateData = new ArrayList<>();
                    for (Expense exp : ExpData) {
                        if (exp.dueDate.substring(0, 7).equals(stryear+"-"+strmonth) && strstore.equals("None")) {
                            dateData.add(exp);
                            
                        }                       
                        else if (exp.dueDate.substring(0, 4).contains(stryear) && strmonth.equals("None") && strstore.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strstore.equals("None")) {
                            dateData.add(exp);
                        }
                    }
                    ExpTableData = FXCollections.observableList(dateData);
                    ExpTable.setItems(ExpTableData);
                });               

                ExpTable.getColumns().addAll(tblcexpid, tblcexpname, tblcexpamt, tblcexpdue, tblcexpstore);
                expPane.add(ExpTable, 0, 2, 10, 1);

                // Event handler to add a supplier to the table
                btnexpadd.setOnAction(eB -> {
                    AddExpenses tempWindow = new AddExpenses(this);
                }); 
                
                // Adding controls to SalPane
                salPane.add(lblsalyr, 2, 0);
                salPane.add(cboxsalyr, 3, 0);
                salPane.add(lblsalmonth, 4, 0);
                salPane.add(cboxsalmonth, 5, 0);
                salPane.add(lblsalday, 6, 0);
                salPane.add(cboxsalday, 7, 0);
                salPane.add(btnsaldate, 8, 0);

                sendDBCommand("select * from PurchaseOrder");
                try {
                    while (dbResults.next()) {
                        SalData.add(new Receipt(dbResults.getString(1), dbResults.getString(2), dbResults.getString(4), dbResults.getString(3), Double.valueOf(dbResults.getString(5)), Double.valueOf(dbResults.getString(6)), dbResults.getString(7).substring(0,10)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Adding Sales Table
                SalTable = new TableView<>();
                SalTableData = FXCollections.observableList(SalData);
                SalTable.setItems(SalTableData);

                TableColumn tblcsalrec = new TableColumn("Receipt");
                TableColumn tblcsalamt = new TableColumn("Sales Amount");
                TableColumn tblcsalstore = new TableColumn("Store");
                TableColumn tblcsaldate = new TableColumn("Date");
                
                tblcsalrec.setCellValueFactory(new PropertyValueFactory<Receipt, String>("receiptID"));
                tblcsalamt.setCellValueFactory(new PropertyValueFactory<Receipt, Double>("finalTotal"));
                tblcsalstore.setCellValueFactory(new PropertyValueFactory<Receipt, String>("storeID"));
                tblcsaldate.setCellValueFactory(new PropertyValueFactory<Receipt, String>("date"));
                
                //String[] days = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
                
                cboxsalday.getItems().addAll(dayItems);
                cboxsalday.setValue("None");
                //String[] months = {"None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                
                cboxsalmonth.getItems().addAll(monthItems);
                cboxsalmonth.setValue("None");
                //String[] years = {"None", "2019", "2020", "2021"};
                
                cboxsalyr.getItems().addAll(yearItems);
                cboxsalyr.setValue("None");
                btnsaldate.setOnAction(eD -> {
                    
                    strday = String.valueOf(cboxsalday.getValue());
                    
                    //day = String.valueOf(cboxsalday.getValue());
                    
                    strmonth = String.valueOf(cboxsalmonth.getValue());
                    
                    
                    stryear = String.valueOf(cboxsalyr.getValue());
                    
                    System.out.println(strday);
                    System.out.println(strmonth);
                    System.out.println(stryear);
                    SalTableData.removeAll();
                    ArrayList<Receipt> dateData = new ArrayList<>();
                    for (Receipt r : SalData) {
                        if (r.date.equals(stryear+"-"+strmonth+"-"+strday)) {
                            dateData.add(r);
                            
                        }
                        else if (r.date.substring(0, 7).equals(stryear+"-"+strmonth) && strday.equals("None")) {
                            dateData.add(r);
                            
                        }                       
                        else if (r.date.substring(0, 4).contains(stryear) && strmonth.equals("None") && strday.equals("None")) {
                            dateData.add(r);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strday.equals("None")) {
                            dateData.add(r);
                        }
                    }
                    SalTableData = FXCollections.observableList(dateData);
                    SalTable.setItems(SalTableData);
                });
                //SupTable.setMinWidth(primaryScene.getWidth());
                SalTable.getColumns().addAll(tblcsalrec, tblcsalamt, tblcsalstore, tblcsaldate);
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
                posPane.add(btnPOScust, 0, 1);
                posPane.add(btnPOSprod, 1, 1);
                posPane.add(btnPOSdelprod, 2, 1);
                
                //Setting up POS Pane Combobox
                cboxPOSclub.getItems().add("yes");
                cboxPOSclub.getItems().add("no");

                //Adding POS Table
                POSTable = new TableView<>();
                POSData = FXCollections.observableArrayList(InvData);
                //POSTable.setItems(POSData);
                TableColumn tblcposprod = new TableColumn("Product");
                TableColumn tblcposprice = new TableColumn("Price");
                TableColumn tblcposclubprice = new TableColumn("Club Price");
                
                tblcposprod.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
                tblcposprice.setCellValueFactory(new PropertyValueFactory<Inventory, String>("salesPrice"));
                tblcposclubprice.setCellValueFactory(new PropertyValueFactory<Inventory, String>("clubPrice"));
                
                POSTable.getColumns().addAll(tblcposprod, tblcposprice, tblcposclubprice);
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
                
                // setting up gridpane alignment for POS add product pane
                AddPOSProdPane.setAlignment(Pos.CENTER);
                
                //POS add prod pane controls
                AddPOSProdPane.add(txtPOSaddprod, 0, 0);
                AddPOSProdPane.add(btnPOSaddprodsrch, 1, 0);
                AddPOSProdPane.add(btnPOSaddprod, 2, 0);
                AddPOSProdPane.add(POSaddprodlst, 0, 1, 4, 1);
                
                //Filling the product listview
                POSInvProducts = FXCollections.observableArrayList(InvData);
                POSaddprodlst.setItems(POSInvProducts);
                
                // setting up gridpane alignment for POS search customer pane
                POSSrchCustPane.setAlignment(Pos.CENTER);
                
                //POS cust pane controls
                POSSrchCustPane.add(txtPOSCustSrch, 0, 0);
                POSSrchCustPane.add(butPOSCustSrch, 1, 0);
                POSSrchCustPane.add(butPOSCustSel, 2, 0);
                POSSrchCustPane.add(lstPOScust, 0, 1, 3, 1);
                
                //Filling the Customer Arraylist
                sendDBCommand("select * from ClubMember");
                try {
                    while (dbResults.next()){
                        POSCustArr.add(new ClubMember(dbResults.getString(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4), dbResults.getString(5), 
                            dbResults.getString(6), Double.valueOf(dbResults.getString(7))));
                                }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Filling the customer listview
                POSCustData = FXCollections.observableArrayList(POSCustArr);
                lstPOScust.setItems(POSCustData);       
                        
                //Event handlers for POS pane
                btnPOSprod.setOnAction(eB -> {
                    
                    System.out.println("yuh its here");
                    AddPOSProd(POSaddprodStage);
                    
                });
                btnPOScust.setOnAction (eB -> {
                    
                    SrchPOSCust(POSsrchcustStage);
                    
                });

                primaryStage.setScene(primaryScene);
                primaryStage.setTitle("Thrifty Store");
                primaryStage.show();

            } else {

                primaryStage.setScene(loginScene);
                primaryStage.setTitle("Thrifty Store");
                primaryStage.show();
            }
        }
        );

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void sendDBCommand(String sqlQuery) {
        // Set up your connection strings
        // IF YOU ARE IN CIS330 NOW: use YOUR Oracle Username/Password
        String URL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser"; // Change to YOUR Oracle username
        String userPASS = "javapass"; // Change to YOUR Oracle password
        OracleDataSource ds;

        // Clear Box Testing - Print each query to check SQL syntax
        //  sent to this method.
        // You can comment this line out when your program is finished
        System.out.println(sqlQuery);

        // Lets try to connect
        try {
            // instantiate a new data source object
            ds = new OracleDataSource();
            // Where is the database located? Web? Local?
            ds.setURL(URL);
            // Send the user/pass and get an open connection.
            dbConn = ds.getConnection(userID, userPASS);
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
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    

    public void AddInventory(Stage AddItemStage, Inventory addInv) {

        //Button addSaveBut = new Button("Save Changes->");
        AddItemStage.setScene(AddInvScene);
        AddItemStage.setTitle("Add Inventory Item");
        AddItemStage.show();

        addSaveBut.setOnAction(e -> {

            for (Inventory td : InvData) {
                if (td.equals(addInv)) {
                    td.setProductName(addProductName.getText());
                    td.setUnitCost(Double.parseDouble(addProductUnitCost.getText()));
                    td.setSalesPrice(Double.parseDouble(addProductSalesPrice.getText()));
                    td.setQIS(Integer.parseInt(addQuantityStock.getText()));
                    td.setStatus(Integer.parseInt(addProductStatus.getText()));
                    td.setExpDate(addProductExpDate.getText());
                    td.setStoreID(addProductStoreLocation.getText());
                    td.setDeptID(addProductDepartment.getText());
                }
            }

            InvTable.getItems().clear();
            for (Inventory td : InvData) {
                InvTable.getItems().add(td);
            }

        });

    }

    public void EditInventory(Stage ItemStage, Inventory editInv) {

        editProductName.setText(editInv.getProductName());
        editProductUnitCost.setText(String.valueOf(editInv.getUnitCost()));
        editProductSalesPrice.setText(String.valueOf(editInv.getSalesPrice()));
        editQuantityStock.setText(String.valueOf(editInv.getQIS()));
        editProductStatus.setText(editInv.getStatus());
        editProductExpDate.setText(editInv.getExpDate());
        editProductStoreLocation.setText(editInv.getStoreID());
        editProductDepartment.setText(editInv.getDeptID());

        //Button editSaveBut = new Button("Save Changes->");
        ItemStage.setScene(ItemScene);
        ItemStage.setTitle("Edit Inventory Item");
        ItemStage.show();

        editSaveBut.setOnAction(e -> {

            for (Inventory td : InvData) {
                if (td.equals(editInv)) {
                    td.setProductName(editProductName.getText());
                    td.setUnitCost(Double.parseDouble(editProductUnitCost.getText()));
                    td.setSalesPrice(Double.parseDouble(editProductSalesPrice.getText()));
                    td.setQIS(Integer.parseInt(editQuantityStock.getText()));
                    td.setStatus(Integer.parseInt(editProductStatus.getText()));
                    td.setExpDate(editProductExpDate.getText());
                    td.setStoreID(editProductStoreLocation.getText());
                    td.setDeptID(editProductDepartment.getText());
                }
            }

            InvTable.getItems().clear();
            for (Inventory td : InvData) {
                InvTable.getItems().add(td);
            }

        });

    }
    public Employee employeeID()
    {
        return EmpData.get(EmpData.size()-1);
    }
    
    public void AddEmployee(Employee e)
    {
        EmpTable.getItems().add(e);
        EmpData.add(e);
    }
    
    
    
    
    public void AddSupplier(Supplier e)
    {
        SupTable.getItems().add(e);
    }
    
        public void AddPOSProd(Stage POSaddprodStage) {
        
        System.out.println("yuh its here");
        
        POSaddprodStage.setScene(POSProdScene);
        POSaddprodStage.setTitle("Add a Product");
        POSaddprodStage.show();
        
        //event handlers
        btnPOSaddprod.setOnAction(eB -> {
            
            //POSData.add(POSaddprodlst.getSelectionModel().getSelectedItem());
            //System.out.print(POSaddprodlst.getSelectionModel().getSelectedItem());
            //Search Inventory 
            int count = 0;
            Inventory tempinv = (Inventory)POSaddprodlst.getSelectionModel().getSelectedItem();
            /**for (Inventory td : InvData) {
                if(td.equals(tempinv));
                {
                    System.out.println("This text:" + td.toString());
                    System.out.println("Is the same as: " + tempinv.toString());
                    POSDataArr.add(td);
                    count++;
                    System.out.println(count);
                }
            }**/
            POSDataArr.add(tempinv);
            
            System.out.println(POSDataArr.size());
            
            
            POSTable.getItems().clear();
            for (Inventory td : POSDataArr) { 
                POSTable.getItems().add(td);
            }
            
            //Determing transaction totals depending on club member statud
            double totsum = 0;
            double savsum = 0;
            if((!cboxPOSclub.getSelectionModel().isEmpty()) && cboxPOSclub.getSelectionModel().getSelectedItem().toString().equals("yes"))
            {
                for (Inventory td : POSDataArr) { 
                    totsum += td.getClubPrice();
                    savsum += (td.getSalesPrice() - td.getClubPrice());
                }
                txtPOSTOT.setText(String.valueOf(totsum));
                txtPOSSAV.setText(String.valueOf(savsum));
            } else {
                for (Inventory td : POSDataArr) { 
                    totsum += td.getSalesPrice();
                }
                txtPOSTOT.setText(String.valueOf(totsum));
                txtPOSSAV.setText(String.valueOf(savsum));
            }
        });
        
        cboxPOSclub.setOnAction(eB -> {
            
            //Determing transaction totals depending on club member statud
            double totsum = 0;
            double savsum = 0;
            if((!cboxPOSclub.getSelectionModel().isEmpty()) && cboxPOSclub.getSelectionModel().getSelectedItem().toString().equals("yes"))
            {
                for (Inventory td : POSDataArr) { 
                    totsum += td.getClubPrice();
                    savsum += (td.getSalesPrice() - td.getClubPrice());
                }
                txtPOSTOT.setText(String.valueOf(totsum));
                txtPOSSAV.setText(String.valueOf(savsum));
            } else {
                for (Inventory td : POSDataArr) { 
                    totsum += td.getSalesPrice();
                }
                txtPOSTOT.setText(String.valueOf(totsum));
                txtPOSSAV.setText(String.valueOf(savsum));
            }
        
        });
    }
    
    public void SrchPOSCust(Stage POSsrchcustStage) {
        
        POSsrchcustStage.setScene(POSCustScene);
        POSsrchcustStage.setTitle("Search for Product");
        POSsrchcustStage.show();
        
        lstPOScust.getItems().clear();
        for (ClubMember cm : POSCustArr) { 
            lstPOScust.getItems().add(cm);
        }
        
        butPOSCustSel.setOnAction(eB -> {
            
            ClubMember tempcm = (ClubMember)lstPOScust.getSelectionModel().getSelectedItem();
            txtPOSEID.setText(tempcm.getCustomerID());
            
        });
    }
}
