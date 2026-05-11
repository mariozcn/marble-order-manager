package org.example.marmura_order_manager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LinieComandaDTO {
    @NotNull(message = "Cantul este obligatoriu")
    private TIP_CANT cant;

    @Min(value = 1, message = "Grosimea trebuie sa fie cel putin 1")
    private int grosime;

    @DecimalMin(value = "0.0", message = "Latimea nu poate fi negativa")
    private double latime;

    @DecimalMin(value = "0.0", message = "Lungimea nu poate fi negativa")
    private double lungime;

    @NotBlank(message = "Materialul este obligatoriu")
    private String material;

    //laturi
    private boolean cantStanga;
    private boolean cantDreapta;
    private boolean cantSus;
    private boolean cantJos;

    //colturi
    private boolean coltSusStanga;
    private boolean coltSusDreapta;
    private boolean coltJosStanga;
    private boolean coltJosDreapta;


    public int getGrosime() {
        return grosime;
    }

    public void setGrosime(int grosime) {
        this.grosime = grosime;
    }

    public double getLatime() {
        return latime;
    }

    public void setLatime(double latime) {
        this.latime = latime;
    }

    public double getLungime() {
        return lungime;
    }

    public void setLungime(double lungime) {
        this.lungime = lungime;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }



    public LinieComandaDTO() {
    }

    public TIP_CANT getCant() {
        return cant;
    }

    public void setCant(TIP_CANT cant) {
        this.cant = cant;
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

    public boolean isCantSus() {
        return cantSus;
    }

    public void setCantSus(boolean cantSus) {
        this.cantSus = cantSus;
    }

    public boolean isCantJos() {
        return cantJos;
    }

    public void setCantJos(boolean cantJos) {
        this.cantJos = cantJos;
    }

    public boolean isColtSusStanga() {
        return coltSusStanga;
    }

    public void setColtSusStanga(boolean coltSusStanga) {
        this.coltSusStanga = coltSusStanga;
    }

    public boolean isColtSusDreapta() {
        return coltSusDreapta;
    }

    public void setColtSusDreapta(boolean coltSusDreapta) {
        this.coltSusDreapta = coltSusDreapta;
    }

    public boolean isColtJosStanga() {
        return coltJosStanga;
    }

    public void setColtJosStanga(boolean coltJosStanga) {
        this.coltJosStanga = coltJosStanga;
    }

    public boolean isColtJosDreapta() {
        return coltJosDreapta;
    }

    public void setColtJosDreapta(boolean coltJosDreapta) {
        this.coltJosDreapta = coltJosDreapta;
    }

    public LinieComandaDTO(TIP_CANT cant, int grosime, double latime, double lungime, String material, boolean cantStanga, boolean cantDreapta, boolean cantSus, boolean cantJos, boolean coltSusStanga, boolean coltSusDreapta, boolean coltJosStanga, boolean coltJosDreapta) {
        this.cant = cant;
        this.grosime = grosime;
        this.latime = latime;
        this.lungime = lungime;
        this.material = material;
        this.cantStanga = cantStanga;
        this.cantDreapta = cantDreapta;
        this.cantSus = cantSus;
        this.cantJos = cantJos;
        this.coltSusStanga = coltSusStanga;
        this.coltSusDreapta = coltSusDreapta;
        this.coltJosStanga = coltJosStanga;
        this.coltJosDreapta = coltJosDreapta;
    }
}
