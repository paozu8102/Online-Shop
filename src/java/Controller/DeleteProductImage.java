/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ImageDAO;
import DAO.ProductDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;

/**
 *
 * @author admin
 */
@WebServlet(name = "DeleteProductImage", urlPatterns = {"/deleteimage"})

public class DeleteProductImage extends HttpServlet {

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
        String url = request.getParameter("url");
        String id = request.getParameter("id");
        String pid = request.getParameter("pid");

        ImageDAO dao = new ImageDAO();

        // Check if the image URL and ID are not null
        if (url != null && id != null) {
            // Delete the image using the ImageDAO
            dao.deleteImage(id);

            // Optionally, delete the image file using the URL (if it's a file path)
            deleteImage(url);
        }

        // Redirect to the "editproduct" page with the "pid" parameter
        response.sendRedirect("editproduct?pid=" + pid);
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
    public void deleteImage(String fileName) {
        ServletContext context = getServletContext();
        String rootPath = context.getRealPath("/");
        rootPath = rootPath.replace("build\\", "");

        String uploadPath = rootPath + "\\images";

        File imageFile = new File(uploadPath, fileName);

        if (imageFile.exists() && imageFile.isFile()) {
            if (imageFile.delete()) {
                System.out.println("Deleted image file: " + fileName);
            } else {
                System.err.println("Failed to delete image file: " + fileName);
            }
        } else {
            System.err.println("Image file not found: " + fileName);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
