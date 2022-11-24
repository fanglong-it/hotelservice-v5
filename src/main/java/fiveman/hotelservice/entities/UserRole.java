package fiveman.hotelservice.entities;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_STAFF, ROLE_RECEPTIONIST, ROLE_HOUSEKEEPING, ROLE_RESTAURANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
