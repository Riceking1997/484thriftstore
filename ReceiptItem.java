/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty2;

/**
 *
 * @author trane
 */
public class ReceiptItem {
    
    String receiptID;
    String productID;
    int quantityPurchased;
    double totalPrice;
    
    public ReceiptItem(String receiptID, String productID, int quantityPurchased, double totalPrice){
        
        this.receiptID = receiptID;
        this.productID = productID;
        this.quantityPurchased = quantityPurchased;
        this.totalPrice = totalPrice;
        
    }
    
    public String getReceiptID(){
        return this.receiptID;
    }
    
    public String getProductID(){
        return this.productID;
    }
    
    public int getQuantityPurchased(){
        return this.quantityPurchased;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public void setReceiptID(String rID){
        this.receiptID = rID;
    }
    
    public void setProductID(String pID){
        this.productID = pID;
    }
    
    public void setQuantityPurchased(int qPurchased){
        this.quantityPurchased = qPurchased;
    }
    
    public void setTotalPrice(int qPurch, double sellPrice){
        
        double tPrice = qPurch * sellPrice;
        this.totalPrice = tPrice;
    }
}
