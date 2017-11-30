package com.ideal.audit.common.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (filters != null && !filters.isEmpty()) {
                    List<Predicate> predicates = new ArrayList<Predicate>();
                    for (SearchFilter filter : filters) {
                        String[] names = StringUtils.split(filter.getFieldName(), ".");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++) {
                            expression = expression.get(names[i]);
                        }
                        Predicate predicate = getPredicateByFilter(filter,expression,builder);
                        if(predicate!= null){
                            predicates.add(predicate);
                        }
                    }
                    if (predicates.size() > 0) {
                        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
                }

                return builder.conjunction();
            }
        };
    }

    public static Predicate getPredicateByFilter(SearchFilter filter,Path expression, CriteriaBuilder builder){
        switch (filter.getOperator()) {
            case EQ:
                return builder.equal(expression, filter.getValue());
            case LIKE:
                return builder.like(expression, "%" + filter.getValue() + "%");
            case GT:
                return builder.greaterThan(expression, (Comparable) filter.getValue());
            case LT:
                return builder.lessThan(expression, (Comparable) filter.getValue());
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue());
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue());
            case IN:
                return expression.in((List)filter.getValue());
            case NEQ:
                return builder.notEqual(expression, filter.getValue());
            case NULL:
                return expression.isNull();
            case NOTNULL:
                return expression.isNotNull();
        }
        return null;
    }
}
