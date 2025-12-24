package com.pm.patientservice.util;

import com.pm.patientservice.cursor.PatientCursor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class PageSpecification<T> implements Specification<T> {

    private final transient String mainFieldName;
    private final transient PatientCursor patientCursor;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicate = applyPaginationFilter(root, criteriaBuilder);

        if (patientCursor.hasPrev()) {
            query.orderBy(criteriaBuilder.desc(root.get(this.mainFieldName)));
        } else {
            query.orderBy(criteriaBuilder.asc(root.get(this.mainFieldName)));
        }

        return predicate;

    }

    private Predicate applyPaginationFilter(Root<T> root, CriteriaBuilder criteriaBuilder) {
        String searchValue = patientCursor.getSearchValue();

        return patientCursor.hasPrev()
                ? criteriaBuilder.lessThan(root.get(mainFieldName), searchValue)
                : criteriaBuilder.greaterThan(root.get(mainFieldName), searchValue);
    }
}
