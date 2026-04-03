package com.moises.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import java.util.UUID;

@Embeddable
public class Level {
    @Transient
    private String id;
    
    @Column(name = "nome_nivel")
    private String nome;
    
    @Transient
    private int xpNecessario;

    // Construtor JPA
    protected Level() {}

    // Construtor para novos níveis
    public Level(String nome, int xpNecessario) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.xpNecessario = xpNecessario;
    }

    // Construtor rápido para o Repository
    public Level(String nome) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.xpNecessario = 100; // Valor padrão inicial
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getXpNecessario() { return xpNecessario; }
    public void setXpNecessario(int xpNecessario) { this.xpNecessario = xpNecessario; }
}