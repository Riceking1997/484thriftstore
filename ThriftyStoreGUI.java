/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.control.TextArea;
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
    Button editProductSaveBut = new Button("Save Changes->");
    Scene ItemScene = new Scene(editProductPane, 800, 800);
    
     // GridPane to edit an Employee on staff
    // Also textfields and buttons for the edit employee window
    GridPane editEmployeePane = new GridPane();
    TextField editEmployeeName = new TextField();
    TextField editEmployeePhone = new TextField();
    TextField editEmployeeAddress = new TextField();
    TextField editEmployeeHourlyPay = new TextField();
    TextField editEmployeeType = new TextField();
    TextField editEmployeeStore = new TextField();
    Button editEmployeeSaveBut = new Button("Save Changes->");
    Scene EmpScene = new Scene(editEmployeePane, 800, 800);
   
    // GridPane to edit a Suppliers Information
    // Also textfields and buttons for the edit supplier window
    GridPane editSupplierPane = new GridPane();
    GridPane viewSupplierPane = new GridPane();
    TextField editSupplierName = new TextField();
    TextField editSupplierAddress = new TextField();
    TextField editContactName = new TextField();
    TextField editContactNumber = new TextField();
    TextField editContactEmail = new TextField();
    Button editSupplierSaveBut = new Button("Save Changes->");
    Scene SupScene = new Scene(editSupplierPane, 800, 800);
    Scene ProScene = new Scene(viewSupplierPane, 800, 800);

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
    Button btnviewempstore = new Button("View by Store");
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
    ArrayList<Product> ProData = new ArrayList<>();
    TableView<Product> ProTable;
    ObservableList<Product> ProTableData;

    //Controls for ExpPane
    Label lblexpmonth = new Label("Month");
    Label lblexpyear = new Label("Year");
    ComboBox cboxexpmonth = new ComboBox();
    ComboBox cboxexpyr = new ComboBox();
    Button btnexpview = new Button("View Expenses");
    ComboBox cboxexpstore = new ComboBox();
    Label lblexpstore = new Label("Store");
    Button btnexpstore = new Button("View Store");
    Button btnexpadd = new Button("Add Expense");
    Label lblexptotal = new Label("Total Expenses: ");
    TextField txtexptotal = new TextField();
    double exptotal = 0;
    ArrayList<Expense> ExpData = new ArrayList<>();
    TableView<Expense> ExpTable;
    ObservableList<Expense> ExpTableData;
    ArrayList<String> ExpStoreData = new ArrayList<>();

    //Controls for SalPane
    Button btnGraph = new Button("Show Graph");
    Button btnProfReport = new Button("Print Profit Report");
    ComboBox cboxsalday = new ComboBox();
    ComboBox cboxsalmonth = new ComboBox();
    ComboBox cboxsalyr = new ComboBox();
    Button btnsaldate = new Button("View Date");
    Label lblsalmonth = new Label("Month");
    Label lblsalday = new Label("Day");
    Label lblsalyr = new Label("Year");
    Button btnsalYPER = new Button("View Yearly Profit Expense Report");
    //Button btnsalYTDPER = new Button("View Year To Date Profit Expense Report");
    Button btnsalPOSR = new Button("View POS Sales Report");
    Label lblsaltotal = new Label("Total Sales: ");
    TextField txtsaltotal = new TextField();
    double saltotal = 0;
    double fYearSales = 0;
    double sYearSales = 0;
    double tYearSales = 0;
    double ytdSales = 0;
    double fYearExp = 0;
    double sYearExp = 0;
    double tYearExp = 0;
    double ytdExp = 0;
    double fProfit;
    double sProfit;
    double tProfit;
    double ytdProfit;
    String salesReport;
    ArrayList<Receipt> SalData = new ArrayList<>();
    TableView<Receipt> SalTable;
    ObservableList<Receipt> SalTableData;
    ArrayList<ReceiptItem> prodData = new ArrayList<>();
    TableView<ReceiptItem> prodTable;
    ObservableList<ReceiptItem> prodTableData;
    Label lblYear = new Label("Year: ");
    ComboBox cbxYear = new ComboBox();
    Label lblMonth = new Label("Month: ");
    ComboBox cbxMonth = new ComboBox();
    Label lblDay = new Label("Day: ");
    ComboBox cbxDay = new ComboBox();
    Label lblStore = new Label("Store");
    ComboBox cbxStore = new ComboBox();
    Button btnviewprod = new Button("View Products");
    Label lblmostpur = new Label("Most Purchased: ");
    TextField txtmostpur = new TextField();
    Label lblquant = new Label("Quantity: ");
    TextField txtquant = new TextField();
    Label lblsalesamt = new Label("Sales Amount: ");
    TextField txtsalesamt = new TextField();
    Button btnprodGraph = new Button("Show Graph");
    Button btnprodReport = new Button("Print Report");
    

    //Controls for PayrollPane
    Label lblpaysearch = new Label("Search Employee");
    Label lblpaystore = new Label("Store:");
    Label lblpaydep = new Label("Department:");
    Label lblpaydate = new Label("Pay Period:");
    Label lblpayempsort = new Label("Selected Employee:");
    TextField txtpayempsort = new TextField();
    ComboBox cboxpaystore = new ComboBox();
    ComboBox cboxpaydep = new ComboBox();
    ComboBox cboxpayday = new ComboBox();
    ComboBox cboxpaymonth = new ComboBox();
    ComboBox cboxpayyr = new ComboBox();
    ComboBox cboxpp = new ComboBox();
    Button btnpayemp = new Button("Search Employee");
    Button btnpayclearemp = new Button("Clear Employee");
    Button btnpayedit = new Button("Edit Payroll Item");
    Button btnpaycreate = new Button("Create Payroll Item");
    Button btnupdatePR = new Button("Update");
    TableView<WorkLog> PayrollTable;
    ObservableList<WorkLog> PayrollData;
    ArrayList<WorkLog> PayData = new ArrayList<>();
    Stage PayESStage = new Stage();
    Stage PayCPStage = new Stage();
    Stage PayEPStage = new Stage();
    
    //Controls for the search employees Pane
    GridPane PayESPane = new GridPane();
    Label lblESstore = new Label("Store:");
    Label lblESdep = new Label("Department:");
    ComboBox cboxESstore = new ComboBox();
    ComboBox cboxESdep = new ComboBox();
    Button btnESupdate = new Button("Update");
    ListView lstES = new ListView();
    ObservableList<Employee> lstESemp;
    Scene PayESScene = new Scene(PayESPane, 800, 800);
    
    //Controls for Edit Payroll Pane
    GridPane PayEPpane = new GridPane();
    Label lblEPeid = new Label("Employee ID:");
    Label lblEPwid = new Label("Worklog ID:");
    Label lblEPsid = new Label("Store ID:");
    Label lblEPdid = new Label("Department ID:");
    Label lblEPsd = new Label("Start Date:");
    Label lblEPed = new Label("End Date:");
    Label lblEPpr = new Label("Pay Rate:");
    Label lblEPhw = new Label("Hours Worked:");
    ComboBox cboxEPeid = new ComboBox();
    ComboBox cboxEPsid = new ComboBox();
    ComboBox cboxEPdid = new ComboBox();
    TextField txtEPsd = new TextField();
    TextField txtEPed = new TextField();
    TextField txtEPpr = new TextField();
    TextField txtEPhw = new TextField();
    Button btnEP = new Button("Update");
    Scene PayEPScene = new Scene(PayEPpane, 800, 800);
    
    //Controls for Create Payroll Pane
    GridPane PayCPpane = new GridPane();
    Label lblCPeid = new Label("Employee ID:");
    Label lblCPwid = new Label("Worklog ID:");
    Label lblCPsid = new Label("Store ID:");
    Label lblCPdid = new Label("Department ID:");
    Label lblCPsd = new Label("Start Date:");
    Label lblCPed = new Label("End Date:");
    Label lblCPpr = new Label("Pay Rate:");
    Label lblCPhw = new Label("Hours Worked:");
    ComboBox cboxCPeid = new ComboBox();
    ComboBox cboxCPsid = new ComboBox();
    ComboBox cboxCPdid = new ComboBox();
    TextField txtCPsd = new TextField();
    TextField txtCPed = new TextField();
    TextField txtCPpr = new TextField();
    TextField txtCPhw = new TextField();
    Button btnCP = new Button("Create");
    Scene PayCPScene = new Scene(PayCPpane, 800, 800);

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
    Button btnCancelOrder = new Button("Cancel Transaction");
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
    
    //For generating Receipt IDs and Customer IDs
    /** MAKE SURE TO FILL THIS ARRAY WITH CUSTOMERS FROM DB AT START **/
    ArrayList<Customer> poscust = new ArrayList<>();
    int RecIDCount = 10000;
    int CustIDCount = 10000;

    //For generating WorkLog IDs
    int WLIDCount = 600;
    boolean onetime = true;
    
    //To match employees with a department
    ArrayList<EmployeeAssignment> depass = new ArrayList<>();
    
    //To keep track of each pay period
    ArrayList<PayPeriod> pps = new ArrayList<>();
    
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
                PayESPane.setAlignment(Pos.CENTER);
                PayEPpane.setAlignment(Pos.CENTER);
                PayCPpane.setAlignment(Pos.CENTER);

                // Adding controls to InvPane
                invPane.add(lblinvsearchtxt, 0, 0);
                invPane.add(txtinvsearch, 1, 0);
                invPane.add(btninvadd, 6, 0);
                invPane.add(btninvdel, 8, 0);
                invPane.add(btninvedit, 9, 0);
                
                
                //Adding Inventory Table
                InvTable = new TableView<Inventory>();
                InvTableData = FXCollections.observableArrayList(InvData);
                InvTable.setItems(InvTableData);
                sendDBCommand("select Product.*, InventoryItem.*, Department.*from Product join InventoryItem on Product.productID = InventoryItem.productID join Department on InventoryItem.departmentID = Department.departmentID");
                try {
                    while (dbResults.next()) {
                        String imgName = "images\\" + dbResults.getString(3) + ".jpg";
                        ImageView invImg = new ImageView(new Image(imgName));
                        invImg.setFitHeight(50);
                        invImg.setFitWidth(50);
                        InvData.add(new Inventory(dbResults.getString(1), dbResults.getString(3), invImg, dbResults.getString(8), dbResults.getString(13), Integer.valueOf(dbResults.getString(9)), dbResults.getString(10), dbResults.getString(11), Double.valueOf(dbResults.getString(6)), Double.valueOf(dbResults.getString(5))));
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
                editEmployeePane.setAlignment(Pos.CENTER);
                editSupplierPane.setAlignment(Pos.CENTER);
                addProductPane.setAlignment(Pos.CENTER);

                Label[] editI = {new Label("Product Name:"), new Label("Unit Cost:"),
                    new Label("Sales Price:"), new Label("QIS:"),
                    new Label("Status:"), new Label("Exp Date:"), new Label("Store:"), new Label("Department:")};
                
                Label[] editE = {new Label("Employee Name:"), new Label("Employee Phone:"),
                    new Label("Employee Address:"), new Label("Hourly Pay:"),
                    new Label("Employee Type:"), new Label("Store Location:")};
                
                Label[] editS = {new Label("Supplier Name:"), new Label("Supplier Address:"),
                    new Label("Contact Name:"), new Label("Contact Number:"),
                    new Label("Contact Email:")};


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
                editProductPane.add(editProductSaveBut, 2, 7);
                
                for (int i = 0; i < 6; i++) {
                    editEmployeePane.add(editE[i], 0, i);
                }

                editEmployeePane.add(editEmployeeName, 1, 0);
                editEmployeePane.add(editEmployeePhone, 1, 1);
                editEmployeePane.add(editEmployeeAddress, 1, 2);
                editEmployeePane.add(editEmployeeHourlyPay, 1, 3);
                editEmployeePane.add(editEmployeeType, 1, 4);
                editEmployeePane.add(editEmployeeStore, 1, 5);
                editEmployeePane.add(editEmployeeSaveBut, 2, 5);
                
                for (int i = 0; i < 5; i++) {
                    editSupplierPane.add(editS[i], 0, i);
                }

                editSupplierPane.add(editSupplierName, 1, 0);
                editSupplierPane.add(editSupplierAddress, 1, 1);
                editSupplierPane.add(editContactName, 1, 2);
                editSupplierPane.add(editContactNumber, 1, 3);
                editSupplierPane.add(editContactEmail, 1, 4);
                editSupplierPane.add(editSupplierSaveBut, 2, 4);

               

                // event handlers for the inventory pane
                btninvadd.setOnAction(eB -> {
                    AddInventory tempWindow = new AddInventory(this);
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
                
                cboxempstore.getItems().addAll(storeItems);
                cboxempstore.setValue("None");
                
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
                    EditEmployee(ItemStage, editEmp);

                });
                btnviewempstore.setOnAction(eS -> {
                    EmpTableData.clear();
                   for (Employee emp : EmpData) {
                       if (emp.storeID.equals(cboxempstore.getValue())){
                           EmpTableData.add(emp);
                       }
                       else if(cboxempstore.getValue().equals("None")){
                           EmpTableData.add(emp);
                       }
                   } 
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
                tblcsupcname.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactName"));
                tblcsupphone.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactPhone"));
                tblcsupemail.setCellValueFactory(new PropertyValueFactory<Supplier, String>("contactEmail"));

                 sendDBCommand("select * from Product");
                    try {
                        while (dbResults.next()) {
                            ProData.add(new Product(dbResults.getString(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4), Double.parseDouble(dbResults.getString(5)), Double.parseDouble(dbResults.getString(6))));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ProTable = new TableView<Product>();
                    ProTableData = FXCollections.observableArrayList(ProData);
                    ProTable.setItems(ProTableData);

                    for (Product p : ProData) {
                        ProTableData.add(p);
                    }
                
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
                    
                     Supplier editSup = new Supplier();
                     for (Supplier td : SupData) {
                        if (td.equals(SupTable.getSelectionModel().getSelectedItem())) {
                            editSup = td;
                        }
                    }
                    EditSupplier(ItemStage, editSup);

                });

                // Event handler to see incoming shipments
                btnsupvs.setOnAction(eB -> {

                });

                // Event handler to see what products certain suppliers carry/distribute
                btnsupvp.setOnAction(eB -> {

                    ViewProducts(ItemStage);

                });
                
                // Event handler to search for specified supplier
                btnsupsrch.setOnAction(eB -> {

                 
                });

                SupTable.getColumns().addAll(tblcsupsup, tblcsupaddy, tblcsupcname, tblcsupphone,
                        tblcsupemail);
                supPane.add(SupTable, 0, 3, 10, 1);

                

                // Adding controls to ExpPane
                expPane.add(lblexpmonth, 1, 0);
                expPane.add(lblexpyear, 0, 0);
                expPane.add(cboxexpmonth, 1, 1);
                expPane.add(cboxexpyr, 0, 1);
                expPane.add(btnexpview, 8, 1);
                expPane.add(lblexpstore, 7, 0);
                expPane.add(cboxexpstore, 7, 1);
                //expPane.add(btnexpstore, 8, 1);
                expPane.add(btnexpadd, 9, 1);
                expPane.add(lblexptotal, 0, 3);
                expPane.add(txtexptotal, 1, 3);

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
                sendDBCommand("select * from Store");
                try {
                    while (dbResults.next()) {
                        ExpStoreData.add(dbResults.getString(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //ObservableList storeItems = FXCollections.observableArrayList(ExpStoreData);
                cboxexpstore.setItems(storeItems);
                cboxexpstore.setValue("None");
                
                btnexpadd.setOnAction(eB -> {
                    AddExpenses tempWindow = new AddExpenses(this);
                }); 
                
                //Adding Expense Table
                ExpTable = new TableView<>();
                ExpTableData = FXCollections.observableList(ExpData);
                ExpTable.setItems(ExpTableData);
                
                for (Expense exp : ExpData) {
                    exptotal += exp.expenseTotal;
                }
                txtexptotal.setText(String.valueOf(exptotal));
                

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
                
                btnexpview.setOnAction(eE -> {
                    strmonth = String.valueOf(cboxexpmonth.getValue());
                    
                    
                    stryear = String.valueOf(cboxexpyr.getValue());
                    
                    strstore = String.valueOf(cboxexpstore.getValue());
                    
                    double viewtotal = 0;
                    System.out.println(strday);
                    System.out.println(strmonth);
                    System.out.println(stryear);
                    ExpTableData.removeAll();
                    ArrayList<Expense> dateData = new ArrayList<>();
                    for (Expense exp : ExpData) {
                        if (exp.dueDate.substring(0, 7).equals(stryear+"-"+strmonth) && exp.storeID.equals(strstore)) {
                            dateData.add(exp);
                        }
                        else if (exp.dueDate.substring(0, 7).equals(stryear+"-"+strmonth) && strstore.equals("None")) {
                            dateData.add(exp);
                            
                        }
                        else if (exp.storeID.equals(strstore) && stryear.equals("None") && strmonth.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (exp.dueDate.substring(0, 4).equals(stryear) && exp.storeID.equals(strstore) && strmonth.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (exp.dueDate.substring(0, 4).contains(stryear) && strmonth.equals("None") && strstore.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (exp.dueDate.substring(5, 7).equals(strmonth) && stryear.equals("None") && strstore.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (exp.dueDate.substring(5, 7).equals(strmonth) && exp.storeID.equals(strstore) && stryear.equals("None")) {
                            dateData.add(exp);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strstore.equals("None")) {
                            dateData.add(exp);
                        }
                    }
                    ExpTableData = FXCollections.observableList(dateData);
                    ExpTable.setItems(ExpTableData);
                    for (Expense exp : dateData) {
                        viewtotal += exp.expenseTotal;
                    }
                    txtexptotal.setText(String.valueOf(viewtotal));
                    
                });               

                ExpTable.getColumns().addAll(tblcexpid, tblcexpname, tblcexpamt, tblcexpdue, tblcexpstore);
                expPane.add(ExpTable, 0, 2, 10, 1);

                
                
                // Adding controls to SalPane
                salPane.add(lblsalyr, 2, 0);
                salPane.add(cboxsalyr, 3, 0);
                salPane.add(lblsalmonth, 4, 0);
                salPane.add(cboxsalmonth, 5, 0);
                salPane.add(lblsalday, 6, 0);
                salPane.add(cboxsalday, 7, 0);
                salPane.add(btnsaldate, 8, 0);
                salPane.add(lblsaltotal, 0, 3);
                salPane.add(txtsaltotal, 1, 3);

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
                
                for (Receipt r : SalData) {
                    saltotal += r.finalTotal;
                }
                txtsaltotal.setText(String.valueOf(saltotal));
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
                    
                    double viewtotal = 0;
                    
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
                        else if (r.date.substring(5, 10).equals(strmonth+"-"+strday) && stryear.equals("None")) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(5, 7).equals(strmonth) && stryear.equals("None") && strday.equals("None")) {
                            dateData.add(r);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strday.equals("None")) {
                            dateData.add(r);
                        }
                    }
                    SalTableData = FXCollections.observableList(dateData);
                    SalTable.setItems(SalTableData);
                    for (Receipt r : dateData) {
                        viewtotal += r.finalTotal;
                    }
                    txtsaltotal.setText(String.valueOf(viewtotal));
                });
                //SupTable.setMinWidth(primaryScene.getWidth());
                SalTable.getColumns().addAll(tblcsalrec, tblcsalamt, tblcsalstore, tblcsaldate);
                salPane.add(SalTable, 0, 1, 10, 1);
                salPane.add(btnsalYPER, 0, 4);
                salPane.add(btnsalPOSR, 1, 4);
                btnsalYPER.setOnAction(eP -> {
                    
                    LocalDate tempDate;
                    LocalDate currentDate = LocalDate.now();
                    System.out.println(currentDate);
                    LocalDate lastyrDate = currentDate.minusYears(1);
                    System.out.println(lastyrDate);
                    salesReport = "Total Revenue\n" + "---------------\n";
                    ArrayList<String> profitData = new ArrayList<>();
                    TextArea lstExpenses = new TextArea();
                    for (Receipt r : SalData) {
                        if (r.date.substring(0, 4).equals("2019")) {
                            fYearSales += r.finalTotal;
                        }
                        if (r.date.substring(0, 4).equals("2020")) {
                            sYearSales += r.finalTotal;
                        }
                        if (r.date.substring(0, 4).equals("2021")) {
                            tYearSales += r.finalTotal;
                        }
                    }
                    for (Receipt r : SalData) {
                        tempDate = LocalDate.parse(r.date);
                        if (tempDate.isAfter(lastyrDate)) {
                            ytdSales += r.finalTotal;
                        }                        
                    }
                    salesReport += "2019 : $" + fYearSales +"\n";
                    salesReport += "2020 : $" + sYearSales +"\n";
                    salesReport += "2021 : $" + tYearSales +"\n";
                    salesReport += " YTD : $" + ytdSales +"\n";
                    salesReport += "\n";
                    for (Expense exp : ExpData) {
                        if (exp.dueDate.substring(0, 4).equals("2019")){
                            fYearExp += exp.expenseTotal;
                        }
                        if (exp.dueDate.substring(0, 4).equals("2020")){
                            sYearExp += exp.expenseTotal;
                        }
                        if (exp.dueDate.substring(0, 4).equals("2021")){
                            tYearExp += exp.expenseTotal;
                        }
                    }
                    for (Expense exp : ExpData) {
                        tempDate = LocalDate.parse(exp.dueDate);
                        if (tempDate.isAfter(lastyrDate)) {
                            ytdExp += exp.expenseTotal;
                        }                        
                    }
                    salesReport += "Total Expenses\n" + "---------------\n";
                    salesReport += "2019 : $" + fYearExp +"\n";
                    salesReport += "2020 : $" + sYearExp +"\n";
                    salesReport += "2021 : $" + tYearExp +"\n";
                    salesReport += " YTD : $" + ytdExp +"\n";
                    salesReport += "\n";
                    fProfit = fYearSales - fYearExp;
                    sProfit = sYearSales - sYearExp;
                    tProfit = tYearSales - tYearExp;
                    ytdProfit = ytdSales - ytdExp;
                    String strFProfit = "";
                    String strSProfit = "";
                    String strTProfit = "";
                    String strYTDProfit = "";
                    if (fProfit < 0){
                        strFProfit += "("+String.valueOf(fProfit).substring(1)+")";
                    }
                    else {
                        strFProfit += String.valueOf(fProfit);
                    }
                    if (sProfit < 0){
                        strSProfit += "("+String.valueOf(sProfit).substring(1)+")";
                    }
                    else {
                        strSProfit += String.valueOf(sProfit);
                    }
                    if (tProfit < 0){
                        strTProfit += "("+String.valueOf(tProfit).substring(1)+")";
                    }
                    else {
                        strTProfit += String.valueOf(tProfit);
                    }
                    if (ytdProfit < 0){
                        strYTDProfit += "("+String.valueOf(ytdProfit).substring(1)+")";
                    }
                    else {
                        strYTDProfit += String.valueOf(ytdProfit);
                    }
                    salesReport += "Profit/(Loss)\n" + "---------------\n";
                    salesReport += "2019 : $" + strFProfit +"\n";
                    salesReport += "2020 : $" + strSProfit +"\n";
                    salesReport += "2021 : $" + strTProfit +"\n";
                    salesReport += " YTD : $" + strYTDProfit +"\n";
                    lstExpenses.setText(salesReport);
                    profitData.add(txtsaltotal.getText());
                    /*for(String s : profitData) {
                        lstExpenses.setText()
                    }*/
                    
                    
                    GridPane listPane = new GridPane();
                    
                    listPane.add(lstExpenses, 0, 0, 2, 1);
                    listPane.add(btnGraph, 0, 1);
                    listPane.add(btnProfReport, 1, 1);
                    listPane.setAlignment(Pos.CENTER);
                    
                    Scene listScene = new Scene(listPane, 700, 700);
                    Stage listStage = new Stage();
                    listStage.setScene(listScene);
                    listStage.show();
                });
                btnGraph.setOnAction(eG -> {
                    final CategoryAxis xAxis = new CategoryAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    final BarChart<String,Number> bc = 
                        new BarChart<String,Number>(xAxis,yAxis);
                    bc.setTitle("Profit/Expenses");
                    xAxis.setLabel("Dollar Amount");
                    yAxis.setLabel("Year");
                    XYChart.Series series1 = new XYChart.Series();
                    series1.setName("Sales");
                    XYChart.Series series2 = new XYChart.Series();
                    series2.setName("Expenses");
                    XYChart.Series series3 = new XYChart.Series();
                    series3.setName("Profit");
                    series1.getData().add(new XYChart.Data("2019", fYearSales));
                    series2.getData().add(new XYChart.Data("2019", fYearExp));
                    series3.getData().add(new XYChart.Data("2019", fProfit));
                    
                    series1.getData().add(new XYChart.Data("2020", sYearSales));
                    series2.getData().add(new XYChart.Data("2020", sYearExp));
                    series3.getData().add(new XYChart.Data("2020", sProfit));
                    
                    series1.getData().add(new XYChart.Data("2021", tYearSales));
                    series2.getData().add(new XYChart.Data("2021", tYearExp));
                    series3.getData().add(new XYChart.Data("2021", tProfit));
                    
                    series1.getData().add(new XYChart.Data("YTD", ytdSales));
                    series2.getData().add(new XYChart.Data("YTD", ytdExp));
                    series3.getData().add(new XYChart.Data("YTD", ytdProfit));
                    
                    Stage stage = new Stage(); 
                    stage.setTitle("Bar Chart");
                    stage.setScene(new Scene(bc, 500, 400));
                    bc.getData().addAll(series1, series2, series3);
                    stage.show();
                });
                btnProfReport.setOnAction(eR -> {
                    File file = new File("profit.txt");
                    try {
                        PrintWriter output = new PrintWriter(file);
                        
                        output.print(salesReport);
                        
                        output.close();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                        
                btnsalPOSR.setOnAction( eP -> {
                    ArrayList<ReceiptItem> dateData = new ArrayList<>();
                    
                    sendDBCommand("select PurchaseOrder.*, PurchaseOrderItem.* from PurchaseOrder join PurchaseOrderItem on PurchaseOrder.POID = PurchaseOrderItem.POID");
                    try {
                        while (dbResults.next()) {
                            prodData.add(new ReceiptItem(dbResults.getString(1), dbResults.getString(8), dbResults.getString(3), Double.valueOf(dbResults.getString(10)), Double.valueOf(dbResults.getString(11)), dbResults.getString(7).substring(0, 10)));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            
                    prodTable = new TableView<>();
                    prodTableData = FXCollections.observableList(prodData);
                    prodTable.setItems(prodTableData);
                    
                    TableColumn tblcprodID = new TableColumn("Product ID");
                    TableColumn tblcprodPO = new TableColumn("Receipt");
                    TableColumn tblcprodStr = new TableColumn("Store");
                    TableColumn tblcprodQ = new TableColumn("Quantity");
                    TableColumn tblcprodTtl = new TableColumn("Total Sales");
                    TableColumn tblcprodDate = new TableColumn("Date");
                    
                    tblcprodID.setCellValueFactory(new PropertyValueFactory<ReceiptItem, String>("productID"));
                    tblcprodPO.setCellValueFactory(new PropertyValueFactory<ReceiptItem, String>("receiptID"));
                    tblcprodStr.setCellValueFactory(new PropertyValueFactory<ReceiptItem, String>("storeID"));
                    tblcprodQ.setCellValueFactory(new PropertyValueFactory<ReceiptItem, Double>("quantityPurchased"));
                    tblcprodTtl.setCellValueFactory(new PropertyValueFactory<ReceiptItem, Double>("totalPrice"));
                    tblcprodDate.setCellValueFactory(new PropertyValueFactory<ReceiptItem, String>("date"));
                    
                    prodTable.getColumns().addAll(tblcprodID, tblcprodPO, tblcprodStr, tblcprodQ, tblcprodTtl, tblcprodDate);
                    
                    GridPane prodPane = new GridPane();
                    prodPane.add(lblYear, 0, 0);
                    prodPane.add(cbxYear, 1, 0);
                    prodPane.add(lblMonth, 2, 0);
                    prodPane.add(cbxMonth, 3, 0);
                    prodPane.add(lblDay, 4, 0);
                    prodPane.add(cbxDay, 5, 0);
                    prodPane.add(lblStore, 6, 0);
                    prodPane.add(cbxStore, 7, 0);
                    prodPane.add(btnviewprod, 8, 0);
                    prodPane.add(prodTable, 0, 1, 9, 1);
                    prodPane.add(lblmostpur, 0, 2);
                    prodPane.add(txtmostpur, 1, 2);
                    prodPane.add(lblquant, 0, 3);
                    prodPane.add(txtquant, 1, 3);
                    prodPane.add(lblsalesamt, 0, 4);
                    prodPane.add(txtsalesamt, 1, 4);
                    prodPane.add(btnprodGraph, 3, 2);
                    prodPane.add(btnprodReport, 3, 3);
                    prodPane.setAlignment(Pos.CENTER);
                        
                    cbxYear.getItems().addAll(yearItems);
                    cbxYear.setValue("None");
                    cbxMonth.getItems().addAll(monthItems);
                    cbxMonth.setValue("None");
                    cbxDay.getItems().addAll(dayItems);
                    cbxDay.setValue("None");
                    cbxStore.setItems(storeItems);
                    cbxStore.setValue("None");
                    double max = 0;
                    for (ReceiptItem r : prodData) {
                        if (r.quantityPurchased > max){
                            max = r.quantityPurchased;
                            txtmostpur.setText(r.productID);
                            txtquant.setText(String.valueOf(r.quantityPurchased));
                            txtsalesamt.setText(String.valueOf(r.totalPrice));
                        }
                    }
                    for (ReceiptItem r : prodData) {
                        dateData.add(r);
                    }
                    Scene prodScene = new Scene(prodPane, 600, 600);
                    Stage prodStage = new Stage();
                    prodStage.setScene(prodScene);
                    prodStage.show();
                    
                    btnviewprod.setOnAction(eZ -> {
                    
                    strday = String.valueOf(cbxDay.getValue());
                    
                    
                    strmonth = String.valueOf(cbxMonth.getValue());
                    
                    
                    stryear = String.valueOf(cbxYear.getValue());
                    
                    strstore = String.valueOf(cbxStore.getValue());
                    
                    prodTableData.removeAll();
                    dateData.clear();
                    
                    for (ReceiptItem r : prodData) {
                        if (r.date.equals(stryear+"-"+strmonth+"-"+strday) && strstore.equals("None")) {
                            dateData.add(r);
                            
                        }
                        else if (r.date.equals(stryear+"-"+strmonth+"-"+strday) && r.storeID.equals(strstore)) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(0, 7).equals(stryear+"-"+strmonth) && strday.equals("None") && strstore.equals("None")) {
                            dateData.add(r);   
                        }
                        else if (r.date.substring(0, 7).equals(stryear+"-"+strmonth) && strday.equals("None") && r.storeID.equals(strstore)) {
                            dateData.add(r);   
                        } 
                        else if (r.date.substring(0, 4).equals(stryear) && strmonth.equals("None") && strday.equals("None") && strstore.equals("None")) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(0, 4).equals(stryear) && strmonth.equals("None") && strday.equals("None") && r.storeID.equals(strstore)) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(0, 4).equals(stryear) && strmonth.equals("None") && r.date.substring(8, 10).equals(strday) && strstore.equals("None")){
                            dateData.add(r);
                        }
                        else if (r.date.substring(0, 4).equals(stryear) && strmonth.equals("None") && r.date.substring(8, 10).equals(strday) && r.storeID.equals(strstore)){
                            dateData.add(r);
                        }
                        else if (r.date.substring(5, 10).equals(strmonth+"-"+strday) && stryear.equals("None") && strstore.equals("None")) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(5, 10).equals(strmonth+"-"+strday) && stryear.equals("None") && r.storeID.equals(strstore)) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(5, 7).equals(strmonth) && stryear.equals("None") && strday.equals("None") && strstore.equals("None")) {
                            dateData.add(r);
                        }
                        else if (r.date.substring(5, 7).equals(strmonth) && stryear.equals("None") && strday.equals("None") && r.storeID.equals(strstore)) {
                            dateData.add(r);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strday.equals("None") && strstore.equals("None")) {
                            dateData.add(r);
                        }
                        else if (stryear.equals("None") && strmonth.equals("None") && strday.equals("None") && r.storeID.equals(strstore)) {
                            dateData.add(r);
                        }
                    }
                    prodTableData = FXCollections.observableList(dateData);
                    prodTable.setItems(prodTableData);
                    double newMax = 0;
                    for (ReceiptItem r : dateData) {
                        if (r.quantityPurchased > newMax){
                            newMax = r.quantityPurchased;
                            txtmostpur.setText(r.productID);
                            txtquant.setText(String.valueOf(r.quantityPurchased));
                            txtsalesamt.setText(String.valueOf(r.totalPrice));
                        }
                    }
                    });
                    btnprodGraph.setOnAction(eGP -> {
                    final CategoryAxis xAxis = new CategoryAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    final BarChart<String,Number> pbc = 
                        new BarChart<String,Number>(xAxis,yAxis);
                    pbc.setTitle("POS Report");
                    xAxis.setLabel("Product");
                    yAxis.setLabel("Amount");
                    XYChart.Series series1 = new XYChart.Series();
                    series1.setName("Quantity Sold");
                    XYChart.Series series2 = new XYChart.Series();
                    series2.setName("Dollars in Sales");
                        for (ReceiptItem r : dateData) {
                            series1.getData().add(new XYChart.Data(r.productID, r.quantityPurchased));
                            series2.getData().add(new XYChart.Data(r.productID, r.totalPrice));
                        }
                        Stage stage = new Stage(); 
                    stage.setTitle("Bar Chart");
                    stage.setScene(new Scene(pbc, 500, 400));
                    pbc.getData().addAll(series1, series2);
                    stage.show();
                    });
                    btnprodReport.setOnAction(eB -> {
                        String rpMost = txtmostpur.getText();
                        String rpQuant = txtquant.getText();                       
                        String rpAmount = txtsalesamt.getText();
                        File file = new File("posreport.txt");
                        try {
                            PrintWriter output = new PrintWriter(file);
                            output.println("Products Purchased and Quantity");
                            output.println("-------------");
                            for (ReceiptItem r : dateData) {
                                output.println(r.productID + " : " + r.quantityPurchased);
                            }
                            output.println("Most Purchased Product");
                            output.println("-------------");
                            output.println(rpMost+"\n");
                            output.println("Quantity");
                            output.println("-------------");
                            output.println(rpQuant+"\n");
                            output.println("Sales Amount");
                            output.println("-------------");
                            output.println(rpAmount+"\n");
                            
                            output.close();
                            
                            
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    });
                });

                // Adding controls to PayPane
                payPane.add(btnpayemp, 0, 0);
                payPane.add(btnpayclearemp, 1, 0);
                payPane.add(lblpayempsort, 2, 0);
                payPane.add(txtpayempsort, 3, 0);
                payPane.add(lblpaystore, 0, 1);
                payPane.add(cboxpaystore, 1, 1);
                payPane.add(lblpaydep, 2, 1);
                payPane.add(cboxpaydep, 3, 1);
                payPane.add(lblpaydate, 0, 2);
                payPane.add(cboxpp, 1, 2);
                //payPane.add(cboxpaymonth, 2, 2);
                //payPane.add(cboxpayyr, 3, 2);
                payPane.add(btnupdatePR, 0, 3);
                payPane.add(btnpayedit, 1, 3);
                payPane.add(btnpaycreate, 2, 3);
                
                //Making the select employee textbox uneditable
                txtpayempsort.editableProperty().setValue(false);
                
                //Filling in PayPane Comboboxes
                cboxpaystore.getItems().add("All");
                cboxpaystore.getItems().add("sto22221");
                cboxpaystore.getItems().add("sto22222");
                cboxpaystore.getItems().add("sto22223");
                cboxpaystore.getItems().add("sto22224");
                cboxpaystore.getItems().add("sto22225");
                cboxpaystore.getItems().add("sto22226");
                //Will set the department table to fill on the store selected event.
                cboxpayday.getItems().addAll(dayItems);
                cboxpayday.setValue("None");
                cboxpaymonth.getItems().addAll(monthItems);
                cboxpaymonth.setValue("None");
                cboxpayyr.getItems().addAll(yearItems);
                cboxpayyr.setValue("None");
                // Event handler for store cbox to set dep cbox
                cboxpaystore.setOnAction(eB -> {
                    
                    cboxpaydep.getItems().clear();
                    cboxpaydep.getItems().add("All");
                    String tempsnum = (String)cboxpaystore.getSelectionModel().getSelectedItem();
                    if(tempsnum.equals("sto22221")) {
                        cboxpaydep.getItems().add("dep55555");
                        cboxpaydep.getItems().add("dep55556");
                        cboxpaydep.getItems().add("dep55554");
                    } else if(tempsnum.equals("sto22222")) {
                        cboxpaydep.getItems().add("dep55557");
                        cboxpaydep.getItems().add("dep55558");
                    } else if(tempsnum.equals("sto22223")) {
                        cboxpaydep.getItems().add("dep55559");
                    } else if(tempsnum.equals("sto22224")) {
                        cboxpaydep.getItems().add("dep55553");
                    }
                    
                });

                //Adding Payroll Table
                PayrollTable = new TableView<WorkLog>();
                PayrollData = FXCollections.observableList(PayData);
                PayrollTable.setItems(PayrollData);
                
                //Recording every employee assignment
                sendDBCommand("select * from EMPLOYEEASSIGNMENT");
                try {
                    while (dbResults.next()) {
                        depass.add(new EmployeeAssignment(dbResults.getString(1), dbResults.getString(2)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Adding "all" to the periods combobox
                cboxpp.getItems().add("All");
                
                //Filling in the table
                sendDBCommand("select * from EMPLOYEEWORKLOG");
                try {
                    while (dbResults.next()) {
                        String templid = dbResults.getString(1);
                        //System.out.print(templid);
                        String tempeid = dbResults.getString(2);
                        String tempsdate = dbResults.getString(3);
                        String tempedate = dbResults.getString(4);
                        String tempDID = "";
                        double temphpay = 0;
                        double temphwk = 0;
                        //to match department
                        for (EmployeeAssignment z : depass) {
                            if(z.getEmployeeID().equals(tempeid))
                                tempDID = z.getDepartmentID();
                                
                        }
                        //use employee arraylist to match eid
                        for (Employee td : EmpData) {
                            if(td.getEmployeeID().equals(tempeid))
                                temphpay = td.getEmpHrPay();
                            //To add departments to employees that match
                            for (EmployeeAssignment z : depass) {
                                if(z.getEmployeeID().equals(td.getEmployeeID()))
                                    td.setdepID(z.getDepartmentID());
                            }
                        }
                        boolean periodexists = false;
                        for (PayPeriod p : pps) {
                            if(p.toString().equals(tempsdate + " - " + tempedate))
                            {
                                periodexists = true;
                            }
                        }
                        if(periodexists == false) {
                            pps.add(new PayPeriod(tempsdate, tempedate));
                        }
                        PayData.add(new WorkLog(templid, tempeid, tempDID, tempsdate, tempedate, temphpay, temphwk));
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThriftyStoreGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (PayPeriod z : pps) {
                    cboxpp.getItems().add(z.toString());
                }

                
                TableColumn tblcpayEID = new TableColumn("Employee ID");
                TableColumn tblcpayWID = new TableColumn("Worklog ID");
                TableColumn tblcpaySID = new TableColumn("Store ID");
                TableColumn tblcpayDID = new TableColumn("Department ID");
                TableColumn tblcpaySD = new TableColumn("Start Date");
                TableColumn tblcpayED = new TableColumn("End Date");
                TableColumn tblcpayHP = new TableColumn("Pay Rate");
                TableColumn tblcpayHW = new TableColumn("Hours Worked");
                TableColumn tblcpayTP = new TableColumn("Total Pay");
                
                //tblcpayEID.setCellValueFactory(new PropertyValueFactory<>("empID"));
                
                tblcpayEID.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("EmployeeID"));
                tblcpayWID.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("WorkLogID"));
                tblcpaySID.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("StoreID"));
                tblcpayDID.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("departmentID"));
                tblcpaySD.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("StartDate"));
                tblcpayED.setCellValueFactory(new PropertyValueFactory<WorkLog, String>("EndDate"));
                tblcpayHP.setCellValueFactory(new PropertyValueFactory<WorkLog, Double>("HourlyPay"));
                tblcpayHW.setCellValueFactory(new PropertyValueFactory<WorkLog, Double>("HoursWorked"));
                tblcpayTP.setCellValueFactory(new PropertyValueFactory<WorkLog, Double>("TotalPay"));
                
                
                PayrollTable.getColumns().addAll(tblcpayEID, tblcpayWID, tblcpaySID, tblcpayDID, 
                        tblcpaySD, tblcpayED, tblcpayHP, tblcpayHW, tblcpayTP);
                //SupTable.setMinWidth(primaryScene.getWidth());
                payPane.add(PayrollTable, 0, 4, 10, 1);
                
                //Controls for Add Employee Payroll Pane
                PayESPane.add(lblESstore, 0, 0);
                PayESPane.add(cboxESstore, 1, 0);
                PayESPane.add(lblESdep, 2, 0);
                PayESPane.add(cboxESdep, 3, 0);
                PayESPane.add(btnESupdate, 4, 0);
                PayESPane.add(lstES, 0, 1, 5, 1);
                
                //Filling in the list in Add Employee Pane
                lstESemp = FXCollections.observableArrayList(EmpData);
                lstES.setItems(lstESemp);
                
                //Filling in the add employees comboboxes
                cboxESstore.getItems().add("All");
                cboxESstore.getItems().add("sto22221");
                cboxESstore.getItems().add("sto22222");
                cboxESstore.getItems().add("sto22223");
                cboxESstore.getItems().add("sto22224");
                cboxESstore.getItems().add("sto22225");
                cboxESstore.getItems().add("sto22226");
                // Event handler for store cbox to set dep cbox
                cboxESstore.setOnAction(eB -> {
                
                    cboxESdep.getItems().clear();
                    cboxESdep.getItems().add("All");
                    String tempsnum2 = (String)cboxESstore.getSelectionModel().getSelectedItem();
                    if(tempsnum2.equals("sto22221")) {
                        cboxESdep.getItems().add("dep55555");
                        cboxESdep.getItems().add("dep55556");
                        cboxESdep.getItems().add("dep55554");
                    } else if(tempsnum2.equals("sto22222")) {
                        cboxESdep.getItems().add("dep55557");
                        cboxESdep.getItems().add("dep55558");
                    } else if(tempsnum2.equals("sto22223")) {
                        cboxESdep.getItems().add("dep55559");
                    } else if(tempsnum2.equals("sto22224")) {
                        cboxESdep.getItems().add("dep55553");
                    }
                    
                });
                
                
                //Controls for Create Payroll Pane
                PayCPpane.add(lblCPeid, 0, 0);
                PayCPpane.add(cboxCPeid, 1, 0);
                PayCPpane.add(lblCPsid, 0, 1);
                PayCPpane.add(cboxCPsid, 1, 1);
                PayCPpane.add(lblCPdid, 0, 2);
                PayCPpane.add(cboxCPdid, 1, 2);
                PayCPpane.add(lblCPsd, 0, 3);
                PayCPpane.add(txtCPsd, 1, 3);
                PayCPpane.add(lblCPed, 0, 4);
                PayCPpane.add(txtCPed, 1, 4);
                PayCPpane.add(lblCPpr, 0, 5);
                PayCPpane.add(txtCPpr, 1, 5);
                PayCPpane.add(lblCPhw, 0, 6);
                PayCPpane.add(txtCPhw, 1, 6);
                PayCPpane.add(btnCP, 1, 7);
                
                //Controls for Edit Payroll Pane
                PayEPpane.add(lblEPeid, 0, 0);
                PayEPpane.add(cboxEPeid, 1, 0);
                PayEPpane.add(lblEPsid, 0, 1);
                PayEPpane.add(cboxEPsid, 1, 1);
                PayEPpane.add(lblEPdid, 0, 2);
                PayEPpane.add(cboxEPdid, 1, 2);
                PayEPpane.add(lblEPsd, 0, 3);
                PayEPpane.add(txtEPsd, 1, 3);
                PayEPpane.add(lblEPed, 0, 4);
                PayEPpane.add(txtEPed, 1, 4);
                PayEPpane.add(lblEPpr, 0, 5);
                PayEPpane.add(txtEPpr, 1, 5);
                PayEPpane.add(lblEPhw, 0, 6);
                PayEPpane.add(txtEPhw, 1, 6);
                PayEPpane.add(btnEP, 1, 7);
                
                //Filling in Comboboxes for Edit Payroll
                for (Employee ez : EmpData) {
                    cboxEPeid.getItems().add(ez.getEmployeeID());
                }
                cboxEPsid.editableProperty().setValue(false);
                cboxEPdid.editableProperty().setValue(false);
                //Event Handler to set store and department box values
                cboxEPeid.setOnAction(eB -> {
                    
                    if(!cboxEPeid.getSelectionModel().isEmpty()) {
                        for (Employee ez : EmpData) {
                            if(ez.getEmployeeID().equals(cboxEPeid.getSelectionModel().getSelectedItem()))
                            {
                                cboxEPsid.setValue(ez.getStoreID());
                                cboxEPdid.setValue(ez.getdepID());
                                txtEPpr.setText(String.valueOf(ez.getEmpHrPay()));
                            }
                        }
                    }
                    
                });
                
                //Filling in Comboboxes for Create Payroll
                for (Employee ez : EmpData) {
                    cboxCPeid.getItems().add(ez.getEmployeeID());
                }
                cboxCPsid.editableProperty().setValue(false);
                cboxCPdid.editableProperty().setValue(false);
                //Event Handler to set store and department box values
                cboxCPeid.setOnAction(eB -> {
                    
                    if(!cboxCPeid.getSelectionModel().isEmpty()) {
                        for (Employee ez : EmpData) {
                            if(ez.getEmployeeID().equals(cboxCPeid.getSelectionModel().getSelectedItem()))
                            {
                                cboxCPsid.setValue(ez.getStoreID());
                                cboxCPdid.setValue(ez.getdepID());
                                txtCPpr.setText(String.valueOf(ez.getEmpHrPay()));
                            }
                        }
                    }
                    
                });
                
                
                
                //Payroll Pane Event Handlers
                btnpayemp.setOnAction(eB -> {
                    
                    SrchPayEmp(PayESStage);
                    
                });
                btnpayclearemp.setOnAction(eB -> {
                    txtpayempsort.clear();
                });
                btnupdatePR.setOnAction(eB -> {
                    ArrayList<WorkLog> specData = new ArrayList<>();
                    PayrollData.removeAll();
                    for (WorkLog wl : PayData) {
                        //If no store is selected or "all" is selected
                        if(cboxpaystore.getSelectionModel().isEmpty() || cboxpaystore.getSelectionModel().getSelectedItem().toString().equals("All")) {
                            //If no Pay period is selected or if "all" is selected
                            if(cboxpp.getSelectionModel().isEmpty() || cboxpp.getSelectionModel().getSelectedItem().toString().equals("All"))
                            {
                                if(txtpayempsort.getText().isEmpty())
                                    specData.add(wl);
                                else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                    specData.add(wl);
                            }
                            //If a particular pay period was selected and it matches the worklog's
                            else if(wl.period().equals(cboxpp.getSelectionModel().getSelectedItem().toString()))
                            {
                                if(txtpayempsort.getText().isEmpty())
                                    specData.add(wl);
                                else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                    specData.add(wl);
                            }
                        //If a particular store is selected
                        } else {
                            //Checks if the selected store ID matches the worklog's
                            if(wl.getStoreID().equals(cboxpaystore.getSelectionModel().getSelectedItem().toString()))
                            {
                                //If no dept is selected or if "all" is selected
                                if(cboxpaydep.getSelectionModel().isEmpty() || cboxpaydep.getSelectionModel().getSelectedItem().toString().equals("All"))
                                {
                                    //If no Pay period is selected or if "all" is selected
                                    if(cboxpp.getSelectionModel().isEmpty() || cboxpp.getSelectionModel().getSelectedItem().toString().equals("All"))
                                    {
                                        if(txtpayempsort.getText().isEmpty())
                                            specData.add(wl);
                                        else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                            specData.add(wl);
                                    }
                                    //If a particular pay period was selected and it matches the worklog's
                                    else if(wl.period().equals(cboxpp.getSelectionModel().getSelectedItem().toString()))
                                    {
                                        if(txtpayempsort.getText().isEmpty())
                                            specData.add(wl);
                                        else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                            specData.add(wl);
                                    }
                                //When a department is selected
                                } else {
                                    //Checks if the worklog's department matches up
                                    if(wl.getDepartmentID().equals(cboxpaydep.getSelectionModel().getSelectedItem().toString())) {
                                        //If no Pay period is selected or if "all" is selected
                                        if(cboxpp.getSelectionModel().isEmpty() || cboxpp.getSelectionModel().getSelectedItem().toString().equals("All"))
                                        {
                                            if(txtpayempsort.getText().isEmpty())
                                                specData.add(wl);
                                            else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                                specData.add(wl);
                                        }
                                        //If a particular pay period was selected and it matches the worklog's
                                        else if(wl.period().equals(cboxpp.getSelectionModel().getSelectedItem().toString()))
                                        {
                                            if(txtpayempsort.getText().isEmpty())
                                                specData.add(wl);
                                            else if(wl.getEmployeeID().equals(txtpayempsort.getText()))
                                                specData.add(wl);
                                        } 
                                    }
                                        
                                }
                            }
                            
                        }
                    }
                    PayrollData = FXCollections.observableList(specData);
                    PayrollTable.setItems(PayrollData);
                    
                });
                btnpayedit.setOnAction(eB -> {
                    if(!PayrollTable.getSelectionModel().isEmpty()) {
                        EditPayroll(PayEPStage, PayrollTable.getSelectionModel().getSelectedItem());
                    }
                    
                });
                btnpaycreate.setOnAction(eB -> {
                    CreatePayroll(PayCPStage);
                });

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
                posPane.add(btnCancelOrder, 2, 6);
                
                txtPOSEID.editableProperty().setValue(false);
                
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
                // To make sure that Receipt and Customer IDs have accurate values
                if(SalData.get(SalData.size()-1).getReceiptID().equals("po99995")) {
                    //when there haven't been any pos sales yet
                } else {
                    //when there have been a few pos sales
                    RecIDCount = Integer.parseInt(SalData.get(SalData.size()-1).getReceiptID().substring(3, 7)) + 1;
                }
                if((poscust.isEmpty()) || (poscust.get(poscust.size()-1).equals("cust00010"))) {
                    //when there haven't been any pos sales yet
                } else {
                    //when there have been a few pos sales
                    CustIDCount = Integer.parseInt(poscust.get(poscust.size()-1).getCustomerID().substring(5, 9)) + 1;
                }
                btnCheckout.setOnAction (eB -> {
                    
                    if(!POSTable.getItems().isEmpty()) {
                    //Arraylist to temporarily store the reciept items before adding
                    //them to the created reciept.
                    ArrayList<ReceiptItem> tempitems = new ArrayList<>();
                    String temprecid = "po" + RecIDCount;
                    RecIDCount++;
                    String tempcustid = "cust" + CustIDCount;
                    CustIDCount++;
                    String temprecempid = CurrentUser.getEmployeeID();
                    String tempStoreid = CurrentUser.getStoreID();
                    Double tempndtot = Double.parseDouble(txtPOSSAV.getText()) + Double.parseDouble(txtPOSTOT.getText());
                    Double tempsav = Double.parseDouble(txtPOSSAV.getText());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    Receipt tempreceipt = new Receipt(temprecid, tempcustid, temprecempid, tempStoreid, tempndtot, tempsav, now.toString().substring(0, 10));
                    for (Inventory td : POSDataArr) {
                        if (cboxPOSclub.getSelectionModel().getSelectedItem().equals("no")) {
                        tempitems.add(new ReceiptItem(tempreceipt.getReceiptID(), td.getProductID(), tempreceipt.storeID, 1, td.getSalesPrice(), now.toString().substring(0, 10)));
                        } else {
                        tempitems.add(new ReceiptItem(tempreceipt.getReceiptID(), td.getProductID(), tempreceipt.storeID, 1, td.getClubPrice(), now.toString().substring(0, 10)));  
                        }
                    }
                    //tempreceipt.setItemList(tempitems);
                    
                    //adding the newly created receipt to the sales tab
                    SalData.add(tempreceipt);
                    } else {
                        //Prompt the user to enter items into POS.
                    }
                    
                    //Clearing combobox selection and ID textbox value
                    cboxPOSclub.getSelectionModel().clearSelection();
                    txtPOSEID.clear();
                    POSTable.getItems().clear();
                    txtPOSTOT.clear();
                    txtPOSSAV.clear();
                    POSDataArr.clear();
                    
                    
                });
                btnCancelOrder.setOnAction (eB -> {
                    
                    cboxPOSclub.getSelectionModel().clearSelection();
                    txtPOSEID.clear();
                    
                    //To return inventory values to original
                    for (Inventory td : POSDataArr) {
                        td.addQIS(1);
                    }
                    POSDataArr.clear();
                    //Updating Productlst Table
                    POSaddprodlst.getItems().clear();
                    for (Inventory td : InvData) {
                    POSaddprodlst.getItems().add(td);
                    }
                    //To update Inventory TabPane table
                    InvTable.getItems().clear();
                    for (Inventory td : InvData) {
                        InvTable.getItems().add(td);
                    }

                    
                    POSTable.getItems().clear();
                    txtPOSTOT.clear();
                    txtPOSSAV.clear();   
                });
                
                btnPOSdelprod.setOnAction (eB -> {
                    
                    //to remove the selected item and add it back into inventory.
                    Inventory tempdel = POSTable.getSelectionModel().getSelectedItem();
                    POSTable.getItems().remove(POSTable.getSelectionModel().getSelectedItem());
                    POSDataArr.remove(POSDataArr.indexOf(tempdel));
                    
                    //to refresh the add product list to display the proper 
                    //inventory values
                    POSaddprodlst.getItems().clear();
                    for (Inventory td : InvData) {
                        if(td.equals(tempdel))
                            td.addQIS(1);
                        POSaddprodlst.getItems().add(td);
                    }
                    
                    //to refresh the POS table after the item is deleted.
                    POSTable.getItems().clear();
                    for (Inventory td : POSDataArr) { 
                        POSTable.getItems().add(td);
                    }
                    
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
                    td.setStatus(addProductStatus.getText());
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

        editProductSaveBut.setOnAction(e -> {

            for (Inventory td : InvData) {
                if (td.equals(editInv)) {
                    td.setProductName(editProductName.getText());
                    td.setUnitCost(Double.parseDouble(editProductUnitCost.getText()));
                    td.setSalesPrice(Double.parseDouble(editProductSalesPrice.getText()));
                    td.setQIS(Integer.parseInt(editQuantityStock.getText()));
                    td.setStatus(editProductStatus.getText());
                    td.setExpDate(editProductExpDate.getText());
                    td.setStoreID(editProductStoreLocation.getText());
                    td.setDeptID(editProductDepartment.getText());
                }
            }

            InvTable.getItems().clear();
            for (Inventory td : InvData) {
                InvTable.getItems().add(td);
            }

            String updateInv = "UPDATE JAVAUSER." + "InventoryItem" + " SET departmentID = '";
            updateInv += editProductDepartment.getText() + "',";
            updateInv += " quantityInStock = '" + Integer.parseInt(editQuantityStock.getText()) + "',";
            updateInv += " status = '" + editProductStatus.getText() + "',";
            updateInv += " expirationDate = '" + editProductExpDate.getText().substring(0, 11) + "'"
                    + " WHERE productID = \'"
                    + editInv.getProductID() + "\'";

            sendDBCommand(updateInv);

        });
    }
    
    public void EditEmployee(Stage ItemStage, Employee editEmp) {

        editEmployeeName.setText(editEmp.getEmployeeName());
        editEmployeePhone.setText(editEmp.getEmployeePhone());
        editEmployeeAddress.setText(editEmp.getEmployeeAddress());
        editEmployeeHourlyPay.setText(String.valueOf(editEmp.getEmpHrPay()));
        editEmployeeType.setText(editEmp.getEmployeeType());
        editEmployeeStore.setText(editEmp.getStoreID());

        //Button editSaveBut = new Button("Save Changes->");
        ItemStage.setScene(EmpScene);
        ItemStage.setTitle("Edit Employee Information");
        ItemStage.show();

        editEmployeeSaveBut.setOnAction(e -> {

            for (Employee td : EmpData) {
                if (td.equals(editEmp)) {
                    td.setEmployeeName(editEmployeeName.getText());
                    td.setEmployeePhone(editEmployeePhone.getText());
                    td.setEmployeeAddress(editEmployeeAddress.getText());
                    td.setEmpHrPay(Double.parseDouble(editEmployeeHourlyPay.getText()));
                    td.setEmployeeType(editEmployeeType.getText());
                    td.setStoreID(editEmployeeStore.getText());
                }
            }

            EmpTable.getItems().clear();
            for (Employee td : EmpData) {
                EmpTable.getItems().add(td);
            }

            String updateEmp = "UPDATE JAVAUSER." + "Employee" + " SET name = '";
            updateEmp += editEmployeeName.getText() + "',";
            updateEmp += " Address = '" + editEmployeeAddress.getText() + "',";
            updateEmp += " phone = '" + editEmployeePhone.getText() + "',";
            updateEmp += " hourlyPay = '" + Double.parseDouble(editEmployeeHourlyPay.getText()) + "',";
            updateEmp += " type = '" + editEmployeeType.getText() + "'"
                    + " WHERE employeeID = \'"
                    + editEmp.getEmployeeID() + "\'";

            sendDBCommand(updateEmp);

        });

    }

    public void EditSupplier(Stage ItemStage, Supplier editSup) {

        editSupplierName.setText(editSup.getSupplierName());
        editSupplierAddress.setText(editSup.getSupplierAddress());
        editContactName.setText(editSup.getContactName());
        editContactNumber.setText(editSup.getContactPhone());
        editContactEmail.setText(editSup.getContactEmail());

        //Button editSaveBut = new Button("Save Changes->");
        ItemStage.setScene(SupScene);
        ItemStage.setTitle("Edit Supplier Information");
        ItemStage.show();

        editSupplierSaveBut.setOnAction(e -> {
            for (Supplier td : SupData) {
                if (td.equals(editSup)) {
                    td.setSupplierName(editSupplierName.getText());
                    td.setSupplierAddress(editSupplierAddress.getText());
                    td.setContactName(editContactName.getText());
                    td.setContactPhone(editContactNumber.getText());
                    td.setContactEmail(editContactEmail.getText());

                }
            }
            SupTable.getItems().clear();
            for (Supplier td : SupData) {
                SupTable.getItems().add(td);
            }
            String updateSup = "UPDATE JAVAUSER." + "Supplier" + " SET name = '";
            updateSup += editSupplierName.getText() + "',";
            updateSup += " phone = '" + editContactNumber.getText() + "',";
            updateSup += " email = '" + editContactEmail.getText() + "',";
            updateSup += " address = '" + editSupplierAddress.getText() + "',";
            updateSup += " contactName = '" + editContactName.getText() + "'"
                    + " WHERE supplierID = \'"
                    + editSup.getSupplierID() + "\'";
            sendDBCommand(updateSup);
        

        });
    }
    
     public void ViewProducts(Stage ItemStage) {
        
        ItemStage.setScene(ProScene);
        ItemStage.setTitle("View Products By Supplier");
        ItemStage.show();

        TableColumn tblcProdSup = new TableColumn("Product");
        TableColumn tblcSupProd = new TableColumn("Supplier");
        TableColumn tblcProdName = new TableColumn("ProductName");
        TableColumn tblcProdDescrip = new TableColumn("ProductDescription");

        //SupTable.setMinWidth(primaryScene.getWidth());
        tblcProdSup.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
        tblcSupProd.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierID"));
        tblcProdName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        tblcProdDescrip.setCellValueFactory(new PropertyValueFactory<Product, String>("productDescription"));

        ProTable.getColumns().addAll(tblcProdSup, tblcSupProd, tblcProdName, tblcProdDescrip);
        viewSupplierPane.add(ProTable, 0, 2, 10, 1);

        viewSupplierPane.setAlignment(Pos.CENTER);

    }
    
     public void ViewShipments(Stage ItemStage) {

        ItemStage.setScene(ProScene);
        ItemStage.setTitle("View Incoming Shipments");
        ItemStage.show();

    }
    
    public Employee employeeID()
    {
        return EmpData.get(EmpData.size()-1);
    }
    
    
    public Expense ExpenseID()
    {
        return ExpData.get(ExpData.size()-1);
    }
    
    public Supplier SupplierID()
    {
        return SupData.get(SupData.size()-1);
    }
    
    //public Inventory 
    public void AddEmployee(Employee e)
    {
        EmpTable.getItems().add(e);
        EmpData.add(e);
    }
    
    public void AddExpense(Expense e)
    {
        ExpTable.getItems().add(e);
        //ExpData.add(e);
        
    }
    
    public void AddInventory(Inventory e)
    {
        InvData.add(e);
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
            Inventory tempinv = (Inventory)POSaddprodlst.getSelectionModel().getSelectedItem();
            if(tempinv.getQIS() > 0) {
                tempinv.removeQIS(1);



                POSDataArr.add(tempinv);

                //To update POS list inventory values
                //POSaddprodlst.getItems().clear();
                for (Inventory td : InvData) { 
                    if(td.equals(tempinv)) {
                        td = tempinv;
                        System.out.println("matching inv found!");
                    }
                }
                POSaddprodlst.getItems().clear();
                for (Inventory td : InvData) {
                    POSaddprodlst.getItems().add(td);
                }

                //To update Inventory TabPane table
                InvTable.getItems().clear();
                for (Inventory td : InvData) {
                    InvTable.getItems().add(td);
                }


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
                    totsum = ((int)(totsum*100))/100;
                    savsum = ((int)(savsum*100))/100;
                    txtPOSTOT.setText(String.valueOf(totsum));
                    txtPOSSAV.setText(String.valueOf(savsum));
                } else {
                    for (Inventory td : POSDataArr) { 
                        totsum += td.getSalesPrice();
                    }
                    totsum = ((int)(totsum*100))/100;
                    savsum = ((int)(savsum*100))/100;
                    txtPOSTOT.setText(String.valueOf(totsum));
                    txtPOSSAV.setText(String.valueOf(savsum));
                }

            } else {
                //Have a message to user signifying lack of inventory
                //The println is a temporary solution
                System.out.println("No more inventory.");
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
            if(!lstPOScust.getSelectionModel().isEmpty())
                cboxPOSclub.getSelectionModel().select(0);
            
        });
    }
    
    public void SrchPayEmp(Stage PayESStage) {
        
        PayESStage.setScene(PayESScene);
        PayESStage.setTitle("Search for Employee");
        PayESStage.show();
        
        btnESupdate.setOnAction(eB -> {
            ArrayList<Employee> specData2 = new ArrayList<>();
            PayrollData.removeAll();
            for (Employee e : EmpData) {
                if(cboxESstore.getSelectionModel().isEmpty() || cboxESstore.getSelectionModel().getSelectedItem().toString().equals("All"))
                {
                    if(cboxESdep.getSelectionModel().isEmpty() || cboxESdep.getSelectionModel().getSelectedItem().toString().equals("All"))
                    {
                        specData2.add(e);
                    }
                    else if(cboxESdep.getSelectionModel().getSelectedItem().toString().equals(e.depID))
                    {
                        specData2.add(e);
                    }
                }
                else if(cboxESstore.getSelectionModel().getSelectedItem().toString().equals(e.storeID))
                {
                    if(cboxESdep.getSelectionModel().isEmpty() || cboxESdep.getSelectionModel().getSelectedItem().toString().equals("All"))
                    {
                        specData2.add(e);
                    }
                    else if(cboxESdep.getSelectionModel().getSelectedItem().toString().equals(e.depID))
                    {
                        specData2.add(e);
                    }
                }
                
            }
            lstESemp = FXCollections.observableArrayList(specData2);
            lstES.setItems(lstESemp);
            
            System.out.println(lstES.getSelectionModel().getSelectedItems().toString().substring(1, lstES.getSelectionModel().getSelectedItems().toString().length()-1));
            String comparethis = lstES.getSelectionModel().getSelectedItems().toString().substring(1, lstES.getSelectionModel().getSelectedItems().toString().length()-1);
            if(!lstES.getSelectionModel().isEmpty()){
                Employee tempemp = null;
                for (Employee e : EmpData) { 
                    if(e.toString().equals(comparethis))
                        tempemp = e;
                }
                if(tempemp != null) {
                    System.out.println(tempemp.getEmployeeID());
                    txtpayempsort.setText(tempemp.getEmployeeID());
                }
            }
        });
        
        /**
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
            if(!lstPOScust.getSelectionModel().isEmpty())
                cboxPOSclub.getSelectionModel().select(0);
            
        });
        **/
    }
    
    public void EditPayroll(Stage daStage, WorkLog editWorkLog) {
        
        cboxEPeid.setValue(editWorkLog.getEmployeeID());
        cboxEPsid.setValue(editWorkLog.getStoreID());
        cboxEPdid.setValue(editWorkLog.getDepartmentID());
        txtEPsd.setText(editWorkLog.getStartDate());
        txtEPed.setText(editWorkLog.getEndDate());
        txtEPpr.setText(String.valueOf(editWorkLog.getHourlyPay()));
        txtEPhw.setText(String.valueOf(editWorkLog.getHoursWorked()));
        
        daStage.setScene(PayEPScene);
        daStage.setTitle("Edit Payroll Information");
        daStage.show();
        
        btnEP.setOnAction (eB -> {
            
            String EPeid = cboxEPeid.getSelectionModel().getSelectedItem().toString();
            String EPsid = cboxEPsid.getSelectionModel().getSelectedItem().toString();
            String EPdid = cboxEPdid.getSelectionModel().getSelectedItem().toString();
            String EPsd = txtEPsd.getText();
            String EPed = txtEPed.getText();
            double EPpr = Double.parseDouble(txtEPpr.getText());
            double EPhw = Double.parseDouble(txtEPhw.getText());
            
            for (WorkLog wl : PayData) {
                if(wl.getWorkLogID().equals(editWorkLog.getWorkLogID())) {
                    wl.setDepartmentID(EPdid);
                    wl.setStoreID(EPsid);
                    wl.setEmployeeID(EPeid);
                    wl.setStartDate(EPsd);
                    wl.setEndDate(EPed);
                    wl.setHourlyPay(EPpr);
                    wl.setHoursWorked(EPhw);
                    wl.setTotalPay();
                }
            }
            
            PayPeriod Original = new PayPeriod(editWorkLog.getStartDate(), editWorkLog.getEndDate());
            PayPeriod NewPP = new PayPeriod(EPsd, EPed);
            if(!Original.toString().equals(NewPP.toString()))
            {
                //To add new PayPeriod to the date combobox
                pps.add(new PayPeriod(EPsd, EPed));

                cboxpp.getItems().clear();
                cboxpp.getItems().add("All");
                for (PayPeriod z : pps) {
                    cboxpp.getItems().add(z.toString());
                }
            }
            
            System.out.println("I'm Reloaded!! also:" + EPsd);
            //refilling the Payroll table
            PayrollTable.getItems().clear();
            PayrollData = FXCollections.observableList(PayData);
            PayrollTable.setItems(PayrollData);
            
            //Insert Code to update the worklog in the database here:
            
        });
        
    }
    
    public void CreatePayroll(Stage daStage) {
        
        daStage.setScene(PayCPScene);
        daStage.setTitle("Create Payroll Item");
        daStage.show();
        
        btnCP.setOnAction (eB -> {
            
            String CPeid = cboxCPeid.getSelectionModel().getSelectedItem().toString();
            String CPsid = cboxCPsid.getSelectionModel().getSelectedItem().toString();
            String CPdid = cboxCPdid.getSelectionModel().getSelectedItem().toString();
            String CPsd = txtCPsd.getText();
            String CPed = txtCPed.getText();
            double CPpr = Double.parseDouble(txtCPpr.getText());
            double CPhw = Double.parseDouble(txtCPhw.getText());
            
            
            if(!PayData.get(PayData.size()-1).getWorkLogID().equals("log555") && (onetime)){
                System.out.println(PayData.get(PayData.size()-1).getWorkLogID().substring(3, 6));
                WLIDCount = Integer.parseInt(PayData.get(PayData.size()-1).getWorkLogID().substring(3, 6)) + 1;
                onetime = false;
            }
            
            //Note: assigning autogenerated worklog IDs
            WorkLog tempWL = new WorkLog("log" + WLIDCount, CPeid, CPdid, CPsd, CPed,
            CPpr, CPhw);
            
            //To add new PayPeriod to the date combobox
            PayPeriod tempPeriod = new PayPeriod(CPsd, CPed);
            boolean periodexists = false;
            for (PayPeriod p : pps) {
                if(p.toString().equals(tempPeriod.toString()))
                {
                    periodexists = true;
                }
            }
            if(periodexists == false) {
                pps.add(tempPeriod);
            }
            cboxpp.getItems().clear();
            cboxpp.getItems().add("All");
            for (PayPeriod z : pps) {
                cboxpp.getItems().add(z.toString());
            }
            
            PayData.add(tempWL);
            
            System.out.println("before: " + WLIDCount);
            WLIDCount = WLIDCount + 1;
            System.out.println("after: " + WLIDCount);
            
            //refilling the Payroll table
            PayrollData = FXCollections.observableList(PayData);
            PayrollTable.setItems(PayrollData);
            
            //Insert Code to insert new WorkLog into database here:
            
        });
        
    }
}
