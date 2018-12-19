package com.forrequia.oauth2.dao;

import com.forrequia.oauth2.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDAO extends JpaRepository<Permission, Long> {
}
