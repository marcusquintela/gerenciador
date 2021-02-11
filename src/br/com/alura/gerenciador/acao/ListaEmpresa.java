package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class ListaEmpresa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this);
		String nomeEmpresa = (String) request.getAttribute("nomeEmpresa");

		List<Empresa> empresas = Banco.getListaEmpresas();

		request.setAttribute("nomeEmpresa", nomeEmpresa);
		request.setAttribute("empresas", empresas);
		
		return "Dispatcher:listaEmpresas.jsp";
		
	}
}
