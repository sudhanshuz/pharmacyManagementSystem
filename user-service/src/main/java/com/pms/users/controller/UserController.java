package com.pms.users.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pms.users.model.Drugs;
import com.pms.users.model.Orders;
import com.pms.users.model.Supplier;
import com.pms.users.model.User;
import com.pms.users.repository.SupplyRepo;
import com.pms.users.repository.UserRepository;
import com.pms.users.service.SequenceGeneratorService;
import com.pms.users.service.SupplierCopyService;
import com.pms.users.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SupplierCopyService supplierCopyService;
	@Autowired
	private SupplyRepo supplyRepo;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		if(user.getRole().equals("ROLE_ADMIN")||user.getRole().equals("ADMIN")||user.getRole().equals("admin")){
			logger.error("please put your role as doctor");
			return null;
		}
		user.setUserId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		return userService.saveUser(user);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/getUserById/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(Long.parseLong(userId));
	}
	
	@PutMapping("/edit")
	@PreAuthorize("hasRole('ADMIN')")
	public User editUser(@RequestBody User user) {
		return userService.editUser(user);
	}
	
	
	@DeleteMapping("deleteById/{userId}")
	@PreAuthorize("hasRole('ADMIN','DOCTOR')")
	public User deleteUserById(@PathVariable String userId) {
		return userService.deleteUserById(Long.parseLong(userId));
	}
	
	
	@GetMapping("/getByName/{name}")
	@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
	public Optional<User> findByUserName(@PathVariable String name) {
		return userService.getByName(name);	
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addSuppliers")
	public List<Supplier> addSupplier() {
		//ResponseEntity<List<Supplier>> suppliers=restTemplate.getForEntity("http://SUPPLIER-SERVICE/supplier/getAll",(Supplier[].class);
		return supplierCopyService.addSupplier();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addOrders")
	public List<Orders> addAllOrders() {
		
		return supplierCopyService.addAllOrders();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/viewOrders")
	public List<Orders> viewAllOrders() {
		
		return supplierCopyService.viewAllOrders();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/viewSuppliers")
	public List<Supplier> viewAllSuppliers() {
		
		return supplierCopyService.viewAllSuppliers();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addDrugs")
	public List<Drugs> addDrugs() {
		
		return supplierCopyService.addDrugs();
	}
	
	
		
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteDrug/{drugName}")
	public String deleteDrugs(@PathVariable String drugName) {
		return supplierCopyService.deleteDrugs(drugName);
	}
	
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
	@GetMapping("/viewDrugs")
	public List<Drugs> viewDrugs() {
		return supplierCopyService.viewDrugs();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/editDrugs")
	public Drugs editDrugs(@RequestBody Drugs drug) {
		return supplierCopyService.editDrugs(drug);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteSupplier/{supplierId}")
	public String deleteSupplier(@PathVariable String supplierId) {
		
		return supplierCopyService.deleteSupplier(Integer.parseInt(supplierId));
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/editSuppliers")
	public Supplier editSupplier(@RequestBody Supplier supplier) {
		return supplierCopyService.editSupplier(supplier);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	public String addOrdersToPickup() {
		
		return null;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public String verifyOrders() {
		return null;
	}
	
	@PreAuthorize("hasRole('DOCTOR')")
	public String placeOrder() {
		return null;
	}
}
