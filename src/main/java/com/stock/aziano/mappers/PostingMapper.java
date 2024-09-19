package com.stock.aziano.mappers;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.dto.PostingDto;
import com.stock.aziano.models.Facility;
import com.stock.aziano.models.Posting;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PostingMapper {

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "quantity", source = "quantity")
//    @Mapping(target = "totalSum", source = "totalSum")
//    @Mapping(target = "entryDate", source = "entryDate")
    Posting toEntity(PostingDto postingDto);

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "quantity", source = "quantity")
//    @Mapping(target = "totalSum", source = "totalSum")
//    @Mapping(target = "entryDate", source = "entryDate")
    PostingDto toDto(Posting posting);
}


