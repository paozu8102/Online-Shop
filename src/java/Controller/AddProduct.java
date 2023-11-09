/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Account;
import Model.User;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 *
 * @author admin
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/addproduct"})
@MultipartConfig(location = "D:\\",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 100)
public class AddProduct extends HttpServlet {

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
      

        // Redirect to the appropriate page
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
      HttpSession session = request.getSession();
        // Get the array of Part objects for "image"
        Collection<Part> imageParts = request.getParts().stream()
                .filter(part -> "image".equals(part.getName()))
                .collect(Collectors.toList());

        String[] category = request.getParameterValues("category");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String height = request.getParameter("height");
        String width = request.getParameter("width");
        String quantity = request.getParameter("quantity");
        String description = request.getParameter("description");
        ProductDAO dao = new ProductDAO();

        User u = (User) session.getAttribute("user");
        dao.addProduct(name, price, description, height, width, quantity, u.getUserID());
        int id = dao.getNewestProductID();
        for (String string : category) {
            dao.addCategory(id, string);
        }
        // Process each uploaded image
        for (Part imagePart : imageParts) {

            ServletContext context = getServletContext();
            // Lấy root path của ứng dụng web
            String rootPath = context.getRealPath("/");
            rootPath = rootPath.replace("build\\", "");

            String uploadPath = rootPath + "\\images";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = UUID.randomUUID().toString() + "_" + Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String filePath = uploadPath + File.separator + fileName;

            try ( InputStream input = imagePart.getInputStream();  OutputStream output = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print any exceptions for debugging
            }
            dao.addImage(id, fileName);
        }

        response.sendRedirect("manage");
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
