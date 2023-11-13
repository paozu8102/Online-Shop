/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Cart;

import Model.Category;
import Model.Product;
import Model.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "ShopController", urlPatterns = { "/shop" })
public class ShopController extends HttpServlet {

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
		String promat = request.getParameter("promat");
		String protopic = request.getParameter("protopic");
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

		String indexPage = request.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";

		}

		int index = Integer.parseInt(indexPage);
		ProductDAO c = new ProductDAO();
		int count = c.getTotalProduct();
		int endPage = count / 15;
		if (count % 15 != 0) {
			endPage++;
		}
		List<Product> list;

		if ("all".equals(sortprice) || "all".equals(sortname)) {
			list = c.getAllProduct(index);
			request.setAttribute("endP", endPage);
			request.setAttribute("tag", index);
		} else if ("asc".equals(sortprice)) {
			list = c.getpriceAsc();
		} else if ("desc".equals(sortprice)) {
			list = c.getpriceDesc();
		} else if ("asc".equals(sortname)) {
			list = c.getNameAsc();
		} else if ("desc".equals(sortname)) {
			list = c.getNameDesc();
		} else if (promat != null) {
			list = c.getProductByMatID(promat);

		} else if (protopic != null) {
			list = c.getProductByTopic(protopic);

		} else if (discountmax != null && discountmin != null) {
			list = c.FilterDiscountProduct(discountmin, discountmax);

		} else if (minprice != null && maxprice != null) {
			list = c.FilterPriceProduct(minprice, maxprice);
		} else if (minwidth != null) {
			list = c.FilterSizeProduct(minwidth, maxwidth, minheight, maxheight);
		} else if (txtSearch != null) {
			list = c.searchProductByName(txtSearch);
		} else {

			list = c.getAllProduct(index);
			request.setAttribute("endP", endPage);
			request.setAttribute("tag", index);
		}

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
		request.getRequestDispatcher("shop.jsp").forward(request, response);

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
