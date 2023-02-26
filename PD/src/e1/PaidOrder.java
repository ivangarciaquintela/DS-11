package e1;

import java.time.LocalDateTime;

public class PaidOrder implements OrderPhase{
    private static final PaidOrder instancia = new PaidOrder ();
    private PaidOrder() {}
    public static PaidOrder getInstancia() {return instancia; }

    @Override
    public String screenInfo (Order order){
        order.date=LocalDateTime.now().withSecond(0).withNano(0);
        return "Order Number :" + order.orderNumber + "\n Phase : Paid Order: " + order.shoppingCartProducts.size() + " products -- date "+ order.date;
    }

    @Override
    public void addProduct(Order order,Product product, Integer amount) throws Exception {
        throw new Exception("Esta operación no está permitida en este caso");
    }
    @Override
    public void modifyAmount(Order order, Product product, Integer newAmount) throws Exception {
        throw new Exception("Esta operación no está permitida en este caso");
    }
    @Override
    public void removeProduct(Order order, Product product) throws Exception {
        throw new Exception("Esta operación no está permitida en este caso");
    }
    @Override
    public void checkOut(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
    @Override
    public void backShopping(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
    @Override
    public void pay(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
    @Override
    public void cancelOrder(Order order) {
        order.phase = Canceled.getInstancia();
    }

    @Override
    public void completeOrder(Order order) throws Exception {
        if(order.date.plusDays(1).isAfter(LocalDateTime.now())){
            throw new Exception("Aún no han pasado 24h por lo q el pedido aún no está completado");
        }else order.phase = Completed.getInstancia();
    }

}