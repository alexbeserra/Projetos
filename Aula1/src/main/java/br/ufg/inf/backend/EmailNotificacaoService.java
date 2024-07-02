package br.ufg.inf.backend;


public class EmailNotificacaoService implements NotificacaoService {
	@Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Enviando email: " + mensagem);
    }
}