package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the portatil database table.
 * 
 */
@Entity
@NamedQuery(name="Portatil.findAll", query="SELECT p FROM Portatil p")
public class Portatil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVenta;

	private String modelo;

	private int numProcesadores;

	private String sn;

	private boolean vendido;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="idMarca")
	private Marca marca;

	public Portatil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumProcesadores() {
		return this.numProcesadores;
	}

	public void setNumProcesadores(int numProcesadores) {
		this.numProcesadores = numProcesadores;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public boolean getVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Portatil [id=" + id + ", fechaVenta=" + fechaVenta + ", modelo=" + modelo + ", numProcesadores="
				+ numProcesadores + ", sn=" + sn + ", vendido=" + vendido + ", marca=" + marca + "]";
	}
	
	

}