package br.com.alura.gerenciador.acao;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LISTA_EMPRESA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;

public class RemoveEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		
		String paramId = request.getParameter("id");
		Integer id = Integer.parseInt(paramId);

		Banco banco = new Banco();
		banco.removeEmpresa(id);

		return REDIRECT + LISTA_EMPRESA;
	}
}
