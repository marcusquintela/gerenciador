package br.com.alura.gerenciador.servlet;

import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.LOGIN_FORM;
import static br.com.alura.gerenciador.servlet.UnicaEntradaServlert.USUARIO_LOGADO;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/**
 * Servlet Filter implementation class AutorizacaoFilter
 */
@WebFilter("/entrada")
public class AutorizacaoFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String paramAcao = request.getParameter("acao");
		Acao acaoAExecutar = UnicaEntradaServlert.acoes.get(paramAcao.toLowerCase());
		boolean isAcaoProtegida = acaoAExecutar.getAcaoProtegida();
		
		if(isAcaoProtegida) {
			if(!usuarioLogado(request, response) && isAcaoProtegida)
				return;
		}
		
		chain.doFilter(request, response);
	}
	
	private boolean usuarioLogado(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession httpSession = request.getSession();
		boolean usuarioNaoEstaLogado = (null == httpSession.getAttribute(USUARIO_LOGADO));
		if(usuarioNaoEstaLogado) {
			UnicaEntradaServlert.redireciona(request, response, Acao.REDIRECT+LOGIN_FORM);		
			return false;
		}
		return true;
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}