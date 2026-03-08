package com.example.tp_springmvcspringdata_jpahibernate.repository;


import com.example.tp_springmvcspringdata_jpahibernate.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // Cherche tous les produits dont le nom contient la keyword (ignore case)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
