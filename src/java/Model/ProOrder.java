/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class ProOrder {

    
       private int oid, pid;
        private String productname;
        private String productimage;
    private int quantity;
    private double price;
    private String date;
    private int cusid;
    private int sellid;
    private String sellname;
    private String customername;
    private String address;
    private String phonenumber;
    private String status;

    public ProOrder() {
    }

    public ProOrder(int oid,String productname, String productimage, int quantity, double price, String date, String sellname, String customername, String address, String phonenumber, String status) {
        this.oid = oid;
        this.productname = productname;
        this.productimage = productimage;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.sellname = sellname;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
        this.status = status;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public int getSellid() {
        return sellid;
    }

    public void setSellid(int sellid) {
        this.sellid = sellid;
    }

    public String getSellname() {
        return sellname;
    }

    public void setSellname(String sellname) {
        this.sellname = sellname;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

}
