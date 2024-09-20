package com.stock.aziano.repository;

import com.stock.aziano.models.PostingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingProductRepository extends JpaRepository<PostingProduct, Long> {
    List<PostingProduct> findPostingProductByPostingId(Long postingProductId);
}
