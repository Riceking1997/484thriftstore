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
public class ClubMember extends Customer {
    
    String memberName;
    String memberDate;
    String memberEmail;
    String memberPhone;
    String memberAddress;
    Double totalSaved;
    
    public ClubMember(String customerID, String memberName, String memberDate, String memberEmail, String memberPhone, String memberAddress, Double totalSaved){
        super(customerID);
        this.memberName = memberName;
        this.memberDate = memberDate;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.totalSaved = totalSaved;
    }
    
    public String getMemberName(){
        return this.memberName;
    }
    
    public String getMemberDate(){
        return this.memberDate;
    }
    
    public String getMemberEmail(){
        return this.memberEmail;
    }
    
    public String getMemberPhone(){
        return this.memberPhone;
    }
    
    public String getMemberAddress(){
        return this.memberAddress;
    }
    
    public Double getTotalSaved(){
        return this.totalSaved;
    }
    
    public void setMemberName(String mName){
        this.memberName = mName;
    }
    
    public void setMemberDate(String mDate){
        this.memberDate = mDate;
    }
    
    public void setMemberEmail(String mEmail){
        this.memberEmail = mEmail;
    }
    
    public void setMemberPhone(String mPhone){
        this.memberPhone = mPhone;
    }
    
    public void setMemberAddress(String mAddr){
        this.memberAddress = mAddr;
    }
    
    public void setTotalSaved(Double sav){
        this.totalSaved += sav;
    }
}
