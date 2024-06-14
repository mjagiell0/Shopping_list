import java.sql.*;

public class ShoppingListPosition {
    private final String product;
    private final String category;
    private int count;
    private boolean status = false;

    ShoppingListPosition(String product, int count){
        if(count < 1 || product.isBlank())
            throw new IllegalArgumentException("Incorrect input");

        this.product = product;
        this.count = count;
        this.category = specifyCategory(product);
    }

    void setCount(int count){
        if(count < 1)
            throw new IllegalArgumentException("Count value lower then 1");

        this.count = count;
    }

    void check(){
        status = !status;
    }

    String getProduct(){
        return product;
    }

    int getCount(){
        return count;
    }

    String getCategory(){
        return category;
    }

    boolean getStatus(){
        return status;
    }

    String specifyCategory(String product){
        String categoryName = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3333/Shop", "root", "root");

            String query = "SELECT c.CategoryName " +
                    "FROM Categories c " +
                    "LEFT JOIN Warehouse w ON c.CategoryID = w.CategoryID " +
                    "LEFT JOIN Products p ON p.ProductID = w.ProductID " +
                    "WHERE p.ProductName = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoryName = resultSet.getString("CategoryName");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return categoryName;
    }

}
