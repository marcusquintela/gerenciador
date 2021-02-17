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
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;
import br.com.alura.gerenciador.acao.AlteraEmpresa;
import br.com.alura.gerenciador.acao.ListaEmpresa;
import br.com.alura.gerenciador.acao.Login;
import br.com.alura.gerenciador.acao.LoginForm;
import br.com.alura.gerenciador.acao.Logout;
import br.com.alura.gerenciador.acao.MostrarEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresa;
import br.com.alura.gerenciador.acao.NovaEmpresaForm;
import br.com.alura.gerenciador.acao.RemoveEmpresa;

/**
 * Servlet implementation class UnicaEntradaServlert
 */
@WebServlet("/entrada")
public class UnicaEntradaServlert extends HttpServlet {

	private static final long serialVersionUID = 543457558937710108L;
		
	public static final String NOVA_EMPRESA_FORM = "novaempresaform";
	public static final String NOVA_EMPRESA = "novaempresa";
	public static final String ALTERA_EMPRESA = "alteraempresa";
	public static final String REMOVE_EMPRESA = "removeempresa";
	public static final String MOSTRA_EMPRESA = "mostraempresa";
	public static final String LISTA_EMPRESA = "listaempresa";
	public static final String LOGIN = "login";
	public static final String LOGIN_FORM = "loginform";
	public static final String LOGOUT = "logout";
	
	private static final String WEB_INF_PATH = "WEB-INF/view/";
	private static final String ENTRADA_ACAO = "entrada?acao=";

	
	public static final String USUARIO_LOGADO = "usuarioLogado";

	public static final String REDIRECT = "REDIRECT";

	private static Map<String, Acao> acoes = new HashMap<>();

	static {
		// Lista das ações existentes do sistema.
		acoes.put(LISTA_EMPRESA, new ListaEmpresa());
		acoes.put(MOSTRA_EMPRESA, new MostrarEmpresa());
		acoes.put(REMOVE_EMPRESA, new RemoveEmpresa());
		acoes.put(ALTERA_EMPRESA, new AlteraEmpresa());
		acoes.put(NOVA_EMPRESA, new NovaEmpresa());
		acoes.put(NOVA_EMPRESA_FORM, new NovaEmpresaForm());
		acoes.put(LOGIN, new Login());
		acoes.put(LOGIN_FORM, new LoginForm());
		acoes.put(LOGOUT, new Logout());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		Acao acaoAExecutar = acoes.get(paramAcao.toLowerCase());
		boolean isAcaoProtegida = acaoAExecutar.getAcaoProtegida();
		
		if(isAcaoProtegida) {
			if(!usuarioLogado(request, response) && isAcaoProtegida)
				return;
		}
		
		String destino = acaoAExecutar.executar(request, response);

		redireciona(request, response, destino);
	}

	private void redireciona(HttpServletRequest request, HttpServletResponse response, String destino)
			throws IOException, ServletException {
		
		String[] opcoesDestino = destino.split(":");
		
		if (opcoesDestino[0].equalsIgnoreCase(REDIRECT)) {
			response.sendRedirect(ENTRADA_ACAO + opcoesDestino[1]);
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(WEB_INF_PATH + opcoesDestino[1]);
			rd.forward(request, response);
		}
	}

	private boolean usuarioLogado(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession httpSession = request.getSession();
		boolean usuarioNaoEstaLogado = (null == httpSession.getAttribute(USUARIO_LOGADO));
		if(usuarioNaoEstaLogado) {
			redireciona(request, response, Acao.REDIRECT+LOGIN_FORM);		
			return false;
		}
		return true;
	}
}
