package com.rca.rosinenzambaza.template.v1.repositories;

import com.rca.rosinenzambaza.template.v1.models.Save;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SaveRepository extends JpaRepository<Save, UUID> {
}
