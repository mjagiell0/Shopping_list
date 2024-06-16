import java.sql.SQLException;
import java.util.Random;

public class UnitTests {
    private static ShoppingList shoppingList = new ShoppingList();
    private final static Random RAND = new Random();
    private static final int countOfProducts = RAND.nextInt(1,10);
    private static final int countOfCategories = 10;
    private final static String[] CATEGORY = {"Elektronika", "Moda", "AGD", "Spożywcze", "Kosmetyki", "Książki",
            "Sport i rekreacja", "Meble", "Gry", "Ogród"};
    private static String[] products;


    /*
    * Variable to set if user wants to
    * analyze condition of programs work
    * */

    private final static int NUMBER_OF_TESTS = 50;



    public void executeTests() throws SQLException {
        initialTests();

        removingPositionTests();

        removingCountOfProductsTests();

        removingCategoryTests();

        positionCheckingTests();
    }

    private void positionCheckingTests() throws SQLException {
        testTitle("CHECK POSITION TESTS");

        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            products = addProductsToShoppingList();

            for (int j = 0; j < shoppingList.size() - (shoppingList.size() / 2); j++) {
                int index;

                do{
                    index = RAND.nextInt(0,shoppingList.size());
                }while (shoppingList.getPosition(products[index]).getStatus() == PositionStatus.CHECKED);

                shoppingList.checkPosition(products[index]);
            }

            System.out.println("Checked:");
            shoppingList.displayChecked();

            System.out.println("Unchecked:");
            shoppingList.displayUnchecked();

            shoppingList.removeAll();
        }
    }

    private void removingCategoryTests() throws SQLException {
        testTitle("REMOVING CATEGORY TESTS");

        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            addProductsToShoppingList();

            int index;
            do{
                index = RAND.nextInt(countOfCategories);
            }while (!shoppingList.hasCategory(CATEGORY[index]));

            System.out.println("Category to remove: " + CATEGORY[index]);

            System.out.println("Before:");
            shoppingList.display();

            shoppingList.removeCategory(CATEGORY[index]);

            System.out.println("After:");
            shoppingList.display();

            shoppingList.removeAll();
        }
    }

    private void removingCountOfProductsTests() throws SQLException {
        testTitle("REMOVING COUNT OF POSITION TESTS");

        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            products = addProductsToShoppingList();
            int index = RAND.nextInt(0, countOfProducts);

            ShoppingListPosition position = shoppingList.getPosition(products[index]);

            int countOfProductToRemove = RAND.nextInt(1,position.getCount() + 1);

            System.out.println("Count of product to remove: " + countOfProductToRemove);

            System.out.println("Before:");
            shoppingList.displayPosition(index);

            int tempCount = shoppingList.getPosition(products[index]).getCount();

            shoppingList.removeCountOfPosition(products[index], countOfProductToRemove);

            System.out.println("After:");
            if(tempCount == countOfProductToRemove)
                System.out.println("Product has been removed");
            else
                shoppingList.displayPosition(index);

            shoppingList.removeAll();
        }
    }

    private void removingPositionTests() throws SQLException {
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

    private void initialTests() throws SQLException {
        testTitle("INITIAL TESTS");
        for (int i = 1; i <= NUMBER_OF_TESTS; i++) {
            UTEST(i);

            products = addProductsToShoppingList();
            shoppingList.display();

            shoppingList.removeAll();
        }

    }

    private void UTEST(int N) {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";

        System.out.println(GREEN + "UTEST: " + N + RESET);
    }

    private void testTitle(String title) {
        String DARK_GREEN = "\u001B[38;5;22m";
        String RESET = "\u001B[0m";
        System.out.println(DARK_GREEN + "####   " + title + "   ####" + RESET);
    }

    private String[] addProductsToShoppingList() throws SQLException {
        String[] products = new String[countOfProducts];

        for (int i = 0; i < countOfProducts; i++) {
            String product;
            do {
                product = Server.SHOP.getProduct(CATEGORY[RAND.nextInt(0, countOfCategories - 1)], RAND.nextInt(10));
            } while (shoppingList.hasProduct(product));

            products[i] = product;
            shoppingList.add(product, RAND.nextInt(10) + 1);
        }

        return products;
    }
}