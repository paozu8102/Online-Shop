/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nhat Anh
 */
public class Product {
    private int ProductID;
    private String ProductName;
    private double Price;
    private String Description;
    private double Height;
    private double Width;
    private int Quantity;
    private int View;
    private double Discount;
    private int UserID;

    public Product() {
    }

    public Product(int ProductID, String ProductName, double Price, String Description, double Height, double Width, int Quantity, int View, double Discount, int UserID) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Description = Description;
        this.Height = Height;
        this.Width = Width;
        this.Quantity = Quantity;
        this.View = View;
        this.Discount = Discount;
        this.UserID = UserID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double Height) {
        this.Height = Height;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double Width) {
        this.Width = Width;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getView() {
        return View;
    }

    public void setView(int View) {
        this.View = View;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", Price=" + Price + ", Description=" + Description + ", Height=" + Height + ", Width=" + Width + ", Quantity=" + Quantity + ", View=" + View + ", Discount=" + Discount + ", UserID=" + UserID + '}';
    }
    
    
}
