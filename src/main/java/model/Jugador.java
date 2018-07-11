package model;

import javax.annotation.PostConstruct;

public class Jugador {
	protected String nombre;
	
	public Jugador(String n) {
		nombre = n;
	}
	
	@PostConstruct
    public void init() {
        System.out.println("Tower.init()");
    }
	
	public String getNombre() {
		return this.nombre;
	}
}
