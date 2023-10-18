/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
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
}
