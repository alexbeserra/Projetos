package br.ufg.inf.backend;


public class SMSNotificacaoService implements NotificacaoService {
	@Override
    public String enviarNotificacao(String mensagem) {
        return "Enviando SMS: " + mensagem;
    }
}