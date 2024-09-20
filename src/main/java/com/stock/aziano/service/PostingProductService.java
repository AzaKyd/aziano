package com.stock.aziano.service;

import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.dto.PostingProductDto;

import java.util.List;

public interface PostingProductService {
    public List<PostingProductDto> getPostingProducts(Long postingProductId);

    public void addPostingProduct(PostingProductDto postingProductDto);

    public PostingProductDto getPostingProductById(Long id);

    public void updatePostingProduct(PostingProductDto postingProductDto);

    public void removePostingProduct(Long id);
}
