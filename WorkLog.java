/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thifty3;

/**
 *
 * @author trane
 */
public class WorkLog {
    
    String worklogID;
    String empID;
    String departmentID;
    String startDate;
    String endDate;
    double hourlyPay;
    double hoursWorked;
    double totalPay;
    
    String storeID;
    
    public WorkLog(){
        
    }
    
    public WorkLog(String worklogID, String empID, String departmentID, String startDate, String endDate,
        double hourlyPay, double hoursWorked){
        
        this.worklogID = worklogID;
        this.empID = empID;
        this.departmentID = departmentID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        this.totalPay = this.hourlyPay * this.hoursWorked;
        
        this.storeID = generateSID(this.departmentID);
        
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
    
    public String getStoreID(){
        return this.storeID;
    }
    
    public String getStartDate(){
        return this.startDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
    
    public double getHourlyPay(){
        return this.hourlyPay;
    }
    
    public double getHoursWorked(){
        return this.hoursWorked;
    }
    
    public double getTotalPay(){
        return this.totalPay;
    }
    
    public void setWorkLogID(String logID){
        this.worklogID = logID;
    }
    
    public void setEmployeeID(String eID){
        this.empID = eID;
    }
    
    public void setStoreID(String SID){
        this.storeID = SID;
    }
    
    public void setDepartmentID(String depID){
        this.departmentID = depID;
    }
    
    public void setStartDate(String sd){
        this.startDate = sd;
    }
    
    public void setEndDate(String ed){
        this.endDate = ed;
    }
    
    public void setHourlyPay(double hp){
        this.hourlyPay = hp;
    }
    
    public void setHoursWorked(double hw){
        this.hoursWorked = hw;
    }
    
    public void setTotalPay(){
        this.totalPay = this.hourlyPay * this.hoursWorked;
    }
    
    public String generateSID(String depID) {
        String result = "";
        if(depID.equals("dep55555") || depID.equals("dep55556") || depID.equals("dep55554")) {
            result = "sto22221";
        } else if(depID.equals("dep55557") || depID.equals("dep55558")) {
            result = "sto22222";
        } else if(depID.equals("dep55559")) {
            result = "sto22223";
        } else if(depID.equals("dep55554")) {
            result = "sto22221";
        } else if(depID.equals("dep55553")) {
            result = "sto22224";
        } else {
            result = "";
        }
        return result;
    }
    
    public String period() {
        return this.startDate + " - " + this.endDate;
    }
}
