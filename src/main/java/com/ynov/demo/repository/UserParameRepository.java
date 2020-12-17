package com.ynov.demo.repository;

import com.ynov.demo.domain.UserParame;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserParame entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserParameRepository extends JpaRepository<UserParame, Long> {
}
