package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.acao.Acao;
import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresa;
import br.com.alura.gerenciador.acao.MostrarEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresaForm;
import br.com.alura.gerenciador.acao.RemoveEmpresa;

/**
 * Servlet implementation class UnicaEntradaServlert
 */
@WebServlet("/entrada")
public class UnicaEntradaServlert extends HttpServlet {

	private static final String NOVA_EMPRESA_FORM = "NOVAEMPRESAFORM";
	private static final String NOVA_EMPRESA = "NOVAEMPRESA";
	private static final String ALTERA_EMPRESA = "ALTERAEMPRESA";
	private static final String REMOVE_EMPRESA = "REMOVEEMPRESA";
	private static final String MOSTRA_EMPRESA = "MOSTRAEMPRESA";
	private static final String LISTA_EMPRESA = "LISTAEMPRESA";

	private static final long serialVersionUID = 1L;

	private static final String REDIRECT = "REDIRECT";

	private static Map<String, Acao> acoes = new HashMap<>();

	static {
		// Lista das ações existentes do sistema.
		acoes.put(LISTA_EMPRESA, new ListaEmpresa());
		acoes.put(MOSTRA_EMPRESA, new MostrarEmpresa());
		acoes.put(REMOVE_EMPRESA, new RemoveEmpresa());
		acoes.put(ALTERA_EMPRESA, new AlteraEmpresa());
		acoes.put(NOVA_EMPRESA, new NovaEmpresa());
		acoes.put(NOVA_EMPRESA_FORM, new NovaEmpresaForm());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paramAcao = request.getParameter("acao");

		Acao acaoAExecutar = acoes.get(paramAcao.toUpperCase());
		String destino = acaoAExecutar.executar(request, response);

		String[] opcoesDestino = destino.split(":");
		if (opcoesDestino[0].equalsIgnoreCase(REDIRECT)) {
			response.sendRedirect(opcoesDestino[1]);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + opcoesDestino[1]);
			rd.forward(request, response);
		}
	}
}
