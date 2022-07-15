package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(p->{
			//Se sustituye esta manera de obtener la property server.port
			//p.setPort(Integer.parseInt(env.getProperty("local.server.port").toString()));
			p.setPort(port);
			return p;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		//Se sustituye esta manera de obtener la property server.port
		Producto p = productoService.findById(id);
		int port = Integer.parseInt(env.getProperty("local.server.port").toString());
		p.setPort(port);
		
		boolean ok = false;
		if(!ok) {
			throw new RuntimeException("¡No se pudo cargar el producto!");
		}
		return p;
	}
}
