package org.example.marmura_order_manager.repository;

import org.example.marmura_order_manager.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {
    Optional<Material> findByNameAndGrosime(String name, int grosime);
}
