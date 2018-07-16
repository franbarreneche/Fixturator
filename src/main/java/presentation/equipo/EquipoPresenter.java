package presentation.equipo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ServicioABMEquipo;
import controller.ServicioABMJugador;
import controller.Sistema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import launcher.CoordinadorVistas;
import model.Equipo;
import model.Jugador;
import presentation.general.GeneralPresenter;

public class EquipoPresenter implements Initializable {
	
		@FXML
	    private Label labelID;
	
	    @FXML
	    private TextField textNombre;
	
	    @FXML
	    private Label labelCapitan;
	
	    @FXML
	    private Button botonDesregistrar;
	
	    @FXML
	    private Button botonCapitan;
	
	    @FXML
	    private Button botonAgregar;
	
	    @FXML
	    private Button botonEditar;
	
	    @FXML
	    private Button botonEliminar;
	
	    @FXML
	    private TextField textBuscar;
	
	    @FXML
	    private Button botonRegistrar;

	    @FXML
	    private ListView<Jugador> listaJugadoresEquipo;

	    @FXML
	    private ListView<Jugador> listaJugadoresTotal;
	    
	    //variables del programador
	    protected ServicioABMEquipo controller;
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	//pedirle al singleton Sistema que me de el servicio abm jugador
	    	controller = (ServicioABMEquipo) Sistema.getInstance().getServicio("abmequipo");
	    	
			if(controller.getSeleccionado()==null) prepararAlta();
			else prepararBajaModificacion();
			
			listaJugadoresTotal.setItems(FXCollections.observableList(controller.getTodosJugadores()));
		}

	    private void prepararBajaModificacion() {
			this.labelID.setText(controller.getSeleccionado().getId().toString());
			this.textNombre.setText(controller.getSeleccionado().getNombre());
			if(controller.getSeleccionado().getCapitan()!=null)
				this.labelCapitan.setText(controller.getSeleccionado().getCapitan().getNombre());
			else this.labelCapitan.setText("");
			this.listaJugadoresEquipo.setItems(FXCollections.observableArrayList(controller.getSeleccionado().getJugadores()));
			this.listaJugadoresTotal.setItems(FXCollections.observableArrayList(controller.getTodosJugadores()));
		}

		private void prepararAlta() {
			this.labelID.setText("");
			this.textNombre.clear();
			this.labelCapitan.setText("");
			this.listaJugadoresEquipo.setItems(FXCollections.observableArrayList());
			this.listaJugadoresTotal.setItems(FXCollections.observableArrayList(controller.getTodosJugadores()));
		}

		@FXML
	    void agregarEquipo(ActionEvent event) {
			String nombre = this.textNombre.getText();
			Equipo e = new Equipo(nombre);
			e.setJugadores(this.listaJugadoresEquipo.getItems());
			controller.altaEquipo(e);
			mostrarMensaje("Se agrego el equipo: "+nombre);
			prepararBajaModificacion();
	    }

	    @FXML
	    void editarEquipo(ActionEvent event) {
	    	Equipo aMod = controller.getSeleccionado();
	    	aMod.setNombre(this.textNombre.getText());
	    	controller.modificacionEquipo(aMod);
	    	mostrarMensaje("Se modificó el equipo: "+aMod.getNombre());
	    	prepararBajaModificacion();
	    }

	    @FXML
	    void eliminarEquipo(ActionEvent event) {
	    	controller.eliminarEquipo(controller.getSeleccionado().getId());
	    	mostrarMensaje("Se eliminó el equipo");
	    	prepararAlta();
	    }

	    @FXML
	    void mostrarDatosCapitan(MouseEvent event) {

	    }

	    @FXML
	    void registrarCapitan(ActionEvent event) {
	    	Jugador cap = this.listaJugadoresEquipo.getSelectionModel().getSelectedItem();
	    	if(cap!=null)
	    		controller.registrarCapitan(cap);
	    	prepararBajaModificacion();
	    }

	    @FXML
	    void registrarJugadorEquipo(ActionEvent event) {
	    	Jugador nuevo = this.listaJugadoresTotal.getSelectionModel().getSelectedItem();
	    	if(nuevo!=null && controller.getSeleccionado()!=null){
	    		controller.registrarJugador(nuevo);
	    		mostrarMensaje("El jugador "+nuevo.getNombre()+" fue registrado exitosamente en "+controller.getSeleccionado().getNombre());
	    		prepararBajaModificacion();
	    	}
	    	else mostrarMensaje("Error al intentar registrar jugador");	 	 
	    }
	    
	    @FXML
	    void desregistrarJugador(ActionEvent event) {
	    	Jugador eliminar = this.listaJugadoresEquipo.getSelectionModel().getSelectedItem();
	    	if(eliminar!=null) 
	    		controller.desregistrarJugador(eliminar);
	    	prepararBajaModificacion();
	    }
	    
	    @FXML
	    void filtrarLista(ActionEvent event) {
	    	List<Jugador> filtrada = new ArrayList<Jugador>();
	    	for(Jugador j:controller.getTodosJugadores()) {
	    		String filtro = this.textBuscar.getText().toLowerCase();
	    		if(j.getNombre().toLowerCase().contains(filtro))
	    			filtrada.add(j);
	    	}
	    	this.listaJugadoresTotal.setItems(FXCollections.observableArrayList(filtrada));
	    }
		
	    private void mostrarMensaje(String mensaje) {
	    	GeneralPresenter principal = (GeneralPresenter) CoordinadorVistas.getInstance().getView("principal").getPresenter();
	    	principal.actualizarBarraEstado(mensaje);
	    }
	
	    
	
}
