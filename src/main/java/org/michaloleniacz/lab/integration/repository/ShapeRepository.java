package org.michaloleniacz.lab.integration.repository;

import org.michaloleniacz.lab.domain.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    Shape getShapeById(Long id);
}
