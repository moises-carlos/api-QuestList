package com.moises.util;

// IMPORTANTE: Importar o Enum que você já criou
import com.moises.enums.Rank;

public class RankUtils {

    // Retorna qual o Rank ideal para uma quantidade de XP
    public static Rank identificarRankPorXp(int xp) {
        // Agora o Rank.values() se refere ao Enum lá da pasta enums
        for (Rank r : Rank.values()) {
            if (xp >= r.getXpMinimo() && xp < r.getXpMaximo()) {
                return r;
            }
        }
        return Rank.RECRUTA;
    }

    // Calcula a porcentagem da barra de progresso (0 a 100%)
    public static double calcularPercentualProximoNivel(int xpAtual) {
        Rank atual = identificarRankPorXp(xpAtual);

        // Verifica se é o último nível do Enum
        if (atual == Rank.LENDA) return 100.0;

        double range = atual.getXpMaximo() - atual.getXpMinimo();
        double progresso = xpAtual - atual.getXpMinimo();

        return (progresso / range) * 100;
    }
}