package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the continente database table.
 * 
 */
@Entity
@NamedQuery(name="Continente.findAll", query="SELECT c FROM Continente c")
public class Continente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Pai
	@OneToMany(mappedBy="continente")
	private List<Pai> pais;

	public Continente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Pai> getPais() {
		return this.pais;
	}

	public void setPais(List<Pai> pais) {
		this.pais = pais;
	}

	public Pai addPai(Pai pai) {
		getPais().add(pai);
		pai.setContinente(this);

		return pai;
	}

	public Pai removePai(Pai pai) {
		getPais().remove(pai);
		pai.setContinente(null);

		return pai;
	}
	public String toString() {
		return this.descripcion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Continente) {
			Continente m = (Continente)obj;
			return m.getId() == this.id;
		}
		return false;
	}

}