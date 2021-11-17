package com.truenorth.vhslab.repositories;

import com.truenorth.vhslab.entities.VHS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VhsRepository extends JpaRepository<VHS, Long> {
}
