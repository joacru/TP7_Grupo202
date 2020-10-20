package ar.edu.unju.escmi.principal;

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

import ar.edu.unju.escmi.dominio.Alumno;
import ar.edu.unju.escmi.dominio.Materia;
import ar.edu.unju.escmi.dominio.Nota;

public class Principal {

	static Map<Long, Alumno> alumnos = new HashMap<>();
	static List<Materia> materias = new ArrayList<>();
	
	public static void main(String[] args) {
		inicializarDatos();
		while(true) {
			menu();
		}
	}
	
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int op;
		System.out.println("----------------------------------- Menú -----------------------------------");
		System.out.println("1. Dar de alta a un alumno");
		System.out.println("2. Buscar a un alumno para asignar nota");
		System.out.println("3. Buscar un alumno y mostrar su promedio y nota más alta por materia");
		System.out.println("4. Mostrar lista de alumnos con sus respectivas notas");
		System.out.println("5. Mostrar lista de materias de un curso");
		System.out.println("6. Salir");
		System.out.print("Opción: ");
		op = sc.nextInt();
		switch(op) {
		case 1: {
			String nombre, apellido;
			int dia, mes, anyo, curso, index;
			Long dni;
			LocalDate fechaNacimiento;
			List<String> cursos = Alumno.obtenerCursos();
			System.out.print("Ingrese el DNI del alumno: ");
			dni = sc.nextLong();
			sc.nextLine();
			System.out.print("Ingrese el nombre del alumno: ");
			nombre = sc.nextLine();
			System.out.print("Ingrese el apellido del alumno: ");
			apellido = sc.nextLine();
			System.out.print("Ingrese el año de nacimiento: ");
			anyo = sc.nextInt();
			System.out.print("Ingrese el dia de nacimiento: ");
			dia = sc.nextInt();
			System.out.print("Ingrese el mes de nacimiento: ");
			mes = sc.nextInt();
			fechaNacimiento = LocalDate.of(anyo, mes, dia);
			index = 1;
			for(String c : cursos) {
				System.out.println("" + index + ". " + c);
				index++;
			}
			System.out.print("Ingrese el número de curso correspondiente: ");
			curso = sc.nextInt();
			if(anadirAlumno(new Alumno(nombre, apellido, dni, fechaNacimiento, curso))) {
				System.out.println("El alumno ha sido agregado con éxito.");
			} else {
				System.out.println("Ya existe un alumno con el mismo DNI.");
			}
			break;
		}
		case 2: {
			int materiaIndex, index, valorNota;
			Long dni;
			System.out.print("Ingrese el DNI del alumno: ");
			dni = sc.nextLong();
			Alumno alumno = buscarAlumno(dni);
			if(alumno == null) {
				System.out.println("No se ha encontrado un alumno con ese DNI.");
				break;
			}
			index = 1;
			for(Materia m : materias) {
				System.out.println("" + index + ". " + m.getNombre());
				index++;
			}
			System.out.print("Ingrese el número de materia correspondiente: ");
			materiaIndex = sc.nextInt() - 1;
			Materia materia = materias.get(materiaIndex);
			System.out.print("Ingrese la nota: ");
			valorNota = sc.nextInt();
			Nota nota = new Nota(valorNota, LocalDate.now(), materia);
			alumno.anadirNota(nota);
			break;
		}
		case 3: {
			Long dni;
			System.out.print("Ingrese el DNI del alumno: ");
			dni = sc.nextLong();
			Alumno alumno = buscarAlumno(dni);
			if(alumno == null) {
				System.out.println("No se ha encontrado un alumno con ese DNI.");
				break;
			}
			System.out.println("Apellido y nombre: " + alumno.getApellido() + ", " + alumno.getNombre());
			System.out.println("Promedio general: " + alumno.obtenerPromedio());
			Map<Materia, Integer> notasMasAltas = alumno.notasMasAltas();
			for(Map.Entry<Materia, Integer> entry : notasMasAltas.entrySet()) {
				System.out.println("Materia: " + entry.getKey().getNombre());
				System.out.println("Nota: " + entry.getValue());
			}
			break;
		}
		case 4:
			System.out.println();
			for(Map.Entry<Long, Alumno> entry : alumnos.entrySet()) {
				System.out.println("DNI: " + entry.getValue().getDni());
				System.out.print("Apellido y nombre: " + entry.getValue().getApellido() + ", ");
				System.out.println(entry.getValue().getNombre());
				for(Nota nota : entry.getValue().getNotas()) {
					System.out.println(nota);
				}
				System.out.println("----------");
			}
			break;
		case 5:
			int curso, index = 1;
			List<String> cursos = Alumno.obtenerCursos();
			index = 1;
			for(String c : cursos) {
				System.out.println("" + index + ". " + c);
				index++;
			}
			System.out.print("Ingrese el número de curso correspondiente: ");
			curso = sc.nextInt();
			for(Materia materia : materias) {
				if(materia.getCurso() != curso) continue;
				System.out.println(materia);
			}
			break;
		case 6:
			System.exit(0);
		default:
			System.out.println("La opción ingresada no es válida.");
			break;
		}
	}
	
	private static Alumno buscarAlumno(Long dni) {
		try {
			Alumno ret = alumnos.get(dni);
			return ret;
		} catch(Exception e) {
			return null;
		}
	}
	
	private static boolean anadirAlumno(Alumno alumno) {
		if(alumnos.get(alumno.getDni()) == null) {
			alumnos.put(alumno.getDni(), alumno);
			return true;
		}
		return false;
	}
	
	private static void inicializarDatos() {
		anadirAlumno(new Alumno("Joaquín", "Cruz", 12345678, LocalDate.of(2001, 05, 15), 5));
		anadirAlumno(new Alumno("Tomás", "Cruz", 23146568, LocalDate.of(2001, 05, 15), 5));
		anadirAlumno(new Alumno("Atilio", "Grafión", 87654321, LocalDate.of(2002, 05, 15), 5));
		anadirAlumno(new Alumno("Nicolás", "Oviedo", 78541354, LocalDate.of(2002, 05, 15), 5));
		materias.add(new Materia(1001, "Programación Orientada a Objetos", Materia.CUATRI, Materia.TERCERO));
		materias.add(new Materia(1002, "Programación Visual", Materia.CUATRI, Materia.TERCERO));
		materias.add(new Materia(1003, "Estructuras de datos", Materia.ANUAL, Materia.SEGUNDO));
		Long k;
		Alumno alumno;
		k = (long) 12345678;
		alumno = alumnos.get(k);
		alumno.anadirNota(new Nota(9, materias.get(0)));
		alumno.anadirNota(new Nota(8, materias.get(0)));
		alumno.anadirNota(new Nota(9, materias.get(0)));
		alumno.anadirNota(new Nota(7, materias.get(1)));
		alumno.anadirNota(new Nota(8, materias.get(1)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
		k = (long) 23146568;
		alumno = alumnos.get(k);
		alumno.anadirNota(new Nota(9, materias.get(0)));
		alumno.anadirNota(new Nota(8, materias.get(1)));
		alumno.anadirNota(new Nota(9, materias.get(1)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
		alumno.anadirNota(new Nota(8, materias.get(2)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
		k = (long) 87654321;
		alumno = alumnos.get(k);
		alumno.anadirNota(new Nota(9, materias.get(0)));
		alumno.anadirNota(new Nota(8, materias.get(0)));
		alumno.anadirNota(new Nota(9, materias.get(1)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
		alumno.anadirNota(new Nota(8, materias.get(2)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
		k = (long) 78541354;
		alumno = alumnos.get(k);
		alumno.anadirNota(new Nota(9, materias.get(0)));
		alumno.anadirNota(new Nota(8, materias.get(1)));
		alumno.anadirNota(new Nota(9, materias.get(1)));
		alumno.anadirNota(new Nota(7, materias.get(1)));
		alumno.anadirNota(new Nota(8, materias.get(2)));
		alumno.anadirNota(new Nota(7, materias.get(2)));
	}

}
