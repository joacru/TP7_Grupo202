package ar.edu.unju.escmi.dominio;

import java.time.LocalDate;
import java.util.*;

public class Alumno {
	public static final int PRIMERO = 1, SEGUNDO = 2, TERCERO = 3, CUARTO = 4, QUINTO = 5;
	private String nombre;
	private String apellido;
	private long dni;
	private LocalDate fechaNacimiento;
	private List<Nota> notas;
	private int curso;
	
	public Alumno() {
		notas = new ArrayList<>();
	}

	public Alumno(String nombre, String apellido, long dni, LocalDate fechaNacimiento, int curso) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.curso = curso;
	}
	
	public double obtenerPromedio() {
		double ret = 0;
		for(Nota nota : notas) ret += nota.getNota();
		return ret / notas.size();
	}
	
	public Map<Materia, Integer> notasMasAltas(){
		Map<Materia, Integer> ret = new HashMap<>();
		for(Nota nota : notas) {
			try {
				ret.put(nota.getMateria(), Math.max(ret.get(nota.getMateria()), nota.getNota()));
			} catch(Exception e){
				ret.put(nota.getMateria(), nota.getNota());
			}
		}
		return ret;
	}
	
	public static List<String> obtenerCursos(){
		List<String> ret = new ArrayList<>();
		ret.add("Primero");
		ret.add("Segundo");
		ret.add("Tercero");
		ret.add("Cuarto");
		ret.add("Quinto");
		return ret;
	}
	
	public void anadirNota(Nota nota) {
		notas.add(nota);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}
	
}
