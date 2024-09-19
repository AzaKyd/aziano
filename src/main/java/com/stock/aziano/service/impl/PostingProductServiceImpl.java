package com.stock.aziano.service.impl;

import com.stock.aziano.dto.PostingProductDto;
import com.stock.aziano.service.PostingProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingProductServiceImpl implements PostingProductService {
    @Override
    public List<PostingProductDto> getPostingProducts() {
        return List.of();
    }

    @Override
    public void addPostingProduct(PostingProductDto postingProductDto) {

    }

    @Override
    public PostingProductDto getPostingProductById(Long id) {
        return null;
    }

    @Override
    public void updatePostingProduct(PostingProductDto postingProductDto) {

    }

    @Override
    public void removePostingProduct(Long id) {

    }
}
