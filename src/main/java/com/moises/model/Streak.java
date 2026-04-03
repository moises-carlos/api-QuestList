package com.moises.model;

import java.time.LocalDateTime;

public class Streak {

    private String usuarioId;
    private int diasConsecutivos;
    private LocalDateTime ultimaDataConclusao;

    public Streak(String usuarioId) {
        this.usuarioId = usuarioId;
        this.diasConsecutivos = 0;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public int getDiasConsecutivos() {
        return diasConsecutivos;
    }

    public LocalDateTime getUltimaDataConclusao() {
        return ultimaDataConclusao;
    }

    public void incrementar() {
        this.diasConsecutivos++;
        this.ultimaDataConclusao = LocalDateTime.now();
    }

    public void resetar() {
        this.diasConsecutivos = 0;
    }
}