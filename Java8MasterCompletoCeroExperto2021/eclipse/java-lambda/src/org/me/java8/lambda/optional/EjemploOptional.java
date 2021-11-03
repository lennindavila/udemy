package org.me.java8.lambda.optional;

import java.util.Optional;

public class EjemploOptional {

	public static void main(String[] args) {
		String nombre = "lennin";
		Optional<String> opt = Optional.of(nombre);
		System.out.println("opt: " + opt);
		System.out.println("opt isPresent: " + opt.isPresent());
		
		if(opt.isPresent()) {
			System.out.println("Hola " + opt.get());
		}
		
		System.out.println("opt isEmpty: " + opt.isEmpty());
		opt.ifPresent(valor -> {
			System.out.println("Hola1 " + valor);
		});
		
		nombre =null;
		opt= Optional.ofNullable(nombre);
		System.out.println("opt = " + opt);
		System.out.println("opt isPresent: " + opt.isPresent());
		System.out.println("opt isEmpty: " + opt.isEmpty());
		
		//Java 9 en adelante ifPresentOrElse
		opt.ifPresentOrElse(valor -> System.out.println("Hola2 " + valor), () -> System.out.println("No esta presente2"));
		
		if(opt.isPresent()) {
			System.out.println("Hola3 " + opt.get());
		}else {
			System.out.println("No esta presente3 ");
		}
		
		Optional<String> optionEmpty = Optional.empty();
		System.out.println("optEmpty = " + optionEmpty );
		System.out.println("optEmpty isPresent = " + optionEmpty.isPresent() );		

	}

}
