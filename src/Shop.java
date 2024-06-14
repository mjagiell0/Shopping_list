import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    HashMap<String, ArrayList<String>> shop;

    Shop(){
        shop = new HashMap<>();
    }

    void addProduct(String category, String product){
        if(!shop.containsKey(category))
            shop.put(category, new ArrayList<>());

        shop.get(category).add(product);
    }

    void display(){
        for (String category : shop.keySet()){
            System.out.println(category + ":");
            for (String product : shop.get(category))
                System.out.println("- " + product);
        }
    }
}
