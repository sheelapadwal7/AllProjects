package com.test;

import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
     
        ArrayList<String> animals = new ArrayList<String>(); 

       
        animals.add("Lion");
        animals.add("Tiger");
        animals.add("Cat");
        animals.add("Dog");

       
        System.out.println("Animals list: " + animals);
        
        Iterator<String> iterator = animals.iterator();  
        while (iterator.hasNext())  
        {  
        String i = iterator.next();  
        System.out.println(i);  
        }
        
        
        animals.remove("Cat");
        System.out.println("After removing Cat " + animals);

        int size = animals.size();
        System.out.println("Size of the list: " + size);

      
        System.out.println("Iterating through the list:");
        for (String a : animals) {
            System.out.println(a);
        }

           
     
        animals.clear();
        System.out.println("After clearing the list: " + animals);
    }
}

