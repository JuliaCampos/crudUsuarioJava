package br.com.estacionamento.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para a conex?o com o banco de dados
 * @author Julia
 *
 */
public class ConexaoMySQL {
	public static String status = "N?o conectou...";

	// m?todo construtor
	public ConexaoMySQL() {

	}

	// M?todo de Conex?o//
	public static java.sql.Connection getConnection() {

		Connection connection = null; // atributo do tipo Connection

		try {

			// Carregando o JDBC Driver padr?o
			String driverName = "com.mysql.jdbc.Driver";			
			Class.forName(driverName);

			// Configurando a nossa conex?o com um banco de dados//
			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "mysql"; // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root"; // nome de um usu?rio de seu BD
			String password = "1234"; // sua senha de acesso
			connection = DriverManager.getConnection(url, username, password);

			// Testa sua conex?o//
			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->N?o foi possivel realizar conex?o");
			}

			return connection;

		} catch (ClassNotFoundException e) {

			// Driver n?o encontrado
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;

		} catch (SQLException e) {
			// N?o conseguindo se conectar ao banco
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}

	// M?todo que retorna o status da sua conex?o//
	public static String statusConection() {
		return status;
	}

	// M?todo que fecha sua conex?o//
	public static boolean FecharConexao() {
		try {
			ConexaoMySQL.getConnection().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// M?todo que reinicia sua conex?o//
	public static java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		return ConexaoMySQL.getConnection();
	}
}
