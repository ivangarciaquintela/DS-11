package e1;

import java.util.Objects;

public class CheckOut implements OrderPhase {
    private static final CheckOut instancia = new CheckOut ();
    private CheckOut() {}
    public static CheckOut getInstancia() {return instancia; }


    @Override
    public String screenInfo(Order order) {
        return "Order Number :"+order.orderNumber+"\n Phase : Check Out: "+order.shoppingCartProducts.size()+" products";
    }

    @Override
    public void addProduct(Order order,Product product, Integer amount) throws Exception {
        throw new Exception("Esta operación no está permitida en este caso");
    }

    @Override
    public void modifyAmount(Order order, Product product, Integer newAmount) throws Exception {
        for (int i = 0; i <= order.shoppingCartProducts.size(); i++) {
            if (Objects.equals(order.shoppingCartProducts.get(i).name, product.name)) {
                Integer oldAmount = order.shoppingCartAmounts.get(i);
                if (product.stock+oldAmount<newAmount) {
                    throw new Exception("Insuficiente stock del producto"+ product.name +", por favor elija una cantidad menor");
                }
                order.shoppingCartAmounts.set(i,newAmount);
                product.stock=product.stock+(oldAmount-newAmount);
                break;
            } else {
                if (i==order.shoppingCartProducts.size()){
                    throw new Exception("This product is not in your cart");
                }
            }
        }
    }

    @Override
    public void removeProduct(Order order, Product product) throws Exception {
        for (int i = 0; i <= order.shoppingCartProducts.size(); i++) {
            if (Objects.equals(order.shoppingCartProducts.get(i).name, product.name)) {
                product.stock=product.stock+(order.shoppingCartAmounts.get(i));
                order.shoppingCartProducts.remove(i);
                order.shoppingCartAmounts.remove(i);
                break;
            } else {
                if (i==order.shoppingCartProducts.size()){
                    throw new Exception("This product is not in your cart");
                }

            }
        }
    }
    @Override
    public void checkOut(Order order) throws Exception {
        throw new Exception("Este cambio de estado no es lógico para este caso");
    }
    @Override
    public void backShopping(Order order){
        order.phase = Shopping.getInstancia();
    }
    @Override
    public void pay(Order order) {

        order.phase = PaidOrder.getInstancia();
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
