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
   
    
    public ManagementEmployee(String empID, String storeID, String empName, String empAddress, String empPhone, String empEmail, Double empSalary,  
                    String username, String password, String empType, boolean isManager){
        
        super(empID,storeID,empName,empAddress,empPhone,empEmail,empSalary,username,password,empType);
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


