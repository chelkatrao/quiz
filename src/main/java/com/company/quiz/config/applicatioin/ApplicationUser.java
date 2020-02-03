package com.company.quiz.config.applicatioin;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
public class ApplicationUser implements UserDetails {

    private final String password;
    private final String username;
    private final Boolean accountNonExpired;
    private final Boolean accountNonLocked;
    private final Boolean credentialsNonExpired;
    private final Boolean isEnabled;
    private final Set<? extends GrantedAuthority> grantedAuthorities;

    public ApplicationUser(String password,
                           String username,
                           Boolean accountNonExpired,
                           Boolean accountNonLocked,
                           Boolean credentialsNonExpired,
                           Boolean isEnabled,
                           Set<? extends GrantedAuthority> grantedAuthorities) {
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.isEnabled = isEnabled;
        this.grantedAuthorities = grantedAuthorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
