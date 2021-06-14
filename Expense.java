  
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
public class Expense {

    String billID;
    String storeID;
    String expenseType;
    Double expenseTotal;
    //String issueDate;
    String dueDate;

    public Expense() {

    }

    public Expense(String billID, String storeID, String expenseType, Double expenseTotal, String dueDate) {

        this.billID = billID;
        this.storeID = storeID;
        this.expenseType = expenseType;
        this.expenseTotal = expenseTotal;
        //this.issueDate = issueDate;
        this.dueDate = dueDate;
            
    }
    
    public String getBillID(){
        return this.billID;
    }
    
    public String getStoreID(){
        return this.storeID;
    }
    
    public String getExpenseType(){
        return this.expenseType;
    }
    
    public Double getExpenseTotal(){
        return this.expenseTotal;
    }
    
    /*public String getIssueDate(){
        return this.issueDate;
    }*/
    
    public String getDueDate(){
        return this.dueDate;
    }
    
    public void setBillID(String bID){
        this.billID = bID;
    }
    
    public void setStoreID(String sID){
        this.storeID = sID;
    }
    
    public void setExpenseType(String eType){
        this.expenseType = eType;
    }
    
    public void setExpenseTotal(Double eTotal){
        this.expenseTotal = eTotal;
    }
    
    /*public void setIssueDate(String issDate){
        this.issueDate = issDate;
    }*/
    
    public void setDueDate(String duDate){
        this.dueDate = duDate;
    }

}
