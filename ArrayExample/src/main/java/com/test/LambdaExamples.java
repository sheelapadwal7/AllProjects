package com.test;

import java.util.function.*;

public class LambdaExamples {

	public static void main(String[] args) {

		// 1. Lambda with No Parameters
		Runnable r = () -> System.out.println("Welcome!");
		r.run();

		// 2. Lambda with Single Parameter and No Type Declaration
		Function<Integer, Integer> square = x -> x * x;
		System.out.println("Square of 5: " + square.apply(5));

		// 3. Lambda with Single Parameter and Type Declaration
		Function<Integer, Integer> squares = (Integer x) -> x * x;
		System.out.println("Square of 6: " + squares.apply(6));

		// 4. Lambda with Multiple Parameters and Type Inference
		BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
		System.out.println("Sum of 5 and 3: " + add.apply(5, 3));

		// 5. Lambda with Multiple Parameters and Type Declared
		BiFunction<Integer, Integer, Integer> multiply = (Integer x, Integer y) -> x * y;
		System.out.println("Product of 4 and 3: " + multiply.apply(4, 3));

		// 6. Lambda with Return Statement
		BiFunction<Integer, Integer, Integer> subtract = (x, y) -> {
			int result = x - y;
			return result;
		};
		System.out.println("Difference of 10 and 4: " + subtract.apply(10, 4));

		// 7. Lambda with No Parameters and a Return Value
		Supplier<String> getMessage = () -> "Hello, World!";
		System.out.println(getMessage.get());
		
		//8. Lambda with use of local variables
		int a = 15;  
        int b = 4;
        
        Runnable s = () -> {
            int result = a - b; 
            System.out.println("Difference of " + a + " and " + b + ": " + result);
        };
        
        s.run();  
	}
}
