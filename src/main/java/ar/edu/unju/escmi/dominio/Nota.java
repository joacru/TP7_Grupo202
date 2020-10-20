package ar.edu.unju.escmi.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
	private static int cnt = 0;
	private int orden;
	private int nota;
	private LocalDate fechaNota;
	private Materia materia;
	
	public Nota() {
		orden = ++cnt;
	}
	
	public Nota(int nota, Materia materia) {
		super();
		this.nota = nota;
		this.fechaNota = LocalDate.now();
		this.materia = materia;
	}

	public Nota(int nota, LocalDate fechaNota, Materia materia) {
		super();
		this.nota = nota;
		this.fechaNota = fechaNota;
		this.materia = materia;
	}

	public static int getCnt() {
		return cnt;
	}
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public LocalDate getFechaNota() {
		return fechaNota;
	}

	public void setFechaNota(LocalDate fechaNota) {
		this.fechaNota = fechaNota;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		String ret = "Materia: " + materia.getNombre() + "\n";
		ret += "Nota: " + nota + "\n";
		ret += "Fecha: " + formatter.format(fechaNota);
		return ret;
	}
	
	
	
}
