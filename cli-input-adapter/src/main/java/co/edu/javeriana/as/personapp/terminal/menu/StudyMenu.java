package co.edu.javeriana.as.personapp.terminal.menu;

import co.edu.javeriana.as.personapp.terminal.adapter.StudyInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.model.StudyModelCLI;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class StudyMenu {

    private static final int OPCION_REGRESAR_MODULOS = 0;
    private static final int PERSISTENCIA_MARIADB = 1;
    private static final int PERSISTENCIA_MONGODB = 2;

    private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
    private static final int GETALL_OPTION = 1;
    private static final int GET_BY_ID_OPTION = 2;
    private static final int CREATE_OPTION = 3;
    private static final int UPDATE_OPTION = 4;
    private static final int COUNT_OPTION = 5;

    public static String DB = "MARIA";

    public void iniciarMenu(StudyInputAdapterCLI studyInputAdapterCLI, Scanner keyboard) {
        boolean isValid = false;
        do {
            mostrarMenuMotorPersistencia();
            int opcion = leerOpcion(keyboard);
            switch (opcion) {
                case OPCION_REGRESAR_MODULOS:
                    isValid = true;
                    break;
                case PERSISTENCIA_MARIADB:
                    StudyMenu.DB = "MARIA";
                    studyInputAdapterCLI.setStudyOutputPortInjection("MARIA");
                    menuOpciones(studyInputAdapterCLI,keyboard);
                    break;
                case PERSISTENCIA_MONGODB:
                    StudyMenu.DB = "MONGO";
                    studyInputAdapterCLI.setStudyOutputPortInjection("MONGO");
                    menuOpciones(studyInputAdapterCLI,keyboard);
                    break;
                default:
                    log.warn("La opción elegida no es válida.");
            }
        } while (!isValid);
    }

    private void menuOpciones(StudyInputAdapterCLI studyInputAdapterCLI, Scanner keyboard) {
        boolean isValid = false;
        do {
            try {
                mostrarMenuOpciones();
                int opcion = leerOpcion(keyboard);
                switch (opcion) {
                    case OPCION_REGRESAR_MOTOR_PERSISTENCIA:
                        isValid = true;
                        break;
                    case GETALL_OPTION:
                        studyInputAdapterCLI.getAll(StudyMenu.DB).forEach(System.out::println);
                        break;
                    case GET_BY_ID_OPTION:
                        System.out.println(studyInputAdapterCLI.getById(leerIdProfesion(keyboard), leerIdPersona(keyboard), StudyMenu.DB));
                        break;
                    case CREATE_OPTION:
                        studyInputAdapterCLI.create(leerEntidad(keyboard), StudyMenu.DB);
                        break;
                    case UPDATE_OPTION:
                        studyInputAdapterCLI.update(leerEntidad(keyboard), StudyMenu.DB);
                        break;
                    case COUNT_OPTION:
                        System.out.println("El número de estudios es: " + studyInputAdapterCLI.count(StudyMenu.DB));
                        break;
                    default:
                        log.warn("La opción elegida no es válida.");
                }
            } catch (InputMismatchException e) {
                log.warn("Solo se permiten números.");
            }
        } while (!isValid);
    }


    private void mostrarMenuOpciones() {
        System.out.println("----------------------");
        System.out.println(GETALL_OPTION + " para ver todos los teléfonos");
        System.out.println(GET_BY_ID_OPTION + " para buscar un información de un teléfono");
        System.out.println(CREATE_OPTION + " para crear un teléfono");
        System.out.println(UPDATE_OPTION + " para actualizar un teléfono");
//        System.out.println(DELETE_OPTION + " para eliminar un teléfono");
        System.out.println(OPCION_REGRESAR_MOTOR_PERSISTENCIA + " para regresar");
    }

    private void mostrarMenuMotorPersistencia() {
        System.out.println("----------------------");
        System.out.println(PERSISTENCIA_MARIADB + " para MariaDB");
        System.out.println(PERSISTENCIA_MONGODB + " para MongoDB");
        System.out.println(OPCION_REGRESAR_MODULOS + " para regresar");
    }

    private int leerOpcion(Scanner keyboard) {
        try {
            System.out.print("Ingrese una opción: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Solo se permiten números.");
            return leerOpcion(keyboard);
        }
    }

    public LocalDate leerFecha(Scanner keyboard) {
        try {
            System.out.println("Ingrese la fecha de graduación (dd/mm/yyyy):");
            String fecha = keyboard.nextLine();
            String[] fechaArray = fecha.split("/");
            return LocalDate.of(Integer.parseInt(fechaArray[2]), Integer.parseInt(fechaArray[1]), Integer.parseInt(fechaArray[0]));
        } catch (Exception e) {
            System.out.println("Fecha no válida, ingrese nuevamente");
            return leerFecha(keyboard);
        }
    }

    private Integer leerIdProfesion(Scanner keyboard) {
        try {
            System.out.print("Ingrese el ID de la profesión: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("El ID de la profesión debe ser un número.");
            keyboard.nextLine(); // Limpiar el buffer del teclado
            return leerIdProfesion(keyboard);
        }
    }

    private Integer leerIdPersona(Scanner keyboard) {
        try {
            System.out.print("Ingrese el ID de la persona: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("El ID de la persona debe ser un número.");
            keyboard.nextLine(); // Limpiar el buffer del teclado
            return leerIdPersona(keyboard);
        }
    }

    public StudyModelCLI leerEntidad(Scanner keyboard) {
        try {
            StudyModelCLI studyModelCLI = new StudyModelCLI();
            keyboard.nextLine();
            System.out.println("Ingrese la identificación de la persona:");
            studyModelCLI.setIdPerson(keyboard.nextLine());
            System.out.println("Ingrese la identificación de la profesión:");
            studyModelCLI.setIdProfession(keyboard.nextLine());
            System.out.println("Ingrese el nombre de la universidad:");
            studyModelCLI.setUniversityName(keyboard.nextLine());
            studyModelCLI.setGraduationDate(leerFecha(keyboard));
            studyModelCLI.setDb(StudyMenu.DB);
            return studyModelCLI;
        }
        catch (InputMismatchException e) {
            System.out.println("Los datos ingresados contienen un error, vuelva a ingresarlos.");
            return leerEntidad(keyboard);
        }
    }






}
