/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBcontext.DBContext {

    //retrieve the top three categories with the highest order totals: BaoMV
    public ArrayList<Category> getTopCategory() {
        String sql = "select top 3 CategoryID, sum(total) as sumary\n"
                + "  from\n"
                + "  (select o.ProductID, count(o.ProductID) as total, c.CategoryID \n"
                + "  from OrderDetail o, Category c, ProductCategory p\n"
                + "  where p.CategoryID = c.CategoryID and o.ProductID = p.ProductID\n"
                + "  group by o.ProductID, c.CategoryID ) d\n"
                + "  group by CategoryID\n"
                + "  order by sumary desc";
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int caid = rs.getInt(1);
                listCategory.add(getCategoryById(caid));
            }
        } catch (Exception e) {
            System.out.println("getTopCategory: " + e.getMessage());
        }
        return listCategory;
    }

    //retrieves category information by its ID: BaoMV
    public Category getCategoryById(int id) {
        String sql = "select * from Category where CategoryID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String caName = rs.getString(2);
                boolean status = rs.getBoolean(3);
                return new Category(id, caName, status);
            }
        } catch (Exception e) {
            System.out.println("getCategoryById: " + e.getMessage());
        }
        return new Category();
    }

    //add category for post: ThanhNX
    public void insertPostCategory(String postID, String[] postCate) {
        int index = 0;
        String command = "INSERT INTO [dbo].[PostCategory]\n"
                + "           ([PostID]\n"
                + "           ,[CategoryID])\n"
                + "     VALUES (?, ?)";
        if (postCate.length > 1) {
            for (int i = 1; i < postCate.length; i++) {
                command += ", (?, ?)";
            }
        }
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            for (int i = 0; i < postCate.length; i++) {
                index++;
                ps.setString(index, postID);
                index++;
                ps.setString(index, postCate[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //get category list from a list of product
    public ArrayList<String> cateListByPostList(ArrayList<Product> productList) {
        String command = "SELECT MIN(CategoryID) AS CategoryID\n"
                + "FROM ProductCategory p\n"
                + "GROUP BY p.ProductID\n"
                + "HAVING p.ProductID = ?";
        ArrayList<String> cateList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            try {
                PreparedStatement ps = getConnection().prepareStatement(command);
                ps.setInt(1, productList.get(i).getProductID());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cateList.add(rs.getString("CategoryID"));
                }
            } catch (Exception e) {
            }
        }
        return cateList;
    }

    public static void main(String[] args) {
        ArrayList<Product> pro = new ProductDAO().getLatestProduct();
        ArrayList<String> cate = new CategoryDAO().cateListByPostList(pro);
        System.out.println(cate.size());
    }
}
