/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Order;
import Model.Product;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBcontext.DBContext {

    //calculates the total number of orders for each day of the past week: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByWeek() {
        String sql = "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, GETDATE()) AS StartDate, GETDATE() AS EndDate\n"
                + ")\n"
                + "SELECT\n"
                + "    DATENAME(WEEKDAY, OrderDate) AS DayName,\n"
                + "    SUM(CASE WHEN OrderDate BETWEEN DateRange.StartDate AND DateRange.EndDate THEN 1 ELSE 0 END) AS TotalOrders\n"
                + "FROM\n"
                + "    [Orders]\n"
                + "CROSS JOIN\n"
                + "    DateRange\n"
                + "WHERE\n"
                + "    OrderDate BETWEEN DateRange.StartDate AND DateRange.EndDate\n"
                + "GROUP BY\n"
                + "    DATENAME(WEEKDAY, OrderDate)\n"
                + "ORDER BY\n"
                + "    MIN(OrderDate)";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        ArrayList<String> daysOfWeek = getLast7Day();
        for (String day : daysOfWeek) {
            listTotal.put("'" + day + "'", "0");
        }
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1) + "'", rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByWeek: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past month: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByMonth() {
        String sql = "WITH DateSequence AS (\n"
                + "    SELECT top 1\n"
                + "        DATEADD(WEEK, DATEDIFF(WEEK, 0, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)), 0) AS WeekStart\n"
                + "    UNION ALL\n"
                + "    SELECT\n"
                + "        DATEADD(WEEK, 1, WeekStart)\n"
                + "    FROM DateSequence\n"
                + "    WHERE\n"
                + "        DATEADD(WEEK, 1, WeekStart) < DATEADD(MONTH, 1, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0))\n"
                + ")\n"
                + "SELECT\n"
                + "    WeekStart,\n"
                + "    DATEADD(DAY, 6, WeekStart) AS WeekEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    DateSequence ds\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON o.OrderDate >= ds.WeekStart AND o.OrderDate < DATEADD(DAY, 7, ds.WeekStart)\n"
                + "GROUP BY\n"
                + "    WeekStart\n"
                + "ORDER BY\n"
                + "    WeekStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past 3 months: BaoMV
    public LinkedHashMap<String, String> getTotalOrderBy3Month() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -2\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                   AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past 6 months: BaoMV
    public LinkedHashMap<String, String> getTotalOrderBy6Month() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -5\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                   AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past year: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByYear() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 1 AS MonthIndex\n"
                + "    UNION ALL\n"
                + "    SELECT MonthIndex + 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthIndex < 12\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    MonthIndex AS MonthNumber,\n"
                + "    CASE\n"
                + "        WHEN MonthIndex = 1 THEN 'January'\n"
                + "        WHEN MonthIndex = 2 THEN 'February'\n"
                + "        WHEN MonthIndex = 3 THEN 'March'\n"
                + "        WHEN MonthIndex = 4 THEN 'April'\n"
                + "        WHEN MonthIndex = 5 THEN 'May'\n"
                + "        WHEN MonthIndex = 6 THEN 'June'\n"
                + "        WHEN MonthIndex = 7 THEN 'July'\n"
                + "        WHEN MonthIndex = 8 THEN 'August'\n"
                + "        WHEN MonthIndex = 9 THEN 'September'\n"
                + "        WHEN MonthIndex = 10 THEN 'October'\n"
                + "        WHEN MonthIndex = 11 THEN 'November'\n"
                + "        WHEN MonthIndex = 12 THEN 'December'\n"
                + "    END AS MonthName,\n"
                + "    DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1) AS MonthStart,\n"
                + "    EOMONTH(DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1)) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(GETDATE()) AND MONTH(o.OrderDate) = MonthIndex\n"
                + "GROUP BY\n"
                + "    MonthIndex\n"
                + "ORDER BY\n"
                + "    MonthIndex;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(2) + "'", rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    public ArrayList<String> getLast7Day() {
        LocalDate currentDate = LocalDate.now();
        LocalDate last7thDay = currentDate.minusDays(6);

        ArrayList<String> daysOfWeek = new ArrayList<>();
        while (!currentDate.isBefore(last7thDay)) {
            String dayName = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            daysOfWeek.add(dayName);
            currentDate = currentDate.minusDays(1);
        }
        Collections.reverse(daysOfWeek);
        return daysOfWeek;
    }

    public List<Order> getManageProduct() {
    List<Order> list = new ArrayList<>();
    String sql = "SELECT Orders.OrderID AS id, CAST(Orders.OrderDate AS DATE) AS date, " +
                 "Orders.UserID AS cusid, Orders.TotalPrice AS totalmoney, " +
                 "Orders.[Status] AS Status, [User].UserName " +
                 "FROM Orders " +
                 "INNER JOIN [User] ON Orders.UserID = [User].UserID";

    try {
        PreparedStatement st = getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setDate(rs.getString("date"));
            order.setCusid(rs.getInt("cusid"));
            order.setTotalmoney(rs.getDouble("totalmoney"));
            order.setStatus(rs.getInt("Status"));

            // Create a User object and set the username
            User user = new User();
            user.setUserName(rs.getString("UserName"));

            // Set the User object in the Order
            order.setUser(user);

            // Add the order to the list
            list.add(order);
        }
    } catch (SQLException e) {
        // Handle SQL exception
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
    } catch (Exception e) {
        // Handle other exceptions
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
    }

    return list;
}


    public static void main(String[] args) {
        (new OrderDAO()).getTotalOrderByWeek();
    }
}
