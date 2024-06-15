import java.util.Random;

public class UnitTests {
    private final static Random RAND = new Random();
    private static int countOfProducts = RAND.nextInt(10);
    private static final int countOfCategories = 10;
    final static String[] CATEGORY = {"Elektronika", "Moda", "AGD", "Spożywcze", "Kosmetyki", "Książki",
            "Sport i rekreacja", "Meble", "Gry", "Ogród"};

    private final static Shop SHOP = new Shop(); // Konstruktor sklepu tworzy 10 kategorii po 10 produktów
    private static ShoppingList shoppingList = new ShoppingList(); // Konstruktor listy zakupów towrzy pustą listę

    private static String[] products;

    public static void main(String[] args) {
        initialTests(5);
    }

    private static void initialTests(int n) {
        for (int i = 1; i <= n; i++) {
            UTEST(i);

            products = addProductsToShoppingList();
            shoppingList.display();

            shoppingList.removeAll();
        }

    }

    private static void UTEST(int N) {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";

        System.out.println(GREEN + "####\tUTEST: " + N + "\t####" + RESET);
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