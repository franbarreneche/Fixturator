package controller;

import java.util.HashMap;
import java.util.Map;

public class Sistema {
	//tiene que ser un singleton asi que guarda su instancia
	private static Sistema instancia = new Sistema();
	
	protected Map<String,Servicio> servicios; //seria una forma de guardar representaciones 1 a 1 de las GUIs pero en el sistema para encapsular comportamiento
		
	private Sistema() {
		servicios = new HashMap<String,Servicio>();
		//crear todos los servicios que puede hacer el sistema y agregarlos al mapeo
		ServicioABMJugador sj = new ServicioABMJugador();
		servicios.put("abmjugador", sj);
		ServicioABMEquipo se = new ServicioABMEquipo();
		servicios.put("abmequipo", se);
		ServicioABMTorneo st = new ServicioABMTorneo();
		servicios.put("abmtorneo", st);
		ServicioSeleccionTorneo seltorneo = new ServicioSeleccionTorneo();
		servicios.put("selecciontorneo", seltorneo);
	}
	
	
	public static Sistema getInstance() {
		return instancia;
	}
	
	
	public Servicio getServicio(String nombreServicio) {
		Servicio toRet = servicios.get(nombreServicio);
		return toRet;
		//si no está tendriamos que crearla y agregarla al mapeo (esto seria para crear ONDemand)
		//tambien podemos intentar poner en las properties todos los servicios y crear las clases leyendo eso, eso seria "configuracion"
		//esta bueno pero es dificil de hacer y capaz es al pedo
	}
	
	public void restart() {
		//TODO por si quiero reiniciar el sistema
	}
	
	
	
}
