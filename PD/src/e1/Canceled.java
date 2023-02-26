package e1;

public class Canceled implements OrderPhase {
    private static final Canceled instancia = new Canceled ();
    private Canceled() {}
    public static Canceled getInstancia() {return instancia; }


    @Override
    public String screenInfo(Order order) {
        return "Order Number :"+order.orderNumber+"\n Phase : Cancelled Order";
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
