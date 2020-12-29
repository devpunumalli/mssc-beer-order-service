package com.dev.msscbeerorderseervice.web.mappers;

import com.dev.msscbeerorderseervice.domain.BeerOrderLine;
import com.dev.msscbeerorderseervice.services.Beer.BeerService;
import com.dev.msscbeerorderseervice.services.Beer.model.BeerDto;
import com.dev.msscbeerorderseervice.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private  BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;
@Autowired
    public void setService(BeerService service) {
        this.beerService = service;
    }
@Autowired
@Qualifier("delegate")
    public void setMapper(BeerOrderLineMapper mapper) {
        this.beerOrderLineMapper = mapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(line.getUpc());

        beerDtoOptional.ifPresent(beerDto -> {
            orderLineDto.setBeerName(beerDto.getBeerName());
            orderLineDto.setBeerStyle(beerDto.getBeerStyle());
            orderLineDto.setPrice(beerDto.getPrice());
            orderLineDto.setBeerId(beerDto.getId());
        });

        return orderLineDto;
    }


}
