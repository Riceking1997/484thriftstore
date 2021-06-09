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
public class Product {

    String productID;
    String supplierID;
    String productName;
    String productDescription;
    Double purchasePrice;
    Double sellPrice;

    public Product(String productID, String supplierID, String productName, String productDescription, Double purchasePrice, Double sellPrice) {

        this.productID = productID;
        this.supplierID = supplierID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;

    }

    public String getProductID() {
        return this.productID;
    }

    public String getSupplierID() {
        return this.supplierID;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public Double getPurchasePrice() {
        return this.purchasePrice;
    }

    public Double getSellPrice() {
        return this.sellPrice;
    }

    public void setProductID(String pID) {
        this.productID = pID;
    }

    public void setSupplierID(String supID) {
        this.supplierID = supID;
    }

    public void setProductName(String pName) {
        this.productName = pName;
    }

    public void setProductDescription(String pDesc) {
        this.productDescription = pDesc;
    }

    public void setPurchasePrice(Double pPrice) {
        this.purchasePrice = pPrice;
    }

    public void setSellPrice(Double sPrice) {
        this.sellPrice = sPrice;
    }

}
