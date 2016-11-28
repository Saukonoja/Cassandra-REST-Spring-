package rest;

import java.util.ArrayList;
import java.util.List;

public class DataLocationList{
	private List<DataLocation> sonera = new ArrayList<DataLocation>();

	public List<DataLocation> getSonera(){
		return sonera;
	}

	public void setSonera(List<DataLocation> sonera){
		this.sonera = sonera;
	}
}
