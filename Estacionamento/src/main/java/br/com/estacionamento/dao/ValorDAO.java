package br.com.estacionamento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.security.MD5Encoder;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.estacionamento.model.Usuario;
import br.com.estacionamento.model.Valor;

/**
 * Classe para a dos valores de tabela de preço do estacionamento
 * 
 * @author Julia
 *
 */
public class ValorDAO {
	/**
	 * DRUD - created, read, update, delete
	 */

	/**
	 * Método para salvar valores da tabela de preço de estacionamento
	 * 
	 * @param usuario
	 */
	public void salvarValor(Valor valor) {

		// insere um valor ao banco de dados
		String salvarValor = "INSERT INTO tbl_valor(valor_primeira_hora, valor_demais_horas, data_fim) "
				+ "VALUES (?, ?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(salvarValor);
			// adicionar os valores que são esperados pela query
			pstm.setDouble(1, valor.getValorPrimeiraHora());
			pstm.setDouble(2, valor.getValorDemaisHoras());
			pstm.setDate(3, (Date) valor.getDataFim());

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
	 * Método para alterar os dados referentes a tabela de valor
	 * 
	 * @param valor
	 *
	 */
	public List<Valor> getValores() {
		String sql = "SELECT * FROM tbl_valor";

		List<Valor> valores = new ArrayList<Valor>();

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
				Valor valor = new Valor();

				valor.setId(rset.getInt("id"));
				valor.setValorPrimeiraHora(rset.getDouble("valor_primeira_hora"));
				valor.setValorDemaisHoras(rset.getDouble("valor_demais_horas"));
				valor.setDataFim(rset.getDate("data_fim"));

				valores.add(valor);
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

		return valores;
	}

	/**
	 * Rotina para a atualização dos valores
	 * 
	 * @param user
	 */
	public void update(Valor valor) {
		String sql = "UPDATE tbl_valor SET valor_primeira_hora = ?, valor_demais_horas = ?, data_fim = ? "
				+ "WHERE id = ?";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			pstm.setDouble(1, valor.getValorPrimeiraHora());
			pstm.setDouble(2, valor.getValorDemaisHoras());
			pstm.setDate(3, (Date) valor.getDataFim());

			// id do registro que será atualizado
			pstm.setInt(4, valor.getId());

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
	 * Deletar um valor da tabela de valores
	 * @param id
	 */
	public void deleteById(int id) {
		String sql = "DELETE FROM tbl_valor WHERE id = ?";

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
