package e1;

import e1.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SistemaDeCompraTest {
    @Test
    void testNormalCancelExecution() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        Product jamonSerrano = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        jamonSerrano.setName("jamonSerrano");
        jamonSerrano.setStock(20);
        Assertions.assertEquals("Order Number :1111\n Phase : Shopping -- Welcome to online shop", order1.screenInfo(order1));
        order1.addProduct(order1, pipas, 3);
        Assertions.assertEquals("Order Number :1111\n Phase : Shopping -- 1 products", order1.screenInfo(order1));
        order1.checkOut();
        order1.backShopping();
        order1.addProduct(order1, jamonSerrano, 5);
        order1.checkOut();
        Assertions.assertEquals("Order Number :1111\n Phase : Check Out: 2 products",order1.screenInfo(order1));
        order1.pay();
        Assertions.assertEquals("Order Number :1111\n Phase : Paid Order: 2 products -- date "+ LocalDateTime.now().withSecond(0).withNano(0),order1.screenInfo(order1));
        order1.cancelOrder();
        Assertions.assertEquals("Order Number :1111\n Phase : Cancelled Order",order1.screenInfo(order1));
    }

    @Test
    void testAddProductExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        Product jamonSerrano = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        jamonSerrano.setName("jamonSerrano");
        jamonSerrano.setStock(0);
        order1.addProduct(order1, pipas, 5);
        Assertions.assertTrue(order1.shoppingCartProducts.contains(pipas));
        order1.removeProduct(order1, pipas);
        Assertions.assertThrows(Exception.class, () ->order1.addProduct(order1, pipas, pipas.getStock()+30));
        Assertions.assertThrows(Exception.class, () ->order1.addProduct(order1, jamonSerrano, 12));
    }

    @Test
    void testModifyAmountAndExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        order1.addProduct(order1, pipas, 5);
        order1.modifyAmount(order1, pipas, 7);
        Assertions.assertEquals(7, order1.shoppingCartAmounts.get(0));
        Assertions.assertThrows(Exception.class, () ->order1.modifyAmount(order1, pipas, 30));
    }

    @Test
    void testRemoveProductAndExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        Product jamonSerrano = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        jamonSerrano.setName("jamonSerrano");
        jamonSerrano.setStock(20);
        order1.addProduct(order1, pipas, 5);
        order1.removeProduct(order1, pipas);
        Assertions.assertTrue(order1.shoppingCartProducts.isEmpty());
        Assertions.assertThrows(Exception.class, () ->order1.removeProduct(order1, jamonSerrano));

    }
    @Test
    void shoppingSpecificExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        pipas.setName("pipas");
        pipas.setStock(10);
        Assertions.assertThrows(Exception.class, () -> order1.checkOut());
        order1.addProduct(order1, pipas, 3);
        Assertions.assertThrows(Exception.class, () -> order1.backShopping());
        Assertions.assertThrows(Exception.class, () -> order1.pay());
        Assertions.assertThrows(Exception.class, () -> order1.cancelOrder());
        Assertions.assertThrows(Exception.class, () -> order1.completeOrder());
    }

    @Test
    void checkOutSpecificExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        Product jamonSerrano = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        jamonSerrano.setStock(20);
        order1.addProduct(order1, pipas, 3);
        order1.checkOut();
        Assertions.assertThrows(Exception.class, () -> order1.addProduct(order1, pipas, 4));
        order1.modifyAmount(order1, pipas, 4);
        Assertions.assertThrows(Exception.class, () -> order1.modifyAmount(order1, pipas, 15));
        Assertions.assertThrows(Exception.class, () -> order1.modifyAmount(order1, jamonSerrano, 7));
        order1.removeProduct(order1, pipas);
        Assertions.assertThrows(Exception.class, () -> order1.removeProduct(order1, jamonSerrano));
        order1.backShopping();
        order1.addProduct(order1, pipas, 3);
        order1.checkOut();
        Assertions.assertThrows(Exception.class, () -> order1.checkOut());
        Assertions.assertThrows(Exception.class, () -> order1.cancelOrder());
        Assertions.assertThrows(Exception.class, () -> order1.completeOrder());
    }

    @Test
    void paidOrderSpecificExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        order1.addProduct(order1, pipas, 3);
        order1.checkOut();
        order1.pay();
        Assertions.assertThrows(Exception.class, () -> order1.addProduct(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.modifyAmount(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.removeProduct(order1, pipas));
        Assertions.assertThrows(Exception.class, () -> order1.checkOut());
        Assertions.assertThrows(Exception.class, () -> order1.pay());
        Assertions.assertThrows(Exception.class, () -> order1.backShopping());
        Assertions.assertThrows(Exception.class, () -> order1.completeOrder());
    }

    @Test
    void testCancelledExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        order1.addProduct(order1, pipas, 3);
        order1.checkOut();
        order1.pay();
        order1.cancelOrder();
        Assertions.assertThrows(Exception.class, () -> order1.addProduct(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.modifyAmount(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.removeProduct(order1, pipas));
        Assertions.assertThrows(Exception.class, () -> order1.checkOut());
        Assertions.assertThrows(Exception.class, () -> order1.backShopping());
        Assertions.assertThrows(Exception.class, () -> order1.pay());
        Assertions.assertThrows(Exception.class, () -> order1.cancelOrder());
        Assertions.assertThrows(Exception.class, () -> order1.completeOrder());
    }

    @Test
    void testCompletedExceptions() throws Exception {
        Order order1 = new Order();
        Product pipas = new Product();
        order1.setDate(LocalDateTime.now().minusDays(2) );
        order1.setOrderNumber(1111);
        pipas.setName("pipas");
        pipas.setStock(10);
        order1.addProduct(order1, pipas, 3);
        order1.checkOut();
        order1.pay();
        order1.completeOrder();
        Assertions.assertThrows(Exception.class, () -> order1.addProduct(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.modifyAmount(order1, pipas, 4));
        Assertions.assertThrows(Exception.class, () -> order1.removeProduct(order1, pipas));
        Assertions.assertThrows(Exception.class, () -> order1.checkOut());
        Assertions.assertThrows(Exception.class, () -> order1.backShopping());
        Assertions.assertThrows(Exception.class, () -> order1.pay());
        Assertions.assertThrows(Exception.class, () -> order1.cancelOrder());
        Assertions.assertThrows(Exception.class, () -> order1.completeOrder());
    }

}

