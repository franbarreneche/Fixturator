package model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity("jugadores")
@Indexes({
    @Index(fields = @Field("nombre"))
})
public class Jugador {
	@Id
	protected ObjectId id;
	protected String nombre;
	protected String dni;
	protected String email;
	protected LocalDate fechaNac;
	
	//constructores
	public Jugador() {
		
	}
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
			
	//setters
	public void setNombre(String n) {
		this.nombre = n;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFechaNac(LocalDate fecha) {
		this.fechaNac = fecha;
	}
	
	//getters
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public ObjectId getID() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public LocalDate getFechaNac() {
		return this.fechaNac;
	}
	
	//otros
	public String toString() {
		return this.getNombre();
	}
	
	@Override
	public boolean equals(Object p) {
		if(p!=null) {
		Jugador aux = (Jugador)p;
		return this.id.compareTo(aux.id) == 0;
		} else return false;
	}
	
	
}
