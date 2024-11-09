package com.stock.aziano.repository;

import com.stock.aziano.models.PostingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingProductRepository extends JpaRepository<PostingProduct, Long> {
    List<PostingProduct> findPostingProductByPostingId(Long postingProductId);
}
