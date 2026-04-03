package com.moises.service;

import com.moises.enums.Rank;
import com.moises.model.User;
import com.moises.repository.UserRepository;
import com.moises.util.RankUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addExperience(User user, int amount) {
        user.adicionarXp(amount);

        // Verifica se o XP atual exige um novo Rank
        Rank rankIdeal = RankUtils.identificarRankPorXp(user.getXp());

        if (!user.getNivel().getNome().equalsIgnoreCase(rankIdeal.getNome())) {
            user.getNivel().setNome(rankIdeal.getNome());
            user.getNivel().setXpNecessario(rankIdeal.getXpMaximo());
            System.out.println("⭐ SUBIU DE NÍVEL: " + rankIdeal.getNome() + " ⭐");
        }

        userRepository.save(user);
    }

    public void applyPenalty(User user, int damage) {
        user.setHp(user.getHp() - damage);
        user.resetarSequencia();
        user.setPenalidadeAtiva(true);
        userRepository.save(user);
    }
}