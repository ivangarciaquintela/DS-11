package e1;

import java.util.Objects;

public class Shopping implements OrderPhase{
    private static final Shopping instancia = new Shopping ();
    private Shopping() {}
    public static Shopping getInstancia() {return instancia; }


    @Override
    public String screenInfo(Order order) {
        if (order.shoppingCartProducts.isEmpty()){
            return "Order Number :"+order.orderNumber+"\n Phase : Shopping -- Welcome to online shop";
        } else return "Order Number :"+order.orderNumber+"\n Phase : Shopping -- "+order.shoppingCartProducts.size()+" products";
    }

    @Override
    public void addProduct(Order order,Product product, Integer amount) throws Exception {
        if (product.stock==0) {
            throw new Exception("El producto"+ product.name + "está agotado");
        } else if (product.stock<amount){
                throw new Exception("Insuficiente stock del producto"+ product.name +", por favor elija una cantidad menor");
            } else {
            order.shoppingCartProducts.add(product);
            order.shoppingCartAmounts.add(amount);
            product.stock=product.stock-amount;
            }

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
                if (i == order.shoppingCartProducts.size()) {
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
        if(order.shoppingCartProducts.isEmpty()){
            throw new Exception("No tienes ningún producto en el carrito");
        } else order.phase = CheckOut.getInstancia();
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

