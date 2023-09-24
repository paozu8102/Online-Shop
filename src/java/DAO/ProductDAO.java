/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nhat Anh
 */
public class ProductDAO extends DBContext {

    //get 8 latest product in DB: ThanhNX
    public ArrayList<Product> getLatestProduct() {
        ArrayList<Product> homeProduct = new ArrayList<>();
        String command = "SELECT TOP 8 * FROM Product";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                String Description = rs.getString("Description");
                double Height = rs.getDouble("Height");
                double Width = rs.getDouble("Width");
                int Quantity = rs.getInt("Quantity");
                int View = rs.getInt("View");
                double Discount = rs.getDouble("Discount");
                int UserID = rs.getInt("UserID");
                homeProduct.add(new Product(ProductID, ProductName, Price, Description,
                        Height, Width, Quantity, View, Discount, UserID));
            }
        } catch (Exception e) {
        }
        return homeProduct;
    }

    //get each product one picture: ThanhNX
    public ArrayList<String> getOnePicPerProduct(ArrayList<Product> products) {
        ArrayList<String> picList = new ArrayList<>();
        String command = "SELECT  ImageUrl\n"
                + "FROM Product p JOIN ProductImage i\n"
                + "ON p.ProductID = i.ProductID\n"
                + "WHERE p.ProductID = ?";
        for (int i = 0; i < products.size(); i++) {
            try {
                PreparedStatement st = getConnection().prepareStatement(command);
                st.setInt(1, products.get(i).getProductID());
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    picList.add(rs.getString("ImageUrl"));
                }
            } catch (Exception e) {
            }
        }

        return picList;
    }

    //method to test: ThanhNX
    public static void main(String[] args) {
        ArrayList<Product> test = new ProductDAO().getLatestProduct();
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).toString());
        }
        
    }
}
