package e1;

public interface OrderPhase {
    String screenInfo(Order order);
    void addProduct(Order order, Product product, Integer i) throws Exception;
    void modifyAmount(Order order, Product product, Integer newAmount) throws Exception;
    void removeProduct(Order order, Product product) throws Exception;
    void checkOut(Order order) throws Exception;
    void backShopping(Order order) throws Exception;
    void pay(Order order) throws Exception;
    void cancelOrder(Order order) throws Exception;
    void completeOrder(Order order) throws Exception;
}
