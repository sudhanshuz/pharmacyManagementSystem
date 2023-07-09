package com.pms.orders.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.pms.orders.controller.OrdersController;
import com.pms.orders.exception.ResourceNotFoundException;
import com.pms.orders.model.Orders;
import com.pms.orders.service.OrdersService;
import com.pms.orders.service.SequenceGeneratorService;

public class OrdersControllerTest {

	@Mock
	  private OrdersService ordersService;
	
	@Mock
	  private SequenceGeneratorService Seqservice;

	  @InjectMocks
	  private OrdersController ordersController;

	  @BeforeEach
	  public void setup() {
	    MockitoAnnotations.initMocks(this);
	  }

	  @Test
	  public void testSaveOrder() {
	    // Mocking dependencies
	    Orders order = new Orders();
	    order.setOrderId(1L);
	    when(Seqservice.generateSequence(Orders.SEQUENCE_NAME)).thenReturn(1L);
	    when(ordersService.addOrder(order)).thenReturn(order);

	    // Call the API
	    Orders order1= ordersController.save(order);
	    ResponseEntity<Orders> response=ResponseEntity.ok(order1);
	    // Verify the response
	    //assertEquals(HttpStatus.SC_OK, response.getStatusCode());
	    assertEquals(order, response.getBody());
	  }

	  @Test
	  public void testGetAllOrders() {
	    // Mocking dependencies
	    List<Orders> ordersList = new ArrayList();
	    when(ordersService.getOrders()).thenReturn(ordersList);

	    // Call the API
	    List<Orders> response = ordersController.getOrders();

	    // Verify the response
	    assertEquals(ordersList, response);
	  }

	  @Test
	  public void testGetOrdersDetailsById() {
	    // Mocking dependencies
	    long orderId = 1L;
	    Orders order = new Orders();
	    when(ordersService.getOrdersDetailsById(orderId)).thenReturn(order);

	    // Call the API
	    Orders response = ordersController.getOrdersDetailsById(orderId);

	    // Verify the response
	    assertEquals(order, response);
	  }
	  
	  @Test
	    void testSetTotalPrice() throws NumberFormatException, ResourceNotFoundException {
	        // Arrange
	        String drugName = "Drug 1";
	        String qty = "10";
	        long orderId = 12345L;
	        Orders expectedOrder = new Orders();
	        when(ordersService.setTotalPrice(drugName, Integer.parseInt(qty), orderId)).thenReturn(expectedOrder);

	        // Act
	        Orders result = ordersController.setTotalPrice(drugName, qty, orderId);

	        // Assert
	        assertEquals(expectedOrder, result);

	    }
	  
	  @Test
	    void testSetPickupDate() throws ResourceNotFoundException {
	        // Arrange
	        long orderId = 12345L;
	        Orders expectedOrder = new Orders();
	        when(ordersService.setPickupDate(orderId)).thenReturn(expectedOrder);

	        // Act
	        Orders result = ordersController.setPickupDate(orderId);

	        // Assert
	        assertEquals(expectedOrder, result);
	        verify(ordersService).setPickupDate(orderId);
	    }
	  
	  @Test
	    void testEditOrder() {
	        // Arrange
	        Orders order = new Orders();
	        Orders expectedOrder = new Orders();
	        when(ordersService.editOrder(order)).thenReturn(expectedOrder);

	        // Act
	        Orders result = ordersController.editOrder(order);

	        // Assert
	        assertEquals(expectedOrder, result);
	        verify(ordersService).editOrder(order);
	    }

	    @Test
	    void testDeleteOrder() {
	        // Arrange
	        String orderId = "12345";
	        long parsedOrderId = 12345L;
	        Orders expectedOrder = new Orders();
	        when(ordersService.deleteOrder(parsedOrderId)).thenReturn(expectedOrder);

	        // Act
	        Orders result = ordersController.deleteOrder(orderId);

	        // Assert
	        assertEquals(expectedOrder, result);
	        verify(ordersService).deleteOrder(parsedOrderId);
	    }



}
