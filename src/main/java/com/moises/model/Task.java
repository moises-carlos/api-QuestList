package com.moises.model;

import com.moises.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tarefas")
public class Task {
    
    @Id
    private String id;
    
    private String titulo;
    private String descricao;
    
    @Column(name = "recompensa_xp")
    private int xpRecompensa;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Column(name = "data_limite")
    private LocalDateTime dataLimite;
    
    @Column(name = "concluido_em")
    private LocalDateTime conclusao;
    
    @Column(name = "usuario_id")
    private String userId;

    // Construtor JPA
    protected Task() {}

    public Task(String titulo, String descricao, int xpRecompensa, LocalDateTime dataLimite, String userId) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descricao = descricao;
        this.xpRecompensa = xpRecompensa;
        this.dataLimite = dataLimite;
        this.userId = userId;
        this.status = TaskStatus.ABERTA;
    }

    public Task(String id, String titulo, String descricao, int xpRecompensa, TaskStatus status, LocalDateTime dataLimite) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.xpRecompensa = xpRecompensa;
        this.status = status;
        this.dataLimite = dataLimite;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getXpRecompensa() { return xpRecompensa; }
    public void setXpRecompensa(int xpRecompensa) { this.xpRecompensa = xpRecompensa; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public LocalDateTime getDataLimite() { return dataLimite; }
    public void setDataLimite(LocalDateTime dataLimite) { this.dataLimite = dataLimite; }
    public LocalDateTime getConclusao() { return conclusao; }
    public void setConclusao(LocalDateTime conclusao) { this.conclusao = conclusao; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public void concluirTarefa(){
        if (status == TaskStatus.CONCLUIDO){
            throw new IllegalStateException("Tarefa já foi concluida");
        }
        this.status = TaskStatus.CONCLUIDO;
        this.conclusao = LocalDateTime.now();
    }
    
    public void reabrirTarefa(){
        this.status = TaskStatus.ABERTA;
        this.conclusao = null;
    }
    
    public void marcarComoCancelada(){
        this.status = TaskStatus.CANCELADA;
    }
}
