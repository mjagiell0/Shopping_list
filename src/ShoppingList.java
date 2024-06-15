import java.util.*;

public class ShoppingList {
    ArrayList<ShoppingListPosition> shoppingList;

    ShoppingList() {
        this.shoppingList = new ArrayList<>();
    }

    void add(String product, int count) {
        if (product.isBlank() || count < 1)
            throw new IllegalArgumentException("Incorrect input");

        if (this.has(product)) {
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
        String PURPLE = "\u001B[35m";
        String RESET = "\u001B[0m";
        String DARK_BLUE = "\u001B[34m";

        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println(PURPLE + (i + 1) + ". position {" + RESET +
                    "\n- Product name: " + shoppingList.get(i).getProduct() +
                    "\n- Category: " + shoppingList.get(i).getCategory() +
                    "\n- Count: " + shoppingList.get(i).getCount() +
                    "\n- Status: " + shoppingList.get(i).getStatus() + PURPLE +
                    "\n}\n" + RESET);
        }
    }

    boolean has(String product) {
        for (ShoppingListPosition position : shoppingList)
            if (position.getProduct().equals(product))
                return true;
        return false;
    }
}
