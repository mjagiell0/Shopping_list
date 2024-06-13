public class ShoppingListPosition {
    private final String productName;
    private int count;
    private boolean status = false;

    ShoppingListPosition(String productName, int count){
        if(count < 1 || productName.isBlank())
            throw new IllegalArgumentException("Incorrect input");

        this.productName = productName;
        this.count = count;
    }

    void setCount(int count){
        if(count < 1)
            throw new IllegalArgumentException("Count value lower then 1");

        this.count = count;
    }

    void check(){
        status = !status;
    }

    String getProductName(){
        return productName;
    }

    int getCount(){
        return count;
    }

    boolean getStatus(){
        return status;
    }


}
