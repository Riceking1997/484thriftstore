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
public class Store {

    ArrayList<Department> deptList;
    ArrayList<Expense> expenseList;
    String storeID;
    int storeNumber;
    String storeAddress;
    String openingDate;

    public Store() {

    }

    public Store(String storeID, int storeNumber, String storeAddress, String openingDate) {

        this.storeID = storeID;
        this.storeNumber = storeNumber;
        this.storeAddress = storeAddress;
        this.openingDate = openingDate;

    }

    public String getStoreID() {
        return this.storeID;
    }

    public int getStoreNumber() {
        return this.storeNumber;
    }

    public String getStoreAddress() {
        return this.storeAddress;
    }

    public String getOpeningDate() {
        return this.openingDate;
    }

    public void setStoreID(String stID) {

        this.storeID = stID;

    }

    public void setStoreNumber(int stNumber) {

        this.storeNumber = stNumber;
    }

    public void setStoreAddress(String stAddress) {

        this.storeAddress = stAddress;
    }

    public void setOpeningDate(String openDate) {

        this.openingDate = openDate;
    }

}
