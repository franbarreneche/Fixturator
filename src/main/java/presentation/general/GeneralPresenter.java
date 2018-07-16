package presentation.general;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.airhacks.afterburner.views.FXMLView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import launcher.CoordinadorVistas;
import model.Jugador;
import presentation.equipo.EquipoView;
import presentation.jugador.JugadorPresenter;
import presentation.jugador.JugadorView;
import presentation.menu.MenuView;
import presentation.torneo.TorneoView;

public class GeneralPresenter implements Initializable{
	    @FXML
	    private Font x1;

	    @FXML
	    private Color x2;

	    @FXML
	    private Font x3;

	    @FXML
	    private Color x4;
	    
	    @FXML
	    private AnchorPane panel_Menu;
	    
	    @FXML
	    private AnchorPane panel_Principal;
	    
	    @FXML
	    private AnchorPane panel_Detalle;
	    
	    @FXML
	    private Label labelStatus;    
	    
	    
	    
	   
		@Override
		public void initialize(URL location, ResourceBundle resources) {
						
			FXMLView vistaMenu = CoordinadorVistas.getInstance().getView("menu");
			panel_Menu.getChildren().add(vistaMenu.getViewWithoutRootContainer());
			FXMLView vistaJugador = CoordinadorVistas.getInstance().getView("abmtorneo");
			panel_Principal.getChildren().add(vistaJugador.getViewWithoutRootContainer());
			
		}
		
		public void actualizarBarraEstado(String mensaje) {
			this.labelStatus.setText(mensaje);
		}
		
		
		
	    
	    
		
}
