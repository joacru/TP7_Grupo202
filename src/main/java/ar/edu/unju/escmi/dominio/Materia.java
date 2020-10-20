package ar.edu.unju.escmi.dominio;

public class Materia {
	public static final int CUATRI = 1, ANUAL = 2;
	public static final int PRIMERO = 1, SEGUNDO = 2, TERCERO = 3, CUARTO = 4, QUINTO = 5;
	private int codigo;
	private String nombre;
	private int tipo;
	private int curso;
	
	public Materia() {
	}

	public Materia(int codigo, String nombre, int tipo, int curso) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.curso = curso;
	}

	public String obtenerTipo() {
		String ret = "";
		switch(tipo) {
		case CUATRI:
			ret = "Cuatrimestral";
			break;
		case ANUAL:
			ret = "Anual";
			break;
		default:
			ret = "No válido";
			break;
		}
		return ret;
	}
	
	public String obtenerCurso() {
		String ret = "";
		switch(curso) {
		case PRIMERO:
			ret = "Primero";
			break;
		case SEGUNDO:
			ret = "Segundo";
			break;
		case TERCERO:
			ret = "Tercero";
			break;
		case CUARTO:
			ret = "Cuarto";
			break;
		case QUINTO:
			ret = "Quinto";
			break;
		default:
			ret = "No válido";
			break;
		}
		return ret;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		String ret = "Código: " + codigo + "\n";
		ret += "Nombre: " + nombre + "\n";
		ret += "Tipo: " + obtenerTipo() + "\n";
		ret += "Curso: " + obtenerCurso();
		return ret;
	}
	
}
