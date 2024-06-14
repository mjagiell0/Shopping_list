public class Main {
    public static void main(String[] args) {
        String[] productNames = {
                "Smartfon", "Laptop", "Tablet", "Słuchawki bezprzewodowe", "Telewizor 4K", "Smartwatch", "Głośnik Bluetooth", "Aparat cyfrowy", "Konsola do gier", "Czytnik e-booków",
                "Sukienka wieczorowa", "Jeansy", "T-shirt", "Buty sportowe", "Kurtka skórzana", "Sweter wełniany", "Koszula formalna", "Spodnie dresowe", "Torebka skórzana", "Płaszcz zimowy",
                "Pralka", "Lodówka", "Kuchenka mikrofalowa", "Zmywarka", "Odkurzacz", "Żelazko", "Ekspres do kawy", "Czajnik elektryczny", "Blender", "Toster",
                "Chleb", "Mleko", "Jajka", "Ser żółty", "Makaron", "Ryż", "Pomidory", "Jabłka", "Kawa", "Masło",
                "Krem nawilżający", "Szampon do włosów", "Odżywka do włosów", "Perfumy", "Podkład do twarzy", "Tusz do rzęs", "Pomadka", "Płyn micelarny", "Balsam do ciała", "Krem pod oczy",
                "Powieść kryminalna", "Powieść science fiction", "Poradnik kulinarny", "Biografia", "Powieść historyczna", "Thriller psychologiczny", "Książka o rozwoju osobistym", "Album fotograficzny", "Podręcznik do nauki języka", "Komiks",
                "Rower górski", "Piłka nożna", "Rolki", "Hantle", "Mata do jogi", "Skakanka", "Rakieta tenisowa", "Kask rowerowy", "Plecak turystyczny", "Buty do biegania",
                "Kanapa", "Stół jadalniany", "Krzesło biurowe", "Szafa ubraniowa", "Łóżko", "Komoda", "Biurko", "Regał na książki", "Stolik kawowy", "Fotel",
                "Klocki Lego", "Lalka Barbie", "Puzzle 1000 elementów", "Gra planszowa", "Zestaw do malowania", "Pluszowy miś", "Samochodzik zdalnie sterowany", "Piłka plażowa", "Zestaw do robienia biżuterii", "Hulajnoga",
                "Kosiarka do trawy", "Zestaw mebli ogrodowych", "Gril ogrodowy", "Nasiona kwiatów", "Konewka", "Narzędzia ogrodnicze", "Doniczki", "Hamak", "Altana ogrodowa", "Oświetlenie solarne"
        };
        String[] categoriesNames = {"Elektronika", "Moda", "AGD", "Spożywcze", "Kosmetyki", "Książki", "Sport i rekreacja", "Meble", "Gry", "Ogród"};

        Shop shop = new Shop();

        shopInit(shop, categoriesNames, productNames);

        ShoppingList shoppingList = new ShoppingList();

        System.out.println("##### Test 1 #####");

        shoppingListInit(shoppingList, productNames);
        shoppingList.display();

        System.out.println("##### Test 2 #####");

        shoppingList.removePosition(productNames[0]);
        shoppingList.display();

        System.out.println("##### Test 3 #####");

        shoppingList.removePosition(productNames[3]);
        shoppingList.display();

        System.out.println("##### Test 4 #####");

        shoppingListInit(shoppingList, productNames);
        shoppingList.display();

        System.out.println("##### Test 5 #####");

        shoppingList.checkPosition(productNames[2]);
        shoppingList.display();

    }

    private static void shopInit(Shop shop, String[] categoriesNames, String[] productNames) {
        for (int i = 0; i < 10; i++)
            for (int j = i*10; j < i*10 + 10; j++)
                shop.addProduct(categoriesNames[i], productNames[j]);
    }

    private static void shoppingListInit(ShoppingList shoppingList, String[] names) {
        for (int i = 0; i < 4; i++)
            shoppingList.setPosition(names[i], (int) (Math.random() * 50));
    }


}