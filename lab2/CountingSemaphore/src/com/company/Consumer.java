package com.company;

public class Consumer implements Runnable {
    private Shop shop;
    private Integer identifier;

    public Consumer(Shop shop, Integer identifier) {
        this.shop = shop;
        this.identifier = identifier;

    }

    public void run() {

        shop.take(this.identifier);
        shop.put(this.identifier);


    }
}
