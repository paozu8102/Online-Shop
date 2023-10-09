/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ImageDAO;
import DAO.PostDAO;
import Model.Comment;
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
        getDataForPostSinglePage(req, resp);
    }
    
    public void getDataForPostSinglePage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        PostDAO postDAO = new PostDAO();
        String id = req.getParameter("id");
        Map<String, String> postData = new PostDAO().getPostInfo(id);
        if (postData.size() == 0) {
            resp.sendRedirect(req.getContextPath()+"/error");
        }
        else{
            postDAO.addViewToPost(id);
            int commentNumber = postDAO.getCommentNumberByPostID(id);
            ArrayList<Image> imageList = new ImageDAO().getPostImageByID(id);
            ArrayList<Comment> rootCommentList = new ArrayList<>();
            ArrayList<ArrayList<Comment>> repCommentList = new ArrayList<>();
            if (commentNumber > 0) {
                rootCommentList = postDAO.getAllRootCommentByPostID(id);
                repCommentList = postDAO.getAllRepComment(rootCommentList);
            }
            
            
            req.setAttribute("imageList", imageList);
            req.setAttribute("post", postData);
            req.setAttribute("CommentNumber", commentNumber);
            req.setAttribute("rootCommentList", rootCommentList);
            req.setAttribute("repCommentList", repCommentList);
            
            req.getRequestDispatcher("blog-single.jsp").forward(req, resp);
        }
    }
}