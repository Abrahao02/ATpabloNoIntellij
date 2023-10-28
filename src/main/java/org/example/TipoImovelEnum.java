package org.example;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Enumeração para TipoImovel
public enum TipoImovelEnum {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial");

    private final String descricao;

    TipoImovelEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
