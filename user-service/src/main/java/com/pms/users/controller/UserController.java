package com.pms.users.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pms.users.model.AuthRequest;
import com.pms.users.model.Drugs;
import com.pms.users.model.Orders;
import com.pms.users.model.Supplier;
import com.pms.users.model.User;
import com.pms.users.repository.SupplyRepo;
import com.pms.users.repository.UserRepository;
import com.pms.users.service.JwtService;
import com.pms.users.service.SequenceGeneratorService;
import com.pms.users.service.SupplierCopyService;
import com.pms.users.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private JwtService jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	//done 
	
	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		user.setUserId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		return userService.saveUser(user);
	}
	
	//done
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return userService.getAll();
	}
	
	//done
	
	@GetMapping("/getUserById/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR')")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(Long.parseLong(userId));
	}
	
	//done
	
	@PutMapping("/edit")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User editUser(@RequestBody User user) {
		return userService.editUser(user);
	}
	
	//done
	
	@DeleteMapping("deleteById/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR')")
	public User deleteUserById(@PathVariable String userId) {
		return userService.deleteUserById(Long.parseLong(userId));
	}
	
	//done
	
	@GetMapping("/getByName/{name}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR')")
	public Optional<User> findByUserName(@PathVariable String name) {
		return userService.getByName(name);	
	}
	
	//done
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addSuppliers")
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		return supplierCopyService.addSupplier(supplier);
	}
	
	//pending
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/addOrders")
	public List<Orders> addAllOrders() {
		
		return supplierCopyService.addAllOrders();
	}
	
	//done
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/viewOrders")
	public Orders[] viewAllOrders() {
		
		return supplierCopyService.viewAllOrders();
	}
	
	//done
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/viewSuppliers")
	public Supplier[] viewAllSuppliers() {
		
		return supplierCopyService.viewAllSuppliers();
	}
	
	
	//done
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addDrugs")
	public Drugs addDrugs(@RequestBody Drugs drug) {
		
		return supplierCopyService.addDrugs(drug);
	}
	
	
	//done
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteDrug/{drugName}")
	public String deleteDrugs(@PathVariable String drugName) {
		return supplierCopyService.deleteDrugs(drugName);
	}
	
	
	
	//done
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR')")
	@GetMapping("/viewDrugs")
	public Drugs[] viewDrugs() {
		return supplierCopyService.viewDrugs();
	}
	
	//done
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editDrugs")
	public String editDrugs(@RequestBody Drugs drug) {
		return supplierCopyService.editDrugs(drug);
	}
	
	//done
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteSupplier/{supplierId}")
	public String deleteSupplier(@PathVariable String supplierId) {
		
		return supplierCopyService.deleteSupplier(Integer.parseInt(supplierId));
	}
	
	//done
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/editSuppliers")
	public String editSupplier(@RequestBody Supplier supplier) {
		return supplierCopyService.editSupplier(supplier);
	}
	
	//pending
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pickUpOrders")
	public List<Orders> addOrdersToPickup() {
		
		return supplierCopyService.addOrdersToPickup();
	}
	
	//done
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/verifyOrderByOrderId/{orderId}")
	public String verifyOrders(@PathVariable String orderId) {
		
		return supplierCopyService.verifyOrders(Long.parseLong(orderId));
	}
	
	//done
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/placeOrder")
	public Orders placeOrder(@RequestBody Orders order) {
		
		return supplierCopyService.placeOrder(order);
	}
	
	//done
	@GetMapping("/viewNewOrders")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Orders[] viewNewOrders(){
		return restTemplate.getForObject("http://ORDERS-SERVICE/orders/viewNewOrders",Orders[].class);
	}
	
	//done
	 @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }


	    }
}
