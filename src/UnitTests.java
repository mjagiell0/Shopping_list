import java.util.Random;

public class UnitTests {
    private final static Random RAND = new Random();
    private static final int countOfProducts = RAND.nextInt(1,10);
    private static final int countOfCategories = 10;
    private final static String[] CATEGORY = {"Elektronika", "Moda", "AGD", "Spożywcze", "Kosmetyki", "Książki",
            "Sport i rekreacja", "Meble", "Gry", "Ogród"};
    private final static Shop SHOP = new Shop(); // Konstruktor sklepu tworzy 10 kategorii po 10 produktów
    private static ShoppingList shoppingList = new ShoppingList(); // Konstruktor listy zakupów towrzy pustą listę
    private static String[] products;
    private final static int NUMBER_OF_TESTS = 5;

    public static void main(String[] args) {
        //initialTests();

        //removingPositionTests();
    }

    private static void removingPositionTests() {
        testTitle("REMOVING POSITION TESTS");

        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            int countOfProductsToRemove = RAND.nextInt(1, countOfProducts);
            System.out.println("Count of products to remove: " + countOfProductsToRemove);

            products = addProductsToShoppingList();

            System.out.println("Before:");
            shoppingList.display();

            int tempCount = countOfProducts;

            while (countOfProductsToRemove != 0 && tempCount != 0) {
                int index;

                do {
                    index = RAND.nextInt(products.length);
                }while (products[index] == null);

                shoppingList.removePosition(products[index]);
                products[index] = null;

                countOfProductsToRemove--;
                tempCount--;
            }

            System.out.println("After:");
            shoppingList.display();

            shoppingList.removeAll();
        }
    }

    private static void initialTests() {
        testTitle("INITIAL TESTS");
        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            products = addProductsToShoppingList();
            shoppingList.display();

            shoppingList.removeAll();
        }

    }

    private static void UTEST(int N) {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";

        System.out.println(GREEN + "UTEST: " + N + "" + RESET);
    }

    private static void testTitle(String title) {
        String DARK_GREEN = "\u001B[38;5;22m";
        String RESET = "\u001B[0m";
        System.out.println(DARK_GREEN + "####   " + title + "   ####" + RESET);
    }

    private static String[] addProductsToShoppingList() {
        String[] products = new String[countOfProducts];

        for (int i = 0; i < countOfProducts; i++) {
            String product;
            do {
                product = SHOP.getProduct(CATEGORY[RAND.nextInt(0, countOfCategories - 1)], RAND.nextInt(10));
            } while (shoppingList.has(product));

            products[i] = product;
            shoppingList.add(product, RAND.nextInt(10) + 1);
        }

        return products;
    }
}