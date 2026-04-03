package com.moises.service;

import com.moises.enums.TaskStatus;
import com.moises.model.Task;
import com.moises.model.User;
import com.moises.repository.TaskRepository;
import com.moises.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final UserRepository userRepo;
    private final TaskRepository taskRepo;
    private final UserService userService;
    private final RewardService rewardService;

    @Autowired
    public GameService(UserRepository userRepo, TaskRepository taskRepo,
                       UserService userService, RewardService rewardService) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.userService = userService;
        this.rewardService = rewardService;
    }

    public void completeTask(String userId, String taskId) {
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Task> taskOpt = taskRepo.findById(taskId);

        if (userOpt.isPresent() && taskOpt.isPresent()) {
            User user = userOpt.get();
            Task task = taskOpt.get();
            
            if (task.getStatus() == TaskStatus.ABERTA) {
                int xpGained = rewardService.calculateFinalXp(user, task);

                user.setSequenciaAtual(user.getSequenciaAtual() + 1);
                userService.addExperience(user, xpGained);

                task.concluirTarefa();
                taskRepo.save(task);

                System.out.println("Tarefa concluída! +" + xpGained + " XP para " + user.getNome());
            }
        }
    }

    public void verificarTarefasAtrasadas(String userId) {
        List<Task> tasks = taskRepo.findByUserId(userId);
        Optional<User> userOpt = userRepo.findById(userId);

        if (userOpt.isEmpty()) return;
        User user = userOpt.get();

        for (Task task : tasks) {
            if (task.getStatus() == TaskStatus.ABERTA && LocalDateTime.now().isAfter(task.getDataLimite())) {
                System.out.println("⚠️ Task '" + task.getTitulo() + "' expirou!");
                userService.applyPenalty(user, 10);
                task.marcarComoCancelada();
                taskRepo.save(task);
            }
        }
    }
}