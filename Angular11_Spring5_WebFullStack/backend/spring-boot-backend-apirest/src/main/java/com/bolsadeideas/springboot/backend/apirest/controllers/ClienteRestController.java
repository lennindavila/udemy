package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;
import com.bolsadeideas.springboot.backend.apirest.models.services.IUploadFileService;

@CrossOrigin(origins= {"http://localhost:81","*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK) //este es el valor por defecto, aqui redundamos solo para tener presente la idea
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			cliente = clienteService.findById(id);
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar la consulta en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}	
	
	//@Valid activa las reglas de validacion definidas en el entity, si no se pone esta anotacion no hara caso a las reglas del entity
	//BindingResult es quien contiene los mensajes de error que ocurrieron al ejecutarse las reglas del entity que se activaron con el @Valid.	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/clientes")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente,BindingResult result) {
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			///forma clasica java 7 and below
			/*
			 * List<String> errors = new ArrayList<>();
			 * 
			 * for(FieldError err: result.getFieldErrors()) { errors.add("El campo '" +
			 * err.getField() + "' " + err.getDefaultMessage()); }
			 */
			
			//Forma java 8 con programacion funcional
			List<String> errors = result.getFieldErrors()
								  .stream()
								  .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
								  .collect(Collectors.toList());		
			
			//fin
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			//Al crear el prePersist en cliente, ya no necesitamos asignar un new Date() aqui.
			//cliente.setCreateAt(new Date());
			System.out.println("que fecha es:" + cliente.getCreateAt());
			clienteNew =  clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido creado con exito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = null;
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			///forma clasica java 7 and below
			/*
			 * List<String> errors = new ArrayList<>();
			 * 
			 * for(FieldError err: result.getFieldErrors()) { errors.add("El campo '" +
			 * err.getField() + "' " + err.getDefaultMessage()); }
			 */
			
			//Forma java 8 con programacion funcional
			List<String> errors = result.getFieldErrors()
								  .stream()
								  .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
								  .collect(Collectors.toList());			
			
			//fin
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}		
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {				
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setRegion(cliente.getRegion());
			
			clienteUpdated = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido actualizado con exito!");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> response = new HashMap<>();			
		
		try {
			 Cliente cliente = clienteService.findById(id);	
			 String nombreFotoAnterior = cliente.getFoto();
			 
			 uploadFileService.eliminar(nombreFotoAnterior);
			 /*if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				 Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				 File archivoFotoAnterior = rutaFotoAnterior.toFile();
				 if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					 archivoFotoAnterior.delete();
				 }				 
			 }*/			
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido eliminado con exito!");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);		
	}
	
	
	
	//optimizado reutilizable
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/clientes/upload")
	 public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo,@RequestParam("id")Long id){
		 Map<String,Object> response = new HashMap<>();
		 Cliente cliente = clienteService.findById(id);		  
		 
		 if(!archivo.isEmpty()) {			
			 
			 String nombreArchivo = null;
			 try {
				 nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}			 
			 
			 String nombreFotoAnterior = cliente.getFoto();
			 uploadFileService.eliminar(nombreFotoAnterior);				 
			 
			 cliente.setFoto(nombreArchivo);
			 clienteService.save(cliente);
			 
			 response.put("cliente", cliente);
			 response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		 }
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	 }
	
	//optimizado reutilizable
	// ":.+" es una expresion regular que indica que ira un punto y la extension del tipo de archivo junto al nombre, ejem la linea siguiente...
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){		
		Resource recurso = null;
		
		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}	
	
	
	//
	//@PostMapping("/clientes/upload")
	 public ResponseEntity<?> upload_bk(@RequestParam("archivo")MultipartFile archivo,@RequestParam("id")Long id){
		 Map<String,Object> response = new HashMap<>();
		 Cliente cliente = clienteService.findById(id);		  
		 
		 if(!archivo.isEmpty()) {
			 
			 String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			 Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			 log.info(rutaArchivo.toString());
			 
			 try {				
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente" + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}			 
			 
			 String nombreFotoAnterior = cliente.getFoto();
			 
			 if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				 Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				 File archivoFotoAnterior = rutaFotoAnterior.toFile();
				 if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					 archivoFotoAnterior.delete();
				 }				 
			 }			 
			 
			 cliente.setFoto(nombreArchivo);
			 clienteService.save(cliente);
			 
			 response.put("cliente", cliente);
			 response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		 }
		 
		 return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	 }
	
	//@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto_bk(@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		Resource recurso = null;
		
	
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}			
			//throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);		
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);	
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");		
		
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	

}
