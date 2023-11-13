/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ImageDAO;
import DAO.PostDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

/**
 *
 * @author Nhat Anh
 */
public class CreatePostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] imageArray = req.getParameterValues("image");
        String[] categoryArray = req.getParameterValues("category");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String userID = req.getParameter("userID");
        
        //if (content == null || userID == null || title == null || categoryArray == null) {
        //    req.getRequestDispatcher("error.jsp").forward(req, resp);
        //}
        
        //add post
        String postID = new PostDAO().addPostAndGetID(content, userID, title);
        //add categories
        new CategoryDAO().insertPostCategory(postID, categoryArray);
        
        //add image
        if (imageArray != null) {
            String imagesFolderPath = new File(getServletContext().getRealPath("/")).getParent();
            imagesFolderPath = new File(imagesFolderPath).getParent();
            imagesFolderPath += "/web/images/post-image";
            for (int i = 0; i < imageArray.length; i++) {
                byte[] decodedData = Base64.getDecoder().decode(imageArray[i].split(",")[1]);
                String fileName = generateUniqueFileName()+".jpg";
                String imagePath = imagesFolderPath + File.separator + fileName;
                try ( FileOutputStream fileOutputStream = new FileOutputStream(imagePath)) {
                    fileOutputStream.write(decodedData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new ImageDAO().addNewPostImage("images/post-image/"+fileName, postID);
            }
            
        }
        
//        req.setAttribute("categoryArray", categoryArray);
//        req.getRequestDispatcher("error.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/UserProfile");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getDataForCreatePostPage(req, resp);
    }

    public void getDataForCreatePostPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
        ArrayList<Map<String, String>> categoryList = new PostDAO().getPostCategory();

        req.setAttribute("cateList", categoryList);
        req.setAttribute("user", user);
        req.getRequestDispatcher("post-create.jsp").forward(req, resp);
    }

    public static String generateUniqueFileName() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int FILENAME_LENGTH = 30;
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder sb = new StringBuilder(FILENAME_LENGTH);
        for (int i = 0; i < FILENAME_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
