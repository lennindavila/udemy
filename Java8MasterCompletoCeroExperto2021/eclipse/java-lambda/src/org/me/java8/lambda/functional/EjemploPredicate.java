package org.me.java8.lambda.functional;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.me.java8.lambda.models.Usuario;

public class EjemploPredicate {
	public static void main(String[] args) {
		Predicate<Integer> test = num -> num > 10;
		boolean r = test.test(9);
		System.out.println("r = " + r);
		
		Predicate<String> t2 = role -> role.equals("ROLE_ADMIN");
		System.out.println(t2.test("ROLE_ADMIN"));
		
		BiPredicate<String, String> t3 = String::equals; // (a,b)-> a.equals(b);
		System.out.println(t3.test("lennin", "lennin"));
		
		BiPredicate<Integer, Integer> t4 = (i,j) -> j > i;
		boolean r2 =t4.test(5, 10);
		System.out.println(r2);
		
		Usuario a = new Usuario();
		Usuario b = new Usuario();
		
		a.setNombre("maria");
		b.setNombre("cata");
		BiPredicate<Usuario,Usuario> t5 = (ua,ub) -> ua.getNombre().equals(b.getNombre());
		System.out.println(t5.test(a, b));
		
	}
}
