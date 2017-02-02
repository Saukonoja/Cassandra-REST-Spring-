package rest;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import java.util.UUID;
import java.util.Date;

public class CassieConnector{
	Session session = null;
	public CassieConnector(){
		this("84.251.189.202");
	}

	public CassieConnector(String addr){
		Cluster cluster = new Cluster.Builder().
				addContactPoint(addr).build();
		this.session = cluster.connect("android");
	}

	public ResultSet selectCassie(String cql){
		ResultSet results = null;
	    try{
		results = session.execute(cql);
                return results;
	    }catch (NoHostAvailableException e){
		System.out.println(e.getErrors());
                return results;
	    }
	
	}

	public void insertCassie(String table, UUID uuid, 
		double lat, double lon, int gsm, int cdma, int evdo, int lte){
		    try{
		        session.execute("INSERT INTO " + table +
		                "(block_id, insertion_time, lat, lon, gsm, cdma, evdo, lts) VALUES (" + 
		                uuid + ", dateof(now()), " + lat + ", " + lon + ", " + gsm + ", " + 
		                cdma + ", " + evdo + ", " + lte + ");" );
		    }catch (NoHostAvailableException e){
		        System.out.println(e.getErrors());
		    }
		}
}
