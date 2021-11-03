package org.me.java8.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamParallel {

	
	public static void main(String[] args) {
		List<Usuario> lista = new ArrayList<>();
		lista.add(new Usuario("Pedro","Picapiedra"));
		lista.add(new Usuario("Vilma","Picapiedra"));
		lista.add(new Usuario("Pebbles","Picapiedra"));
		lista.add(new Usuario("Pablo","Marmol"));
		lista.add(new Usuario("Betty","Marmol"));
		lista.add(new Usuario("BanBan","Marmol"));
		lista.add(new Usuario("Pedro","Picapiedra2"));
		
		long t1 = System.currentTimeMillis();
		String resultado = lista.stream()
				.parallel() //con esta linea el proceso que hicimos que demora 6s ahora demora 1s, procesa mas rapido pero consume mas recursos
				.map(u -> u.toString().toUpperCase())
				.peek(n-> {
					System.out.println("Nombre Thread: " + Thread.currentThread().getName() + " # " + n);
					})
				.flatMap(nombre -> {
					//le ponemos un delay para simular la demora de procesamiento
					try {
						TimeUnit.SECONDS.sleep(1);//aca le decimos que demora 1s por cada item, y banban esta en el 6to lugar, asi que hasta llegar ahi demorara 6s
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if(nombre.contains("BanBan".toUpperCase())) {
						return Stream.of(nombre);
					}
					return Stream.empty();
				})
				.findAny().orElse("");

		long t2 = System.currentTimeMillis();
		System.out.println("Tiempo total: " + (t2-t1));
		System.out.println(resultado);
	}

}
