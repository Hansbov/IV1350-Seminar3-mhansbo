package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.retailStore.integration.GoodsDTO;
import se.kth.iv1350.retailStore.integration.InventoryManager;

import static java.sql.DriverManager.println;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    GoodsDTO firstGoodsDTO;
    GoodsDTO secondGoodsDTO;
    GoodsDTO thirdGoodsDTO;
    Goods myGoods;
    Goods myOtherGoods;
    Goods firstGoods;
    Goods secondGoods;
    Goods thirdGoods;
    Cart currentCart;


    @BeforeEach
    void setUp() {
        InventoryManager inventoryManager = new InventoryManager();
        firstGoodsDTO = new GoodsDTO(1, inventoryManager);
        secondGoodsDTO = new GoodsDTO(3,inventoryManager);
        thirdGoodsDTO = new GoodsDTO(2,inventoryManager);
        currentCart = new Cart();

        //creates goods by calling on cart
        myGoods = currentCart.addGoodsToCart(firstGoodsDTO, 1);
        myOtherGoods = currentCart.addGoodsToCart(secondGoodsDTO, 1);

        //creates goods directly
        firstGoods = new Goods(firstGoodsDTO,1);
        secondGoods = new Goods(secondGoodsDTO, 1);
        thirdGoods = new Goods(thirdGoodsDTO, 1);
    }

    @AfterEach
    void tearDown() {
        firstGoodsDTO = null;
        secondGoodsDTO = null;
        thirdGoodsDTO = null;
        firstGoods = null;
        secondGoods = null;
        thirdGoods = null;
        myGoods = null;
        myOtherGoods = null;
        currentCart = null;
    }

    @Test
    void addGoodsToCart() {

        assertSame(myGoods, currentCart.getListOfGoods().get(0)," goods not added in the expected place in cart ");
        assertSame(myOtherGoods,currentCart.getListOfGoods().get(1),"goods not added in the expected place in cart ");
        assertNotSame(myGoods,currentCart.getListOfGoods().get(1)," goods not added in the expected place in cart ");

        currentCart.addGoodsToCart(firstGoodsDTO, 2);
        assertSame(myGoods,currentCart.getListOfGoods().get(0),"adding more items changes the wrong goods");
        assertSame(firstGoods.getGoodsDescription(),myGoods.getGoodsDescription()," item description changed for goods already in cart");
        assertEquals(3, currentCart.getListOfGoods().get(0).getNumberOfItems()," wrong number of items added to cart");
        assertEquals(2, currentCart.getListOfGoods().size(),"wrong number of goods in cart ");

        currentCart.addGoodsToCart(thirdGoodsDTO, 1);
        assertSame(thirdGoods.getNumberOfItems(),currentCart.getListOfGoods().get(2).getNumberOfItems()," wrong number of goods added");
        assertSame(thirdGoods.getGoodsDescription(),currentCart.getListOfGoods().get(2).getGoodsDescription()," wrong item description ");
        assertEquals(3, currentCart.getListOfGoods().size()," wrong number of goods in cart");
    }
}