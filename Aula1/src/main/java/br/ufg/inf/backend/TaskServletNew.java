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

    public TaskServletNew() {
    	this.tarefaService = new TarefaService(new EmailNotificacaoService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        List<String> tasks = tarefaService.listarTarefas();
        if (tasks.isEmpty()) {
            response.getWriter().println("Nao ha tarefas disponiveis.");
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
            response.getWriter().println("Parametro 'task' e obrigatorio.");
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
                response.getWriter().println("Parametro invalido.");
                return;
            }
            tarefaService.atualizarTarefa(index, task);
            response.getWriter().println("Tarefa atualizada com sucesso: " + task);
        } catch (NumberFormatException e) {
            response.getWriter().println("Parametro 'index' deve ser um numero valido.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index")) - 1;
            if (index < 0 || index >= tarefaService.listarTarefas().size()) {
                response.getWriter().println("Parametro 'index' esta fora do intervalo.");
                return;
            }
            tarefaService.removerTarefa(index);
            response.getWriter().println("Tarefa removida com sucesso.");
        } catch (NumberFormatException e) {
            response.getWriter().println("Parametro 'index' deve ser um numero valido.");
        }
    }
}