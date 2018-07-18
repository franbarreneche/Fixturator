package controller;

import java.util.List;

import dao.CrudService;
import dao.CrudServiceBean;
import model.Equipo;
import model.Torneo;

public class ServicioABMTorneo extends Servicio{
	//por ahora creamos el objeto, idealmente hay que hacerlo estatico porque son servicios 
		protected CrudService<Torneo> dao;
		protected Torneo seleccionado;
		
		public ServicioABMTorneo() {
			dao = new CrudServiceBean<Torneo>();
		}
		
		public void altaTorneo(Torneo t) {
			dao.create(t);
			seleccionado = t;
		}
		
		public void modificacionTorneo(Torneo t) {
			dao.update(t);
			seleccionado = t;
		}
		
		public void eliminarTorneo(Object id) {
			dao.delete(Torneo.class, id);
		}
		
		public Torneo getSeleccionado() {
			return this.seleccionado;
		}
		
		public void setSeleccionado(Torneo t) {
			this.seleccionado = t;
		}
		
		public List<Equipo> getTodosEquipos() {
			return new CrudServiceBean<Equipo>().findAll(Equipo.class);
		}

		public void registrarEqupipo(Equipo e) {
			if(!seleccionado.getEquipos().contains(e)) {
				this.seleccionado.agregarEquipo(e);
				modificacionTorneo(seleccionado);
			}
		}

		public void desregistrarEquipo(Equipo e) {
			seleccionado.getEquipos().remove(e);
			modificacionTorneo(seleccionado);
		}
		
}
