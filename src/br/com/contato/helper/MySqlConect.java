package br.com.contato.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConect {

		public static Connection ConectarDB(){

			Connection con = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = DriverManager.getConnection
							("jdbc:mysql://10.107.134.58/contatos", "root", "root");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
}
