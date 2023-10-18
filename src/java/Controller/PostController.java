/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Nhat Anh
 */
public class PostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataForPostPage(req, resp);
        req.getRequestDispatcher("blog.jsp").forward(req, resp);
    }

    public void getDataForPostPage(HttpServletRequest req, HttpServletResponse resp) {
        String searchKey = req.getParameter("searchKey");
        String cate = req.getParameter("cate");
        if (searchKey == null) {
            searchKey = "";
        }

        PostDAO postDAO = new PostDAO();
        ArrayList<Map<String, String>> cateAndPostNumList = postDAO.getPostNumberPerCategory();
        ArrayList<Map<String, String>> postList = postDAO.getAllPosts("%" + searchKey.toLowerCase() + "%");
        ArrayList<Map<String, String>> recentPostList = new PostDAO().getRecentPost("-1");
        ArrayList<Integer> idList = new ArrayList<>();
        try {
            idList = postDAO.getPostIDByCategory(Integer.valueOf(cate));
            Iterator<Map<String, String>> iterator = postList.iterator();
            while (iterator.hasNext()) {
                Map<String, String> map = iterator.next();
                if (!idList.contains(Integer.valueOf(map.get("BlogID")))) {
                    iterator.remove(); //
                }
            }
        } catch (Exception e) {
        }

        req.setAttribute("cateAndPostNumList", cateAndPostNumList);
        req.setAttribute("postList", postList);
        req.setAttribute("recentPostList", recentPostList);
        req.setAttribute("searchKey", searchKey);
    }

}
