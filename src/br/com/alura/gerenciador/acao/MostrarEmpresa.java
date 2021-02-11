package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class MostrarEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		String paramId = request.getParameter("id");
		Integer id = Integer.parseInt(paramId);

		Banco banco = new Banco();
		Empresa empresa = banco.buscaEmpresa(id);
		request.setAttribute("empresa", empresa);
		
		return "Dispatcher:formAlteraEmpresa.jsp";

	}
}
