/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;


import DAO.ImageDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Cart;
import Model.Comment;
import Model.Image;
import Model.Product;

import java.io.IOException;
import java.io.PrintWriter;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author admin
 */
@WebServlet(name = "DetailControl", urlPatterns = {"/detail"})
public class DetailControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        String cateID = request.getParameter("cid");
        String id = request.getParameter("pid");
        ProductDAO dao = new ProductDAO();
        UserDAO userDAO = new UserDAO();
        List<Product> list = dao.getRelatedProduct(id, cateID);
        Product p = dao.getProductByID(id);
        int userID = p.getUserID();
        if (p!=null){
            User artist = userDAO.getArtistById(userID);
            request.setAttribute("artist", artist);
        }
        ImageDAO c = new ImageDAO();
        List<Image> listI = c.getProductImage(id);
        Cookie[] arr = request.getCookies();
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
        request.setAttribute("cartlist", cartlist);
        request.setAttribute("listP", list);
        request.setAttribute("listI", listI);
        request.setAttribute("detail", p);
        if (userID != 0){
            request.setAttribute("boughtProducts", dao.getNumberOfSales(userID));
            request.setAttribute("saleProducts", dao.getTotalProductActiveCountByUserId(userID));
        }
        
        //get comment data
        int commentNumber = dao.getCommentNumberByProductID(id);
        ArrayList<Comment> rootCommentList = new ArrayList<>();
        ArrayList<ArrayList<Comment>> repCommentList = new ArrayList<>();
        if (commentNumber > 0) {
            rootCommentList = dao.getAllRootCommentByProductID(id);
            repCommentList = dao.getAllRepFeedback(rootCommentList);
        }
        
        request.setAttribute("CommentNumber", commentNumber);
        request.setAttribute("rootCommentList", rootCommentList);
        request.setAttribute("repCommentList", repCommentList);
        request.getRequestDispatcher("product-single.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
