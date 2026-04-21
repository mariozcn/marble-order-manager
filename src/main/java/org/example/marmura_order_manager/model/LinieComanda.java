package org.example.marmura_order_manager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.marmura_order_manager.dto.TIP_CANT;

@Entity
public class LinieComanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
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
    @Enumerated(EnumType.STRING)
    private TIP_CANT cant;

    @Column(name="pret")
    private double pret;

    @Column(name="cant_stanga")
    private boolean cantStanga;

    @Column(name="cant_dreapta")
    private boolean cantDreapta;

    @Column(name="cant_jos")
    private boolean cantJos;

    @Column(name="cant_sus")
    private boolean cantSus;

    @Column(name="colt_sus_dreapta")
    private boolean coltSusDreapta;

    @Column(name="colt_sus_stanga")
    private boolean coltSusStanga;

    @Column(name="colt_jos_dreapta")
    private boolean coltJosDreapta;

    @Column(name="colt_jos_stanga")
    private boolean coltJosStanga;

    public Long getId() {
        return id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getLungime() {
        return lungime;
    }

    public void setLungime(double lungime) {
        this.lungime = lungime;
    }

    public double getLatime() {
        return latime;
    }

    public void setLatime(double latime) {
        this.latime = latime;
    }

    public double getGrosime() {
        return grosime;
    }

    public void setGrosime(double grosime) {
        this.grosime = grosime;
    }

    public TIP_CANT getCant() {
        return cant;
    }

    public void setCant(TIP_CANT cant) {
        this.cant = cant;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean isCantStanga() {
        return cantStanga;
    }

    public void setCantStanga(boolean cantStanga) {
        this.cantStanga = cantStanga;
    }

    public boolean isCantDreapta() {
        return cantDreapta;
    }

    public void setCantDreapta(boolean cantDreapta) {
        this.cantDreapta = cantDreapta;
    }

    public boolean isCantJos() {
        return cantJos;
    }

    public void setCantJos(boolean cantJos) {
        this.cantJos = cantJos;
    }

    public boolean isCantSus() {
        return cantSus;
    }

    public void setCantSus(boolean cantSus) {
        this.cantSus = cantSus;
    }

    public boolean isColtSusDreapta() {
        return coltSusDreapta;
    }

    public void setColtSusDreapta(boolean coltSusDreapta) {
        this.coltSusDreapta = coltSusDreapta;
    }

    public boolean isColtSusStanga() {
        return coltSusStanga;
    }

    public void setColtSusStanga(boolean coltSusStanga) {
        this.coltSusStanga = coltSusStanga;
    }

    public boolean isColtJosDreapta() {
        return coltJosDreapta;
    }

    public void setColtJosDreapta(boolean coltJosDreapta) {
        this.coltJosDreapta = coltJosDreapta;
    }

    public boolean isColtJosStanga() {
        return coltJosStanga;
    }

    public void setColtJosStanga(boolean coltJosStanga) {
        this.coltJosStanga = coltJosStanga;
    }

    public LinieComanda(Long id, Comanda comanda, String material, double lungime, double latime, double grosime, TIP_CANT cant, double pret, boolean cantStanga, boolean cantDreapta, boolean cantJos, boolean cantSus, boolean coltSusDreapta, boolean coltSusStanga, boolean coltJosDreapta, boolean coltJosStanga) {
        this.id = id;
        this.comanda = comanda;
        this.material = material;
        this.lungime = lungime;
        this.latime = latime;
        this.grosime = grosime;
        this.cant = cant;
        this.pret = pret;
        this.cantStanga = cantStanga;
        this.cantDreapta = cantDreapta;
        this.cantJos = cantJos;
        this.cantSus = cantSus;
        this.coltSusDreapta = coltSusDreapta;
        this.coltSusStanga = coltSusStanga;
        this.coltJosDreapta = coltJosDreapta;
        this.coltJosStanga = coltJosStanga;
    }

    public LinieComanda() {
    }
}
