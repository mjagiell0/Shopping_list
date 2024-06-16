import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class Shop {
    private final HashMap<String, ArrayList<String>> shop;

    Shop() throws SQLException {
        shop = new HashMap<>();

        String[] categories = new String[10];
        ArrayList<String> products = new ArrayList<>();

        ResultSet resultSet = UnitTests.dbHandler.getData("SELECT CategoryName FROM Categories");
        for (int i = 0; resultSet.next(); i++)
            categories[i] = resultSet.getString(1);

        resultSet = UnitTests.dbHandler.getData("SELECT ProductName FROM Products");
        while(resultSet.next())
            products.add(resultSet.getString(1));

        for (int i = 0; i < categories.length; i++) {
            ArrayList<String> categoryProductsList = new ArrayList<>();

            for (int j = i * 10; j < i * 10 + 10; j++)
                categoryProductsList.add(products.get(j));

            shop.put(categories[i], categoryProductsList);
        }
    }

    String getProduct(String category, int index) {
        return shop.get(category).get(index);
    }

    void display() {
        for (String category : shop.keySet()) {
            System.out.println(category + ":");
            for (String product : shop.get(category))
                System.out.println("- " + product);
        }
    }

    String getCategory(String product) {
        for (String category : shop.keySet()) {
            ArrayList<String> products = shop.get(category);
            for (String categoryProduct : products)
                if (categoryProduct.equals(product))
                    return category;
        }
        return null;
    }
}
