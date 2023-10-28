package resource.DataSet;

import java.io.*;
import java.util.*;

public class DataSetMunicipios {
	
	private static DataSetMunicipios dataSetMunicipios;
	private List<Municipio> lMunicipios = new ArrayList<Municipio>();
	private static Hashtable<String, Hashtable<String, TreeSet<Municipio>>> municipiosHashTable;
	private static Hashtable<String, Hashtable<String, Hashtable<String, Municipio>>> municipiosHashTableForTree;
	private int poblacionTotal = 0;

	/** Crea un nuevo dataset de municipios, cargando los datos desde el fichero indicado
	 * @param nombreFichero	Nombre de fichero o recurso en formato de texto. En cada línea debe incluir los datos de un municipio <br>
	 * separados por tabulador: código nombre habitantes provincia autonomía
	 * @throws IOException	Si hay error en la lectura del fichero
	 */
	public DataSetMunicipios( String nombreFichero ) throws IOException {

		municipiosHashTable = new Hashtable<>();
		municipiosHashTableForTree = new Hashtable<>();
		File ficMunicipios = new File( nombreFichero );
		loadDataSet(ficMunicipios);
		//System.out.println(municipiosHashTableForTree);
		dataSetMunicipios = this;
	}
	
	/** Devuelve la lista de municipios
	 * @return	Lista de municipios
	 */
	public List<Municipio> getListaMunicipios() {
		return lMunicipios;
	}
	
	/** Añade un municipio al final
	 * @param muni	Municipio a añadir
	 */
	public void anyadir( Municipio muni ) {
		lMunicipios.add( muni );
	}
	
	/** Añade un municipio en un punto dado
	 * @param muni	Municipio a añadir
	 * @param posicion	Posición relativa del municipio a añadir (de 0 a n)
	 */
	public void anyadir( Municipio muni, int posicion ) {
		lMunicipios.add( posicion, muni );
	}
	
	/** Quita un municipio
	 * @param codigoMuni	Código del municipio a eliminar
	 */
	public void quitar( int codigoMuni ) {
		for (int i=0; i<lMunicipios.size(); i++) {
			if (lMunicipios.get(i).getCodigo() == codigoMuni) {
				lMunicipios.remove(i);
				return;
			}
		}
	}
	
	/** Cargar un archivo de municipios
	 * 
	 */
	private void loadDataSet(File file){
		Municipio municipio;
		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] data;

			while( (data = reader.readLine().split("\t")) != null ){

				for(int i = 0; i < data.length; i++){
					data[i] = removeSpaces(data[i]);
				}

				int codigo = 0;		
				String muni = ""; 		
				int poblacion = 0; 		
				String provincia = ""; 		
				String autonomia = ""; 	

				try{
					 codigo = Integer.valueOf(data[0]);
					 muni = data[1];
					 poblacion = Integer.valueOf(data[2]);
					 provincia = data[3];
					 autonomia = data[4];
				}catch(java.lang.ArrayIndexOutOfBoundsException e){
					autonomia = data[3];
				}

				municipio = new Municipio(codigo, muni, poblacion, provincia, autonomia);
				lMunicipios.add(municipio);
				addToHashMap(municipio);
			}
			reader.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		System.out.println (poblacionTotal);
	}

	private String removeSpaces(String str){
		String result = "";
		for (int i = 0; i < str.length(); i++){
			if(str.charAt(i) != ' ' ){
				result += str.charAt(i);
			}
		}
		return result;
	}

	private void addToHashMap(Municipio municipio){
		if(!municipiosHashTable.containsKey(municipio.getAutonomia())){ //Miramos si hay hashmap de esa Autonomia
			municipiosHashTable.put(municipio.getAutonomia(), new Hashtable<>());
			municipiosHashTableForTree.put(municipio.getAutonomia(), new Hashtable<>());
		}
		if(!municipiosHashTable.get(municipio.getAutonomia()).containsKey(municipio.getProvincia())){ //Miramos si hay hashmap de esa provincia dentro de esa autonomia
			municipiosHashTable.get(municipio.getAutonomia()).put(municipio.getProvincia(), new TreeSet<>());
			municipiosHashTableForTree.get(municipio.getAutonomia()).put(municipio.getProvincia(), new Hashtable<>());
		}
		municipiosHashTable.get(municipio.getAutonomia()).get(municipio.getProvincia()).add(municipio);
		municipiosHashTableForTree.get(municipio.getAutonomia()).get(municipio.getProvincia()).put(municipio.getNombre(), municipio);
		addToPoblacionTotal(municipio.getHabitantes());
	}
	
	public Hashtable<String, Hashtable<String, TreeSet<Municipio>>> getmunicipiosHashTable(){
		return municipiosHashTable;
	}

	public Hashtable<String, TreeSet<Municipio>> getAutonomiaHasTable(String autonomia){
		return municipiosHashTable.get(autonomia);
	}

	public TreeSet<Municipio> getProvinciaHashTable(String autonomia, String provincia){
		return municipiosHashTable.get(autonomia).get(provincia);
	}

	public static DataSetMunicipios getDataSetMunicipios(){
		return dataSetMunicipios;
	}

	public Hashtable<String, Hashtable<String, Hashtable<String, Municipio>>> getHashTableForTree(){
		return municipiosHashTableForTree;
	}

	public void removeMunicipio(Municipio municipio){
		municipiosHashTable.get(municipio.getAutonomia()).get(municipio.getProvincia()).remove(municipio);
		municipiosHashTableForTree.get(municipio.getAutonomia()).get(municipio.getProvincia()).remove(municipio.getNombre());
		poblacionTotal -= municipio.getHabitantes();
	}
	
	public void addMunicipio(Municipio municipio){
		addToHashMap(municipio);
	}

	public int getPoblacionTotal() {
		//System.out.println("Devolviendo poblacionTotal de España: " + poblacionTotal);
		return poblacionTotal;
	}

	public int getAutonomiaPoblacion(String autonomiaNombre){
		int result = 0;
		for(String provinciaNombre: municipiosHashTable.get(autonomiaNombre).keySet()){
			for(Municipio municipio: municipiosHashTable.get(autonomiaNombre).get(provinciaNombre)){
				result+= municipio.getHabitantes();
			}
		}
		return result;
	}

	public int getProvinciaPoblacion(String autonomiaNombre, String provinciaNombre){
		System.out.println("De la autonomia: "+ autonomiaNombre + "; en la provincia: " +provinciaNombre);
		int result = 0;
		for(Municipio municipio: municipiosHashTable.get(autonomiaNombre).get(provinciaNombre)){
			result+=municipio.getHabitantes();
		}
		return result;
	}

	public void addToPoblacionTotal(int poblacion){
		this.poblacionTotal += poblacion;
	}

}
