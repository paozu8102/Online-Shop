/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer
 */
public class User extends Account {

    private int UserID;
    private String UserName;
    private int Gender;
    private String PhoneNumber;
    private String Address;
    private String Avatar;

    public User() {
    }

    public User(int UserID, String UserName, int Gender, String PhoneNumber, String Address, String Avatar, String Email) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Gender = Gender;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.Avatar = Avatar;
        this.Email = Email;
    }

    public User(String UserName, String PhoneNumber, String Email) {
        this.UserName = UserName;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
    }

    public User(int UserID, String UserName, int Gender, String PhoneNumber, String Address, String Avatar, String Email, int Status, int RoleID) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Gender = Gender;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.Avatar = Avatar;
        this.Email = Email;
        this.Status = Status;
        this.RoleID = RoleID;

    }
 public User(int UserID, String UserName,String Email, String PhoneNumber, int Status) {
        this.UserID = UserID;
        this.UserName = UserName;
        
        this.PhoneNumber = PhoneNumber;
        
        this.Email = Email;
        this.Status = Status;
       

    }
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }
    
    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", UserName=" + UserName + ", Gender=" + Gender + ", PhoneNumber=" + PhoneNumber + ", Address=" + Address + ", Avatar=" + Avatar + ", Email=" + Email + ", Status=" + Status + ", RoleID=" + RoleID + '}';
    }

}
