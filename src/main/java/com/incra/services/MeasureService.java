package com.incra.services;

import com.incra.models.Measure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The <i>MeasureService</i> handles the JPA-based updating of Measure entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class MeasureService {

    @PersistenceContext
    private EntityManager em;

    public List<Measure> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Measure> criteria = cb.createQuery(Measure.class);
        criteria.from(Measure.class);

        return em.createQuery(criteria).getResultList();
    }

    public Measure findEntityById(int id) {
        return em.find(Measure.class, id);
    }

    public Measure findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Measure> criteria = builder.createQuery(Measure.class);
        Root<Measure> root = criteria.from(Measure.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Measure measure) {
        if (measure.getId() == null || measure.getId() == 0) {
            em.persist(measure);
        } else {
            em.merge(measure);
        }
    }

    public void delete(Measure measure) {
        this.delete(measure.getId());
    }

    public void delete(int measureId) {
        Measure existingMeasure = this.findEntityById(measureId);
        if (null != existingMeasure) {
            em.remove(existingMeasure);
        }
    }
}
