package org.example.marmura_order_manager.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tip_material")
    @Enumerated(EnumType.STRING)
    private TIP_MATERIAL tipMaterial;

    @NotBlank(message = "Numele este obligatoriu")
    @Column(name="name")
    private String name;

    @NotBlank(message = "Pretul este obligatoriu")
    @Column(name="pret")
    private double pret;

    @Column(name="grosime")
    private int grosime;

    @Column(name="origine")
    private String origine;

    public Material() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TIP_MATERIAL getTipMaterial() {
        return tipMaterial;
    }

    public void setTipMaterial(TIP_MATERIAL tipMaterial) {
        this.tipMaterial = tipMaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getGrosime() {
        return grosime;
    }

    public void setGrosime(int grosime) {
        this.grosime = grosime;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Material(Long id, TIP_MATERIAL tipMaterial, String name, double pret, int grosime, String origine) {
        this.id = id;
        this.tipMaterial = tipMaterial;
        this.name = name;
        this.pret = pret;
        this.grosime = grosime;
        this.origine = origine;
    }
}
