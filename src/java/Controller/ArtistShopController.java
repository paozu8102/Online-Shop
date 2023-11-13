/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import DAO.UserDAO;
import Model.Cart;

import Model.Category;
import Model.Product;
import Model.Setting;
import Model.User;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet(name = "ArtistShopController", urlPatterns = {"/artist-shop"})
public class ArtistShopController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String promat = request.getParameter("promat"); // a
        String protopic = request.getParameter("protopic"); // a
        String sortprice = request.getParameter("sortprice");
        String sortname = request.getParameter("sortname");
        String minprice = request.getParameter("minprice");
        String maxprice = request.getParameter("maxprice");
        String minwidth = request.getParameter("minwidth");
        String maxwidth = request.getParameter("maxwidth");
        String minheight = request.getParameter("minheight");
        String maxheight = request.getParameter("maxheight");
        String discountmin = request.getParameter("discountmin");
        String discountmax = request.getParameter("discountmax");
        String txtSearch = request.getParameter("txt");
        String artisId = request.getParameter("artistId");

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";

        }

        if (promat == null || promat.trim().isEmpty()) {
            promat = request.getParameter("promatHidden");
        }

        if (protopic == null || protopic.trim().isEmpty()) {
            protopic = request.getParameter("protopicHidden");
        }
        request.setAttribute("promat", promat);
        request.setAttribute("protopic", protopic);

        int index = Integer.parseInt(indexPage);
        ProductDAO c = new ProductDAO();
        UserDAO userDao = new UserDAO();
        User artist;
        if (artisId != null && !artisId.trim().isEmpty()) {
            artist = userDao.getArtistById(Integer.parseInt(artisId));
            request.setAttribute("artist", artist);
        } else {
            request.getRequestDispatcher("/shop").forward(request, response);
            return;
        }

        List<Product> list =
                c.filterAndPaginateProducts
                        (promat, protopic, sortprice, sortname, minprice, maxprice, minwidth, maxwidth, minheight, maxheight, discountmin, discountmax, txtSearch, artisId, Integer.parseInt(indexPage), 15);

        int count = list.size();
        int endPage = count / 15;
        if (count % 15 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
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
        List<Category> listC = c.getProductCate();
        List<Category> listT = c.getProductTopic();

        request.setAttribute("listC", listC);
        request.setAttribute("listT", listT);
        request.setAttribute("saleProducts", c.getTotalProductActiveCountByUserId(artist.getUserID()));
        request.setAttribute("boughtProducts", c.getNumberOfSales(artist.getUserID()));
        System.out.println("---list.size()----:" + list.size());
        System.out.println("---c.getNumberOfSales(artist.getUserID()----:" + c.getNumberOfSales(artist.getUserID()));
        request.getRequestDispatcher("artist-shop.jsp").forward(request, response);

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">

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
