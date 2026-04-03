package com.moises.enums;

public enum Rank {
    RECRUTA("Recruta", 0, 100),
    GUERREIRO("Guerreiro", 100, 500),
    VETERANO("Veterano", 500, 1500),
    MESTRE("Mestre", 1500, 4000),
    LENDA("Lenda", 4000, Integer.MAX_VALUE);

    private final String nome;
    private final int xpMinimo;
    private final int xpMaximo;

    Rank(String nome, int xpMinimo, int xpMaximo) {
        this.nome = nome;
        this.xpMinimo = xpMinimo;
        this.xpMaximo = xpMaximo;
    }

    public String getNome() { return nome; }
    public int getXpMinimo() { return xpMinimo; }
    public int getXpMaximo() { return xpMaximo; }
}