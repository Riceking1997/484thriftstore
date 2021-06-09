/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty;

import java.util.ArrayList;

/**
 *
 * @author trane
 */
public class Customer {
    
    String customerID;
    ArrayList<Receipt> receiptList;
    
    public Customer(String customerID){
        
        this.customerID = customerID;
    }
    
    public String getCustomerID(){
        return this.customerID;
    }
    
    public void setCustomerID(String cuID){
        this.customerID = cuID;
    }
    
    public String toString(){
        return customerID;
    }
    
}
