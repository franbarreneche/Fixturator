package presentation.general;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	    
	    @Inject
	    protected JugadorView vistaJugador;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			MenuView menu = new MenuView();
			panel_Menu.getChildren().add(menu.getViewWithoutRootContainer());
			
			//JugadorView jugador = new JugadorView();
			//panel_Detalle.getChildren().add(jugador.getViewWithoutRootContainer());
			
			//EquipoView equipo = new EquipoView();
			//panel_Principal.getChildren().add(equipo.getViewWithoutRootContainer());
			
			TorneoView torneo = new TorneoView();
			panel_Principal.getChildren().add(torneo.getViewWithoutRootContainer());
			
		}
		
		
	    @FXML
	    void mostrarJugador(ActionEvent event) {
	    	panel_Detalle.getChildren().add(vistaJugador.getViewWithoutRootContainer());
	    	JugadorPresenter presenter = (JugadorPresenter)vistaJugador.getPresenter();
	    	presenter.mostrarInformacion(new Jugador("Pepe"));
	    }
		
}
