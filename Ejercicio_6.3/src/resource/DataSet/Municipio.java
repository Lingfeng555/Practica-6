package resource.DataSet;
/** Permite crear objetos municipio con información de población, provincia y comunidad autónoma
 */
public class Municipio implements Comparable {
	private int codigo;
	private String nombre;
	private int habitantes;
	private String provincia;
	private String autonomia;

	/** Crea un municipio
	 * @param codigo	Código único del municipio (1-n)
	 * @param nombre	Nombre oficial
	 * @param habitantes	Número de habitantes
	 * @param provincia	Nombre de su provincia
	 * @param autonomia	Nombre de su comunidad autónoma
	 */
	public Municipio(int codigo, String nombre, int habitantes, String provincia, String autonomia) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.provincia = provincia;
		this.autonomia = autonomia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(String autonomia) {
		this.autonomia = autonomia;
	}

	@Override
	public String toString() {
		return "[" + codigo + "] " + nombre + ", " + habitantes + " en " + provincia + " (" + autonomia + ")";
	}

	public boolean isEmpty(){
		if(
			habitantes == 0 &&
			provincia == null &&
			autonomia == null &&
			nombre == null &&
			codigo == 0
			)
			{return true;}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + habitantes;
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((autonomia == null) ? 0 : autonomia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		if (codigo != other.codigo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (habitantes != other.habitantes)
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (autonomia == null) {
			if (other.autonomia != null)
				return false;
		} else if (!autonomia.equals(other.autonomia))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		Municipio municipio = (Municipio)o;
		return this.nombre.compareTo(municipio.getNombre());
	}
	
}
