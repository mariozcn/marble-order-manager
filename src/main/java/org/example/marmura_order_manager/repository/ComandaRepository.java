package org.example.marmura_order_manager.repository;


import org.example.marmura_order_manager.model.Client;
import org.example.marmura_order_manager.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda,Long> {
    List<Comanda> findComandaByClient(Client client);
}
