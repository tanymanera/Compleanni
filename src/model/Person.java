package model;

import java.time.LocalDate;

public class Person {
	private int id;
	private String nome;
	private String cognome;
	private LocalDate natoIl;
	
	public Person() {
		this.id = 0;
		this.nome = "";
		this.cognome = "";
		this.natoIl = LocalDate.MIN;
	}

	public Person(int id, String nome, String cognome, LocalDate natoIl) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.natoIl = natoIl;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getNatoIl() {
		return natoIl;
	}

	public void setNatoIl(LocalDate natoIl) {
		this.natoIl = natoIl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("%s %s, natoIl = %s", nome, cognome, natoIl);
	}

}
