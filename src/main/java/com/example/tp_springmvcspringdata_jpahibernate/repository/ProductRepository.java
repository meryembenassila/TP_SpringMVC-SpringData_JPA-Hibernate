package com.example.tp_springmvcspringdata_jpahibernate.repository;


import com.example.tp_springmvcspringdata_jpahibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
