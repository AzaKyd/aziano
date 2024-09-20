package com.stock.aziano.mappers;

import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.models.Posting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostingMapper {

    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "totalSum", source = "totalSum")
    @Mapping(target = "postingDate", source = "postingDate")
    @Mapping(target = "facility", source = "facility")
    @Mapping(target = "description", source = "description")
    Posting toEntity(PostingDto postingDto);

    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "totalSum", source = "totalSum")
    @Mapping(target = "postingDate", source = "postingDate")
    @Mapping(target = "facility", source = "facility")
    @Mapping(target = "description", source = "description")
    PostingDto toDto(Posting posting);
}


