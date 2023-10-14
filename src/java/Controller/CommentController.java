/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Nhat Anh
 */
public class CommentController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userID = req.getParameter("userID");
        String objectID = req.getParameter("objectID");
        String content = req.getParameter("content");
        CommentDAO commentDAO = new CommentDAO();
        if (action.equals("comment")) {
            commentDAO.addComment(Integer.valueOf(userID), Integer.valueOf(objectID), content);
        }
        else if (action.equals("commentRep")) {
            int commentRepID = Integer.valueOf(req.getParameter("commentRepID"));
            commentDAO.addRepComment(Integer.valueOf(userID), Integer.valueOf(objectID), content, commentRepID);
        }
        else if (action.equals("commentDelete")) {
            int commentDeleteID = Integer.valueOf(req.getParameter("commentID"));
            commentDAO.deleteComment(commentDeleteID);
        }
        resp.sendRedirect(req.getContextPath()+"/PostSingle?id="+Integer.valueOf(objectID));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    
}
