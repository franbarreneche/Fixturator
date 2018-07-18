package presentation.jugador;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import controller.ServicioABMJugador;
import controller.Sistema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import launcher.CoordinadorVistas;
import model.Jugador;
import presentation.general.GeneralPresenter;

public class JugadorPresenter implements Initializable{
	//variables de FXML
	@FXML
    private TextField idJugador;

    @FXML
    private TextField nombreJugador;

    @FXML
    private TextField dniJugador;

    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonEditar;

    @FXML
    private Button botonEliminar;
    
    @FXML
    private TextField emailJugador;

    @FXML
    private DatePicker fechaNacJugador;
    
    //variables mias
	private ServicioABMJugador controller;
	
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	//pedirle al singleton Sistema que me de el servicio abm jugador
    	controller = (ServicioABMJugador) Sistema.getInstance().getServicio("abmjugador");
    	
		String estado = controller.getEstado();
		if(estado.equals(ServicioABMJugador.ALTA)) prepararAlta();
		else prepararBajaModificacion();
	}

    private void prepararAlta() {
		idJugador.clear(); nombreJugador.clear();dniJugador.clear();emailJugador.clear();fechaNacJugador.setValue(null);
		botonAgregar.setDisable(false);botonEditar.setDisable(true);botonEliminar.setDisable(true);
	}

	private void prepararBajaModificacion() {
		idJugador.setText(controller.getSeleccionado().getID().toString());
		nombreJugador.setText(controller.getSeleccionado().getNombre());
		dniJugador.setText(controller.getSeleccionado().getDni());
		emailJugador.setText(controller.getSeleccionado().getEmail());
		fechaNacJugador.setValue(controller.getSeleccionado().getFechaNac());
		botonAgregar.setDisable(true);botonEditar.setDisable(false);botonEliminar.setDisable(false);
	}

	@FXML
    void agregarJugador(ActionEvent event) {
    	String nombre = nombreJugador.getText();
    	String dni = dniJugador.getText();
    	if(!nombre.equals("")) {
    		Jugador nuevo = new Jugador();nuevo.setNombre(nombre);nuevo.setDni(dni);nuevo.setEmail(emailJugador.getText());nuevo.setFechaNac(fechaNacJugador.getValue());    		
    		controller.altaJugador(nuevo);
    	}
    	mostrarMensaje("Se agrego el jugador: "+nombre);
    	prepararBajaModificacion();
    }

    @FXML
    void editarJugador(ActionEvent event) {
    	Jugador aEditar = controller.getSeleccionado();
    	aEditar.setNombre(nombreJugador.getText());
    	aEditar.setDni(dniJugador.getText());
    	aEditar.setEmail(emailJugador.getText());
    	aEditar.setFechaNac(fechaNacJugador.getValue());
    	controller.modificacionJugador(aEditar);
    	mostrarMensaje("Se modfico el jugador: "+aEditar.getNombre());
    	prepararBajaModificacion();
    }

    @FXML
    void eliminarJugador(ActionEvent event) {
    	controller.eliminarJguador(controller.getSeleccionado().getID());
    	mostrarMensaje("Se elimino el jugador");
    	prepararAlta();
    }
    
    void mostrarMensaje(String mensaje) {
    	GeneralPresenter principal = (GeneralPresenter) CoordinadorVistas.getInstance().getView("principal").getPresenter();
    	principal.actualizarBarraEstado(mensaje);
    }
	
	

}
