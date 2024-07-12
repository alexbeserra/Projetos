package br.ufg.inf.backend;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numero1 = req.getParameter("num1");
		String numero2 = req.getParameter("num2");
		String operacao = req.getParameter("oper");
		double num1 = 0;
		double num2 = 0;
		double resultado;
		String mensagem = "";
		
		try {
			num1 = Double.parseDouble(numero1);
			num2 = Double.parseDouble(numero2);
		} catch (NullPointerException e) {
			resp.getWriter().println("Os parametros devem ser informados");
			return;
		} catch (NumberFormatException e) {
            resp.getWriter().println("Os parametros num1 e num2 devem ser valores numericos.");
            return;
		}
		
		
		
		if ("soma".equals(operacao)) {
			resultado = num1 + num2;
			mensagem = "A soma de " + num1 + " + " + num2 + " = " + resultado;
		} else if ("subtracao".equals(operacao)) {
			resultado = num1 - num2;
			mensagem = "A subtracao de " + num1 + " - " + num2 + " = " + resultado;
		} else if ("multiplicacao".equals(operacao)) {
			resultado = num1 * num2;
			mensagem = "A multiplicacao de " + num1 + " * " + num2 + " = " + resultado;
		} else if ("divisao".equals(operacao)) {
			if (num2 != 0) {
				resultado = num1 / num2;
				mensagem = "A divisao de " + num1 + " / " + num2 + " = " + resultado;
			} else if (num2 == 0) {
				mensagem = "O divisor nao pode ser zero";
			}
		} else {
			mensagem = "A operacao " + operacao + " e invalida";
		}
		
		resp.getWriter().println(mensagem);
	}
}