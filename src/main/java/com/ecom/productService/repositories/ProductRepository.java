package com.ecom.productService.repositories;

import com.ecom.productService.models.Product;
import com.ecom.productService.projections.ProductTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByProductNameContainingIgnoreCase(String name);

    List<Product> findByProductNameAndDescription(String name,String description);

    List<Product> findByPriceBetween(double startRange,double endRange);

    void deleteByProductNameIgnoreCase(String name);

    @Query("select p from Product p where p.price > :id")
    List<Product> doSomethingSomething(@Param("id") long id);

    @Query("""
            select p.productName as productName, p.description as description
            from Product p
            where p.id = :id
            """)
    ProductTitleAndDescription somethingSomething2(@Param("id") long id);
}
