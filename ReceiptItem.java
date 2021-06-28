  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capstone;

/**
 *
 * @author trane
 */
public class ReceiptItem {
    
    String receiptID;
    String productID;
    String storeID;
    double quantityPurchased;
    double totalPrice;
    String date;
    
    public ReceiptItem(){
        
    }
    public ReceiptItem(String receiptID, String productID, String storeID, double quantityPurchased, double totalPrice, String date){
        
        this.receiptID = receiptID;
        this.productID = productID;
        this.storeID = storeID;
        this.quantityPurchased = quantityPurchased;
        this.totalPrice = totalPrice;
        this.date = date;
        
    }
    
    public String getReceiptID(){
        return this.receiptID;
    }
    
    public String getProductID(){
        return this.productID;
    }
    
    public String getStoreID() {
        return this.storeID;
    }
    
    public double getQuantityPurchased(){
        return this.quantityPurchased;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setReceiptID(String rID){
        this.receiptID = rID;
    }
    
    public void setProductID(String pID){
        this.productID = pID;
    }
    
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
    
    public void setQuantityPurchased(double qPurchased){
        this.quantityPurchased = qPurchased;
    }
    
    public void setTotalPrice(double tPrice, double qPurch, double sellPrice){
        
        tPrice = qPurch * sellPrice;
        this.totalPrice = tPrice;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}
