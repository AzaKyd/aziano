package com.stock.aziano.service.impl;

import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.dto.PostingProductDto;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.FacilityProductMapper;
import com.stock.aziano.mappers.PostingMapper;
import com.stock.aziano.mappers.PostingProductMapper;
import com.stock.aziano.models.Facility;
import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.models.Posting;
import com.stock.aziano.models.PostingProduct;
import com.stock.aziano.repository.FacilityProductRepository;
import com.stock.aziano.repository.PostingProductRepository;
import com.stock.aziano.repository.PostingRepository;
import com.stock.aziano.service.PostingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PostingServiceImpl implements PostingService {

    private final PostingMapper postingMapper;
    private final PostingRepository postingRepository;

    private final PostingProductMapper postingProductMapper;
    private final PostingProductRepository postingProductRepository;

    private final FacilityProductMapper facilityProductMapper;
    private final FacilityProductRepository facilityProductRepository;

    @Autowired
    public PostingServiceImpl(PostingRepository postingRepository,
                              PostingMapper postingMapper,
                              PostingProductRepository postingProductRepository,
                              PostingProductMapper postingProductMapper,
                              FacilityProductMapper facilityProductMapper,
                              FacilityProductRepository facilityProductRepository
                              ) {
        this.postingRepository = postingRepository;
        this.postingMapper = postingMapper;
        this.postingProductRepository = postingProductRepository;
        this.postingProductMapper = postingProductMapper;
        this.facilityProductMapper = facilityProductMapper;
        this.facilityProductRepository = facilityProductRepository;
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
    @Transactional
    public void addPosting(PostingDto postingDto, List<PostingProductDto> postingProducts) {
        Posting posting = postingRepository.save(postingMapper.toEntity(postingDto));
        List<PostingProduct> productsToSave = postingProducts.stream().map(postingProduct -> {
                    PostingProduct entity = postingProductMapper.toEntity(postingProduct);
                    entity.setPosting(posting);          // Связываем с оприходованием
                    entity.setFacility(posting.getFacility());        // Связываем с facility
                    return entity;
                })
                .toList();

        postingProductRepository.saveAll(productsToSave);

        List<FacilityProduct> facilityProducts = postingProducts.stream()
                .map(facilityProductDto -> {
                    Long facilityId = posting.getFacility().getId();
                    Long productId = facilityProductDto.getProduct().getId();

                    FacilityProduct existingEntity = facilityProductRepository.findFacilityProductByFacilityIdAndProductId(facilityId, productId);

                    if (existingEntity != null) {
                        // Если продукт уже существует, обновляем его количество и стоимость
                        existingEntity.setQuantity(existingEntity.getQuantity() + facilityProductDto.getQuantity());
                        existingEntity.setTotalCost(existingEntity.getTotalCost().add(facilityProductDto.getTotalCost()));
                        return existingEntity; // Возвращаем обновленный объект
                    } else {
                        // Если продукта нет, создаем новый
                        return FacilityProduct.builder()
                                .facility(posting.getFacility())
                                .product(facilityProductDto.getProduct())
                                .quantity(facilityProductDto.getQuantity())
                                .cost(facilityProductDto.getCost())
                                .totalCost(facilityProductDto.getTotalCost())
                                .build();
                    }
                })
                .toList();
        facilityProductRepository.saveAll(facilityProducts);
    }

    @Override
    public PostingDto getPostingById(Long id) {
        return postingRepository.findById(id)
                .map(postingMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Posting not found with id: " + id));
    }

    @Override
    public void updatePosting(PostingDto postingDto) {

    }

    @Override
    public void removePosting(Long id) {

    }
}
