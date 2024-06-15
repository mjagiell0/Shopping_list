import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class Shop {
    private final HashMap<String, ArrayList<String>> shop;

    Shop(){
        shop = new HashMap<>();

        DatabaseSpecifier dbSpec = new DatabaseSpecifier();

        String[] categories = new String[10];
        ArrayList<String> products = new ArrayList<>();

        try{
            Class.forName(dbSpec.getClassName());
            Connection connection = DriverManager.getConnection(dbSpec.getURL(), dbSpec.getUsername(), dbSpec.getPassword());
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT CategoryName FROM Categories");
            for (int i = 0; resultSet.next() ; i++)
                categories[i] = resultSet.getString(1);

            resultSet = statement.executeQuery("SELECT ProductName FROM Products");
            for (int i = 0; resultSet.next() ; i++)
                products.add(resultSet.getString(1));

            for (int i = 0; i < categories.length; i++){
                ArrayList<String> categoryProductsList = new ArrayList<>();

                for (int j = i * 10; j < i * 10 + 10; j++)
                    categoryProductsList.add(products.get(j));

                shop.put(categories[i], categoryProductsList);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    String getProduct(String category, int index){
        return shop.get(category).get(index);
    }

    void display(){
        for (String category : shop.keySet()){
            System.out.println(category + ":");
            for (String product : shop.get(category))
                System.out.println("- " + product);
        }
    }
}
