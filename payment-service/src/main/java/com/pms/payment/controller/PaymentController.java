package com.pms.payment.controller;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.payment.model.OrderRequest;
import com.pms.payment.model.OrderResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentController {
	
	
	private RazorpayClient client;
	private static final String SECRET_ID = "rzp_test_qhkBI8zT2ejI3F";
	private static final String SECRET_KEY = "r5JaLYFeYuXBFrqLGbwK6wvM";
	
	
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description:create order for razorpay account
	 */
	@PostMapping("/createOrder")
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) throws RazorpayException {
		OrderResponse response = new OrderResponse();
			client = new RazorpayClient(SECRET_ID, SECRET_KEY);

			Order order = createRazorPayOrder(orderRequest.getAmount());
			System.out.println("---------------------------");
			String orderId = (String) order.get("id");
			System.out.println("Order ID: " + orderId);
			System.out.println("---------------------------");
			response.setRazorpayOrderId(orderId);
			response.setApplicationFee("" + orderRequest.getAmount());
				response.setSecretKey(SECRET_KEY);
				response.setSecretId(SECRET_ID);
				response.setPgName("razor1");
			return response;
	}
	
	/* Author: Sudhanshu
	  Modified By:Sudhanshu
	  Modified Time:
	  description: add amount related info to order
	 */
	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.orders.create(options);
	}
}
