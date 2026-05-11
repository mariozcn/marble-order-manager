package org.example.marmura_order_manager.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ComandaDTO {
    @NotNull(message = "Clientul este obligatoriu")
    private Long client_id;

    @Valid
    @NotEmpty(message = "Macar o linie de comanda obligatorie")
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
