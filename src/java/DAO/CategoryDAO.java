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
    // retrieve the top three types (object types) with the highest order totals: BaoMV
    public ArrayList<String> getTopType() {
        String sql = "select top 3 TypeID, TypeName, count(TypeID) as sumary\n"
                + "  from\n"
                + "  (select o.ProductID, count(o.ProductID) as total, c.CategoryID, ot.TypeID, ot.TypeName \n"
                + "  from OrderDetail o, Category c, ProductCategory p, ObjectType ot\n"
                + "  where p.CategoryID = c.CategoryID and o.ProductID = p.ProductID and ot.TypeID = c.ObjectTypeID\n"
                + "  group by o.ProductID, c.CategoryID, ot.TypeName, ot.TypeID ) d\n"
                + "  group by TypeName, TypeID\n"
                + "  order by sumary desc";
        ArrayList<String> listTopType = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String id = rs.getString(1);
                listTopType.add(id + "-" + name);
            }
        } catch (Exception e) {
            System.out.println("getTopType: " + e.getMessage());
        }
        while (listTopType.size() < 3) {            
            listTopType.add("no data-no data");
        }
        return listTopType;
    }

}
