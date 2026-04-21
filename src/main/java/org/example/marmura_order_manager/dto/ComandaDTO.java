package org.example.marmura_order_manager.dto;

import java.util.List;

public class ComandaDTO {
    private Long clientId;
    private String observatii;
    private List<LiniiComandaDTO> linii;

    public Long getClientId() {
        return clientId;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public List<LiniiComandaDTO> getLinii() {
        return linii;
    }

    public void setLinii(List<LiniiComandaDTO> linii) {
        this.linii = linii;
    }

    public ComandaDTO() {
    }

    public ComandaDTO(Long clientId, String observatii, List<LiniiComandaDTO> linii) {
        this.clientId = clientId;
        this.observatii = observatii;
        this.linii = linii;
    }
}
