package com.stock.aziano.service.impl;

import com.stock.aziano.dto.PostingProductDto;
import com.stock.aziano.mappers.PostingMapper;
import com.stock.aziano.mappers.PostingProductMapper;
import com.stock.aziano.repository.PostingProductRepository;
import com.stock.aziano.repository.PostingRepository;
import com.stock.aziano.repository.ProductRepository;
import com.stock.aziano.service.PostingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingProductServiceImpl implements PostingProductService {

    private final PostingProductRepository postingProductRepository;
    private final PostingProductMapper postingProductMapper;

    private final PostingRepository postingRepository;
    private final PostingMapper postingMapper;

    @Autowired
    public PostingProductServiceImpl(
            PostingProductRepository postingProductRepository,
            PostingProductMapper postingProductMapper,
            PostingRepository postingRepository,
            PostingMapper postingMapper
            )
    {
        this.postingProductRepository = postingProductRepository;
        this.postingProductMapper = postingProductMapper;
        this.postingRepository = postingRepository;
        this.postingMapper = postingMapper;
    }


    @Override
    public List<PostingProductDto> getPostingProducts(Long postingProductId) {
        return postingProductRepository.findPostingProductByPostingId(postingProductId).
                stream().
                map(postingProduct -> {
                    PostingProductDto dto = postingProductMapper.toDto(postingProduct);
                    dto.setProductName(postingProduct.getProduct().getName()); // Извлекаем имя продукта
                    return dto;
                }).
                toList();
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
