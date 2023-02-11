package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
    orderRepository.addOrder(order);

    }

    public void addPartner( String PartnerId){
    orderRepository.addPartner(PartnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
    orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
    return orderRepository.getOrderById(orderId);

    }

    public DeliveryPartner getDeliveryPartner(String partnerId){
    return orderRepository.getDeliveryPartner(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId){
    return  orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
    return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders(){
    return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassignedOrders(){
    return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer  getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int hoursToMins = Integer.valueOf(time.substring(0,2)) * 60;
        int mins = Integer.valueOf(time.substring(3,5));
        int Time = hoursToMins + mins;
    return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(Time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
     int time=orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
     return minutesToTimeString(time);
    }

    public String minutesToTimeString(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return String.format("%02d:%02d", hours, min);
    }

    public void deletePartnerById( String partnerId){

    }

    public void deleteOrderById( String orderId){

    }


}
