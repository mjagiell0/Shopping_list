import java.util.Random;

public class Main {
    private final static Random RAND = new Random();
    private static int countOfProducts = RAND.nextInt(10);
    final static String CATEGORY = "Elektronika";

    private final static Shop SHOP = new Shop(); // Konstruktor sklepu tworzy 10 kategorii po 10 produktów
    private static ShoppingList shoppingList = new ShoppingList(); // Konstruktor listy zakupów towrzy pustą listę

    private static String[] products;

    public static void main(String[] args) {


        /* UTEST1 */
        UTEST(1);

        products = addProductsToShoppingList();
        shoppingList.display();

        shoppingList.removeAll();
        /*############################*/
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
                product = SHOP.getProduct(CATEGORY, RAND.nextInt(10));
            }while(shoppingList.has(product));

            products[i] = product;
            shoppingList.add(product, RAND.nextInt(10) + 1);
        }

        return products;
    }
}