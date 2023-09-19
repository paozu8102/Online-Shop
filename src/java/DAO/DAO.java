/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Account;
import java.sql.Connection;

/**
 *
 * @author Mai Vu Bao
 */
public class DAO {
    protected Connection con;
    protected String status="";
    public DAO(){
        try{
            con = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Error Connection "+e.getMessage();
        }
    }
}
