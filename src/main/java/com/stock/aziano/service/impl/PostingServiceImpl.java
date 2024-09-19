package com.stock.aziano.service.impl;

import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.dto.PostingProductDto;
import com.stock.aziano.mappers.PostingMapper;
import com.stock.aziano.models.Posting;
import com.stock.aziano.repository.PostingRepository;
import com.stock.aziano.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    private final PostingMapper postingMapper;

    @Autowired
    public PostingServiceImpl(PostingRepository postingRepository, PostingMapper postingMapper) {
        this.postingRepository = postingRepository;
        this.postingMapper = postingMapper;
    }

    @Override
    public List<PostingDto> getPostings() {
        return postingRepository.
                findAll()
                .stream()
                .map(postingMapper::toDto)
                .toList();
    }

    @Override
    public void addPosting(PostingDto postingDto, List<PostingProductDto> postingProducts) {
        var posting = postingRepository.save(postingMapper.toEntity(postingDto));
        postingProducts.stream()
                .peek(item -> item.setPosting(posting)) // Связываем товар с оприходованием
                .forEach(System.out::println);
    }

    @Override
    public PostingDto getPostingById(Long id) {
        return null;
    }

    @Override
    public void updatePosting(PostingDto postingDto) {

    }

    @Override
    public void removePosting(Long id) {

    }
}
