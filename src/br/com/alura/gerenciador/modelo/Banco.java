package br.com.alura.gerenciador.modelo;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	private static Integer chaveSequencial = 1;

	static {
		Empresa empresa = new Empresa();
		empresa.setId(Banco.chaveSequencial++);
		empresa.setNome("Alura");
		listaEmpresas.add(empresa);
		
		Empresa empresa1 = new Empresa();
		empresa1.setId(Banco.chaveSequencial++);
		empresa1.setNome("Caelum");
		listaEmpresas.add(empresa1);
		
		Usuario usuarioMarcus = new Usuario();
		usuarioMarcus.setLogin("marcus");
		usuarioMarcus.setSenha("123456");
		listaUsuarios.add(usuarioMarcus);
		
		Usuario usuarioShirley = new Usuario();
		usuarioShirley.setLogin("shirley");
		usuarioShirley.setSenha("123456");
		listaUsuarios.add(usuarioShirley);
	}

	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.listaEmpresas.add(empresa);
	}

	public static List<Empresa> getListaEmpresas() {
		return Banco.listaEmpresas;
	}
	public void removeEmpresa(Integer id) {
		List<Empresa> lista = new ArrayList<>(Banco.listaEmpresas);
		lista.forEach(e -> {
			if (e.getId() == id) {
				listaEmpresas.remove(e);
			}
		});
	}

	public Empresa buscaEmpresa(Integer id) {
		for (Empresa empresa : listaEmpresas) {
			if (empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

	public void altera(Integer id, Empresa novaEmpresa) {
		Empresa empresa = buscaEmpresa(id);
		empresa.setNome(novaEmpresa.getNome());
		empresa.setDataAbertura(novaEmpresa.getDataAbertura());
	}
	
	public boolean validaLogin(String login, String senha) {
		for (Usuario u : listaUsuarios) {
			if(u.getLogin().equals(login) && u.getSenha().equals(senha)) 
				return true;
		}
		return false;
	}

	
}
