/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;



import DAO.UserDAO;
import Model.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "SettingControl", urlPatterns = {"/settingcontrol"})
public class SettingControl extends HttpServlet {

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
        UserDAO c = new UserDAO();
        List<Setting> listC = c.getAllSetting();
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("setting-management.jsp").forward(request, response);
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
   String settingtype = request.getParameter("setting_type");
String settingstatus = request.getParameter("setting_status");
UserDAO c = new UserDAO();
List<Setting> listC = null;

if ("all".equals(settingtype) || "all".equals(settingstatus)) {
    listC = c.getAllSetting();
} else if ("type".equals(settingtype)) {
    listC = c.getAllSettingTyp();
} else if ("category".equals(settingtype)) {
    listC = c.getAllSettingCat();
} else if ("active".equals(settingstatus)) {
    listC = c.getSettingActive();
} else if ("inactive".equals(settingstatus)) {
    listC = c.getSettingInactive();
}

request.setAttribute("listC", listC);
request.getRequestDispatcher("setting-management.jsp").forward(request, response);

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
