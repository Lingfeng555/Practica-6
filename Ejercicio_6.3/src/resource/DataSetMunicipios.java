package resource;

import java.io.*;
import java.util.*;

public class DataSetMunicipios {
	
	private List<Municipio> lMunicipios = new ArrayList<Municipio>();
	HashMap<String, HashMap<String, HashMap<String, Municipio>>> municipiosHashMap;
	
	/** Crea un nuevo dataset de municipios, cargando los datos desde el fichero indicado
	 * @param nombreFichero	Nombre de fichero o recurso en formato de texto. En cada línea debe incluir los datos de un municipio <br>
	 * separados por tabulador: código nombre habitantes provincia autonomía
	 * @throws IOException	Si hay error en la lectura del fichero
	 */
	public DataSetMunicipios( String nombreFichero ) throws IOException {

		municipiosHashMap = new HashMap<>();
		File ficMunicipios = new File( nombreFichero );
		loadDataSet(ficMunicipios);
		//System.out.println(removeSpaces("65 45"));
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
	public void quitar( String codigoMuni ) {
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

				Municipio municipio = new Municipio(codigo, muni, poblacion, provincia, autonomia);
				lMunicipios.add(municipio);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if(!municipiosHashMap.containsKey(municipio.getAutonomia())){ //Miramos si hay hashmap de esa Autonomia
			municipiosHashMap.put(municipio.getAutonomia(), new HashMap<>());
		}
		if(!municipiosHashMap.get(municipio.getAutonomia()).containsKey(municipio.getProvincia())){ //Miramos si hay hashmap de esa provincia dentro de esa autonomia
			municipiosHashMap.get(municipio.getAutonomia()).put(municipio.getProvincia(), new HashMap<>());
		}
		municipiosHashMap.get(municipio.getAutonomia()).get(municipio.getProvincia()).
	}
}
