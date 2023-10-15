/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Post {
    String PostID,Title ,Content,Date,UserID,StatusID,PostType,View;

    public Post() {
    }

    public Post(String PostID, String Title, String Content, String Date, String UserID, String StatusID, String PostType, String View) {
        this.PostID = PostID;
        this.Title = Title;
        this.Content = Content;
        this.Date = Date;
        this.UserID = UserID;
        this.StatusID = StatusID;
        this.PostType = PostType;
        this.View = View;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String PostID) {
        this.PostID = PostID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getStatusID() {
        return StatusID;
    }

    public void setStatusID(String StatusID) {
        this.StatusID = StatusID;
    }

    public String getPostType() {
        return PostType;
    }

    public void setPostType(String PostType) {
        this.PostType = PostType;
    }

    public String getView() {
        return View;
    }

    public void setView(String View) {
        this.View = View;
    }
    
}
