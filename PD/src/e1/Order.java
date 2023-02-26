package e1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    OrderPhase phase =  Shopping.getInstancia();
    Integer orderNumber;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    LocalDateTime date;
    ArrayList<Product> shoppingCartProducts = new ArrayList<>();
    ArrayList<Integer> shoppingCartAmounts = new ArrayList<>();


    public Order(){
        this.setDate(LocalDateTime.now());
    }
    public String screenInfo(Order order1){
        return phase.screenInfo(this);
    }
    public void addProduct(Order order, Product product, Integer amount) throws Exception {
        phase.addProduct(this, product, amount);
    }
    public void modifyAmount(Order order1, Product product, int i) throws Exception{
        phase.modifyAmount(this, product, i);
    }
    public void removeProduct(Order order, Product product) throws Exception {
        phase.removeProduct(this, product);
    }
    public void checkOut() throws Exception {
        phase.checkOut(this);
    }
    public void backShopping() throws Exception {
        phase.backShopping(this);
    }
    public void pay() throws Exception {
        phase.pay(this);
    }
    public void cancelOrder() throws Exception {
        phase.cancelOrder(this);
    }
    public void completeOrder() throws Exception {
        phase.completeOrder(this);
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}