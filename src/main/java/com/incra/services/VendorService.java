package com.incra.services;

import com.incra.models.Vendor;
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
 * The <i>VendorService</i> handles the JPA-based updating of Vendor entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class VendorService {

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

    public Vendor findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Vendor> criteria = builder.createQuery(Vendor.class);
        Root<Vendor> root = criteria.from(Vendor.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Vendor vendor) {
        if (vendor.getId() == null || vendor.getId() == 0) {
            em.persist(vendor);
        } else {
            em.merge(vendor);
        }
    }

    public void delete(Vendor vendor) {
        this.delete(vendor.getId());
    }

    public void delete(int vendorId) {
        Vendor existingVendor = this.findEntityById(vendorId);
        if (null != existingVendor) {
            em.remove(existingVendor);
        }
    }
}
