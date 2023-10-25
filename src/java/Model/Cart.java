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
    // Khởi tạo một đối tượng ProductDAO để thao tác với sản phẩm
    ProductDAO pd = new ProductDAO();
    
    // Khởi tạo một LinkedHashMap để lưu trữ lịch sử mua hàng (Product và số lượng)
    LinkedHashMap<Product, Integer> historyMap = new LinkedHashMap<>();

    try {
        // Kiểm tra nếu chuỗi txt không null và không rỗng
        if (txt != null && txt.length() != 0) {
            // Tách chuỗi txt thành mảng các phần tử sử dụng dấu '/'
            String[] s = txt.split("/");

            // Duyệt qua từng phần tử trong mảng s
            for (String i : s) {
                // Tách mỗi phần tử con thành mảng sử dụng dấu ':'
                String[] n = i.split(":");

                // Kiểm tra nếu mảng con có đúng 2 phần tử
                if (n.length == 2) {
                    String pid = n[0];
                    int quantity = Integer.parseInt(n[1]);

                    // Lấy thông tin sản phẩm từ cơ sở dữ liệu bằng id (pid)
                    Product p = pd.getProductByID(pid);

                    // Nếu sản phẩm tồn tại
                    if (p != null) {
                        // Đặt sản phẩm và số lượng vào lịch sử mua hàng
                        historyMap.put(p, quantity);
                    }
                }
            }
        }
    } catch (NumberFormatException e) {
        // Xử lý NumberFormatException ở đây nếu cần thiết.
        e.printStackTrace();
    }

    // Trả về LinkedHashMap lịch sử mua hàng
    return historyMap;
}


   public String processString(String input) {
    // Khởi tạo một HashMap để lưu trữ các cặp key-value
    Map<String, Integer> map = new HashMap<>();
    
    // Tách chuỗi input thành mảng các phần tử sử dụng dấu '/'
    String[] parts = input.split("/");
    for (String part : parts) {
        // Tách mỗi phần tử con thành mảng sử dụng dấu ':'
        String[] s = part.split(":");
        
        // Kiểm tra nếu mảng con có đúng 2 phần tử
        if (s.length == 2) {
            String id = s[0];
            int qty = Integer.parseInt(s[1]);
            
            // Nếu id đã tồn tại trong map, cộng thêm qty vào giá trị hiện tại
            if (map.containsKey(id)) {
                qty += map.get(id);
            }
            
            // Cập nhật hoặc thêm id và giá trị qty vào map
            map.put(id, qty);
        }
    }
    
    // Tạo một StringBuilder để xây dựng chuỗi kết quả
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // Thêm mỗi cặp key-value vào chuỗi kết quả, ngăn cách bằng ':'
        result.append(entry.getKey()).append(":").append(entry.getValue()).append("/");
    }
    
    // Kiểm tra nếu chuỗi kết quả có độ dài lớn hơn 0, loại bỏ dấu '/' ở cuối
    if (result.length() > 0) {
        result.deleteCharAt(result.length() - 1);
    }
    
    // Trả về chuỗi kết quả dưới dạng String
    return result.toString();
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

    Map<String, Integer> map = new HashMap<>();

    
    String[] parts = input.split("/");
    for (String part : parts) {
        String[] split = part.split(":");
        if (split.length == 2) {
            String id = split[0];
            int qty = Integer.parseInt(split[1]);
            map.put(id, qty);
        }
    }

 
    String result = "";

  
    for (String id : ids) {
        Integer qty = map.get(id);
        if (qty != null) {
            
            result += id + ":" + qty + "/";
        }
    }


    if (!result.isEmpty()) {
        result = result.substring(0, result.length() - 1);
    }

    return result;
}

    public int getQuantityById(String input, String id) {
        if (input == null || input.isEmpty()) {
            return 0; 
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

        
        if (map.containsKey(id)) {
            return map.get(id); 
        } else {
            return 0; 
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
