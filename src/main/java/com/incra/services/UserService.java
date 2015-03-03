package com.incra.services;

import com.incra.models.User;
import com.incra.services.dto.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * The <i>UserService</i> provides support for persistence of Users.
 *
 * @author Jeffrey Risberg
 * @since 12/06/11
 */
@Transactional
@Repository
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal != null && principal instanceof MyUserDetails) {
            MyUserDetails userDetail = (MyUserDetails) principal;

            return findEntityById(userDetail.getUserId());
        }
        return null;
    }

    public List<User> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        criteria.from(User.class);

        return em.createQuery(criteria).getResultList();
    }

    public User findEntityById(Integer id) {
        return em.find(User.class, id);
    }

    public User findEntityByUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        Path<String> rootUsername = root.get("username");
        criteria.where(builder.equal(rootUsername, username));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public void delete(User user) {
        this.delete(user.getId());
    }

    public void delete(Integer userId) {
        User existingUser = this.findEntityById(userId);
        if (null != existingUser) {
            em.remove(existingUser);
        }
    }
}
