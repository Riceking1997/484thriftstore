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
public class PayPeriod {
    
    String startDate;
    String endDate;
    
    public PayPeriod(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return this.startDate + " - " + this.endDate;
    }
}
