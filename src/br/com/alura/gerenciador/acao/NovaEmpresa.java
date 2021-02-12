package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import br.com.alura.gerenciador.util.UtilData;

public class NovaEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		
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

		request.setAttribute("nomeEmpresa", nomeEmpresa);

		return "Redirect:entrada?acao=ListaEmpresa";

	}
}
