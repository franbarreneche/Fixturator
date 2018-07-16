package launcher;

import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.views.FXMLView;

import presentation.equipo.EquipoView;
import presentation.general.GeneralView;
import presentation.jugador.JugadorView;
import presentation.menu.MenuView;
import presentation.torneo.TorneoView;

public class CoordinadorVistas {
	//singleton
	private static CoordinadorVistas instancia = new CoordinadorVistas();
	
	//variables del programador
    private Map<String,FXMLView> vistas;
	
	private CoordinadorVistas() {
		vistas = new HashMap<String,FXMLView>();
		//agregamos las vistas al mapeo	
		GeneralView appView = new GeneralView();
		vistas.put("principal", appView);
		MenuView menu = new MenuView();
		vistas.put("menu", menu);
		JugadorView abmjugador = new JugadorView();
		vistas.put("abmjugador", abmjugador);
		EquipoView abmequipo = new EquipoView();
		vistas.put("abmequipo", abmequipo);
		TorneoView abmtorneo = new TorneoView();
		vistas.put("abmtorneo", abmtorneo);
	}
	
	public static CoordinadorVistas getInstance() {
		return instancia;
	}
	
	public FXMLView getView(String key) {
		return vistas.get(key);
	}
    
    
}
