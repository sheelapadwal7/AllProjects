package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;

public class ProductApp {

    public static void main(String[] args) {
        
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        
       
        try (Session session = sessionFactory.openSession()) {
            
            Transaction transaction = session.beginTransaction();
            
            
            List<Product> products = new ArrayList<>();
           // products.add(new Product(1, "Laptop", 899.99));
            products.add(new Product(2, "Smartphone",5000));
            products.add(new Product(3, "Tablet", 6000));
            products.add(new Product(4, "Smartwatch", 8000));

            
            for (Product product : products) {
                session.save(product);
            }
            
           
            transaction.commit();
            System.out.println("Products saved successfully!");

            
            for (Product product : products) {
                Product fetchedProduct = session.get(Product.class, product.getId());
                System.out.println("Fetched Product: " + fetchedProduct.getName() + " - " + fetchedProduct.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            HibernateUtil.shutdown();
        }
    }
}
