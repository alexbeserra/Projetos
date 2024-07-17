package br.ufg.inf.backend.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufg.inf.backend.model.Produto;

@Controller
public class ProdutoController {
	@GetMapping("/produtos")
	public String listarProdutos(Model model) {
// Simulando a obtenção de produtos do banco de dados
		List<Produto> produtos = Arrays.asList(
				new Produto(1L, "Notebook", 2000.00),
				new Produto(2L, "Smartphone", 1000.00));
		model.addAttribute("produtos", produtos);
		return "produtos";
	}

	@GetMapping("/produtos/adicionar")
	public String mostrarFormularioAdicionarProduto() {
		return "adicionar-produto";
	}

	@PostMapping("/produtos")
	public String adicionarProduto(@RequestParam String nome, @RequestParam double preco, Model model) {
// Simulando a adição de um produto ao banco de dados
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(preco);
// Adicionando o produto à lista de produtos
		model.addAttribute("produtos", Arrays.asList(produto));
		model.addAttribute("sucesso", "Produto adicionado com sucesso!");
		return "produtos";
	}
}