/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Product;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nhat Anh
 */
public class HomeController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataForHome(req, resp);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
    //ThanhNX
    public static void getDataForHome(HttpServletRequest req, HttpServletResponse resp){
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();
        ArrayList<Product> homeProducts = productDAO.getLatestProduct();
        ArrayList<User> homeUsers = userDAO.getTopUser();
        ArrayList<String> roleOfHomeUser = userDAO.getRoleList(homeUsers);
        ArrayList<String> picOfProduct = productDAO.getOnePicPerProduct(homeProducts);
        
        
        req.setAttribute("homeProduct", homeProducts);
        req.setAttribute("homeUsers", homeUsers);
        req.setAttribute("roleOfHomeUser", roleOfHomeUser);
        req.setAttribute("picOfProduct", picOfProduct);
    }
    
    
}
