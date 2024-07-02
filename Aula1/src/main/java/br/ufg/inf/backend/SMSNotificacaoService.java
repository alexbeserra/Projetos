package br.ufg.inf.backend;


public class SMSNotificacaoService implements NotificacaoService {
	@Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Enviando SMS: " + mensagem);
    }
}