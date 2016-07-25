package br.com.contato.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.contato.helper.MySqlConect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable{

	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView lstContatos;

	private void PreencherLista(){

		lstContatos.getItems().clear();

		Connection con = MySqlConect.ConectarDB();
		String sql = "select *from contact;";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);

			while(rs.next()){
				String nome = rs.getString("name");
				String telefone = rs.getString("phone");

				lstContatos.getItems().add(nome + "  -  " + telefone);
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@FXML public void inserirContato(){
		TextField nome = txtNome;
		TextField telefone = txtTelefone;

		//verificando se esta vazia ou nao o TXT
		if(nome.getText().isEmpty() || telefone.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Alguma caixa não está preenchida !");
		}else{
			//BD
			Connection con = MySqlConect.ConectarDB();
			String sql = "insert into contact (name, phone) values(?, ?);";

			PreparedStatement parametros;

			try {
				parametros = con.prepareStatement(sql);
				parametros.setString(1, nome.getText());
				parametros.setString(2, telefone.getText());

				parametros.executeUpdate();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/***
			//Pegando valores do txt
			String contato = nome.getText() + " - " + telefone.getText();
			//preenchendo a lista
			lstContatos.getItems().add(contato);
			***/
			nome.setText("");
			telefone.setText("");
			JOptionPane.showMessageDialog(null, "Registro salvo com sucesso !");
			//System.out.println("É NOISSSSSSSSSSSSSS!");
		}
		PreencherLista();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//metodo para preencher a lista
		PreencherLista();
	}
}
