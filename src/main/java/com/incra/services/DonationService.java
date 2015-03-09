package com.incra.services;

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
import java.util.List;

/**
 * The <i>DonationService</i> handles the JPA-based updating of Box entities.
 *
 * @author Jeffrey Risberg
 * @since September 2014
 */
@Transactional
@Repository
public class DonationService {

    @PersistenceContext
    private EntityManager em;

    public List<Donation> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Donation> criteria = cb.createQuery(Donation.class);
        criteria.from(Donation.class);

        return em.createQuery(criteria).getResultList();
    }

    public Donation findEntityById(int id) {
        return em.find(Donation.class, id);
    }

    public Donation findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Donation> criteria = builder.createQuery(Donation.class);
        Root<Donation> root = criteria.from(Donation.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Donation box) {
        if (box.getId() == null || box.getId() == 0) {
            em.persist(box);
        } else {
            em.merge(box);
        }
    }

    public void delete(Donation box) {
        this.delete(box.getId());
    }

    public void delete(int boxId) {
        Donation existingBox = this.findEntityById(boxId);
        if (null != existingBox) {
            em.remove(existingBox);
        }
    }
}
