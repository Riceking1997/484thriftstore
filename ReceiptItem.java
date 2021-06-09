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
public class ReceiptItem {
    
    int receiptID;
    String productID;
    Double quantityPurchased;
    Double totalPrice;
    
    public ReceiptItem(int receiptID, String productID, Double quantityPurchased, Double totalPrice){
        
        this.receiptID = receiptID;
        this.productID = productID;
        this.quantityPurchased = quantityPurchased;
        this.totalPrice = totalPrice;
        
    }
    
    public int getReceiptID(){
        return this.receiptID;
    }
    
    public String getProductID(){
        return this.productID;
    }
    
    public Double getQuantityPurchased(){
        return this.quantityPurchased;
    }
    
    public Double getTotalPrice(){
        return this.totalPrice;
    }
    
    public void setReceiptID(int rID){
        this.receiptID = rID;
    }
    
    public void setProductID(String pID){
        this.productID = pID;
    }
    
    public void setQuantityPurchased(Double qPurchased){
        this.quantityPurchased = qPurchased;
    }
    
    public void setTotalPrice(Double tPrice, Double qPurch, Double sellPrice){
        
        tPrice = qPurch * sellPrice;
        this.totalPrice = tPrice;
    }
}
