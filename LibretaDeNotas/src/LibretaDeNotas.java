import java.util.*;

public class LibretaDeNotas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, ArrayList<Double>> calificaciones = new HashMap<>();

        // Paso 1: Pedir cantidad de alumnos y de notas por alumno
        System.out.print("Ingrese la cantidad de alumnos: ");
        int cantidadAlumnos = leerNumeroPositivo(scanner);

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int cantidadNotas = leerNumeroPositivo(scanner);

        // Paso 2: Ingresar los nombres y las notas
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno #" + (i + 1) + ": ");
            String nombre = scanner.next();

            ArrayList<Double> notas = new ArrayList<>();
            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota #" + (j + 1) + " para " + nombre + ": ");
                double nota = leerNotaValida(scanner);
                notas.add(nota);
            }

            calificaciones.put(nombre, notas);
        }

        // Paso 3: Menú de opciones
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    mostrarPromedios(calificaciones);
                    break;
                case 2:
                    verificarAprobacion(scanner, calificaciones);
                    break;
                case 3:
                    compararConPromedioCurso(scanner, calificaciones);
                    break;
                case 0:
                    System.out.println("¡Saliendo del programa!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Métodos auxiliares

    // Mostrar menú
    public static void mostrarMenu() {
        System.out.println("\n---- Menú de Opciones ----");
        System.out.println("1. Mostrar el Promedio de Notas por Estudiante");
        System.out.println("2. Verificar si la Nota es Aprobatoria o Reprobatoria");
        System.out.println("3. Verificar si la Nota está por Sobre o Debajo del Promedio del Curso");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Leer número positivo
    public static int leerNumeroPositivo(Scanner scanner) {
        int numero;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Debe ingresar un número válido: ");
                scanner.next();
            }
            numero = scanner.nextInt();
            if (numero <= 0) System.out.print("Debe ser mayor a 0. Intente nuevamente: ");
        } while (numero <= 0);
        return numero;
    }

    // Leer nota entre 1.0 y 7.0
    public static double leerNotaValida(Scanner scanner) {
        double nota;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.print("Debe ingresar una nota válida (1.0 - 7.0): ");
                scanner.next();
            }
            nota = scanner.nextDouble();
            if (nota < 1.0 || nota > 7.0)
                System.out.print("Nota fuera de rango (1.0 - 7.0). Intente nuevamente: ");
        } while (nota < 1.0 || nota > 7.0);
        return nota;
    }

    // Mostrar promedios
    public static void mostrarPromedios(HashMap<String, ArrayList<Double>> calificaciones) {
        for (String alumno : calificaciones.keySet()) {
            ArrayList<Double> notas = calificaciones.get(alumno);
            double promedio = calcularPromedio(notas);
            double max = Collections.max(notas);
            double min = Collections.min(notas);

            System.out.printf("\n%s: Promedio = %.2f | Máxima = %.2f | Mínima = %.2f\n", alumno, promedio, max, min);
        }
    }

    // Verificar aprobación
    public static void verificarAprobacion(Scanner scanner, HashMap<String, ArrayList<Double>> calificaciones) {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();
        if (!calificaciones.containsKey(nombre)) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Ingrese la nota a evaluar: ");
        double nota = leerNotaValida(scanner);

        if (nota >= 4.0) {
            System.out.println("La nota es aprobatoria.");
        } else {
            System.out.println("La nota es reprobatoria.");
        }
    }

    // Comparar con promedio general
    public static void compararConPromedioCurso(Scanner scanner, HashMap<String, ArrayList<Double>> calificaciones) {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();
        if (!calificaciones.containsKey(nombre)) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Ingrese la nota a comparar: ");
        double nota = leerNotaValida(scanner);
        double promedioCurso = calcularPromedioGeneral(calificaciones);

        if (nota > promedioCurso) {
            System.out.printf("La nota está por SOBRE el promedio general del curso (%.2f).\n", promedioCurso);
        } else if (nota < promedioCurso) {
            System.out.printf("La nota está por DEBAJO del promedio general del curso (%.2f).\n", promedioCurso);
        } else {
            System.out.println("La nota es IGUAL al promedio general del curso.");
        }
    }

    // Calcular promedio de una lista de notas
    public static double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    // Calcular promedio general del curso
    public static double calcularPromedioGeneral(HashMap<String, ArrayList<Double>> calificaciones) {
        double sumaTotal = 0;
        int cantidadTotal = 0;

        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                cantidadTotal++;
            }
        }
        return sumaTotal / cantidadTotal;
    }
}

