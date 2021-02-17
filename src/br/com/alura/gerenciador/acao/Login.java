package br.com.alura.gerenciador.acao;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LISTA_EMPRESA;
import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LOGIN_FORM;
import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.USUARIO_LOGADO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	private static final String ERRO_LOGIN = "erroLogin";
	private static final String MSG_USUARIO_OU_SENHA_INVALIDO = "Usuário ou senha inválido!";

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(this);
		
		HttpSession httpSession = request.getSession();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Banco banco = new Banco();
		
		Usuario usuario = banco.validaLogin(login, senha); 
		
		if(usuario == null) {
			System.out.println(MSG_USUARIO_OU_SENHA_INVALIDO);
			httpSession.setAttribute(ERRO_LOGIN, MSG_USUARIO_OU_SENHA_INVALIDO);
			return REDIRECT+LOGIN_FORM;
		}
		
		httpSession.setAttribute(USUARIO_LOGADO, usuario);		
		
		return REDIRECT+LISTA_EMPRESA;		
	}

	@Override
	public boolean getAcaoProtegida() {
		return false;
	}
}
