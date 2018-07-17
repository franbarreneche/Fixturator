package presentation.seleccionTorneo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import controller.ServicioABMJugador;
import controller.ServicioSeleccionTorneo;
import controller.Sistema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import launcher.CoordinadorVistas;
import model.Torneo;

public class SeleccionTorneoPresenter implements Initializable {

    @FXML
    private TreeView<Torneo> arbolTorneos;
    
    //variables del usuario
    protected ServicioSeleccionTorneo controller;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//pedirle al singleton Sistema que me de el servicio abm jugador
    	controller = (ServicioSeleccionTorneo) Sistema.getInstance().getServicio("selecciontorneo");
    	
		//poblamos los elementos de la gui
    	TreeItem<Torneo> raiz = new TreeItem<Torneo>();
    	
    	List<Torneo> torneos = controller.getTodosTorneos();
    	/*for(Integer i:getListaAnios(torneos)) {
    		raiz.getChildren().add(new TreeItem<Torneo>(new Torneo(i.toString())));
    	}
    	for(TreeItem<Torneo> item:raiz.getChildren()) {
			item.getChildren().addAll(getTorneosAnio(torneos,Integer.parseInt(item.getValue().getNombre())));
		}*/
    	
    	List<Integer> listaAnios = getListaAnios(torneos);
    	//agregamos los anios al arbol
    	for(Integer i:listaAnios) {
    		TreeItem<Torneo> nodoAnio = new TreeItem<Torneo>(new Torneo(i.toString()));
    		raiz.getChildren().add(nodoAnio);
    		List<Torneo> torneosAnio = getTorneosEnAnio(torneos,i.intValue());
    		List<String> listaDias = getListaDias(torneosAnio);
    		for(String dia:listaDias) {
    			TreeItem<Torneo> nodoDia = new TreeItem<Torneo>(new Torneo(dia));
    			List<Torneo> torneosAnioDia = getTorneosEnDia(torneosAnio,dia);
    			nodoDia.getChildren().addAll(listaNodos(torneosAnioDia));
    			nodoAnio.getChildren().add(nodoDia);
    		}
    	}
    	
    	 	
    	
    	
    	this.arbolTorneos.setRoot(raiz);
    	this.arbolTorneos.setShowRoot(false);
    	//TreeItem<Torneo> reciente = this.arbolTorneos.getRoot().getChildren().get(0);
    	//if(reciente!=null) this.arbolTorneos.getRoot().getChildren().get(0).setExpanded(true);
	}
    
    private List<Torneo> getTorneosEnAnio(List<Torneo> torneos,int anio){
    	List<Torneo> aRet = new ArrayList<Torneo>();
    	for(Torneo t:torneos) {
    		if(t.getAnio()==anio) aRet.add(t);
    	}
    	return aRet;
    }
    
    private List<Torneo> getTorneosEnDia(List<Torneo> torneos, String dia) {
    	List<Torneo> aRet = new ArrayList<Torneo>();
    	for(Torneo t:torneos) {
    		if(t.getDiaJuego().equals(dia)) aRet.add(t);
    	}
    	return aRet;
    }
    
    private TreeItem<Torneo> armarNodo(List<TreeItem<Torneo>> hijos,String nombre) {
    	TreeItem<Torneo> aRet = new TreeItem<Torneo>(new Torneo(nombre));
    	aRet.getChildren().addAll(hijos);
    	return aRet;
    }
    
    private List<TreeItem<Torneo>> listaNodos(List<Torneo> lista) {
    	List<TreeItem<Torneo>> aRet = new ArrayList<TreeItem<Torneo>>();
    	for(Torneo t:lista) aRet.add(new TreeItem<Torneo>(t));
    	return aRet;
    }
    
    private List<Integer> getListaAnios(List<Torneo> lista) {
    	List<Integer> aRet = new ArrayList<Integer>();
    	for(Torneo t: lista) {
    		if(t.getAnio()!=0 && !aRet.contains(t.getAnio())) 
    			aRet.add(t.getAnio());
    	}
    	Collections.sort(aRet, Collections.reverseOrder());
    	return aRet;
    }
    
    private List<String> getListaTemporadas(List<Torneo> lista) {
    	List<String> aRet = new ArrayList<String>();
    	for(Torneo t:lista) {
    		if(!aRet.contains(t.getTemporada())) aRet.add(t.getTemporada());
    	}
    	return aRet;
    }
    
    private List<String> getListaDias(List<Torneo> lista) {
    	List<String> aRet = new ArrayList<String>();
    	for(Torneo t:lista) {
    		if(!aRet.contains(t.getDiaJuego())) aRet.add(t.getDiaJuego());
    	}
    	return aRet;
    }
    
    private List<TreeItem<Torneo>> getTorneosAnio(List<Torneo> torneos, int anio) {
    	List<TreeItem<Torneo>> aRet = new ArrayList<TreeItem<Torneo>>();
    	for(Torneo t: torneos) {
    		if(t.getAnio()==anio) aRet.add(new TreeItem<Torneo>(t));
    	}
    	return aRet;
    }

    @FXML
    void verTorneo(ActionEvent event) {
    	TreeItem<Torneo> nodo = this.arbolTorneos.getSelectionModel().getSelectedItem();
    	
    	if(nodo.isLeaf()) {
    		controller.administrarTorneo(nodo.getValue());
    		CoordinadorVistas.getInstance().irVistaadministrarTorneo();
    	}
    		
    	
    }

	

}
