package com.example.tp_springmvcspringdata_jpahibernate.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
        import org.hibernate.annotations.Generated;

@Entity
// annotation de lombok
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue
    private  Long Id;
    @NotEmpty
    @Size(min = 2 ,max = 50 )
    private  String name;
    @Min(0)
    private double price ;
    @Min(1)
    private double quantity ;

}
