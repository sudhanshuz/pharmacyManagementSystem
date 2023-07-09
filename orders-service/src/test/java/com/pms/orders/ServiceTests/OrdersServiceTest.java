package com.pms.orders.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.pms.orders.model.NewOrders;
import com.pms.orders.model.Orders;
import com.pms.orders.model.PickedUpOrders;
import com.pms.orders.model.VerifiedOrders;
import com.pms.orders.repository.NewOrdersRepo;
import com.pms.orders.repository.OrdersRepository;
import com.pms.orders.repository.PickedUpOrdersRepo;
import com.pms.orders.repository.VerifiedOrdersRepo;
import com.pms.orders.service.OrdersService;

public class OrdersServiceTest {
	@Mock
    private OrdersRepository orderRepository;

    @Mock
    private PickedUpOrdersRepo pickedUpOrdersRepo;

    @Mock
    private VerifiedOrdersRepo verifiedOrderRepo;

    @Mock
    private NewOrdersRepo newOrdersRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrdersService ordersService;

    public OrdersServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrders() {
        // Arrange
        List<Orders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Orders());
        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Act
        List<Orders> result = ordersService.getOrders();

        // Assert
        assertEquals(expectedOrders, result);
        verify(orderRepository).findAll();
    }

    @Test
    void testGetOrdersDetailsById_existingOrderId() {
        // Arrange
        long orderId = 1L;
        Orders expectedOrder = new Orders();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        Orders result = ordersService.getOrdersDetailsById(orderId);

        // Assert
        assertEquals(expectedOrder, result);
        verify(orderRepository).findById(orderId);
    }

    @Test
    void testGetOrdersDetailsById_nonExistingOrderId() {
        // Arrange
        long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        Orders result = ordersService.getOrdersDetailsById(orderId);

        // Assert
        assertNull(result);
        verify(orderRepository).findById(orderId);
    }

    @Test
    void testGetOrdersByDocName() {
        // Arrange
        String docName = "John Doe";
        List<Orders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new Orders());
        when(orderRepository.findByDocName(docName)).thenReturn(expectedOrders);
        List<Orders> result = ordersService.getOrdersByDocName(docName);

        assertEquals(expectedOrders, result);
        verify(orderRepository).findByDocName(docName);
    }

    @Test
    void testViewNewOrders() {
        // Arrange
        List<NewOrders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new NewOrders());
        when(newOrdersRepo.findAll()).thenReturn(expectedOrders);

        // Act
        List<NewOrders> result = ordersService.viewNewOrders();

        // Assert
        assertEquals(expectedOrders, result);
        verify(newOrdersRepo).findAll();
    }

    @Test
    void testViewVerifiedOrders() {
        // Arrange
        List<VerifiedOrders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new VerifiedOrders());
        when(verifiedOrderRepo.findAll()).thenReturn(expectedOrders);

        // Act
        List<VerifiedOrders> result = ordersService.viewVerifiedOrders();

        // Assert
        assertEquals(expectedOrders, result);
        verify(verifiedOrderRepo).findAll();
    }

    @Test
    void testViewPickedUpOrders() {
        // Arrange
        List<PickedUpOrders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new PickedUpOrders());
        when(pickedUpOrdersRepo.findAll()).thenReturn(expectedOrders);

        // Act
        List<PickedUpOrders> result = ordersService.viewPickedUpOrders();

        // Assert
        assertEquals(expectedOrders, result);
        verify(pickedUpOrdersRepo).findAll();
    }

    @Test
    void testGetMyOrdersByDocName() {
        // Arrange
        String docName = "John Doe";
        List<PickedUpOrders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new PickedUpOrders());
        when(pickedUpOrdersRepo.findByDocName(docName)).thenReturn(expectedOrders);

        // Act
        List<PickedUpOrders> result = ordersService.getMyOrdersByDocName(docName);

        // Assert
        assertEquals(expectedOrders, result);
        verify(pickedUpOrdersRepo).findByDocName(docName);
    }

    @Test
    void testGetOrdersByPickUpDate() {
        // Arrange
        String pickUpDate = "2023-07-09";
        List<PickedUpOrders> expectedOrders = new ArrayList<>();
        expectedOrders.add(new PickedUpOrders());
        when(pickedUpOrdersRepo.findByPickUpDate(pickUpDate)).thenReturn(expectedOrders);

        // Act
        List<PickedUpOrders> result = ordersService.getOrdersByPickUpDate(pickUpDate);

        // Assert
        assertEquals(expectedOrders, result);
        verify(pickedUpOrdersRepo).findByPickUpDate(pickUpDate);
    }

}
