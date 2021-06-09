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
public class WorkLog {
    
    String worklogID;
    String empID;
    String departmentID;
    String daysOfWeek;
    String startShift;
    String endShift;
    
    public WorkLog(){
        
    }
    
    public WorkLog(String worklogID, String empID, String departmentID, String daysOfWeek, String startShift, String endShift){
        
        this.worklogID = worklogID;
        this.empID = empID;
        this.departmentID = departmentID;
        this.daysOfWeek = daysOfWeek;
        this.startShift = startShift;
        this.endShift = endShift;
        
    }
    
    public String getWorkLogID(){
        return this.worklogID;
    }
    
    public String getEmployeeID(){
        return this.empID;
    }
    
    public String getDepartmentID(){
        return this.departmentID;
    }
    
    public String getDaysOfWeek(){
        return this.daysOfWeek;
    }
    
    public String getShiftStart(){
        return this.startShift;
    }
    
    public String getShiftEnd(){
        return this.endShift;
    }
    
    public void setWorkLogID(String logID){
        this.worklogID = logID;
    }
    
    public void setEmployeeID(String eID){
        this.empID = eID;
    }
    
    public void setDepartmentID(String depID){
        this.departmentID = depID;
    }
    
    public void setDaysOfWeek(String dow){
        this.daysOfWeek = dow;
    }
    
    public void setShiftStart(String shiftS){
        this.startShift = shiftS;
    }
    
    public void setShiftEnd(String shiftE){
        this.endShift = shiftE;
    }
}
