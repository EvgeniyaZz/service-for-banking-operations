package com.github.EvgeniyaZz.bank.web;

import com.github.EvgeniyaZz.bank.HasId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class JwtUser extends JwtAuthenticationToken implements HasId {
    @Getter
    @Setter
    private Integer id;

    public JwtUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities, int id) {
        super(jwt, authorities);
        this.id = id;
    }

    @Override
    public String toString() {
        return "JwtUser:" + id + '[' + getName() + ']';
    }
}