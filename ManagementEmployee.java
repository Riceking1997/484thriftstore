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
public class ManagementEmployee extends Employee {
    
    boolean isManager;
   
    
    public ManagementEmployee(String empID, String empName, Double empSalary,  String empAddress, String empEmail, String empPhone, String empType, String username, String password, boolean isManager){
        
        super(empID,empName,empSalary,empAddress,empEmail,empPhone,empType,username,password);
        setIsManager(empType);
    }
    
    public boolean getIsManager(){
        return this.isManager;
    }
    
    public void setIsManager(String eType){
        
        if(eType.contains("manager") || eType.contains("executive") || eType.contains("ceo")){
            this.isManager = true;
        }
        else{
            this.isManager = false;
        }
    }
    
}

