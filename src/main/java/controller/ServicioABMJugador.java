package controller;

import dao.CrudService;
import dao.CrudServiceBean;
import model.Jugador;

public class ServicioABMJugador extends Servicio {
	public static final String ALTA = "alta";
	public static final String BAJA = "baja";
	public static final String MODIFICACION = "modificiacion";
	
	//por ahora creamos el objeto, idealmente hay que hacerlo estatico porque son servicios 
	protected CrudService<Jugador> dao;
	protected String estado;
	protected Jugador seleccionado;
	
	public ServicioABMJugador() {
		dao = new CrudServiceBean<Jugador>();
		this.estado = ALTA;		
	}
	
	public void altaJugador(Jugador j) {
		dao.create(j);
		seleccionado = j;
	}
	
	public void modificacionJugador(Jugador j) {
		dao.update(j);
		seleccionado = j;
	}
	
	public void eliminarJguador(Object id)	{
		dao.delete(Jugador.class, id);
		seleccionado = null;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String nuevo) {
		this.estado = nuevo;
	}
	
	public Jugador getSeleccionado() {
		return this.seleccionado;
	}
}
