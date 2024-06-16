import java.sql.*;


public class ShoppingListPosition {
    private final String product;
    private final String category;

    private int count;
    PositionStatus status = PositionStatus.NOT_CHECKED;

    ShoppingListPosition(String product, int count) throws SQLException {
        if (count < 1 || product.isBlank())
            throw new IllegalArgumentException("Incorrect input");

        this.product = product;
        this.count = count;
        this.category = specifyCategory(product);
    }

    void setCount(int count) {
        if (count < 1)
            throw new IllegalArgumentException("Count value lower then 1");

        this.count = count;
    }

    void check() {
        status = PositionStatus.CHECKED;
    }

    void uncheck() {
        status = PositionStatus.NOT_CHECKED;
    }

    String getProduct() {
        return product;
    }

    int getCount() {
        return count;
    }

    String getCategory() {
        return category;
    }

    PositionStatus getStatus() {
        return status;
    }

    String specifyCategory(String product) throws SQLException {
        Shop shop = new Shop();
        return shop.getCategory(product);
    }

    boolean isChecked(){
        return status == PositionStatus.CHECKED;
    }

}
