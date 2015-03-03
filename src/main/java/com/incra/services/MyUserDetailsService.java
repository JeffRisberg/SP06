package com.incra.services;

import com.incra.services.dto.MyUserDetails;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * The <i>MyUserDetailsService</i> handles connecting the Spring Security module
 * to the data model.
 *
 * @author Jeff Risberg
 * @since 01/15/11
 */
@Service("customUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private final static Logger logger = Logger
            .getLogger(MyUserDetailsService.class);

    @Inject
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        try {
            if (logger.isInfoEnabled()) {
                logger.info(">>loading user with name=" + username);
            }

            com.incra.models.User domainUser = userService
                    .findEntityByUsername(username);

            if (domainUser == null) {
                throw new UsernameNotFoundException("Username " + username
                        + " not found");
            }

            String password = domainUser.getPassword();

            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new GrantedAuthorityImpl("ROLE_USER"));

            Integer userId = domainUser.getId();
            String fullName = domainUser.getFirstName() + " "
                    + domainUser.getLastName();
            String email = domainUser.getEmail();

            MyUserDetails user = new MyUserDetails(username, password,
                    false, true, true, true, authList, userId,
                    fullName, email);

            if (logger.isInfoEnabled()) {
                logger.info("<<returning user " + user);
            }
            return user;
        } catch (DataAccessException dae) {
            logger.warn("<<Can't access data for user " + username);
            throw new DataRetrievalFailureException("Name " + username
                    + " data not accessible");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Username " + username + " not found "
                    + e.getLocalizedMessage());
            throw new UsernameNotFoundException("Username " + username
                    + " not found", e);
        }
    }
}