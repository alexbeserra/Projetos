package br.ufg.inf.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private List<String> tasks = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        if (tasks.isEmpty()) {
            resp.getWriter().println("Nao ha tarefas disponiveis.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                resp.getWriter().println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if (task == null || task.trim().isEmpty()) {
            resp.getWriter().println("Parametro 'task' e obrigatorio.");
            return;
        }
        tasks.add(task);
        resp.getWriter().println("Tarefa adicionada com sucesso: " + task);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(req.getParameter("index")) - 1;
            String task = req.getParameter("task");
            if (index < 0 || index >= tasks.size() || task == null || task.trim().isEmpty()) {
                resp.getWriter().println("Parametro invalido.");
                return;
            }
            tasks.set(index, task);
            resp.getWriter().println("Tarefa atualizada com sucesso: " + task);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Parametro 'index' deve ser um numero valido.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(req.getParameter("index")) - 1;
            if (index < 0 || index >= tasks.size()) {
                resp.getWriter().println("Parametro 'index' esta fora do intervalo.");
                return;
            }
            String removedTask = tasks.remove(index);
            resp.getWriter().println("Tarefa removida com sucesso: " + removedTask);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Parametro 'index' deve ser um numero valido.");
        }
    }
}