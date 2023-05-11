package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQuery(name="Marca.findAll", query="SELECT m FROM Marca m")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String denominacion;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="idPais")
	private Pai pai;

	//bi-directional many-to-one association to Portatil
	@OneToMany(mappedBy="marca")
	private List<Portatil> portatils;

	public Marca() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Pai getPai() {
		return this.pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}

	public List<Portatil> getPortatils() {
		return this.portatils;
	}

	public void setPortatils(List<Portatil> portatils) {
		this.portatils = portatils;
	}

	public Portatil addPortatil(Portatil portatil) {
		getPortatils().add(portatil);
		portatil.setMarca(this);

		return portatil;
	}

	public Portatil removePortatil(Portatil portatil) {
		getPortatils().remove(portatil);
		portatil.setMarca(null);

		return portatil;
	}
	
	public String toString() {
		return this.denominacion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Marca) {
			Marca m = (Marca)obj;
			return m.getId() == this.id;
		}
		return false;
	}
	
	

}