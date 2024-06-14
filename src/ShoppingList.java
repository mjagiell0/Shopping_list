import java.util.*;

public class ShoppingList {
    ArrayList<ShoppingListPosition> shoppingList;

    ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    void setPosition(String productName, int count) {
        if (productName.isBlank() || count < 1)
            throw new IllegalArgumentException("Incorrect input");

        for (ShoppingListPosition position : shoppingList)
            if (position.getProductName().equals(productName)) {
                this.changeCount(productName, count);
                return;
            }

        ShoppingListPosition newPosition = new ShoppingListPosition(productName, count);
        shoppingList.add(newPosition);
    }

    ShoppingListPosition getPosition(String productName) {
        if (productName.isBlank())
            throw new IllegalArgumentException("Blank product name argument");

        for (ShoppingListPosition position : shoppingList)
            if (position.getProductName().equals(productName))
                return position;

        throw new NoSuchElementException("No such a product in list.");
    }

    void removePosition(String productName) {
        shoppingList.removeIf(position -> position.getProductName().equals(productName));
    }

    void removeAll() {
        shoppingList.clear();
    }

    void removeCategory(String category) {
        shoppingList.removeIf(position -> position.getCategory().equals(category));
    }

    void changeCount(String productName, int count) {
        if (count < 1 || productName.isBlank())
            throw new IllegalArgumentException("Incorrect input");

        ShoppingListPosition position = getPosition(productName);

        position.setCount(position.getCount() + count);
    }

    void checkPosition(String productName) {
        ShoppingListPosition position = getPosition(productName);

        position.check();
    }

    void display() {
        for (ShoppingListPosition position : shoppingList)
            System.out.println("Name: \t" + position.getProductName() + ",\t count: \t" + position.getCount() + ", status: \t" + position.getStatus());
    }
}
