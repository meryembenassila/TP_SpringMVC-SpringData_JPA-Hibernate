package com.example.tp_springmvcspringdata_jpahibernate;
import com.example.tp_springmvcspringdata_jpahibernate.entities.Product;
import com.example.tp_springmvcspringdata_jpahibernate.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TestSpringMVCAPP {
    public static void main(String[] args) {
        SpringApplication.run(TestSpringMVCAPP.class,args); }
    @Bean
    public CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder()
                    .name("SmartPhone")
                    .price(3000)
                    .quantity(2)
                    .build());
            productRepository.save(Product.builder()
                    .name("PC")
                    .price(10000)
                    .quantity(11)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(1200)
                    .quantity(10)
                    .build());
            productRepository.findAll().forEach(p->{ System.out.println(p.toString()); }); }; } }