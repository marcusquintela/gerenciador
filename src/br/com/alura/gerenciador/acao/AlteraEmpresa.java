package br.com.alura.gerenciador.acao;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LISTA_EMPRESA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;
import br.com.alura.gerenciador.util.UtilData;

public class AlteraEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		
		String nomeEmpresa = request.getParameter("nome");
		String dataAbertura = request.getParameter("data");
		String paramId = request.getParameter("id");
		Integer id = Integer.parseInt(paramId);

		Empresa novaEmpresa = new Empresa();
		novaEmpresa.setNome(nomeEmpresa);
		novaEmpresa.setDataAbertura(UtilData.converteData(dataAbertura));
		
		if(novaEmpresa.getDataAbertura() == null) {
			throw new ServletException("Empresa sem data de abertura valida!");
		}

		Banco banco = new Banco();
		banco.altera(id, novaEmpresa);

		return REDIRECT + LISTA_EMPRESA;
	}

	@Override
	public boolean getAcaoProtegida() {
		return true;
	}
}
