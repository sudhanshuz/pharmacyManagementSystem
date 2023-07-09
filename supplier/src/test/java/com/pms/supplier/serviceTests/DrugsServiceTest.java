package com.pms.supplier.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.DrugRepository;
import com.pms.supplier.repository.SupplierRepository;
import com.pms.supplier.service.SupplyService;

public class DrugsServiceTest {

	
	@Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private SupplyService service;
    
    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	 @Test
	    public void testGetDrugPrice() throws ResourceNotFoundException {
	        // Arrange
	        String drugName = "Drug A";
	        double expectedPrice = 10.0;
	        Drugs drug = new Drugs("Drug A",10,new Date(),"b101");
	        when(drugRepository.findById(drugName)).thenReturn(Optional.of(drug));

	        // Act
	        double actualPrice = service.getDrugPrice(drugName);

	        // Assert
	        assertEquals(expectedPrice, actualPrice);
	        verify(drugRepository, times(1)).findById(drugName);
	    }
	 
	 @Test
	    public void testDeleteSupplier() throws ResourceNotFoundException {
	        // Arrange
	        int supplierId = 1;
	        Supplier supplier = new Supplier();
	        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

	        // Act
	        Supplier deletedSupplier = service.deleteSupplier(supplierId);

	        // Assert
	        verify(supplierRepository, times(1)).deleteById(supplierId);
	        verify(supplierRepository, times(1)).findById(supplierId);
	        assertEquals(supplier, deletedSupplier);
	    }

	    @Test
	    public void testDeleteSupplier_ThrowsException() {
	        // Arrange
	        int supplierId = 1;
	        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(ResourceNotFoundException.class, () -> service.deleteSupplier(supplierId));
	        verify(supplierRepository, times(1)).findById(supplierId);
	    }
	    
	    
	    @Test
	    public void testViewSuppliers() {
	        // Arrange
	        List<Supplier> expectedSuppliers = Arrays.asList(new Supplier(), new Supplier());
	        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

	        // Act
	        List<Supplier> actualSuppliers = service.viewSuppliers();

	        // Assert
	        verify(supplierRepository, times(1)).findAll();
	        assertEquals(expectedSuppliers, actualSuppliers);
	    }

	    @Test
	    public void testInsertSupplier() {
	        // Arrange
	        Supplier supplier = new Supplier();

	        // Act
	        Supplier insertedSupplier = service.insertSuppliers(supplier);

	        // Assert
	        verify(supplierRepository, times(1)).insert(supplier);
	        assertEquals(supplier, insertedSupplier);
	    }

	    @Test
	    public void testGetDrugs() {
	        // Arrange
	        List<Drugs> expectedDrugs = Arrays.asList(new Drugs(), new Drugs());
	        when(drugRepository.findAll()).thenReturn(expectedDrugs);

	        // Act
	        List<Drugs> actualDrugs = service.getDrugs();

	        // Assert
	        verify(drugRepository, times(1)).findAll();
	        assertEquals(expectedDrugs, actualDrugs);
	    }

	    @Test
	    public void testInsertDrug() {
	        Drugs drug = null;
	        Drugs insertedDrug = service.insertDrug(drug);
	        //verify(drugRepository, times(1)).insert(drug);
	        assertEquals(drug, insertedDrug);
	    }
	    
	    @Test
	    public void testAddStock_ValidIdAndValidDrug() throws ResourceNotFoundException {
	        // Arrange
	        int supplierId = 101;
	        String drugName = "Drug A";
	        int qty = 10;
	        Supplier supplier = new Supplier();
	        Drugs drug = new Drugs(drugName,10,new Date(),"spw");
	        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
	        when(drugRepository.findById(drugName)).thenReturn(Optional.of(drug));

	        // Act
	        Supplier updatedSupplier = service.addStock(supplierId, drugName, qty);

	        // Assert
	        verify(supplierRepository, times(1)).findById(supplierId);
	        verify(drugRepository, times(1)).findById(drugName);
	        assertTrue(updatedSupplier.getStock().stream().anyMatch(stock -> stock.containsKey(drugName) && stock.containsValue(qty)));
	    }

	    @Test
	    public void testAddStock_InvalidId() {
	        // Arrange
	        int supplierId = 1;
	        String drugName = "Drug A";
	        int qty = 10;
	        when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(ResourceNotFoundException.class, () -> service.addStock(supplierId, drugName, qty));
	        verify(supplierRepository, times(1)).findById(supplierId);
	    }

	    @Test
	    public void testAddStock_InvalidDrug() {
	        // Arrange
	        int supplierId = 1;
	        String drugName = "Nonexistent Drug";
	        int qty = 10;
	        Supplier supplier = new Supplier();
	        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
	        when(drugRepository.findById(drugName)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(ResourceNotFoundException.class, () -> service.addStock(supplierId, drugName, qty));
	        verify(supplierRepository, times(1)).findById(supplierId);
	        verify(drugRepository, times(1)).findById(drugName);
	    }

	    @Test
	    public void testViewDrugByName() throws ResourceNotFoundException {
	        // Arrange
	        String drugName = "Drug A";
	        Drugs expectedDrug = new Drugs(drugName,10,new Date(),"spw");
	        when(drugRepository.findById(drugName)).thenReturn(Optional.of(expectedDrug));

	        // Act
	        Drugs actualDrug = service.viewDrugByName(drugName);

	        // Assert
	        verify(drugRepository, times(1)).findById(drugName);
	        assertEquals(expectedDrug, actualDrug);
	    }

	    @Test
	    public void testViewDrugByName_ThrowsException() {
	        // Arrange
	        String drugName = "Nonexistent Drug";
	        when(drugRepository.findById(drugName)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(ResourceNotFoundException.class, () -> service.viewDrugByName(drugName));
	        verify(drugRepository, times(1)).findById(drugName);
	    }
	}

