/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import DAO.PostDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Cart;
import Model.Product;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
        ArrayList<Product> homeProducts = productDAO.getLatestProduct();
        ArrayList<String> picOfProduct = productDAO.getOnePicPerProduct(homeProducts);
        ArrayList<Map<String, String>> postInfoList = new PostDAO().getTop4Posts();
        ArrayList<String> picCateList = new CategoryDAO().cateListByPostList(homeProducts);
        
		Cookie[] arr = req.getCookies();
		String txt = "";
		if (arr != null) {
			for (Cookie o : arr) {
				if (o.getName().equals("cart")) {
					txt += o.getValue();

				}
			}
		}
		Cart t = new Cart();
		LinkedHashMap<Product, Integer> cartlist = t.getCart(txt);
		req.setAttribute("cartlist", cartlist);
        req.setAttribute("homeProduct", homeProducts);
        req.setAttribute("postInfoList", postInfoList);
        req.setAttribute("picOfProduct", picOfProduct);
        req.setAttribute("listCate", picCateList);
    }
    
    
}
