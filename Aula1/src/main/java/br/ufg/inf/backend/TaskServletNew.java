package br.ufg.inf.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasksnew")
public class TaskServletNew extends HttpServlet {
    private TarefaService tarefaService;

    public TaskServletNew(TarefaService tarefaService) {
        // Use EmailNotificacaoService for this example
        //NotificacaoService notificacaoService = new EmailNotificacaoService();
        // tarefaService = new TarefaService(notificacaoService);
    	this.tarefaService = tarefaService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        List<String> tasks = tarefaService.listarTarefas();
        if (tasks.isEmpty()) {
            response.getWriter().println("Não há tarefas disponíveis.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                response.getWriter().println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if (task == null || task.trim().isEmpty()) {
            response.getWriter().println("Parâmetro 'task' é obrigatório.");
            return;
        }
        tarefaService.adicionarTarefa(task);
        response.getWriter().println("Tarefa adicionada com sucesso: " + task);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index")) - 1;
            String task = request.getParameter("task");
            if (index < 0 || index >= tarefaService.listarTarefas().size() || task == null || task.trim().isEmpty()) {
                response.getWriter().println("Parâmetro inválido.");
                return;
            }
            tarefaService.atualizarTarefa(index, task);
            response.getWriter().println("Tarefa atualizada com sucesso: " + task);
        } catch (NumberFormatException e) {
            response.getWriter().println("Parâmetro 'index' deve ser um número válido.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index")) - 1;
            if (index < 0 || index >= tarefaService.listarTarefas().size()) {
                response.getWriter().println("Parâmetro 'index' está fora do intervalo.");
                return;
            }
            tarefaService.removerTarefa(index);
            response.getWriter().println("Tarefa removida com sucesso.");
        } catch (NumberFormatException e) {
            response.getWriter().println("Parâmetro 'index' deve ser um número válido.");
        }
    }
}