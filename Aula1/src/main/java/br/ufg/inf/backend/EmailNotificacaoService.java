package br.ufg.inf.backend;


public class EmailNotificacaoService implements NotificacaoService {
	@Override
    public String enviarNotificacao(String mensagem) {
        return "Enviando email: " + mensagem;
    }
}