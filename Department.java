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
public class Department {
    
    ArrayList<Employee>  empList;
    ArrayList<Inventory>  invenList;
    String deptID;
    String storeID;
    String deptDescription;
    int deptAisleNumber;
    
    public Department(){
        
    }
    
    public Department(String deptID, String storeID, String deptDescription, int deptAisleNumber){
        
        this.deptID = deptID;
        this.storeID = storeID;
        this.deptDescription = deptDescription;
        this.deptAisleNumber = deptAisleNumber;
        
    }
    
    public String getDepartmentID(){
        return this.deptID;
    }
    
    public String getStoreID(){
        return this.storeID;
    }
    
    public String getDeparmentDescription(){
        return this.deptDescription;
    }
    
    public int getDeparmentAisle(){
        return this.deptAisleNumber;
    }
    
    public void setDepartmentID(String departID){
        this.deptID = departID;
    }
    
    public void setStoreID(String storeeID){
        this.storeID = storeeID;
    }
    
    public void setDeparmentDescription(String departDescription){
        this.deptDescription = departDescription;
    }
    
    public void setDeparmentAisle(int departAisleNumber){
        this.deptAisleNumber = departAisleNumber;
           
    }
}
