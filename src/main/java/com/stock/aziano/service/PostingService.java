package com.stock.aziano.service;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.dto.PostingProductDto;

import java.util.List;

public interface PostingService {
    public List<PostingDto> getPostings();

    public void addPosting(PostingDto postingDto, List<PostingProductDto> postingProductDto);

    public PostingDto getPostingById(Long id);

    public void updatePosting(PostingDto postingDto);

    public void removePosting(Long id);
}
