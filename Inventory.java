/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty;

import javafx.scene.image.ImageView;

/**
 *
 * @author trane
 */
public class Inventory {
    
    String productID;
    String productName;
    ImageView image;
    String deptID;
    String storeID;
    int QIS;
    double salesPrice;
    double clubPrice;
    double unitCost;
    String expDate;
    String status;
    
    public Inventory(){
        
    }
    public Inventory(String productID, String productName, ImageView img, String deptID, String storeID, int QIS, String status, String expDate, double salesPrice, double unitCost){
        
        this.productID = productID;
        this.productName = productName;
        this.image = img;
        this.deptID = deptID;
        this.storeID = storeID;
        this.QIS = QIS;
        this.status = status;
        this.expDate = expDate;
        this.salesPrice = salesPrice;
        this.unitCost = unitCost;
        this.clubPrice = ((int)((salesPrice*(.9)) * 100))/100;
        
    }
    
    public String getProductID(){
        return this.productID;
    }
    
    public String getProductName(){
        return this.productName;
    }
    
    public ImageView getImage(){
        return this.image;
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
    public double getUnitCost(){
        return this.unitCost;
    }
    
    public String getExpDate(){
        return this.expDate;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void setProductID(String pID){
        this.productID = pID;
    }
    
    public void setImage(ImageView invPic){
        this.image = invPic;
    }
    
    public void setQIS(int Q){
        this.QIS = Q;
    }
    
    public void setUnitCost(Double uC){
        this.unitCost = uC;
    }
    
    public void setSalesPrice(Double sP){
        this.salesPrice = sP;
    }
    
    public void setExpDate(String expD){
        this.expDate = expD;
    }
    public void setProductName(String pID){
        this.productName = pID;
    }
    
    public void setDeptID(String dID){
        this.deptID = dID;
    }
    
    public void setStoreID(String sID){
        this.storeID = sID;
    }
    
    public void setStatus(String stat){
        
      this.status = stat;
        
    }
    
     public void changeStatus(int currQIS){
        
        if(currQIS != 0){
            this.status = "In-Stock";
        }
        if(currQIS == 0){
            this.status = "Out-Of-Stock";
        }
        
    }
    
    public double getClubPrice(){
        return this.clubPrice;
    }
    
    public void setClubPrice(double clubPrice){
        this.clubPrice = (int)(clubPrice*100)/100;
    }
    
    public void addQIS(int shipment){
        this.QIS += shipment;
    }
    
    public void removeQIS(int sale){
        this.QIS -= sale;
    }
    
    @Override
    public String toString() {
        return "Store: " + storeID + "\tProduct Name: " + productName + "\t(" + QIS + ") ";
    }
    
}
