package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(name="pais")
@NamedQuery(name="Pai.findAll", query="SELECT p FROM Pai p")
public class Pai implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String descripcion;

	//bi-directional many-to-one association to Marca
	@OneToMany(mappedBy="pai")
	private List<Marca> marcas;

	//bi-directional many-to-one association to Continente
	@ManyToOne
	@JoinColumn(name="idContinente")
	private Continente continente;

	public Pai() {
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

	public List<Marca> getMarcas() {
		return this.marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Marca addMarca(Marca marca) {
		getMarcas().add(marca);
		marca.setPai(this);

		return marca;
	}

	public Marca removeMarca(Marca marca) {
		getMarcas().remove(marca);
		marca.setPai(null);

		return marca;
	}

	public Continente getContinente() {
		return this.continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}
	
	public String toString() {
		return this.descripcion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pai) {
			Pai m = (Pai)obj;
			return m.getId() == this.id;
		}
		return false;
	}

}