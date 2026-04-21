package org.example.marmura_order_manager.dto;

public class LiniiComandaDTO {
    private String material;
    private double lungime;
    private double latime;
    private double grosime;
    private String cant;
    private double pret;

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

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public LiniiComandaDTO() {
    }

    public LiniiComandaDTO(String material, double lungime, double latime, double grosime, String cant, double pret) {
        this.material = material;
        this.lungime = lungime;
        this.latime = latime;
        this.grosime = grosime;
        this.cant = cant;
        this.pret = pret;
    }
}
