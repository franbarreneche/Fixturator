package controller;

import java.util.List;

import dao.CrudService;
import dao.CrudServiceBean;
import model.Equipo;
import model.Jugador;

public class ServicioABMEquipo extends Servicio{
	//por ahora creamos el objeto, idealmente hay que hacerlo estatico porque son servicios 
	protected CrudService<Equipo> dao;
	protected Equipo seleccionado;
	
	public ServicioABMEquipo() {
		dao = new CrudServiceBean<Equipo>();
	}
	
	public void altaEquipo(Equipo e) {
		dao.create(e);
		seleccionado = e;
	}
	
	public void modificacionEquipo(Equipo e) {
		dao.update(e);
		seleccionado = e;
	}
	
	public void eliminarEquipo(Object id) {
		dao.delete(Equipo.class, id);
		seleccionado = null;
	}
	
	public void registrarCapitan(Jugador cap) {
		if(!seleccionado.getJugadores().contains(cap)) seleccionado.agregarJugador(cap);
		seleccionado.setCapitan(cap);
		modificacionEquipo(seleccionado);
	}
	
	public void registrarJugador(Jugador nuevo) {
		if(!seleccionado.getJugadores().contains(nuevo)) {
			seleccionado.agregarJugador(nuevo);
			modificacionEquipo(seleccionado);
		}
	}
	
	public void desregistrarJugador(Jugador j) {
		seleccionado.getJugadores().remove(j);
		if(seleccionado.getCapitan().equals(j))
			seleccionado.setCapitan(null);
		modificacionEquipo(seleccionado);
	}
	
	public Equipo getSeleccionado() {
		return this.seleccionado;
	}
	
	public List<Jugador> getTodosJugadores() {
		return new CrudServiceBean<Jugador>().findAll(Jugador.class);
	}
}
