package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.olx.dto.SearchCriteria;
import com.olx.dto.SearchOperation;

public class GenericSpesification<T> implements Specification<T> {

	private static final long serialVersionUID = 1900581010229669687L;

	private List<SearchCriteria> list;

	public GenericSpesification() {

		this.list = new ArrayList<>();
	}

	public void add(SearchCriteria crriteria) {

		list.add(crriteria);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		List<Predicate> predicates = new ArrayList<>();
	
		for(SearchCriteria criteria : list ) {
			
			if(criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
				predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } 			
			else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_DATE)) {
            	System.out.println("criteria.getValue().toString(): "+criteria.getValue().toString());
            	
                predicates.add(criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), LocalDate.parse(criteria.getValue().toString())));
            }
			else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_DATE)) {
            	System.out.println("criteria.getValue().toString(): "+criteria.getValue().toString());
            	
                predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), LocalDate.parse(criteria.getValue().toString())));
            }
			else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL_DATE)) {
            	System.out.println("criteria.getValue().toString(): "+criteria.getValue().toString());
            	
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), LocalDate.parse(criteria.getValue().toString())));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL_DATE)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), LocalDate.parse(criteria.getValue().toString())));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(criteriaBuilder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(criteriaBuilder.like(
                		criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(criteriaBuilder.like(
                		criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            }
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
