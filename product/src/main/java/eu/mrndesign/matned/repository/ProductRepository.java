package eu.mrndesign.matned.repository;

import eu.mrndesign.matned.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}