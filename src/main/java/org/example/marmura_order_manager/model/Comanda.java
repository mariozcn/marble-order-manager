package org.example.marmura_order_manager.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_comenzii")
    private LocalDate dataComenzii;

    public Comanda() {
    }

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name="observatii")
    private String observatii;

    public void setDataComenzii(LocalDate dataComenzii) {
        this.dataComenzii = dataComenzii;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataComenzii() {
        return dataComenzii;
    }

    public Status getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public String getObservatii() {
        return observatii;
    }

    public Comanda(Long id,Status status, Client client, String observatii) {
        this.id = id;
        this.dataComenzii = LocalDate.now();
        this.status = status;
        this.client = client;
        this.observatii = observatii;
    }
}
