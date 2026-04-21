package org.example.marmura_order_manager.dto;

import java.util.List;

public class ComandaDTO {
    private Long client_id;
    private List<LinieComandaDTO> linii;
    private String observatii;
    public ComandaDTO() {
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public List<LinieComandaDTO> getLinii() {
        return linii;
    }

    public void setLinii(List<LinieComandaDTO> linii) {
        this.linii = linii;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public ComandaDTO(Long client_id, String observatii, List<LinieComandaDTO> linii) {
        this.client_id = client_id;
        this.observatii = observatii;
        this.linii = linii;
    }
}
