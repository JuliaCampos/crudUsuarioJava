package br.com.estacionamento.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.security.MD5Encoder;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.estacionamento.model.Usuario;

/**
 * Classe para a criação do administrador do estacionamento Este adiministrador
 * fará as operações de cadastro, alteração, update e exclusão do veículo
 * 
 * @author Julia
 *
 */
public class UsuarioDAO {
	/**
	 * DRUD - created, read, update, delete
	 */

	/**
	 * Método para salvar os dados do administrador no banco
	 * 
	 * @param usuario
	 */
	public void salvarUsuario(Usuario usuario) {

		// insere um usuario ao banco de dados
		String salvarUsuario = "INSERT INTO tbl_usuario(nome, usuario, senha) VALUES (?, ?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(salvarUsuario);
			// adicionar os valores que são esperados pela query
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getUsuario());
			pstm.setString(3, MD5Encoder.encode(usuario.getSenha().getBytes()));

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
	 * Método para alterar os dados do usuario no banco
	 * 
	 * @param usuario
	 *
	 */
	public List<Usuario> getUsuarios() {
		String sql = "SELECT * FROM tbl_usuario";

		List<Usuario> usuarios = new ArrayList<Usuario>();

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
				Usuario user = new Usuario();

				user.setId(rset.getInt("id"));
				user.setNome(rset.getString("nome"));
				user.setUsuario(rset.getString("usuario"));

				usuarios.add(user);
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

		return usuarios;
	}

	/**
	 * Rotina para a atualização do usuario
	 * 
	 * @param user
	 */
	public void update(Usuario user) {
		String sql = "UPDATE tbl_usuario " + "SET nome = ?, usuario = ?, senha = ? " + "WHERE id = ?";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// cria a conexão com o banco de dados
			conn = br.com.estacionamento.factory.ConexaoMySQL.getConnection();

			// cria uma PrepareStatement para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, user.getNome());
			pstm.setString(2, user.getUsuario());
			pstm.setString(3, MD5Encoder.encode(user.getSenha().getBytes()));
			
			//id do registro que será atualizado
			pstm.setInt(4, user.getId());
			
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
	
	public void deleteById(int id) {
		String sql = "DELETE FROM tbl_usuario WHERE id = ?";
	
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
