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
public class Supplier {

    ArrayList<Product> productList;
    String supplierID;
    String supplierName;
    String supplierAddress;
    String startDate;
    String contactName;
    String contactPhone;
    String contactEmail;

    public Supplier(){
        
    }
    
    public Supplier(String supplierID, String supplierName, String supplierAddress, String startDate, String contactName, String contactPhone, String contactEmail) {

        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.startDate = startDate;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;

    }

    public String getSupplierID() {
        return this.supplierID;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public String getSupplierAddress() {
        return this.supplierAddress;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setSupplierID(String supID) {
        this.supplierID = supID;
    }

    public void setSupplierName(String supName) {
        this.supplierName = supName;
    }

    public void setSupplierAddress(String supAddr) {
        this.supplierAddress = supAddr;
    }

    public void setStartDate(String start) {
        this.startDate = start;
    }

    public void setContactName(String coName) {
        this.contactName = coName;
    }

    public void setContactPhone(String coPhone) {
        this.contactPhone = coPhone;
    }

    public void setContactEmail(String coEmail) {
        this.contactEmail = coEmail;
    }

}
