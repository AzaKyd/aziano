package com.stock.aziano.repository;

import com.stock.aziano.models.PostingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingProductRepository extends JpaRepository<PostingProduct, Long> {
}
