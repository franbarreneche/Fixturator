package controller;

import java.util.List;

import dao.CrudService;
import dao.CrudServiceBean;
import model.Torneo;

public class ServicioSeleccionTorneo extends Servicio {
	protected CrudServiceBean<Torneo> dao;
	
	public ServicioSeleccionTorneo() {
		dao = new CrudServiceBean<Torneo>(); 
	}
	
	public List<Torneo> getTodosTorneos() {
		return dao.findAll(Torneo.class);
	}
	
	public void administrarTorneo(Torneo t) {
		ServicioABMTorneo serv = (ServicioABMTorneo)Sistema.getInstance().getServicio("abmtorneo");
		serv.setSeleccionado(t);
	}
}
