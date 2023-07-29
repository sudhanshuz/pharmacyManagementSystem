package com.pms.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.orders.exception.ResourceNotFoundException;
import com.pms.orders.model.NewOrders;
import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;
import com.pms.orders.model.VerifiedOrders;
import com.pms.orders.repository.NewOrdersRepo;
import com.pms.orders.repository.OrdersRepository;
import com.pms.orders.repository.PickedUpOrdersRepo;
import com.pms.orders.repository.VerifiedOrdersRepo;



@Service
public class OrdersService {
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
PickedUpOrdersRepo pickedUpOrdersRepo;
	@Autowired
	VerifiedOrdersRepo verifiedOrderRepo;
	@Autowired
	NewOrdersRepo newOrdersRepo;
	@Autowired
	RestTemplate restTemplate;
	public Orders addOrder(Orders order) {
		double total=0;
             String drugName=order.getDrugName();
             int qty=order.getQty();
             System.out.println(order);
     		double price=restTemplate.getForObject("http://SUPPLIER-SERVICE/drugs/getDrugPrice/"+drugName,Double.class);
     		total=total+(price*qty);
        order.setTotal(total);
		
		NewOrders vObj=new NewOrders(order.getOrderId(),order.getDocName(),order.getDocContact(),order.getDocEmail(),order.getTotal(),order.getPickupDate(),order.getDrugName(),order.getQty());
		newOrdersRepo.insert(vObj);
		
	return orderRepository.insert(order);	
	}

	public List<Orders> getOrders() {
		return orderRepository.findAll();
	}

	public Orders getOrdersDetailsById(long orderId) {		
		return orderRepository.findById(orderId).orElse(null);
	}

	public List<Orders> getOrdersByDocName(String docName) {
		return orderRepository.findByDocName(docName);
	}

	public Orders setTotalPrice(String drugName, int qty,long orderId) throws ResourceNotFoundException {
		double price=restTemplate.getForObject("http//SUPPLIER-SERVICE/drugs//getDrugPrice/"+drugName,Double.class);
		Orders order=orderRepository.findById(orderId).orElse(null);
		if(order==null) {
			throw new ResourceNotFoundException("invalid order id");
		}
		order.setTotal(price*qty);
		return orderRepository.save(order);
	}

	public Orders setPickupDate(long orderId) throws ResourceNotFoundException {
		Orders order=orderRepository.findById(orderId).orElse(null);
		if(order==null) {
			throw new ResourceNotFoundException("invalid order id");
		}
		String pickupDate=restTemplate.getForObject("http//SUPPLIER-SERVICE/setPickupDate/"+orderId,String.class);
		order.setPickupDate(pickupDate);
		return orderRepository.save(order);
	}

	public Orders editOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders deleteOrder(long orderId) {
		orderRepository.deleteById(orderId);
		return orderRepository.findById(orderId).orElse(null);
	}

	public VerifiedOrders addVerifiedOrders(Orders order) {
		
		VerifiedOrders vObj=new VerifiedOrders(order.getOrderId(),order.getDocName(),order.getDocContact(),order.getDocEmail(),order.getTotal(),order.getPickupDate(),order.getDrugName(),order.getQty(),true,false);
		verifiedOrderRepo.save(vObj);
		orderRepository.save(order);
		newOrdersRepo.deleteById(vObj.getOrderId());
		return vObj;
	}

	public Orders addPickedUpOrders(Orders order) {
		// TODO Auto-generated method stub
			PickedUpOrders pObj=new PickedUpOrders(order.getOrderId(),order.getDocName(),order.getDocContact(),order.getDocEmail(),order.getTotal(),order.getPickupDate(),order.getDrugName(),order.getQty(),order.isVerified(),order.isPickedUp());
			pObj.setSupplierId(order.getSupplierId());
			pickedUpOrdersRepo.save(pObj);
			verifiedOrderRepo.deleteById(order.getOrderId());
			return order;
	}

	public List<NewOrders> viewNewOrders() {
		// TODO Auto-generated method stub
		return newOrdersRepo.findAll();
	}

	public List<VerifiedOrders> viewVerifiedOrders() {
		// TODO Auto-generated method stub
		return verifiedOrderRepo.findAll();
	}

	public List<PickedUpOrders> viewPickedUpOrders() {
		// TODO Auto-generated method stub
		return pickedUpOrdersRepo.findAll();
	}


	public List<PickedUpOrders> getMyOrdersByDocName(String docName) {
		// TODO Auto-generated method stub
		return pickedUpOrdersRepo.findByDocName(docName);
	}

	public List<PickedUpOrders> getOrdersByPickUpDate(String pickUpDate) {
		// TODO Auto-generated method stub
		System.out.println(pickedUpOrdersRepo.findByPickUpDate(pickUpDate));
		return pickedUpOrdersRepo.findByPickUpDate(pickUpDate);
	}


	
	
	
	
}
