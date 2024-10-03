import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class proyectofinal {


    static String nombreAlumno = null;
    static String dniAlumno = null;
    static String cursoAlumno = null;
    static int edadAlumno = -1;
    static double pc1 = -1, pc2 = -1, eParcial = -1, eFinal = -1;


    static ArrayList<Alumno> alumnosMatriculados = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Imprimir el menú
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar calificaciones");
            System.out.println("3. Calcular promedio parciales");
            System.out.println("4. Generar reportes");
            System.out.println("5. Cerrar sesión");
            System.out.print("Selecciona una opción: ");

            // Leer la opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine();

            // Procesar la opción seleccionada
            switch (opcion) {
                case 1:
                    System.out.println("Opción 1 seleccionada: Registrar estudiante");
                    registrarEstudiante(scanner);
                    break;
                case 2:
                    System.out.println("Opción 2 seleccionada: Registrar calificaciones");
                    registrarCalificaciones(scanner);
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Calcular promedio parciales");
                    calcularPromedioParciales(scanner);
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Generar reportes");
                    mostrarSubMenuReportes(scanner);
                    break;
                case 5:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println();

        } while (opcion != 5);

        scanner.close();
    }


    static class Alumno {
        String nombre;
        String dni;
        int edad;
        String curso;
        double promedio;
        boolean calificacionesIngresadas;

        Alumno(String nombre, String dni, int edad, String curso, double promedio) {
            this.nombre = nombre;
            this.dni = dni;
            this.edad = edad;
            this.curso = curso;
            this.promedio = promedio;
            this.calificacionesIngresadas = false;
        }
    }

    // Método para registrar un estudiante
    public static void registrarEstudiante(Scanner scanner) {
        System.out.print("Ingrese el nombre del alumno: ");
        nombreAlumno = scanner.nextLine();


        do {
            System.out.print("Ingrese el DNI del alumno : ");
            dniAlumno = scanner.nextLine();

            // verifica 8 caractecteres numericos de DNI

            if (dniAlumno.length() != 8 || !dniAlumno.matches("\\d+")) {
                System.out.println("Error: El DNI debe contener exactamente 8 caracteres numéricos.");
            }
        } while (dniAlumno.length() != 8 || !dniAlumno.matches("\\d+"));

        // verifica edad 2 caracteres numericos
        String edadStr;
        do {
            System.out.print("Ingrese la edad del alumno : ");
            edadStr = scanner.nextLine();


            if (edadStr.length() != 2 || !edadStr.matches("\\d+")) {
                System.out.println("Error: La edad debe tener exactamente 2 caracteres numéricos.");
            }
        } while (edadStr.length() != 2 || !edadStr.matches("\\d+"));

        edadAlumno = Integer.parseInt(edadStr);  // Convertir el string a un entero después de la validación

        System.out.print("Ingrese el curso que llevará el alumno: "); // Solicitar el curso
        cursoAlumno = scanner.nextLine();

        // Agregar el alumno a la lista (aún sin promedio)
        alumnosMatriculados.add(new Alumno(nombreAlumno, dniAlumno, edadAlumno, cursoAlumno, -1));

        // Mostrar los datos registrados del estudiante
        System.out.println("Estudiante registrado correctamente:");
        System.out.println("Nombre: " + nombreAlumno + ", DNI: " + dniAlumno + ", Edad: " + edadAlumno + ", Curso: " + cursoAlumno);
    }

    // Método para registrar calificaciones
    public static void registrarCalificaciones(Scanner scanner) {
        // Solicitar el nombre del alumno si no se han registrado calificaciones
        System.out.print("Ingrese el nombre del alumno para registrar calificaciones: ");
        String nombreBuscado = scanner.nextLine();

        // Buscar al alumno en la lista
        Alumno alumnoEncontrado = null;
        for (Alumno alumno : alumnosMatriculados) {
            if (alumno.nombre.equalsIgnoreCase(nombreBuscado)) {
                alumnoEncontrado = alumno;
                break;
            }
        }

        // Verificar si el alumno existe
        if (alumnoEncontrado == null) {
            System.out.println("Error: Alumno no encontrado.");
            return;
        }

        // Si las calificaciones aún no han sido ingresadas, solicitarlas
        if (!alumnoEncontrado.calificacionesIngresadas) {
            System.out.println("No se han registrado calificaciones para este alumno. Ingrese las calificaciones:");
            pc1 = ingresarNota(scanner, "Practica calificada 1");
            pc2 = ingresarNota(scanner, "Practica calificada 2");
            eParcial = ingresarNota(scanner, "Examen parcial");
            eFinal = ingresarNota(scanner, "Examen final");

            // Calcular el promedio
            double promedio = (pc1 + pc2 + eParcial + eFinal) / 4;

            // Actualizar los datos del alumno
            alumnoEncontrado.promedio = promedio;
            alumnoEncontrado.calificacionesIngresadas = true;

            // Mostrar las calificaciones registradas junto con el nombre del alumno
            System.out.println("Calificaciones registradas para " + alumnoEncontrado.nombre + ":");
            System.out.println("Curso: " + alumnoEncontrado.curso);
            System.out.println("Practica calificada 1: " + pc1);
            System.out.println("Practica calificada 2: " + pc2);
            System.out.println("Examen parcial: " + eParcial);
            System.out.println("Examen final: " + eFinal);
            System.out.println("Promedio: " + promedio);
        } else {
            // Si ya se han registrado calificaciones, simplemente actualizarlas
            System.out.println("Calificaciones ya registradas. Puede actualizarlas:");
            pc1 = ingresarNota(scanner, "Practica calificada 1");
            pc2 = ingresarNota(scanner, "Practica calificada 2");
            eParcial = ingresarNota(scanner, "Examen parcial");
            eFinal = ingresarNota(scanner, "Examen final");

            // Calcular el promedio
            double promedio = (pc1 + pc2 + eParcial + eFinal) / 4;

            // Actualizar los datos del alumno
            alumnoEncontrado.promedio = promedio;

            // Mostrar las calificaciones actualizadas
            System.out.println("Calificaciones actualizadas para " + alumnoEncontrado.nombre + ":");
            System.out.println("Curso: " + alumnoEncontrado.curso);
            System.out.println("Practica calificada 1: " + pc1);
            System.out.println("Practica calificada 2: " + pc2);
            System.out.println("Examen parcial: " + eParcial);
            System.out.println("Examen final: " + eFinal);
            System.out.println("Promedio: " + promedio);
        }
    }

    // Método auxiliar para ingresar una nota y validarla
    public static double ingresarNota(Scanner scanner, String descripcion) {
        double nota;
        do {
            System.out.print("Ingrese la calificación para " + descripcion + " (0-20): ");
            nota = scanner.nextDouble();
            if (nota < 0 || nota > 20) {
                System.out.println("Error: La calificación debe estar entre 0 y 20.");
            }
        } while (nota < 0 || nota > 20);
        return nota;
    }

    // Método para calcular el promedio de un alumno
    public static void calcularPromedioParciales(Scanner scanner) {
        System.out.print("Ingrese el nombre del alumno para calcular el promedio: ");
        String nombreBuscado = scanner.nextLine();

        // Buscar al alumno en la lista
        Alumno alumnoEncontrado = null;
        for (Alumno alumno : alumnosMatriculados) {
            if (alumno.nombre.equalsIgnoreCase(nombreBuscado)) {
                alumnoEncontrado = alumno;
                break;
            }
        }

        // Mostrar el promedio del alumno si lo encuentra
        if (alumnoEncontrado != null) {
            System.out.println("Alumno: " + alumnoEncontrado.nombre + ", Promedio: " + alumnoEncontrado.promedio);
        } else {
            System.out.println("Error: Alumno no encontrado.");
        }
    }

    // Método para mostrar el submenú de reportes
    public static void mostrarSubMenuReportes(Scanner scanner) {
        String opcionReporte;

        System.out.println("SUBMENU REPORTES");
        System.out.println("a. Alumnos matriculados");
        System.out.println("b. Alumnos con mejor promedio");
        System.out.print("Selecciona una opción: ");
        opcionReporte = scanner.nextLine();

        switch (opcionReporte) {
            case "a":
                listarAlumnosMatriculados();
                break;
            case "b":
                listarMejoresPromedios();
                break;
            default:
                System.out.println("Opción no válida en el submenú de reportes.");
        }
    }

    // Método para listar los alumnos matriculados con DNI, Nombre, Edad y Curso
    public static void listarAlumnosMatriculados() {
        System.out.println("Listado de Alumnos Matriculados:");
        if (alumnosMatriculados.isEmpty()) {
            System.out.println("No hay alumnos matriculados.");
        } else {
            for (Alumno alumno : alumnosMatriculados) {
                System.out.println("DNI: " + alumno.dni + ", Nombre: " + alumno.nombre + ", Edad: " + alumno.edad + ", Curso: " + alumno.curso);
            }
        }
    }

    // Método para listar los tres alumnos con mejor promedio
    public static void listarMejoresPromedios() {
        System.out.println("Listado de los 3 mejores alumnos según su promedio:");

        // Ordenar la lista por promedio de mayor a menor
        alumnosMatriculados.sort(Comparator.comparingDouble(alumno -> -alumno.promedio));

        // muestra los primeros tres alumnos
        int limite = Math.min(3, alumnosMatriculados.size());
        for (int i = 0; i < limite; i++) {
            Alumno alumno = alumnosMatriculados.get(i);
            System.out.println((i + 1) + ". " + alumno.nombre + " - Promedio: " + alumno.promedio);
        }

        if (limite == 0) {
            System.out.println("No hay alumnos matriculados.");
        }
    }

    public static void generarReportes() {
        System.out.println("Funcionalidad no implementada todavía.");
    }
}
