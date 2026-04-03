package com.moises.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class User {
    
    @Id
    private String id;
    
    private String nome;
    private int xp;
    
    @Embedded
    private Level nivel;
    
    private int hp;
    
    @Column(name = "sequencia_atual")
    private int sequenciaAtual;
    
    @Column(name = "multiplicador_xp")
    private double multiplicadorXp;
    
    @Column(name = "penalidade_ativa")
    private boolean penalidadeAtiva;

    // Construtor vazio para o JPA
    protected User() {}

    public User(String nome) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.xp = 0;
        this.hp = 100;
        this.nivel = new Level("Recruta", 100);
        this.sequenciaAtual = 0;
        this.multiplicadorXp = 1.0;
        this.penalidadeAtiva = false;
    }

    public User(String id, String nome, int hp, int xp, Level nivel, int sequencia, double mult, boolean penalidade) {
        this.id = id;
        this.nome = nome;
        this.hp = hp;
        this.xp = xp;
        this.nivel = nivel;
        this.sequenciaAtual = sequencia;
        this.multiplicadorXp = mult;
        this.penalidadeAtiva = penalidade;
    }

    public void adicionarXp(int quantidade) { this.xp += quantidade; }
    public void resetarSequencia() { this.sequenciaAtual = 0; }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public Level getNivel() { return nivel; }
    public void setNivel(Level nivel) { this.nivel = nivel; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getSequenciaAtual() { return sequenciaAtual; }
    public void setSequenciaAtual(int sequencia) { this.sequenciaAtual = sequencia; }
    public double getMultiplicadorXp() { return multiplicadorXp; }
    public void setMultiplicadorXp(double mult) { this.multiplicadorXp = mult; }
    public boolean isPenalidadeAtiva() { return penalidadeAtiva; }
    public void setPenalidadeAtiva(boolean status) { this.penalidadeAtiva = status; }
}