package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Person;
import db.DBConnect;

public class DAOcompleanni {

	public List<Person> listaPersone() {
		List<Person> persons = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT id, nome, cognome, nato_il FROM persone";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int id = res.getInt("id");
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				LocalDate natoIl = LocalDate.parse(res.getDate("nato_il").toString());
				Person p = new Person(id, nome, cognome, natoIl);
				persons.add(p);
			}
			res.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nella connessione al database.");
		}
		return persons;
	}

	public void insertPerson(Person newPerson) {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO persone " + "(nome, cognome, nato_il) " + "VALUES " + "(?, ?, ?)";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, newPerson.getNome());
			st.setString(2, newPerson.getCognome());
			st.setDate(3, Date.valueOf(newPerson.getNatoIl()));
			int nuoveRighe = st.executeUpdate();
			st.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException("Errore nella connessione al database.");
		}
	}

	public void delete(Person delenda) {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM persone WHERE id = ?";
		PreparedStatement st;
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, delenda.getId());
			int righeEliminate = st.executeUpdate();
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nella connessione al database.");
		}
		
	}

	public boolean isPutIn(String nome, String cognome) {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM persone WHERE nome = ? AND cognome = ?";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, nome);
			st.setString(2, cognome);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				return true;
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Errore nella connessione al database.");
		}
		return false;
	}
}
