package org.example.marmura_order_manager.model;


import jakarta.persistence.*;

@Entity
public class LinieComanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="comanda_id")
    private Comanda comanda;

    @Column(name="material")
    private String material;

    @Column(name="lungime")
    private double lungime;

    @Column(name="latime")
    private double latime;

    @Column(name="grosime")
    private double grosime;

    @Column(name="cant")
    private String cant;

    @Column(name="pret")
    private double pret;

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setLungime(double lungime) {
        this.lungime = lungime;
    }

    public void setLatime(double latime) {
        this.latime = latime;
    }

    public void setGrosime(double grosime) {
        this.grosime = grosime;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public String getMaterial() {
        return material;
    }

    public double getLungime() {
        return lungime;
    }

    public double getLatime() {
        return latime;
    }

    public double getGrosime() {
        return grosime;
    }

    public String getCant() {
        return cant;
    }

    public double getPret() {
        return pret;
    }

    public Long getId() {
        return id;
    }

    public LinieComanda(Long id, Comanda comanda, String material, double lungime, double latime, double grosime, String cant, double pret) {
        this.id = id;
        this.comanda = comanda;
        this.material = material;
        this.lungime = lungime;
        this.latime = latime;
        this.grosime = grosime;
        this.cant = cant;
        this.pret = pret;
    }

    public LinieComanda() {
    }
}
