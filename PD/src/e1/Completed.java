package e1;

public class Completed implements OrderPhase {
    private static final Completed instancia = new Completed ();
    private Completed() {}
    public static Completed getInstancia() {return instancia; }

    @Override
    public String screenInfo(Order order) {
        return "Order Number :"+order.orderNumber+"\n Phase : Completed: "+order.shoppingCartProducts.size()+"products";
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
    public void cancelOrder(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
    @Override
    public void completeOrder(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
}

