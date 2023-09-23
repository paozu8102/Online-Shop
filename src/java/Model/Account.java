/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Account {
    String Email;
    String Password;
    int Status;
    int RoleID;

    public Account() {
    }

    public Account(String Email, String Password, int Status, int RoleID) {
        this.Email = Email;
        this.Password = Password;
        this.Status = Status;
        this.RoleID = RoleID;
    }

    public Account(String Email, String Password, int RoleID) {
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
    }

    public Account(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }
    

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Account{" + "Email=" + Email + ", Password=" + Password + ", Status=" + Status + ", RoleID=" + RoleID + '}';
    }
    
}
