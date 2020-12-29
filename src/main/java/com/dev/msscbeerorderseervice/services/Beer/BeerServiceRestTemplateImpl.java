package com.dev.msscbeerorderseervice.services.Beer;

import com.dev.msscbeerorderseervice.services.Beer.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery")
public class BeerServiceRestTemplateImpl implements BeerService {
    private static final String BEER_UPC_PATH_V = "api/v1/beerUPC/";
    private static final String BEER_PATH_V = "/api/v1/beer/";
    private final RestTemplate template;
private String beerServiceUrl;
    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilderTemplate) {
        this.template = restTemplateBuilderTemplate.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {

        return Optional.of(template.getForObject(beerServiceUrl + BEER_PATH_V + id.toString(), BeerDto.class));
    }

    public void setBeerServiceUrl(String beerServiceUrl) {
        this.beerServiceUrl = beerServiceUrl;
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.of(template.getForObject(beerServiceUrl + BEER_UPC_PATH_V + upc.toString(), BeerDto.class));

    }
}
