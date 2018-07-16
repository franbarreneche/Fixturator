package dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DBconnection {
	//instancia del Singleton
	private static DBconnection instancia = new DBconnection(); 
	
	protected Datastore datastore;
	
	
	private  DBconnection() {
		final Morphia morphia = new Morphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes
		morphia.mapPackage("modelo");

		// create the Datastore connecting to the default port on the local host
		//MongoClientURI uri = new MongoClientURI(
			//    "mongodb://franbarreneche:xander@indiana01-shard-00-00-srzvi.mongodb.net:27017,indiana01-shard-00-01-srzvi.mongodb.net:27017,indiana01-shard-00-02-srzvi.mongodb.net:27017/test?ssl=true&replicaSet=Indiana01-shard-0&authSource=admin&retryWrites=true");
		//MongoClientURI uri = new MongoClientURI("mongodb://franbarreneche:make1313@ds147030.mlab.com:47030/morphia");
		MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1:27017");
		this.datastore = morphia.createDatastore(new MongoClient(uri), "fixturator");
		datastore.ensureIndexes();
	}
	
	public static DBconnection getInstance() {
		return instancia;
	}
	
	
	public Datastore getDatstore() {
		return this.datastore;
	} 
}
