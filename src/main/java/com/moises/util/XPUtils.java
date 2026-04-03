package com.moises.util;

public class XPUtils {


    public static int calcularXpFinal(int xpBase, double multiplicador, int sequencia) {
        double bonusStreak = 1.0 + (sequencia * 0.05);
        return (int) (xpBase * multiplicador * bonusStreak);
    }
    public static int calcularDanoHp(int sequenciaAtual) {

        return sequenciaAtual > 5 ? 20 : 10;
    }
}