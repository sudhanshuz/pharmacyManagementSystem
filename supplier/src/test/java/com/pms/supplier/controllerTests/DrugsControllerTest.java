package com.pms.supplier.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.pms.supplier.controller.drugController;
import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Drugs;
import com.pms.supplier.service.SupplyService;

public class DrugsControllerTest {

	@Mock
    private SupplyService serviceObj;

    @InjectMocks
    private drugController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewDrugs() {

        List<Drugs> expectedDrugs = new ArrayList();
        expectedDrugs.add(new Drugs("Drug A",100,new Date(),"batchId",""));
        expectedDrugs.add(new Drugs("Drug B",100,new Date(),"lab101",""));
        when(serviceObj.getDrugs()).thenReturn(expectedDrugs);
        List<Drugs> actualDrugs = controller.viewDrugs();

        assertEquals(expectedDrugs, actualDrugs);
    }
    
    @Test
    public void testAddDrugs() {

        Drugs drug = new Drugs();
        when(serviceObj.insertDrug(drug)).thenReturn(drug);
        
        Drugs response = controller.addDrugs(drug);
        ResponseEntity<Drugs> responseEntity = ResponseEntity.ok(response);
        //assertEquals(HttpStatus., responseEntity.getStatusCode());
        assertEquals(drug, responseEntity.getBody());
    }
    
    
    @Test
    public void testGetDrugPrice() throws ResourceNotFoundException {
    
        String drugName = "Drug A";
        double expectedPrice = 10.0;
        when(serviceObj.getDrugPrice(drugName)).thenReturn(expectedPrice);


        double actualPrice = controller.getDrugPrice(drugName);


        assertEquals(expectedPrice, actualPrice);
    }
}
