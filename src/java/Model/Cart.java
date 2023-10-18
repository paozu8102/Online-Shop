/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ProductDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import Model.Product;

/**
 *
 * @author HP
 */
public class Cart {

    public LinkedHashMap<Product, Integer> getCart(String txt) {
        ProductDAO pd = new ProductDAO();
        LinkedHashMap<Product, Integer> historyMap = new LinkedHashMap<>();

        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");

                for (String i : s) {
                    String[] n = i.split(":");

                    if (n.length == 2) {
                        String pid = n[0];
                        int quantity = Integer.parseInt(n[1]);

                        Product p = pd.getProductByID(pid);

                        if (p != null) {
                            historyMap.put(p, quantity);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException here if needed.
            e.printStackTrace();
        }

        return historyMap;
    }

    // xử lí id trùng lặp
    public String processString(String input) {

        Map<String, Integer> map = new HashMap<>();

        String[] parts = input.split("/");
        for (String part : parts) {
            String[] s = part.split(":");
            String id = s[0];
            int qty = Integer.parseInt(s[1]);

            if (map.containsKey(id)) {
                qty += map.get(id);
            }

            map.put(id, qty);
        }

        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result += entry.getKey() + ":" + entry.getValue() + "/";
        }

        // Kiểm tra nếu chuỗi kết quả không rỗng thì loại bỏ ký tự '/' cuối cùng
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public String updateCartQuantity(String input, String pid, int newQty) {

        Map<String, Integer> map = new HashMap<>();

        String[] parts = input.split("/");
        for (String part : parts) {
            String[] s = part.split(":");
            String id = s[0];
            int qty = Integer.parseInt(s[1]);

            map.put(id, qty);
        }

        // Cập nhật lại số lượng của key cần thay đổi
        map.put(pid, newQty);

        // Chuyển map về chuỗi
        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result += entry.getKey() + ":" + entry.getValue() + "/";
        }

        // Kiểm tra nếu chuỗi kết quả không rỗng thì loại bỏ ký tự '/' cuối cùng
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public String getQuantityBypid(String input, String[] ids) {
    // Create a map to store ID-Quantity pairs
    Map<String, Integer> map = new HashMap<>();

    // Split the input string into parts and populate the map
    String[] parts = input.split("/");
    for (String part : parts) {
        String[] split = part.split(":");
        if (split.length == 2) {
            String id = split[0];
            int qty = Integer.parseInt(split[1]);
            map.put(id, qty);
        }
    }

    // Initialize a result string
    String result = "";

    // Iterate over the provided IDs
    for (String id : ids) {
        Integer qty = map.get(id);
        if (qty != null) {
            // If a matching ID is found, add it to the result string
            result += id + ":" + qty + "/";
        }
    }

    // Remove the trailing slash if the result is not empty
    if (!result.isEmpty()) {
        result = result.substring(0, result.length() - 1);
    }

    return result;
}

    public int getQuantityById(String input, String id) {
        if (input == null || input.isEmpty()) {
            return 0; // Return 0 if the input is empty or null
        }

        Map<String, Integer> map = new HashMap<>();

        // Split the input string into parts
        String[] parts = input.split("/");
        for (String part : parts) {
            String[] s = part.split(":");
            String productId = s[0];
            int quantity = Integer.parseInt(s[1]);
            map.put(productId, quantity);
        }

        // Check if the product with the specified ID is in the cart
        if (map.containsKey(id)) {
            return map.get(id); // Return the quantity of the product
        } else {
            return 0; // Return 0 if the product is not in the cart
        }
    }

    public String removeProductinCart(String input, String pid) {
        if (input.equals("") || input == null) {
            return "";
        }
        Map<String, Integer> map = new HashMap<>();

        String[] parts = input.split("/");
        for (String part : parts) {
            String[] s = part.split(":");
            String id = s[0];
            int qty = Integer.parseInt(s[1]);

            if (!id.equals(pid)) {
                map.put(id, qty);
            }
        }

        // Chuyển map về chuỗi
        String result = "";
        if (map.isEmpty()) {
            return "";
        } else {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                result += entry.getKey() + ":" + entry.getValue() + "/";
            }

            // Kiểm tra nếu chuỗi kết quả không rỗng thì loại bỏ ký tự '/' cuối cùng
            if (!result.isEmpty()) {
                result = result.substring(0, result.length() - 1);
            }

            return result;
        }

    }

    public String removeDuplicateElements(String s, String s1) {
        Map<String, String> mapS = new HashMap<>();

        // Chuyển chuỗi 1 về map
        String[] parts = s.split("/");
        for (String part : parts) {
            String[] a = part.split(":");
            String id = a[0];
            String qty = a[1];
            mapS.put(id, qty);
        }

        // Chuyển chuỗi 2 về map
        String[] parts1 = s1.split("/");
        for (String part1 : parts1) {
            String[] a1 = part1.split(":");
            String id = a1[0];
            if (mapS.containsKey(id)) {
                mapS.remove(id);
            }
        }

        String result = "";
        // Chuyển mapS về chuỗi
        for (Map.Entry<String, String> entry : mapS.entrySet()) {
            result += entry.getKey() + ":" + entry.getValue() + "/";
        }

        // Kiểm tra nếu chuỗi kết quả không rỗng thì loại bỏ ký tự '/' cuối cùng
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }
public String addQuantityToProduct(String input, String pid, int quantityToAdd) {
    if (input == null || input.isEmpty()) {
        return input; // Return the original input if it's empty or null
    }

    Map<String, Integer> map = new HashMap<>();

    // Split the input string into parts
    String[] parts = input.split("/");
    for (String part : parts) {
        String[] s = part.split(":");
        String id = s[0];
        int qty = Integer.parseInt(s[1]);
        map.put(id, qty);
    }

    // Check if the product with the specified ID is already in the cart
    if (map.containsKey(pid)) {
        int currentQty = map.get(pid);
        // Add the new quantity to the existing quantity
        int newQty = currentQty + quantityToAdd;
        map.put(pid, newQty);
    } else {
        // If the product is not in the cart, add it with the given quantity
        map.put(pid, quantityToAdd);
    }

    // Convert the map back to a string
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        result.append(entry.getKey()).append(":").append(entry.getValue()).append("/");
    }

    // Remove the trailing '/' if the result is not empty
    if (result.length() > 0) {
        result.setLength(result.length() - 1);
    }

    return result.toString();
}
public String decreaseQuantityOfProduct(String input, String pid) {
    if (input == null || input.isEmpty()) {
        return input; // Return the original input if it's empty or null
    }

    Map<String, Integer> map = new HashMap<>();

    // Split the input string into parts
    String[] parts = input.split("/");
    for (String part : parts) {
        String[] s = part.split(":");
        String id = s[0];
        int qty = Integer.parseInt(s[1]);
        map.put(id, qty);
    }

    // Check if the product with the specified ID is in the cart
    if (map.containsKey(pid)) {
        int currentQty = map.get(pid);
        // Decrease the quantity by 1, but ensure it doesn't go below 0
        int newQty = Math.max(currentQty - 1, 0);
        map.put(pid, newQty);
    }

    // Remove the product if its quantity becomes 0
    map.entrySet().removeIf(entry -> entry.getValue() == 0);

    // Convert the map back to a string
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        result.append(entry.getKey()).append(":").append(entry.getValue()).append("/");
    }

    // Remove the trailing '/' if the result is not empty
    if (result.length() > 0) {
        result.setLength(result.length() - 1);
    }

    return result.toString();
}

    public static void main(String[] args) {
        String s = "1:2/2:3/3:4/5:1/6:2";
        String[] s1 = {"6", "3"};
        Cart t = new Cart();
        String result = t.getQuantityBypid(s, s1);
        System.out.println(result); // Kết quả in ra: "2:3/3:4/5:1/6:2"

    }

}
