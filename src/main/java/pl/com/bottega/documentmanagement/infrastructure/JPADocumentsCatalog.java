package pl.com.bottega.documentmanagement.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.documentmanagement.api.DocumentCriteria;
import pl.com.bottega.documentmanagement.api.DocumentDto;
import pl.com.bottega.documentmanagement.api.DocumentsCatalog;
import pl.com.bottega.documentmanagement.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Collection;
import java.util.HashSet;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by maciuch on 12.06.16.
 */
@Component
public class JPADocumentsCatalog implements DocumentsCatalog {

    @PersistenceContext
    private EntityManager entityManager;

   /*

   "SELECT new pl.com.bottega.documentmanagement.api.DocumentDto(" +
    "d.title, d.content, d.status, d.employee.employeeId.id" +
    ")"+
    "FROM Document d" +
    "WHERE () AND () AND () ";

    */


    @Override
    public DocumentDto get(DocumentNumber documentNumber) {
        checkNotNull(documentNumber);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DocumentDto> query = builder.createQuery(DocumentDto.class);
        Root<Document> root = query.from(Document.class);
        query.where(builder.equal(root.get(Document_.documentNumber), documentNumber));
        query.select(builder.construct(DocumentDto.class,
                root.get(Document_.documentNumber).get(DocumentNumber_.number),
                root.get(Document_.title),
                root.get(Document_.content),
                root.get(Document_.status),
                root.get(Document_.createdAt),
                root.get(Document_.verifiedAt),
                root.get(Document_.updatedAt),
                root.get(Document_.creator).get(Employee_.employeeId).get(EmployeeId_.id),
                root.get(Document_.verificator).get(Employee_.employeeId).get(EmployeeId_.id)
        ));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Iterable<DocumentDto> find(DocumentCriteria documentCriteria) {
        checkNotNull(documentCriteria);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DocumentDto> query = builder.createQuery(DocumentDto.class);
        Root<Document> root = query.from(Document.class);
        Collection<Predicate> predicates = new HashSet<>();

        query.select(builder.construct(DocumentDto.class,
                root.get(Document_.documentNumber).get(DocumentNumber_.number),
                root.get(Document_.title),
                root.get(Document_.content),
                root.get(Document_.status),
                root.get(Document_.createdAt),
                root.get(Document_.verifiedAt),
                root.get(Document_.updatedAt),
                root.get(Document_.creator).get(Employee_.employeeId).get(EmployeeId_.id),
                root.get(Document_.verificator).get(Employee_.employeeId).get(EmployeeId_.id)
        ));

        if (documentCriteria.isStatusDefined()){
            predicates.add(builder.equal(root.get(Document_.status),
                    documentCriteria.getStatus()));
        }
        if (documentCriteria.isCreatedByDefined()){
            predicates.add(builder.equal(root.get(Document_.creator).get(Employee_.employeeId),
                    documentCriteria.getCreateBy()));
        }
        if (documentCriteria.isCreatedDatesDefined()){
            if (documentCriteria.isCreatedFromDefined()){
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(Document_.createdAt), documentCriteria.getCreatedFrom()
                ));

            }
            if (documentCriteria.isCreatedUntilDefined()){
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(Document_.createdAt), documentCriteria.getCreatedUntil()
                ));
            }

        }

        if (documentCriteria.isQueryDefined()){
            //content like %query% OR ....
            predicates.add(builder.or(
                builder.like(root.get(Document_.content), "%" + documentCriteria.getQuery()+ "%"),
                    builder.like(root.get(Document_.title), "%" + documentCriteria.getQuery() +"%")

            ));
        }

        query.where(predicates.toArray( new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }

}
