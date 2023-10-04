/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PostAndBlogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nhat Anh
 */
public class BlogOrPostSingleController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataForBlogOrPostSinglePage(req, resp);
        req.getRequestDispatcher("blog-single.jsp").forward(req, resp);
    }
    
    public void getDataForBlogOrPostSinglePage(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        
        PostAndBlogDAO blogDAO = new PostAndBlogDAO();
        //ArrayList<Map<String, String>> cateAndPicNum = blogDAO.cateAndPicNumber();
        
        //req.setAttribute("cateAndPicNum", cateAndPicNum);
        
    }
}
