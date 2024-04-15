package com.ncr.serviceticket.repo;

import com.ncr.serviceticket.model.AuthorizationPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<AuthorizationPosition, Long> {

    Optional<AuthorizationPosition> findByRole(String name);
}
