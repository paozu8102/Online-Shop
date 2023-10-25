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
<<<<<<< HEAD
          private int id;
      private String date;
      private int cusid;
      private double totalmoney;
      private int Status;
      private User user;
=======

    private int id;
    private String date;
    private int cusid;
    private double totalmoney;
    private String customername;
    private String address;
    private String phonenumber;
    
>>>>>>> 32c8775e83638ada5464a660e4dcf3afeb98e58d

    public Order() {
    }

<<<<<<< HEAD
    public Order(int id, String date, int cusid, double totalmoney, int Status) {
        this.id = id;
        this.date = date;
        this.cusid = cusid;
        this.totalmoney = totalmoney;
        this.Status = Status;
    }

    public Order(int id, String date, int cusid, double totalmoney) {
=======
    public Order(int id, String date, int cusid, double totalmoney, String customername, String address, String phonenumber) {
>>>>>>> 32c8775e83638ada5464a660e4dcf3afeb98e58d
        this.id = id;
        this.date = date;
        this.cusid = cusid;
        this.totalmoney = totalmoney;
        this.customername = customername;
        this.address = address;
        this.phonenumber = phonenumber;
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

<<<<<<< HEAD
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", cusid=" + cusid + ", totalmoney=" + totalmoney + ", Status=" + Status + '}';
=======
    public String getCustomername() {
        return customername;
>>>>>>> 32c8775e83638ada5464a660e4dcf3afeb98e58d
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

  

   

}
