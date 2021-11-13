package br.com.estacionamento.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import br.com.estacionamento.dao.MovimentacaoDAO;
import br.com.estacionamento.dao.UsuarioDAO;
import br.com.estacionamento.dao.ValorDAO;
import br.com.estacionamento.model.Movimentacao;
import br.com.estacionamento.model.Usuario;
import br.com.estacionamento.model.Valor;

public class Main {

	public static void main(String[] args) {

		// inserção dos dados na tabela de usuario
		Usuario usuario = new Usuario();
		usuario.setNome("admin");
		usuario.setUsuario("admin");
		usuario.setSenha("123456");

		UsuarioDAO saveUsuario = new UsuarioDAO();
		saveUsuario.salvarUsuario(usuario);

		// visualização dos registros todos
		for (Usuario u : saveUsuario.getUsuarios()) {
			u.getNome();
		}

		// atualizar usuario
		Usuario atualizaUsuario = new Usuario();
		atualizaUsuario.setNome("Admin");
		atualizaUsuario.setUsuario("Admin");
		atualizaUsuario.setSenha("123456");
		atualizaUsuario.setId(1);

		UsuarioDAO atualizar = new UsuarioDAO();
		atualizar.update(atualizaUsuario);

		// deletar usuario
		UsuarioDAO delete = new UsuarioDAO();
		delete.deleteById(1);



		// inserção dos valores na tabela de valores
		Valor valor = new Valor();
		valor.setValorPrimeiraHora(6.00);
		valor.setValorDemaisHoras(4.00);
		valor.setDataFim(null);
		
		ValorDAO valorDao = new ValorDAO();
		valorDao.salvarValor(valor);
		valorDao.update(valor);
		valorDao.deleteById(1);
		
		// visualização dos registros todos
		for (Valor v : valorDao.getValores()) {
			v.getValorPrimeiraHora();
			v.getValorDemaisHoras();
			v.getDataFim();
		}

		// inserção das movimentações
		Movimentacao veiculo1 = new Movimentacao();

		veiculo1.setPlaca("KAH-8225");
		veiculo1.setModelo("Gol");
		veiculo1.setDataEntrada(new Date());
		veiculo1.setDataSaida(new Date());
		veiculo1.setTempo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		veiculo1.setValorPago(8.00);

		MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO();
		movimentacaoDao.salvarMovimentacao(veiculo1);
		movimentacaoDao.update(veiculo1);
		movimentacaoDao.deleteById(1);
		
		// visualização dos registros todos
		for (Movimentacao m : movimentacaoDao.getMovimentacoes()) {
			m.getPlaca();
			m.getModelo();
			m.getDataEntrada();
			m.getDataSaida();
			m.getTempo();
			m.getValorPago();
		}
		
	}

}
