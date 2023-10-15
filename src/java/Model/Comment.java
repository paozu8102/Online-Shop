/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nhat Anh
 */
public class Comment {
    private int UserID;
    private String Avatar;
    private String UserName;
    private int CommentID;
    private String CommentDate;
    private String CommentContent;
    
    public Comment() {
    }
      
    public Comment(int UserID, String Avatar, String UserName, int CommentID, String CommentDate, String CommentContent) {
        this.UserID = UserID;
        this.Avatar = Avatar;
        this.UserName = UserName;
        this.CommentID = CommentID;
        this.CommentDate = CommentDate;
        this.CommentContent = CommentContent;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int CommentID) {
        this.CommentID = CommentID;
    }

    public String getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(String CommentDate) {
        this.CommentDate = CommentDate;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String CommentContent) {
        this.CommentContent = CommentContent;
    }

    @Override
    public String toString() {
        return "CommentDAO{" + "UserID=" + UserID + ", Avatar=" + Avatar + ", UserName=" + UserName + ", CommentID=" + CommentID + ", CommentDate=" + CommentDate + ", CommentContent=" + CommentContent + '}';
    }
    
    
}
