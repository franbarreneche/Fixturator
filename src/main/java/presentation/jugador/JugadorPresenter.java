package presentation.jugador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Jugador;

public class JugadorPresenter implements Initializable{
	@FXML
	private TextField nombreJugador;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void mostrarInformacion(Jugador j) {
		nombreJugador.setText(j.getNombre());
	}

}
