package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        int time=Integer.valueOf(deliveryTime);// The deliveryTime has to converted from string to int and then stored in the attribute
        int hoursToMins = Integer.valueOf(deliveryTime.substring(0,2)) * 60;
        int mins = Integer.valueOf(deliveryTime.substring(3,5));
        this.deliveryTime = hoursToMins + mins;// deliveryTime  = HH*60 + MM

        this.id=id;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
