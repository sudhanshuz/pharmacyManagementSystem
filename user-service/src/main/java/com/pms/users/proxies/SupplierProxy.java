package com.pms.users.proxies;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.pms.users.model.Supplier;


@FeignClient(name="supplier-service",url="localhost:8000")
public interface SupplierProxy {	
}
