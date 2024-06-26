package br.ufg.inf.backend;

import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private List<String> tasks = new ArrayList<>();
    private NotificacaoService notificacaoService;

    public TarefaService(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    public List<String> listarTarefas() {
        return tasks;
    }

    public void adicionarTarefa(String task) {
        tasks.add(task);
        notificacaoService.enviarNotificacao("Tarefa adicionada: " + task);
    }

    public void atualizarTarefa(int index, String task) {
        tasks.set(index, task);
        notificacaoService.enviarNotificacao("Tarefa atualizada: " + task);
    }

    public void removerTarefa(int index) {
        String removedTask = tasks.remove(index);
        notificacaoService.enviarNotificacao("Tarefa removida: " + removedTask);
    }
}