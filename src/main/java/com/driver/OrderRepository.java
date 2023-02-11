package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.OutputDeviceAssigned;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

@Repository
public class OrderRepository {
  HashMap<String,Order> orderDb; // integer=orderId
  HashMap<String,DeliveryPartner> deliveryPartnerDb;  //integer= deliveryId
  HashMap<String,String> assignedOrder;
  HashMap<String, List<Order>> orderPartnerMapping; //string=partnerId

  public OrderRepository() {
   orderDb=new HashMap<>();
   deliveryPartnerDb=new HashMap<>();
   assignedOrder=new HashMap<>();
   orderPartnerMapping=new HashMap<>();
  }

  //adding order to the DB
  public void addOrder(Order order){
  String Id= order.getId();
  orderDb.put(Id,order);
  }

  //adding partner to the DB
  public  void addPartner(String partnerId){
   deliveryPartnerDb.put(partnerId,new DeliveryPartner(partnerId));

  }

  // add orderPartnerPair
  public void addOrderPartnerPair(String orderId, String partnerId){
      if(orderDb.containsKey(orderId) && deliveryPartnerDb.containsKey(partnerId) && !assignedOrder.containsKey(orderId)){

          Order currentOrder = orderDb.get(orderId);

          DeliveryPartner currentPartner = deliveryPartnerDb.get(partnerId);

          currentPartner.setNumberOfOrders(currentPartner.getNumberOfOrders()+1);

          assignedOrder.put(orderId,partnerId);

          List<Order> ordersListOfAPartner = new ArrayList<>();

          if(orderPartnerMapping.containsKey(partnerId))
              ordersListOfAPartner = orderPartnerMapping.get(partnerId);
          ordersListOfAPartner.add(currentOrder);

          orderPartnerMapping.put(partnerId,ordersListOfAPartner);
      }
  }

  // get order by ID
  public Order getOrderById(String orderId){
  Order order=orderDb.get(orderId);
  return order;
  }

  // get delivery Partner by partner Id
  public DeliveryPartner getDeliveryPartner(String partnerId){
  DeliveryPartner deliveryPartner=deliveryPartnerDb.get(partnerId);
  return deliveryPartner;
  }

  // get order count by partner ID
  public int getOrderCountByPartnerId(String partnerId){
  if(deliveryPartnerDb.containsKey(partnerId)){
   return deliveryPartnerDb.get(partnerId).getNumberOfOrders();
  }
  return -1;
  }

  // list of order corresponding to partnerId
  public List<String> getOrdersByPartnerId(String partnerId){
      List<String> orderList=new ArrayList<>();
      if(orderPartnerMapping.containsKey(partnerId)){
     List<Order> orders=orderPartnerMapping.get(partnerId);
     for(Order order:orders){
      orderList.add(order.getId());
                 }
     return orderList; }
   return orderList;
  }

  //get list of all orders
  public List<String> getAllOrders(){
  List<String> allOrders=new ArrayList<>();
  for(Order order:orderDb.values()){
    allOrders.add(order.getId());
  }
  return allOrders;
  }

  //count of unassigned orders
  public int getCountOfUnassignedOrders(){
  return orderDb.size()-assignedOrder.size();
  }

  //order left after given time
  public int  getOrdersLeftAfterGivenTimeByPartnerId(Integer time,String partnerId){
  List<Order> orderList =orderPartnerMapping.get(partnerId);
  int countOfOrderLeft=0;
  for(Order order:orderList){
      if(order.getDeliveryTime()>time)
          countOfOrderLeft++;
  }
  return countOfOrderLeft;
  }

  //Last delivery time correspond to partner id
  public int getLastDeliveryTimeByPartnerId(String partnerId){
      List<Order> ordersOfAPartner = orderPartnerMapping.get(partnerId);

      Integer latestTime = 0;

      for(Order order : ordersOfAPartner){
          if(order.getDeliveryTime()>=latestTime)
              latestTime = order.getDeliveryTime();
      }
      return latestTime;
   }

   //delete partnerById
    public void deletePartnerById( String partnerId){


    }

    // delete order by partner
    public void deleteOrderById( String orderId){

    }

}
