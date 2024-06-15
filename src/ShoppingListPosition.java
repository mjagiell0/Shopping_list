import javax.xml.crypto.Data;
import java.sql.*;


public class ShoppingListPosition {
    private final String product;
    private final String category;

    private int count;
    Status status = Status.NOT_CHECKED;

    ShoppingListPosition(String product, int count) {
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
        status = Status.CHECKED;
    }

    void uncheck() {
        status = Status.NOT_CHECKED;
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

    Status getStatus() {
        return status;
    }

    String specifyCategory(String product) {
        Shop shop = new Shop();
        return shop.getCategory(product);
    }

}
