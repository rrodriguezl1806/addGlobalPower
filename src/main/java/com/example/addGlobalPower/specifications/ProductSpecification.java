package com.example.addGlobalPower.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.addGlobalPower.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<Product> {
 
  private SearchCriteria criteria;

    public ProductSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
  public Predicate toPredicate
    (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

      if (criteria.getOperation().equalsIgnoreCase(">")) {
          return builder.greaterThanOrEqualTo(
            root.<String> get(criteria.getKey()), criteria.getValue().toString());
      } 
      else if (criteria.getOperation().equalsIgnoreCase("<")) {
          return builder.lessThanOrEqualTo(
            root.<String> get(criteria.getKey()), criteria.getValue().toString());
      } 
      else if (criteria.getOperation().equalsIgnoreCase("=")) {
        return builder.equal(
          root.<String> get(criteria.getKey()), criteria.getValue().toString());
      }
      else if (criteria.getOperation().equalsIgnoreCase(":")) {
          if (root.get(criteria.getKey()).getJavaType() == String.class) {
              return builder.like(
                root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
          } else {
              return builder.equal(root.get(criteria.getKey()), criteria.getValue());
          }
      }
      return null;
  }
}