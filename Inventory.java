/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty;

/**
 *
 * @author trane
 */
public class Inventory {
    
    String productID;
    String deptID;
    String storeID;
    int QIS;
    double salesPrice;
    String expDate;
    String status;
    
    public Inventory(){
        
    }
    public Inventory(String productID, String deptID, String storeID, int QIS, String status, String expDate, double salesPrice){
        
        this.productID = productID;
        this.deptID = deptID;
        this.storeID = storeID;
        this.QIS = QIS;
        this.status = status;
        this.expDate = expDate;
        this.salesPrice = salesPrice;
        
    }
    
    public String getProductID(){
        return this.productID;
    }
    
    public String getDeptID(){
        return this.deptID;
    }
    
    public String getStoreID(){
        return this.storeID;
    }
    
    public int getQIS(){
        return this.QIS;
    }
    
    public double getSalesPrice(){
        return this.salesPrice;
    }
    
    public String getExpDate(){
        return this.expDate;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void setSalesPrice(Double sP){
        this.salesPrice = sP;
    }
    
    public void setExpDate(String expD){
        this.expDate = expD;
    }
    public void setProductID(String pID){
        this.productID = pID;
    }
    
    public void setDeptID(String dID){
        this.deptID = dID;
    }
    
    public void setStoreID(String sID){
        this.storeID = sID;
    }
    
    public void setStatus(int currQIS){
        
        if(currQIS != 0){
            this.status = "In-Stock";
        }
        if(currQIS == 0){
            this.status = "Out-Of-Stock";
        }
        
    }
    
    public void addQIS(int shipment){
        this.QIS += shipment;
    }
    
    public void removeQIS(int sale){
        this.QIS -= sale;
    }
    
    
    
}
