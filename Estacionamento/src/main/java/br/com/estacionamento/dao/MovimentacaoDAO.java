package br.com.estacionamento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.estacionamento.model.Movimentacao;
import br.com.estacionamento.model.Valor;

/**
 * Classe para a criação da movimentação do estacionamento O adiministrador fará
 * as operações de cadastro, alteração, update e exclusão do veículo
 * 
 * @author Julia
 *
 */
public class MovimentacaoDAO {
	/**
	 * DRUD - created, read, update, delete
	 */

	/**
	 * Método para salvar os dados das movimentações do estacionamento
	 * 
	 * @param usuario
	 */
	public void salvarMovimentacao(Movimentacao movimentacao) {

		// insere um usuario ao banco de dados
		String salvarMovimentacao = "INSERT INTO tbl_usuario(placa, modelo, data_entrada, data_saida, tempo, valor_pago) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(salvarMovimentacao);
			// adicionar os valores que são esperados pela query
			pstm.setString(1, movimentacao.getPlaca());
			pstm.setString(2, movimentacao.getModelo());
			pstm.setDate(3, (Date) movimentacao.getDataEntrada());
			pstm.setDate(4, (Date) movimentacao.getDataSaida());
			pstm.setString(5, movimentacao.getTempo());
			pstm.setDouble(6, movimentacao.getValorPago());

			// executar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método para alterar os dados referentes a movimentação de veiculos
	 * 
	 * @param movimentacao
	 *
	 */
	public List<Movimentacao> getMovimentacoes() {
		String sql = "SELECT * FROM tbl_movimentacao";

		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

		// conexão com mysql
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		// classe que vai recuperar os dados do banco
		ResultSet rset = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			// recuperar os dados do banco de dados
			while (rset.next()) {
				Movimentacao movimentacao = new Movimentacao();

				movimentacao.setId(rset.getInt("id"));
				movimentacao.setPlaca(rset.getString("placa"));
				movimentacao.setModelo(rset.getString("modelo"));
				movimentacao.setDataEntrada(rset.getDate("data_entrada"));
				movimentacao.setDataSaida(rset.getDate("data_saida"));
				movimentacao.setTempo(rset.getString("tempo"));
				movimentacao.setValorPago(rset.getDouble("valor_pago"));

				movimentacoes.add(movimentacao);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return movimentacoes;
	}

	/**
	 * Rotina para a atualização das movimentacoes
	 * 
	 * @param user
	 */
	public void update(Movimentacao movimentacao) {
		String sql = "UPDATE tbl_movimentacao SET placa = ?, modelo = ?, data_entrada = ?, "
				+ "data_saida = ?, tempo = ?, valor_pago = ? "
				+ "WHERE id = ?";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, movimentacao.getPlaca());
			pstm.setString(2, movimentacao.getModelo());
			pstm.setDate(3, (Date) movimentacao.getDataEntrada());
			pstm.setDate(4, (Date) movimentacao.getDataSaida());
			pstm.setString(5, movimentacao.getTempo());
			pstm.setDouble(6, movimentacao.getValorPago());
			
			// id do registro que será atualizado
			pstm.setInt(4, movimentacao.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Deletar uma movimentação
	 * 
	 * @param id
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM tbl_movimentacao WHERE id = ?";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
