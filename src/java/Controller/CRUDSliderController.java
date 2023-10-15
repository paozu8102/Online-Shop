/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ImageDAO;
import Model.Image;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CRUDSliderController", urlPatterns = {"/crudslider"})
public class CRUDSliderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CRUDSliderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CRUDSliderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String mode = request.getParameter("mode");
        ImageDAO imageDAO = new ImageDAO();
        switch (mode) {
            case "delete":
                imageDAO.deleteImage(id);
                request.getRequestDispatcher("manageslide").forward(request, response);
                break;
            case "add":
                request.setAttribute("action", "add");
                request.setAttribute("action", "add");
                request.getRequestDispatcher("EditSlider.jsp").forward(request, response);
                break;
            case "update":
                Image image = imageDAO.getImageById(id);
                request.setAttribute("i", image);
                request.setAttribute("action", "update");
                request.getRequestDispatcher("EditSlider.jsp").forward(request, response);
                break;
            case "submitEdit":
                String action = request.getParameter("action");
                String imageURL = request.getParameter("imageURL");
                if(action.equals("update")) {
                    imageDAO.updateImage(id, imageURL);
                }
                else {
                    imageDAO.addNew(imageURL);
                }
                request.getRequestDispatcher("manageslide").forward(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
