package rest;

import java.util.UUID;
import java.util.Date;


public class DataLocation {
    
    //Attributes for a single row written to database
    public UUID block_id;
    public Date insertion_time;
    public double lat;
    public double lon;
    public int gsm;
    public int cdma;
    public int evdo;
    public int lte;

    //default constructor. 
    //This has to call to 'super();' otherwise Maven gives error in build 
    public DataLocation(){
        super();
    }

    //Constructor we actually use to create objects of this class
    public DataLocation(UUID block_id, Date insertion_time, double lat, 
        double lon, int gsm, int cdma, int evdo, int lte) {
        this.block_id = block_id;
	    this.insertion_time = insertion_time;
        this.lat = lat;
        this.lon = lon;
        this.gsm = gsm;
        this.cdma = cdma;
        this.evdo = evdo;
        this.lte = lte;
    }

    //Getters only. 
    //We do not need setters 
    //because the values of an object will not change in objects lifetime
    //but we need to get the values of object to write them in database
    public UUID getBlock_id() {
        return block_id;
    }
    public Date getInsertion_time() {
	   return insertion_time;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }
    public int getGsm() {
        return gsm;
    }
    public int getCdma() {
        return cdma;
    }
    public int getEvdo() {
        return evdo;
    }
    public int getLte() {
        return lte;
    }

}

