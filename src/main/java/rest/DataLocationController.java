package rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import java.util.UUID;
import java.util.Date;
import static java.util.UUID.randomUUID;


@RestController
public class DataLocationController {
CassieConnector cc = new CassieConnector();
    @RequestMapping("/test")
    public DataLocation json(@RequestParam(value="block_id", defaultValue="cfd66ccc-d857-4e90-b1e5-df98a3d40cd6") String block_id) {
        
	ResultSet result;
        result = cc.selectCassie("SELECT * FROM sonera");


        
	Row row = result.one();
        return new DataLocation(row.getUUID("block_id"),
				row.getTimestamp("insertion_time"),
				row.getDouble("lat"),
				row.getDouble("lon"),
				row.getInt("gsm"),
				row.getInt("cdma"),
				row.getInt("evdo"),
				row.getInt("lte"));
    }

    @RequestMapping("/sonera")
    public DataLocationList getSonera(){
        DataLocationList sonera = new DataLocationList();
	ResultSet result;
        result = cc.selectCassie("SELECT * FROM sonera");

	for (Row row : result) {
                DataLocation dl = new DataLocation(row.getUUID("block_id"),
                                row.getTimestamp("insertion_time"),
                                row.getDouble("lat"),
                                row.getDouble("lon"),
                                row.getInt("gsm"),
                                row.getInt("cdma"),
                                row.getInt("evdo"),
                                row.getInt("lte"));
                sonera.getSonera().add(dl);
        }
        return sonera;

    }

    @RequestMapping(value = "/signals/sonera", method = RequestMethod.POST)
    public DataLocation postSonera(@RequestBody DataLocation dl){
	cc.insertCassie("sonera", dl.getBlock_id(), dl.getLat(), dl.getLon(), 
        dl.getGsm(), dl.getCdma(), dl.getEvdo(), dl.getLte());
	return dl;
	
    }

    @RequestMapping(value = "/signals/dna", method = RequestMethod.POST)
    public DataLocation postDna(@RequestBody DataLocation dl){
	CassieConnector cc = new CassieConnector();
	cc.insertCassie("dna", dl.getBlock_id(), dl.getLat(), dl.getLon(), dl.getGsm(), dl.getCdma(), 		dl.getEvdo(), dl.getLte());
	return dl;
	
    }

    @RequestMapping(value = "/signals/saunalahti", method = RequestMethod.POST)
    public DataLocation postSaunalahti(@RequestBody DataLocation dl){
	CassieConnector cc = new CassieConnector();
	cc.insertCassie("saunalahti", dl.getBlock_id(), dl.getLat(), dl.getLon(), dl.getGsm(), dl.getCdma(), dl.getEvdo(), dl.getLte());
	return dl;
	
    }


}












