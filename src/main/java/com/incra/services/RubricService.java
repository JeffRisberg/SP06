package com.incra.services;

import com.incra.models.Box;
import com.incra.models.Rubric;
import com.incra.models.RubricBox;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * The <i>RubricService</i> handles the JPA-based updating of Rubric entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class RubricService {

    @PersistenceContext
    private EntityManager em;

    public List<Rubric> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rubric> criteria = cb.createQuery(Rubric.class);
        criteria.from(Rubric.class);

        return em.createQuery(criteria).getResultList();
    }

    public Rubric findEntityById(int id) {
        return em.find(Rubric.class, id);
    }

    public Rubric findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Rubric> criteria = builder.createQuery(Rubric.class);
        Root<Rubric> root = criteria.from(Rubric.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Rubric rubric) {
        if (rubric.getId() == null || rubric.getId() == 0) {
            em.persist(rubric);
        } else {
            em.merge(rubric);
        }
    }

    public void delete(Rubric rubric) {
        this.delete(rubric.getId());
    }

    public void delete(int rubricId) {
        Rubric existingRubric = this.findEntityById(rubricId);
        if (null != existingRubric) {
            em.remove(existingRubric);
        }
    }

    public List<Rubric> findEntityListByBox(Box box) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<RubricBox> criteria = builder.createQuery(RubricBox.class);
        Root<RubricBox> root = criteria.from(RubricBox.class);

        Path<Box> rootBox = root.get("box");
        criteria.where(builder.equal(rootBox, box));

        List<RubricBox> rbList = em.createQuery(criteria).getResultList();
        List<Rubric> result = new ArrayList<Rubric>();

        for (RubricBox rb : rbList) {
            result.add(rb.getRubric());
        }

        return result;
    }
}
