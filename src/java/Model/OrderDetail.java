/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class OrderDetail {
    private int did, oid, pid;
    private int quantity;
    private double price;
 private String status;
 private String expDate;
private String delDate;
private Product product;
    public OrderDetail() {
    }

    public OrderDetail(int oid, int pid, int quantity, double price, String status) {
        
        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public OrderDetail(int did, int oid, int pid, int quantity, double price, String status, String expDate, String delDate) {
          this.did = did;
        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.expDate = expDate;
        this.delDate = delDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "oid=" + oid + ", pid=" + pid + ", quantity=" + quantity + ", price=" + price + ", status=" + status + ", expDate=" + expDate + ", delDate=" + delDate + ", product=" + product + '}';
    }

  

    

}
