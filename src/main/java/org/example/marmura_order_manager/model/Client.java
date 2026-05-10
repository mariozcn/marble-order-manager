package org.example.marmura_order_manager.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    @Size(min = 2, max = 100,message = "Numele trebuie sa contina intre 2 si 100 caractere")
    @Column(name="nume")
    private String name;


    @NotBlank(message = "Numar telefon obligatoriu")
    @Pattern(regexp = "^[0-9+\\-\\s]{7,15}$",message = "Trebuie sa fie un numar valid")
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
