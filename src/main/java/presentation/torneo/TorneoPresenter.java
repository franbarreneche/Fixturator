package presentation.torneo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.ServicioABMEquipo;
import controller.ServicioABMTorneo;
import controller.Sistema;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import launcher.CoordinadorVistas;
import model.Equipo;
import model.Jugador;
import model.Partido;
import model.Torneo;
import presentation.general.GeneralPresenter;

public class TorneoPresenter implements Initializable{

    @FXML
    private Label labelID;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textSede;

    @FXML
    private ListView<Equipo> listaEquiposTorneo;

    @FXML
    private Button botonDesregistrarEquipo;

    @FXML
    private Button botonAgregarTorneo;

    @FXML
    private Button botonEditarTorneo;

    @FXML
    private Button botonEliminarTorneo;

    @FXML
    private TextField textBuscar;

    @FXML
    private ListView<Equipo> listaEquiposTotal;

    @FXML
    private Button botonRegistrarEquipo;

    @FXML
    private ListView<Partido> listaPartidos;

    @FXML
    private Button botonEliminarPartido;
    
    @FXML
    private TextField textAnio;

    @FXML
    private ChoiceBox<String> comboDia;
    
    @FXML
    private VBox panelListaPartidos;
    
    @FXML
    private VBox panelListaEquipos;
    
    @FXML
    private VBox panelEquiposTorneo;
    
    //variables del programador
    protected ServicioABMTorneo controller;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//pedirle al singleton Sistema que me de el servicio abm jugador
    	controller = (ServicioABMTorneo) Sistema.getInstance().getServicio("abmtorneo");
    	
		if(controller.getSeleccionado()==null) prepararAlta();
		else prepararBajaModificacion();
		
		String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		this.comboDia.setItems(FXCollections.observableArrayList(dias));
	}

    private void prepararBajaModificacion() {
    	Torneo t = controller.getSeleccionado();
    	this.labelID.setText(t.getId().toString());
		this.textNombre.setText(t.getNombre());;
		this.textSede.setText(t.getSede());
		this.botonAgregarTorneo.setDisable(true);
		this.botonEditarTorneo.setDisable(false);
		this.botonEliminarTorneo.setDisable(false);
		this.panelEquiposTorneo.setDisable(false);
		this.panelListaEquipos.setDisable(false);
		this.panelListaPartidos.setDisable(false);
		
		this.listaEquiposTorneo.setItems(FXCollections.observableArrayList(t.getEquipos()));
		this.listaEquiposTotal.setItems(FXCollections.observableArrayList(controller.getTodosEquipos()));
		this.listaPartidos.setItems(FXCollections.observableArrayList(t.getPartidos()));
    }

	private void prepararAlta() {
		this.labelID.setText("");
		this.textNombre.clear();
		this.textSede.clear();
		this.botonAgregarTorneo.setDisable(false);
		this.botonEditarTorneo.setDisable(true);
		this.botonEliminarTorneo.setDisable(true);
		this.panelEquiposTorneo.setDisable(true);
		this.panelListaEquipos.setDisable(true);
		this.panelListaPartidos.setDisable(true);
	}

	@FXML
    void agregarTorneo(ActionEvent event) {
		String nombre = this.textNombre.getText();
		String sede = this.textSede.getText();
		Torneo nuevo = new Torneo(); nuevo.setNombre(nombre);nuevo.setSede(sede);
		controller.altaTorneo(nuevo);
		mostrarMensaje("El torneo "+nombre+" ha sido agregado exitosamente.");
		prepararBajaModificacion();
    }

   
    @FXML
    void editarTorneo(ActionEvent event) {
    	Torneo t = controller.getSeleccionado();
    	t.setNombre(this.textNombre.getText());
    	t.setSede(this.textSede.getText());
    	controller.modificacionTorneo(t);
    	mostrarMensaje("El torneo "+t.getNombre()+" ha sido modificado exitosamente.");
		prepararBajaModificacion();
    }

    @FXML
    void eliminarPartido(ActionEvent event) {
    	//TODO
    }

    @FXML
    void eliminarTorneo(ActionEvent event) {
    	controller.eliminarTorneo(controller.getSeleccionado().getId());
    	mostrarMensaje("El torneo ha sido eliminado correctamente.");
    	prepararAlta();
    }

    @FXML
    void filtrarLista(ActionEvent event) {
    	List<Equipo> filtrada = new ArrayList<Equipo>();
    	for(Equipo e:controller.getTodosEquipos()) {
    		String filtro = this.textBuscar.getText().toLowerCase();
    		if(e.getNombre().toLowerCase().contains(filtro))
    			filtrada.add(e);
    	}
    	this.listaEquiposTotal.setItems(FXCollections.observableArrayList(filtrada));
    }

    @FXML
    void registrarEquipo(ActionEvent event) {
    	Equipo agregar = this.listaEquiposTotal.getSelectionModel().getSelectedItem();
    	if(agregar!=null) {
    		controller.registrarEqupipo(agregar);
	    	mostrarMensaje("El equipo "+agregar.getNombre()+" fue registrado al Torneo.");
	    	prepararBajaModificacion();
    	}
    }
    
    @FXML
    void desregistrarEquipo(ActionEvent event) {
    	Equipo eliminar = this.listaEquiposTorneo.getSelectionModel().getSelectedItem();
    	if(eliminar!=null) {
    		controller.desregistrarEquipo(eliminar);
	    	mostrarMensaje("El equipo "+eliminar.getNombre()+" fue desregistrado del Torneo.");
	    	prepararBajaModificacion();
    	}
    }
    
    private void mostrarMensaje(String mensaje) {
    	GeneralPresenter principal = (GeneralPresenter) CoordinadorVistas.getInstance().getView("principal").getPresenter();
    	principal.actualizarBarraEstado(mensaje);
    }

	

}

