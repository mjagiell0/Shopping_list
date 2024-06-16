import java.sql.SQLException;
import java.util.*;

public class ShoppingList {
    private ArrayList<ShoppingListPosition> shoppingList;

    ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    void add(String product, int count) throws SQLException {
        if (product.isBlank() || count < 1)
            throw new IllegalArgumentException("Incorrect input");

        if (this.hasProduct(product)) {
            this.changeCount(product, count);
            return;
        }

        ShoppingListPosition newPosition = new ShoppingListPosition(product, count);
        shoppingList.add(newPosition);
    }

    ShoppingListPosition getPosition(String product) {
        if (product.isBlank())
            throw new IllegalArgumentException("Blank product name argument");

        for (ShoppingListPosition position : shoppingList)
            if (position.getProduct().equals(product))
                return position;

        throw new NoSuchElementException("No such a product in list.");
    }

    void removePosition(String product) {
        shoppingList.removeIf(position -> position.getProduct().equals(product));
    }

    void removeCountOfPosition(String product, int count) {
        if (!this.hasProduct(product))
            throw new NoSuchElementException("No such a product in list.");

        ShoppingListPosition position = getPosition(product);

        if (position.getCount() < count)
            throw new IllegalArgumentException("Number of this product on list is lower than count in argument");
        else if (position.getCount() == count)
            removePosition(product);
        else
            position.setCount(position.getCount() - count);
    }

    void removeAll() {
        shoppingList.clear();
    }

    void removeCategory(String category) {
        shoppingList.removeIf(position -> position.getCategory().equals(category));
    }

    void changeCount(String product, int count) {
        if (count < 1 || product.isBlank())
            throw new IllegalArgumentException("Incorrect input");

        ShoppingListPosition position = getPosition(product);

        position.setCount(position.getCount() + count);
    }

    void checkPosition(String product) {
        if(getPosition(product).getStatus() == Status.CHECKED)
            getPosition(product).uncheck();
        else
            getPosition(product).check();
    }

    void display() {
        for (int i = 0; i < shoppingList.size(); i++)
            displayPosition(i);
    }

    void displayPosition(int i) {
        String PURPLE = "\u001B[35m";
        String RESET = "\u001B[0m";
        System.out.println(PURPLE + (i + 1) + ". position {" + RESET +
                "\n- Product name: " + shoppingList.get(i).getProduct() +
                "\n- Category: " + shoppingList.get(i).getCategory() +
                "\n- Count: " + shoppingList.get(i).getCount() +
                "\n- Status: " + shoppingList.get(i).getStatus() + PURPLE +
                "\n}\n" + RESET);
    }

    boolean hasProduct(String product) {
        for (ShoppingListPosition position : shoppingList)
            if (position.getProduct().equals(product))
                return true;
        return false;
    }

    boolean hasCategory(String category){
        for (ShoppingListPosition position : shoppingList)
            if(position.getCategory().equals(category))
                return true;
        return false;
    }

    int size(){
        return shoppingList.size();
    }
}
