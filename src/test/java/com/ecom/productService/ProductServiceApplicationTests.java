package com.ecom.productService;

import com.ecom.productService.models.Product;
import com.ecom.productService.projections.ProductTitleAndDescription;
import com.ecom.productService.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	@Commit
	void testQueries() {
//		List<Product> products = productRepository.doSomethingSomething(100L);
//
//		//projection implementation
//		ProductTitleAndDescription productTitleAndDescription = productRepository.somethingSomething2(52L);
//		System.out.println(products);
	}
}
