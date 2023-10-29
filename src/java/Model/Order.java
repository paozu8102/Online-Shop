/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class Order {


    private int id;
    private String date;
    private int cusid;
    private double totalmoney;
    private String customername;
    private String address;
    private String phonenumber;
    private User user;
    private String payment;
    private OrderDetail orderDetail;

    public Order() {
    }


    public Order(int id, String date, int cusid, double totalmoney, String customername, String address, String phonenumber) {

        this.id = id;
        this.date = date;
        this.cusid = cusid;
        this.totalmoney = totalmoney;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public Order(int id, String date, int cusid, double totalmoney, String customername, String address, String phonenumber, String payment) {
        this.id = id;
        this.date = date;
        this.cusid = cusid;
        this.totalmoney = totalmoney;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
        this.payment = payment;
    }


    public Order(int id, double totalmoney, String customername, String address, String phonenumber) {
        this.id = id;
        this.totalmoney = totalmoney;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }


    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomername() {
        return customername;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", cusid=" + cusid + ", totalmoney=" + totalmoney + ", customername=" + customername + ", address=" + address + ", phonenumber=" + phonenumber + ", user=" + user + ", payment=" + payment + ", orderDetail=" + orderDetail + '}';
    }

   

}
