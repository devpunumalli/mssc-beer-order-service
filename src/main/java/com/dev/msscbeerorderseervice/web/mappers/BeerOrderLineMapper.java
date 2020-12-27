package com.dev.msscbeerorderseervice.web.mappers;


import com.dev.msscbeerorderseervice.domain.BeerOrderLine;
import com.dev.msscbeerorderseervice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
