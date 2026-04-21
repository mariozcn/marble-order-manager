package org.example.marmura_order_manager.repository;


import org.example.marmura_order_manager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByTelefonContainingOrNameContaining(String telefon, String name);
}
