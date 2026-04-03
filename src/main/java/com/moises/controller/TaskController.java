package com.moises.controller;

import com.moises.enums.TaskStatus;
import com.moises.model.Task;
import com.moises.repository.TaskRepository;
import com.moises.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final GameService gameService;

    @Autowired
    public TaskController(TaskRepository taskRepository, GameService gameService) {
        this.taskRepository = taskRepository;
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestParam String userId,
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam int xpRecompensa,
            @RequestParam int diasParaExpirar) {

        Task novaTask = new Task(titulo, descricao, xpRecompensa, LocalDateTime.now().plusDays(diasParaExpirar), userId);
        taskRepository.save(novaTask);
        return ResponseEntity.ok(novaTask);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getActiveTasks(@PathVariable String userId) {
        List<Task> tarefasAtivas = taskRepository.findByUserId(userId)
                .stream()
                .filter(t -> t.getStatus() == TaskStatus.ABERTA)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(tarefasAtivas);
    }

    @PostMapping("/{taskId}/complete")
    public ResponseEntity<String> completeTask(
            @PathVariable String taskId,
            @RequestParam String userId) {
        
        try {
            gameService.completeTask(userId, taskId);
            return ResponseEntity.ok("Tarefa completada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao completar tarefa: " + e.getMessage());
        }
    }
}
