package com.incra.services;

import com.incra.models.Dimension;
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
 * The <i>DimensionService</i> handles the JPA-based updating of Dimension entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class DimensionService {

    @PersistenceContext
    private EntityManager em;

    public List<Dimension> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Dimension> criteria = cb.createQuery(Dimension.class);
        criteria.from(Dimension.class);

        return em.createQuery(criteria).getResultList();
    }

    public Dimension findEntityById(int id) {
        return em.find(Dimension.class, id);
    }

    public Dimension findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dimension> criteria = builder.createQuery(Dimension.class);
        Root<Dimension> root = criteria.from(Dimension.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Dimension dimension) {
        if (dimension.getId() == null || dimension.getId() == 0) {
            em.persist(dimension);
        } else {
            em.merge(dimension);
        }
    }

    public void delete(Dimension dimension) {
        this.delete(dimension.getId());
    }

    public void delete(int dimensionId) {
        Dimension existingDimension = this.findEntityById(dimensionId);
        if (null != existingDimension) {
            em.remove(existingDimension);
        }
    }
}
