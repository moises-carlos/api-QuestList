package com.moises.service;

import com.moises.model.Task;
import com.moises.model.User;
import com.moises.util.XPUtils;
import org.springframework.stereotype.Service;

@Service
public class RewardService {
    public int calculateFinalXp(User user, Task task) {
        return XPUtils.calcularXpFinal(
                task.getXpRecompensa(),
                user.getMultiplicadorXp(),
                user.getSequenciaAtual()
        );
    }
}