package com.incra.services;

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
 * The <i>SiteService</i> handles the JPA-based updating of Site entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class SiteService {

    @PersistenceContext
    private EntityManager em;

    public List<Site> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Site> criteria = cb.createQuery(Site.class);
        criteria.from(Site.class);

        return em.createQuery(criteria).getResultList();
    }

    public Site findEntityById(int id) {
        return em.find(Site.class, id);
    }

    public Site findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Site> criteria = builder.createQuery(Site.class);
        Root<Site> root = criteria.from(Site.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Site site) {
        if (site.getId() == null || site.getId() == 0) {
            em.persist(site);
        } else {
            em.merge(site);
        }
    }

    public void delete(Site site) {
        this.delete(site.getId());
    }

    public void delete(int siteId) {
        Site existingSite = this.findEntityById(siteId);
        if (null != existingSite) {
            em.remove(existingSite);
        }
    }
}
