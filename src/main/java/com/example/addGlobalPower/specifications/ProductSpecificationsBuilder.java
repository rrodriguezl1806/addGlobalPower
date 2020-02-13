package com.example.addGlobalPower.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

/**
 * ProductSpecificationsBuilder
 */
public class ProductSpecificationsBuilder {

  private final List<SearchCriteria> params;

  public ProductSpecificationsBuilder() {
    params = new ArrayList<>();
  }

  public ProductSpecificationsBuilder with(String key, String operation, Object value) {
    params.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification build() {
    if (params.size() == 0) {
      return null;
    }
    List<Specification> specs = params.stream().map(ProductSpecification::new).collect(Collectors.toList());

    return specs.get(0);
  }
}