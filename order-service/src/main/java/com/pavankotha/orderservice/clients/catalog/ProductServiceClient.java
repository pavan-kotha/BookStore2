package com.pavankotha.orderservice.clients.catalog;

import com.pavankotha.orderservice.ApplicationProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductServiceClient {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);
    private final RestClient restClient;
    private final ApplicationProperties applicationProperties;

    public ProductServiceClient(RestClient restClient, ApplicationProperties applicationProperties) {
        this.restClient = restClient;
        this.applicationProperties = applicationProperties;
    }

    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallback")
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product by code {}", code);

        log.info("Ctalog source url is" + applicationProperties.catalogServiceUrl());

        var product =
                restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
        return Optional.ofNullable(product);
    }

    Optional<Product> getProductByCodeFallback(String code, Throwable t) {
        System.out.println("In fallback method");
        return Optional.empty();
    }
}
