package org.example.marmura_order_manager.model;


import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nume")
    private String name;

    @Column(name="telefon")
    private String telefon;

    public Client(Long id, String name, String telefon) {
        this.id = id;
        this.name = name;
        this.telefon = telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelefon() {
        return telefon;
    }

    public Client() {
    }
}
