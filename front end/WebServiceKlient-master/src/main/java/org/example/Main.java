package org.example;

import org.apache.hc.core5.http.ParseException;
import org.example.models.ClothingItem;
import org.example.models.LoginResponse;

import java.io.IOException;

import static org.example.service.BookService.*;
import static org.example.service.UserService.*;
import static org.example.service.ClothingItemService.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello world!");


        //LoginResponse register = adminRegister();
        LoginResponse login = login();
        if (login == null) {
            System.out.println("Inloggning misslyckades");
            return;
        }

        getAllClothingItems(login.getAccess_token());
        //addClothingItem(login.getAccess_token());
        //getAllClothingItems(login.getAccess_token());
        System.out.println("-------------------");
        //int itemid = 52;
        //getClothingItemById(login.getAccess_token(), itemid);
        //deleteClothingItem(login.getAccess_token(),itemid);

        int itemId = 2;
        ClothingItem retrievedItem = getClothingItemById(login.getAccess_token(), itemId);

        // Om produkten hittades, uppdatera den
        if (retrievedItem != null) {
            retrievedItem.setProductName("Updated Product Name");
            retrievedItem.setPrice(799.99);

            // Uppdatera produkten
            updateClothingItem(login.getAccess_token(), itemId, retrievedItem);
        }





    }


}