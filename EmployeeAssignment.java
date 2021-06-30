/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty3;

/**
 *
 * @author Diallo
 */
public class EmployeeAssignment {
    
     String EmployeeID;
     String DepartmentID;
     
     public EmployeeAssignment(String DID, String EID){
         this.EmployeeID = EID;
         this.DepartmentID = DID;
     }
     
     public String getEmployeeID(){
         return EmployeeID;
     }
     
     public String getDepartmentID(){
         return DepartmentID;
     }
}
