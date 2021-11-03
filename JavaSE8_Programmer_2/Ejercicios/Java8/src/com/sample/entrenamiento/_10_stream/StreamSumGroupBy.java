package com.sample.entrenamiento._10_stream;

import java.util.ArrayList;
import java.util.stream.Collectors;

class Empleado{	
	private Integer id;
	private String name;
	private Double salary;
	private String departamento;
	
	public Empleado(Integer id, String name, Double salary, String departamento) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", name=" + name + ", salary=" + salary + ", Departamento=" + departamento + "]";
	}	
}



public class StreamSumGroupBy {
	public static void main(String[] args) {
		ArrayList<Empleado> lista = new ArrayList<>();
		lista.add(new Empleado(1,"Lennin",1500.00,"SISTEMA"));
		lista.add(new Empleado(2,"maria",1500.00,"VENTAS"));
		lista.add(new Empleado(3,"pilar",1500.00,"VENTAS"));
		lista.add(new Empleado(4,"jesica",1000.00,"TELEPROCESO"));
		lista.add(new Empleado(5,"Samy",1500.00,"SSISTEMAS"));
		lista.add(new Empleado(6,"Fernando",1200.00,"SISTEMAS"));
		
		lista.stream().collect(Collectors.groupingBy(e -> e.getDepartamento(), Collectors.summingDouble(e -> e.getSalary()))).forEach( (departamento,suma) -> System.out.println(departamento + " : " + suma) );
	}
	

}
