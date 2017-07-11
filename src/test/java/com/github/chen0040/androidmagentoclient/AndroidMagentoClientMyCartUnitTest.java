package com.github.chen0040.androidmagentoclient;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.chen0040.magento.models.Cart;
import com.github.chen0040.magento.models.CartItem;
import com.github.chen0040.magento.models.CartTotal;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;


/**
 * Created by xschen on 10/7/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class AndroidMagentoClientMyCartUnitTest extends AndroidMagentoLogContext {


   @Test
   public void test_newCart(){
      AndroidMagentoClient client = new AndroidMagentoClient(Mediator.url);
      String token = client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String cartId = client.myCart().newQuote();
      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("token: {}", token);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_addItemToCart(){
      AndroidMagentoClient client = new AndroidMagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      System.out.println(quoteId);

      item = client.myCart().addItemToCart(quoteId, item);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("cartItem: \r\n{}", JSON.toJSONString(item, SerializerFeature.PrettyFormat));
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_updateItemInCart(){
      AndroidMagentoClient client = new AndroidMagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.myCart().addItemToCart(quoteId, item);
      item.setQty(3);
      item = client.myCart().updateItemInCart(quoteId, item);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("cartItem: \r\n{}", JSON.toJSONString(item, SerializerFeature.PrettyFormat));
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_deleteItemInCart(){
      AndroidMagentoClient client = new AndroidMagentoClient(Mediator.url);
      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      String quoteId = client.myCart().newQuote();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.myCart().addItemToCart(quoteId, item);
      boolean result = client.myCart().deleteItemInCart(item.getItem_id());


      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("result: {}", result);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }

   @Test
   public void test_transferGuestCartToMyCart(){
      AndroidMagentoClient client = new AndroidMagentoClient(Mediator.url);

      String cartId = client.guestCart().newCart();

      CartItem item = new CartItem();
      item.setQty(1);
      item.setSku("product_dynamic_758");

      item = client.guestCart().addItemToCart(cartId, item);

      client.loginAsClient(Mediator.customerUsername, Mediator.customerPassword);
      boolean result = client.myCart().transferGuestCartToMyCart(cartId);

      Cart cart = client.myCart().getCart();
      CartTotal cartTotal = client.myCart().getCartTotal();

      logger.info("result: {}", result);
      logger.info("cart: \r\n{}", JSON.toJSONString(cart, SerializerFeature.PrettyFormat));
      logger.info("cartTotal: \r\n{}", JSON.toJSONString(cartTotal, SerializerFeature.PrettyFormat));
   }
}
