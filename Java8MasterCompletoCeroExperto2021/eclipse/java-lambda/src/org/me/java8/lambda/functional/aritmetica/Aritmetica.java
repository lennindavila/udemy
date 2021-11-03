package org.me.java8.lambda.functional.aritmetica;

@FunctionalInterface
//El decorador @FunctionalInterface no aporta nada a nivel funcional, solo lo hace descriptivo o semantico
public interface Aritmetica {
	double operacion(double a,double b);
}
