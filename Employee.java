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
public class Employee {
    
    ArrayList<WorkLog> worklogList;
    String empID;
    String empName;
    Double empSalary;
    String empAddress;
    String empEmail;
    String empPhone;
    String empType;
    
    public Employee(){
        
    }
    
    public Employee(String empID, String empName, Double empSalary,  String empAddress, String empEmail, String empPhone, String empType){
        
        this.empID = empID;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAddress = empAddress;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empType = empType;
        
    }
    
    public String getEmployeeID(){
        return this.empID;
    }
    
    public String getEmployeeName(){
        return this.empName;
    }
    
    public Double getEmployeeSalary(){
        return this.empSalary;
    }
    
    public String getEmployeeAddress(){
        return this.empAddress;
    }
    
    public String getEmployeeEmail(){
        return this.empEmail;
    }
    
    public String getEmployeePhone(){
        return this.empPhone;
    }
    
    public String getEmployeeType(){
        return this.empType;
    }
    
    public void setEmployeeID(String eID){
        this.empID = eID;
    }
    
    public void setEmployeeName(String eName){
        this.empName = eName;
    }
    
    public void setEmployeeSalary(Double eSalary){
        this.empSalary = eSalary;
    }
    
    public void setEmployeeAddress(String eAddress){
        this.empAddress = eAddress;
    }
    
    public void setEmployeeEmail(String eEmail){
        this.empEmail = eEmail;
    }
    
    public void setEmployeePhone(String ePhone){
        this.empPhone = ePhone;
    }
    
    public void setEmployeeType(String eType){
        this.empType = eType;
    }
}
