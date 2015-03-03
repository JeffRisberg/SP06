package com.incra.services;

import com.incra.models.Vendor;
import com.incra.models.Site;
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
 * The <i>BoxService</i> handles the JPA-based updating of Box entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class BoxService {

    @PersistenceContext
    private EntityManager em;

    public List<Vendor> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vendor> criteria = cb.createQuery(Vendor.class);
        criteria.from(Vendor.class);

        return em.createQuery(criteria).getResultList();
    }

    public Vendor findEntityById(int id) {
        return em.find(Vendor.class, id);
    }

    public Vendor findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vendor> criteria = builder.createQuery(Vendor.class);
        Root<Vendor> root = criteria.from(Vendor.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Vendor box) {
        if (box.getId() == null || box.getId() == 0) {
            em.persist(box);
        } else {
            em.merge(box);
        }
    }

    public void delete(Vendor box) {
        this.delete(box.getId());
    }

    public void delete(int boxId) {
        Vendor existingBox = this.findEntityById(boxId);
        if (null != existingBox) {
            em.remove(existingBox);
        }
    }

    public List<Vendor> findEntityListBySite(Site site) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vendor> criteria = builder.createQuery(Vendor.class);
        Root<Vendor> root = criteria.from(Vendor.class);

        Path<Site> rootSite = root.get("site");
        criteria.where(builder.equal(rootSite, site));

        return em.createQuery(criteria).getResultList();
    }
}
