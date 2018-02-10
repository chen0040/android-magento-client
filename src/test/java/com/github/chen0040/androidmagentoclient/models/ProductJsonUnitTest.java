package com.github.chen0040.androidmagentoclient.models;

import com.alibaba.fastjson.JSON;
import com.github.chen0040.androidmagentoclient.AndroidMagentoLogContext;
import com.github.chen0040.magento.models.MagentoAttribute;
import com.github.chen0040.magento.models.Product;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProductJsonUnitTest extends AndroidMagentoLogContext {


    @Test
    public void testJsonDeserialization() {
        String json = readStream(ProductJsonUnitTest.class.getClassLoader().getResourceAsStream("product.json"));
        logger.info("json: {}", json);
        Product product = JSON.parseObject(json, Product.class);

        logger.info("sku: {}", product.getSku());
        for(MagentoAttribute ma : product.getCustom_attributes()){
            logger.info("custom attribute: key = {}, value = {}", ma.getAttribute_code(), ma.getValue());
        }
        for(MagentoAttribute ma : product.getExtension_attributes()){
            logger.info("extension attribute: key = {}, value = {}", ma.getAttribute_code(), ma.getValue());
        }
    }

    private String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        }catch(IOException ioex){
            logger.error("Failed to read stream", ioex);
        }
        return sb.toString();
    }

}
