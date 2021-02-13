package br.com.alura.gerenciador.acao;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LISTA_EMPRESA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import br.com.alura.gerenciador.util.UtilData;

public class NovaEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		HttpSession httpSession = request.getSession();

		String nomeEmpresa = request.getParameter("nome");
		String dataAbertura = request.getParameter("data");

		Empresa novaEmpresa = new Empresa();
		novaEmpresa.setNome(nomeEmpresa);
		novaEmpresa.setDataAbertura(UtilData.converteData(dataAbertura));
		
		if(novaEmpresa.getDataAbertura() == null) {
			throw new ServletException("Empresa sem data de abertura!");
		}

		Banco banco = new Banco();
		banco.adiciona(novaEmpresa);

		httpSession.setAttribute("nomeEmpresa", nomeEmpresa);
		request.setAttribute("nomeEmpresa", nomeEmpresa);

		return REDIRECT+LISTA_EMPRESA;

	}
}
