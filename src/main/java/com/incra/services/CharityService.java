package com.incra.services;

import com.incra.models.Vendor;
import com.incra.models.Charity;
import com.incra.models.Donation;
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
 * The <i>CharityService</i> handles the JPA-based updating of Charity entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class CharityService {

    @PersistenceContext
    private EntityManager em;

    public List<Charity> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Charity> criteria = cb.createQuery(Charity.class);
        criteria.from(Charity.class);

        return em.createQuery(criteria).getResultList();
    }

    public Charity findEntityById(int id) {
        return em.find(Charity.class, id);
    }

    public Charity findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Charity> criteria = builder.createQuery(Charity.class);
        Root<Charity> root = criteria.from(Charity.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Charity charity) {
        if (charity.getId() == null || charity.getId() == 0) {
            em.persist(charity);
        } else {
            em.merge(charity);
        }
    }

    public void delete(Charity charity) {
        this.delete(charity.getId());
    }

    public void delete(int charityId) {
        Charity existingCharity = this.findEntityById(charityId);
        if (null != existingCharity) {
            em.remove(existingCharity);
        }
    }

    public List<Charity> findEntityListByBox(Vendor box) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Donation> criteria = builder.createQuery(Donation.class);
        Root<Donation> root = criteria.from(Donation.class);

        Path<Vendor> rootBox = root.get("box");
        criteria.where(builder.equal(rootBox, box));

        List<Donation> rbList = em.createQuery(criteria).getResultList();
        List<Charity> result = new ArrayList<Charity>();

        //for (Donation rb : rbList) {
        //    result.add(rb.getCharity());
        //}

        return result;
    }
}
