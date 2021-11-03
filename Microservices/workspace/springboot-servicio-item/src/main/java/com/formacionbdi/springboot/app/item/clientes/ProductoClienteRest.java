package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.models.Item;
//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

//@FeignClient(name="servicio-productos",url="http://localhost:8001/")
@FeignClient(name="servicio-productos") //llevamos el url al properties a servicio-productos.ribbon.listOfServers
public interface ProductoClienteRest {
	
	/*
	Los atributos de @GetMapping,@PutMapping y etc deben ser indenticos a los @GetMapping,@PutMapping y etc del controler que se consumira, en nuestro caso
	identicos al ProductoController.java del proyecto springboot-servicio-productos
	*/

	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);	
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto,@PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
}
