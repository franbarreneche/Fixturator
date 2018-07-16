package dao;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;

public class CrudServiceBean<T> implements CrudService<T>{
	//datastore
	private Datastore almacenamiento;
	
	public CrudServiceBean() {
		this.almacenamiento = DBconnection.getInstance().getDatstore();
	}

	@Override
	public T create(T t) {
		almacenamiento.save(t);
		return t;
	}

	@Override
	public T find(Class type, Object id) {
		T entity = (T) almacenamiento.get(type,id);
		return entity;		
	}

	@Override
	public T update(T t) {
		almacenamiento.save(t);
		return t;
	}

	@Override
	public void delete(Class type, Object id) {
		almacenamiento.delete(type, id);
	}

	@Override
	public List findWithNamedQuery(String queryName) {
		return null;
	}

	@Override
	public List findWithNamedQuery(String queryName, int resultLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Class type) {
		return almacenamiento.createQuery(type).asList();
	}

	

}
