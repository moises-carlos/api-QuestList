package com.moises.service;

import com.moises.enums.TaskStatus;
import com.moises.model.Task;
import com.moises.model.User;
import com.moises.repository.TaskRepository;
import com.moises.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void concluirTarefa(String taskId, String userId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (taskOpt.isEmpty() || userOpt.isEmpty()) {
            System.err.println("Erro: Tarefa ou Usuário não encontrado.");
            return;
        }

        Task task = taskOpt.get();
        User user = userOpt.get();

        if (task.getStatus() == TaskStatus.CONCLUIDO) {
            System.out.println("Esta tarefa já foi concluída anteriormente.");
            return;
        }

        int xpGanho = (int) (task.getXpRecompensa() * user.getMultiplicadorXp());
        user.adicionarXp(xpGanho);

        user.setSequenciaAtual(user.getSequenciaAtual() + 1);

        task.concluirTarefa();

        taskRepository.save(task);
        userRepository.save(user);

        System.out.println("Parabéns, " + user.getNome() + "! Você ganhou " + xpGanho + " XP. 🏆");
    }
}