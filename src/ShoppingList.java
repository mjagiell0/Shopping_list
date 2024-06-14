import java.util.*;

public class ShoppingList {
    ArrayList<ShoppingListPosition> shoppingList;

    ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    void setPosition(String product, int count) {
        if (product.isBlank() || count < 1)
            throw new IllegalArgumentException("Incorrect input");

        for (ShoppingListPosition position : shoppingList)
            if (position.getProduct().equals(product)) {
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
        ShoppingListPosition position = getPosition(product);

        position.check();
    }

    void display() {
        System.out.println("|\t\tName\t\t|\tCategory\t|\tCount\t|\tStatus\t|");
        System.out.println("--------------------------------------------");
        for (ShoppingListPosition position : shoppingList)
            System.out.println("|\t" + position.getProduct() + "\t|\t" + position.getCategory() + "\t|\t" + position.getCount() + "\t|\t" + position.getStatus());
    }
}
