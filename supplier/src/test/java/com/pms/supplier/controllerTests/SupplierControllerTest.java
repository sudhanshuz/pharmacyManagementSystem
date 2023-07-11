package com.pms.supplier.controllerTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pms.supplier.controller.supplyController;
import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Orders;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.SupplierRepository;
import com.pms.supplier.service.SupplyService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SupplierControllerTest {

    @Mock
    private SupplyService serviceObj;

    @InjectMocks
    private supplyController supplierController;
    
    @Mock
    private SupplierRepository supplyRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = Arrays.asList(new Supplier(), new Supplier());
        when(serviceObj.viewSuppliers()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierController.getSuppliers();

        // Assert
        verify(serviceObj, times(1)).viewSuppliers();
        assertEquals(expectedSuppliers, actualSuppliers);
    }

    @Test
    public void testAddSuppliers() {
        // Arrange
        Supplier supplier = new Supplier();

        // Act
        Supplier insertedSupplier = supplierController.addSuppliers(supplier);

        // Assert
        verify(serviceObj, times(1)).insertSuppliers(supplier);
        assertEquals(supplier, insertedSupplier);
    }

    @Test
    public void testLoadStock() throws ResourceNotFoundException {
        // Arrange
        int id = 1;
        String drugName = "Drug A";
        int qty = 10;
        Supplier supplier = new Supplier();
        when(serviceObj.addStock(id, drugName, qty)).thenReturn(supplier);

        // Act
        Supplier updatedSupplier = supplierController.loadStock(id, drugName, qty);

        // Assert
        verify(serviceObj, times(1)).addStock(id, drugName, qty);
        assertEquals(supplier, updatedSupplier);
    }

    @Test
    public void testSetPickupDate() {
        // Arrange
        String orderId = "123";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String expectedPickupDate = dateFormat.format(date);

        // Act
        String actualPickupDate = supplierController.setPickupDate(orderId);

        // Assert
        assertEquals(expectedPickupDate, actualPickupDate);
    }

    @Test
    public void testDeleteSupplier() throws ResourceNotFoundException {
        // Arrange
        String supplierId = "1";
        int id = Integer.parseInt(supplierId);
        Supplier supplier = new Supplier();
        when(serviceObj.deleteSupplier(id)).thenReturn(supplier);

        // Act
        Supplier deletedSupplier = supplierController.deleteSupplier(supplierId);

        // Assert
        verify(serviceObj, times(1)).deleteSupplier(id);
        assertEquals(supplier, deletedSupplier);
    }

    @Test
    public void testEditSupplier() {
        // Arrange
        Supplier supplier = new Supplier();

        // Act
        Supplier editedSupplier = supplierController.editSupplier(supplier);

        // Assert
        verify(serviceObj, times(1)).editSupplier(supplier);
        assertEquals(supplier, editedSupplier);
    }

    @Test
    public void testViewAvailableOrders() {
        // Arrange
        Orders[] expectedOrders = {new Orders(), new Orders()};
        when(serviceObj.serviceObj()).thenReturn(expectedOrders);

        // Act
        Orders[] actualOrders = supplierController.viewAvailableOrders();

        // Assert
        verify(serviceObj, times(1)).serviceObj();
        assertArrayEquals(expectedOrders, actualOrders);
    }

    @Test
    void testPickUpOrder() {
        // Mock input values
        String orderId = "123";
        String supplierId = "456";
        long parsedOrderId = 123L;
        int parsedSupplierId = 456;

        // Mock service method behavior
        Orders expectedOrders = new Orders(); // Create an instance of the expected Orders object
        when(serviceObj.pickUpOrder(parsedOrderId, parsedSupplierId)).thenReturn(expectedOrders);

        // Call the method under test
        Orders result = supplierController.pickUpOrder(orderId, supplierId);

        // Verify the service method was called with the correct arguments
        verify(serviceObj).pickUpOrder(parsedOrderId, parsedSupplierId);

        // Assert the expected result
        assertEquals(expectedOrders, result);
    }

    @Test
    public void testFindMyOrders() {
        // Arrange
        String supplierId = "1";
        int id = Integer.parseInt(supplierId);
        List<Orders> expectedOrders = Arrays.asList(new Orders(), new Orders());
        when(serviceObj.findMyOrders(id)).thenReturn(expectedOrders);

        // Act
        List<Orders> actualOrders = supplierController.findMyOrders(supplierId);

        // Assert
        verify(serviceObj, times(1)).findMyOrders(id);
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    public void testViewSupplierByName() {
        // Arrange
        String supplierName = "Supplier A";
        Supplier expectedSupplier = new Supplier();
        when(supplyRepository.findBySupplierName(supplierName)).thenReturn(expectedSupplier);

        // Act
        Supplier actualSupplier = supplierController.viewSupplierByName(supplierName);

        // Assert
        verify(supplyRepository, times(1)).findBySupplierName(supplierName);
        assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    public void testViewSupplierById() {
        // Arrange
        String supplierId = "1";
        int id = Integer.parseInt(supplierId);
        Optional<Supplier> expectedSupplier = Optional.of(new Supplier());
        when(supplyRepository.findById(id)).thenReturn(expectedSupplier);

        // Act
        Optional<Supplier> actualSupplier = supplierController.viewSupplierById(supplierId);

        // Assert
        verify(supplyRepository, times(1)).findById(id);
        assertEquals(expectedSupplier, actualSupplier);
    }
}

