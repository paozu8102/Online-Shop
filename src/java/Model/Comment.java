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
    private int CommentID;
    private int UserID;
    private int TypeID;
    private int ObjectID;
    private String CommentContent;
    private String CommentDate;

    public Comment() {
    }

    public Comment(int CommentID, int UserID, int TypeID, int ObjectID, String CommentContent, String CommentDate) {
        this.CommentID = CommentID;
        this.UserID = UserID;
        this.TypeID = TypeID;
        this.ObjectID = ObjectID;
        this.CommentContent = CommentContent;
        this.CommentDate = CommentDate;
    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int CommentID) {
        this.CommentID = CommentID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int TypeID) {
        this.TypeID = TypeID;
    }

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int ObjectID) {
        this.ObjectID = ObjectID;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String CommentContent) {
        this.CommentContent = CommentContent;
    }

    public String getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(String CommentDate) {
        this.CommentDate = CommentDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "CommentID=" + CommentID + ", UserID=" + UserID + ", TypeID=" + TypeID + ", ObjectID=" + ObjectID + ", CommentContent=" + CommentContent + ", CommentDate=" + CommentDate + '}';
    }
    
    
}
