package br.com.alura.gerenciador.acao;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LOGIN_FORM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logout implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();		

		return REDIRECT + LOGIN_FORM;
	}

	@Override
	public boolean getAcaoProtegida() {
		return false;
	}

}
