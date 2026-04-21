package org.example.marmura_order_manager.dto;

public class LinieComandaDTO {
    private String cant;
    private double grosime;
    private double latime;
    private double lungime;
    private String material;
    private double pret;

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public double getGrosime() {
        return grosime;
    }

    public void setGrosime(double grosime) {
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

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public LinieComandaDTO() {
    }

    public LinieComandaDTO(String cant, double grosime, double latime, double lungime, String material, double pret) {
        this.cant = cant;
        this.grosime = grosime;
        this.latime = latime;
        this.lungime = lungime;
        this.material = material;
        this.pret = pret;
    }
}
