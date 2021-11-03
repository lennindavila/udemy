package com.formacionbdi.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		//Sin definir @LoadBalanced en el AppConfig
		//List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar",Producto[].class));
		
		//luego de definir @LoadBalanced en el AppConfig, se retira el "localhost:8001" y ponemos el nombre del app al que nos queremos conectar "servicio-productos"
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar",Producto[].class));		
		return productos.stream().map( p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		//Sin definir @LoadBalanced en el AppConfig
		//Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}",Producto.class,pathVariables);
		
		//luego de definir @LoadBalanced en el AppConfig, se retira el "localhost:8001" y ponemos el nombre del app al que nos queremos conectar "servicio-productos"
		Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}",Producto.class,pathVariables);
		return new Item(producto,cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);		
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST,body,Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/editar/{id}", HttpMethod.PUT,body,Producto.class,pathVariables);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public void delete(Long id) {
		Map<String,String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());		
		clienteRest.delete("http://servicio-productos/eliminar/{id}",pathVariables);
	}

}
