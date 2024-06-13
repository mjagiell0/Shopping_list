public class Main {
    public static void main(String[] args) {
        String[] names = {"Jabłko", "Banan", "Żelki", "Twaróg"};

        ShoppingList shoppingList = new ShoppingList();

        System.out.println("##### Test 1 #####");

        setPositions(shoppingList, names);
        shoppingList.display();

        System.out.println("##### Test 2 #####");

        shoppingList.removePosition(names[0]);
        shoppingList.display();

        System.out.println("##### Test 3 #####");

        shoppingList.removePosition(names[3]);
        shoppingList.display();

        System.out.println("##### Test 4 #####");

        setPositions(shoppingList, names);
        shoppingList.display();

        System.out.println("##### Test 5 #####");

        shoppingList.checkPosition(names[2]);
        shoppingList.display();

    }

    private static void setPositions(ShoppingList shoppingList, String[] names) {
        for (int i = 0; i < 4; i++)
            shoppingList.setPosition(names[i], (int) (Math.random() * 50));
    }


}