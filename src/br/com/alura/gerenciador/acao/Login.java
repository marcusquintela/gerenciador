package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;

public class Login implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this);
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Banco banco = new Banco();
		if(!banco.validaLogin(login, senha)) {
			throw new RuntimeException("Login invalido");
		}
		
		return "Redirect:entrada?acao=ListaEmpresa";

	}

}
