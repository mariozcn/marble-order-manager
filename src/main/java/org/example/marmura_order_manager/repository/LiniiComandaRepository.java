package org.example.marmura_order_manager.repository;

import org.example.marmura_order_manager.dto.LiniiComandaDTO;
import org.example.marmura_order_manager.model.LinieComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiniiComandaRepository extends JpaRepository<LinieComanda,Long> {
}
