package org.example.marmura_order_manager.repository;


import org.example.marmura_order_manager.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda,Long> {
    
}
