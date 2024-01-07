package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class CartService {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void addToCart(String jwt, int productId, int customerId) throws IOException {
        String url = String.format("http://localhost:8080/Cart/addtocart/%d/%d", productId, customerId);
        HttpPost request = new HttpPost(url);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        // Skapa JSON-body för begäran
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();

        // Skapa cartItem och customerlist i JSON-body
        ObjectNode cartItemNode = mapper.createObjectNode();
        cartItemNode.put("id", productId);

        ObjectNode customerlistNode = mapper.createObjectNode();
        customerlistNode.put("custId", customerId);

        requestBody.set("cartItem", cartItemNode);
        requestBody.set("customerlist", customerlistNode);

        StringEntity entity = new StringEntity(requestBody.toString());
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("Response Code: " + response.getCode());

        if (response.getCode() == 201 || response.getCode() == 200) {
            System.out.println("Product added to cart successfully.");
        } else {
            System.out.println("Error occurred while adding the product to the cart.");
        }
    }



}
