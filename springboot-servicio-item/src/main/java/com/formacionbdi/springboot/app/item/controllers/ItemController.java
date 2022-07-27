package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; //Lo comentamos para poder utilizar Resilience4j

@RestController
public class ItemController {
	
	private Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	//@Qualifier("serviceFeign")
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(@RequestParam(name="nombre", required = false) String nombre, @RequestHeader(name="token-request", required = false) String tokenRequest){
		logger.info(String.format("Nombre %s", nombre));
		logger.info(String.format("Token- request %s", tokenRequest));
		return itemService.findAll();
	}
	
	//@HystrixCommand(fallbackMethod = "metodoAlternativo") //Lo comentamos para poder utilizar Resilience4j
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item itemDefecto = getItemDefecto(id, cantidad);
		return itemDefecto;
	}
	
	private Item getItemDefecto(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		producto.setId(id);
		producto.setNombre("Cámara sony");
		producto.setPrecio(500.0);
		item.setCantidad(cantidad);
		item.setProducto(producto);
		return item;
	}
	
}
