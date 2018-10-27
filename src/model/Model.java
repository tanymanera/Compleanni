package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DAOcompleanni;

public class Model {
	
	private List<Person> persone;
	private DAOcompleanni dao = new DAOcompleanni();
	
	public Model() {
	persone = new ArrayList<>();
	}
	
	public List<Person> getPersons(){
//		persone.add(new Person("Tany", "Manera", LocalDate.parse("1958-03-29")));
//		persone.add(new Person("Beppe", "Manera", LocalDate.parse("1932-02-01")));
//		persone.add(new Person("Rina", "Fraschetti", LocalDate.parse("1938-01-01")));
		persone = dao.listaPersone();
		return persone;
	}

	public boolean isPutIn(String nome, String cognome) {
		return dao.isPutIn(nome, cognome);
	}

}
