/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capstone;

import java.util.ArrayList;

/**
 *
 * @author trane
 */
public class Receipt {

    ArrayList<ReceiptItem> itemList;
    String receiptID;
    String customerID;
    String employeeID;
    String storeID;
    Double nodiscountTotal;
    Double savings;
    Double finalTotal;
    String date;

    public Receipt(String receiptID, String customerID, String employeeID, String storeID, Double nodiscountTotal, Double savings, String date) {

        this.receiptID = receiptID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.storeID = storeID;
        this.nodiscountTotal = nodiscountTotal;
        this.savings = savings;
        this.finalTotal = nodiscountTotal - savings;
        this.date = date;
        
    }
    
    public String getReceiptID(){
        return this.receiptID;
    }
    
    public String getCustomerID(){
        return this.customerID;
    }
    
    public String getEmployeeeID(){
        return this.employeeID;
    }
    
    public String getStoreID(){
        return this.storeID;
    }
    
    public Double getNoDiscountTotal(){
        return this.nodiscountTotal;
    }
    
    public Double getSavings(){
        return this.savings;
    }
    
    public Double getFinalTotal(){
        return this.finalTotal;
    }
    
    public String getDate(){
        return this.date;
    }

    public void setReceiptID(String rID){
        this.receiptID = rID;
    }
    
    public void setCustomerID(String cuID){
        this.customerID = cuID;
    }
    
    public void setEmployeeeID(String eID){
        this.employeeID = eID;
    }
    
    public void setStoreID(String stID){
        this.storeID = stID;
    }
    
    public void setNoDiscountTotal(Double noDiscount){
        this.nodiscountTotal = noDiscount;
    }
    
    public void setSavings(Double saved){
        this.savings = saved;
    }
    
    public void setDate(String time){
        this.date = time;
    }
}
