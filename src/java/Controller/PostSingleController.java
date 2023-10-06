/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ImageDAO;
import DAO.PostDAO;
import Model.Image;
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
public class PostSingleController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataForBlogOrPostSinglePage(req, resp);
    }
    
    public void getDataForBlogOrPostSinglePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PostDAO blogDAO = new PostDAO();
        String id = req.getParameter("id");
        Map<String, String> postData = new PostDAO().getPostInfo(id);
        if (postData.size() == 0) {
            resp.sendRedirect(req.getContextPath()+"/error");
        }
        else{
            blogDAO.addViewToPost(id);
            ArrayList<Image> imageList = new ImageDAO().getPostImageByID(id);
            
            req.setAttribute("imageList", imageList);
            req.setAttribute("post", postData);
            req.getRequestDispatcher("blog-single.jsp").forward(req, resp);
        }
    }
}
