package org.example.marmura_order_manager.dto;

public class LinieComandaDTO {
    private String cant;
    private int grosime;
    private double latime;
    private double lungime;
    private String material;

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

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

    public LinieComandaDTO(String cant, int grosime, double latime, double lungime, String material) {
        this.cant = cant;
        this.grosime = grosime;
        this.latime = latime;
        this.lungime = lungime;
        this.material = material;
    }
}
