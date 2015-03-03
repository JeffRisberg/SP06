package com.incra.services.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * The <i>MyUserDetails</i> dto augments the standard Spring security
 * UserDetails object to have additional information used to drive the security
 * policy.
 *
 * @author Jeff Risberg
 * @since 01/15/11
 */
public class MyUserDetails extends
        org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 0L;

    private Integer userId;
    private String fullName;
    private String email;

    public MyUserDetails(String username, String password, boolean enabled,
                         boolean accountNonExpired, boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities, Integer userId,
                         String fullName,
                         String email) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
