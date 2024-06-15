import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String[] products = new String[100], categories = new String[10];

        DatabaseSpecifier dbSpecifier = new DatabaseSpecifier();
        try {
            Class.forName(dbSpecifier.getClassName());

            Connection connection = DriverManager.getConnection(dbSpecifier.getURL(), dbSpecifier.getUsername(), dbSpecifier.getPassword());
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT CategoryName FROM Categories");

            for (int i = 0; resultSet.next() ; i++)
                categories[i] = resultSet.getString(1);

            resultSet = statement.executeQuery("SELECT ProductName FROM Products");

            for (int i = 0; resultSet.next() ; i++)
                products[i] = resultSet.getString(1);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        Shop shop = new Shop();

        shopInit(shop, categories, products);

        ShoppingList shoppingList = new ShoppingList();

        System.out.println("##### Test 1 #####");

        shoppingListInit(shoppingList, products);
        shoppingList.display();

        System.out.println("##### Test 2 #####");

        shoppingList.removePosition(products[0]);
        shoppingList.display();

        System.out.println("##### Test 3 #####");

        shoppingList.removePosition(products[3]);
        shoppingList.display();

        System.out.println("##### Test 4 #####");

        shoppingListInit(shoppingList, products);
        shoppingList.display();

        System.out.println("##### Test 5 #####");

        shoppingList.checkPosition(products[2]);
        shoppingList.display();

    }

    private static void shopInit(Shop shop, String[] categoriesNames, String[] productNames) {
        for (int i = 0; i < 10; i++)
            for (int j = i*10; j < i*10 + 10; j++)
                shop.addProduct(categoriesNames[i], productNames[j]);
    }

    private static void shoppingListInit(ShoppingList shoppingList, String[] names) {
        for (int i = 0; i < 4; i++)
            shoppingList.setPosition(names[i], (int) (Math.random() * 50));
    }


}