package com.pms.supplier.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pms.supplier.exception.ResourceNotFoundException;
import com.pms.supplier.model.Orders;
import com.pms.supplier.model.Supplier;
import com.pms.supplier.repository.SupplierRepository;
import com.pms.supplier.service.SupplyService;

@RestController
@RequestMapping("/supplier")
@CrossOrigin("*")
public class supplyController {
	@Autowired
	private SupplyService serviceObj;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SupplierRepository supplyRepository;
	
	Logger logger=LoggerFactory.getLogger(supplyController.class);
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/getAll")
	public List<Supplier> getSuppliers() {
		logger.debug("if list is null");
		logger.info("executed");
		return serviceObj.viewSuppliers();
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PostMapping("/add")
	public Supplier addSuppliers(@RequestBody @Valid Supplier supplier) {
		return serviceObj.insertSuppliers(supplier);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PutMapping("/load")
	public Supplier loadStock(@RequestParam int id,@RequestParam String drugName,@RequestParam int qty) throws ResourceNotFoundException{
		return serviceObj.addStock(id,drugName,qty);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/setPickupDate/{orderId}")
	public String setPickupDate(@PathVariable String orderId) {
		//verify order if everything is ok set pickup date
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    return dateFormat.format(date);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@DeleteMapping("/delete/{supplierId}")
	public Supplier deleteSupplier(@PathVariable String supplierId ) throws ResourceNotFoundException {
		int id=Integer.parseInt(supplierId);
		return serviceObj.deleteSupplier(id );
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PutMapping("/edit")
	public Supplier editSupplier(@RequestBody Supplier supplier) {
		return serviceObj.editSupplier(supplier);
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/viewAvailableOrders")
	public Orders[] viewAvailableOrders() {
		return serviceObj.serviceObj();
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@PutMapping("/pickUpOrder/{orderId}/{supplierId}")
	public String pickUpOrder(@PathVariable String orderId,@PathVariable String supplierId) {
		return serviceObj.pickUpOrder(Long.parseLong(orderId),Integer.parseInt(supplierId));
	}
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:
	 */
	@GetMapping("/findMyOrders/{supplierId}")
	public List<Orders> findMyOrders(@PathVariable String supplierId) {
		return serviceObj.findMyOrders(Integer.parseInt(supplierId));
	}
	@GetMapping("/viewSupplierByName/{supplierName}")
	public Supplier viewSupplierByName(@PathVariable String supplierName) {
		return supplyRepository.findBySupplierName(supplierName);
	}
	
	@GetMapping("/viewSupplierById/{supplierId}")
	public Optional<Supplier> viewSupplierById(@PathVariable String supplierId) {
		return supplyRepository.findById(Integer.parseInt(supplierId));
	}
}
