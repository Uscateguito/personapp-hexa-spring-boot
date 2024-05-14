package co.edu.javeriana.as.personapp.terminal.menu;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfessionInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.mapper.ProfessionMapperCLI;
import co.edu.javeriana.as.personapp.terminal.model.ProfessionModelCLI;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class ProfessionMenu {

    private static final int OPCION_REGRESAR_MODULOS = 0;
    private static final int PERSISTENCIA_MARIADB = 1;
    private static final int PERSISTENCIA_MONGODB = 2;

    private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
    private static final int GETALL_OPTION = 1;
    private static final int GET_BY_ID_OPTION = 2;
    private static final int CREATE_OPTION = 3;
    private static final int UPDATE_OPTION = 4;
    private static final int DELETE_OPTION = 5;

    private static final int COUNT_OPTION = 6;

    // mas opciones

    // Truco

    public static String DB = "MARIA";

    public void iniciarMenu(ProfessionInputAdapterCLI professionInputAdapterCli, Scanner keyboard) {
        boolean isValid = false;
        do {
            mostrarMenuMotorPersistencia();
            int opcion = leerOpcion(keyboard);
            switch (opcion) {
                case OPCION_REGRESAR_MODULOS:
                    isValid = true;
                    break;
                case PERSISTENCIA_MARIADB:
                    ProfessionMenu.DB = "MARIA";
                    professionInputAdapterCli.setProfessionOutputPortInjection("MARIA");
                    menuOpciones(professionInputAdapterCli,keyboard);
                    break;
                case PERSISTENCIA_MONGODB:
                    ProfessionMenu.DB = "MONGO";
                    professionInputAdapterCli.setProfessionOutputPortInjection("MONGO");
                    menuOpciones(professionInputAdapterCli,keyboard);
                    break;
                default:
                    log.warn("La opción elegida no es válida.");
            }
        } while (!isValid);
    }

    private void menuOpciones(ProfessionInputAdapterCLI professionInputAdapterCli, Scanner keyboard) {
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
                        System.out.println(professionInputAdapterCli.getAll(ProfessionMenu.DB));
                        break;
                    case GET_BY_ID_OPTION:
                        System.out.println(professionInputAdapterCli.getById(leerIdentificacion(keyboard), ProfessionMenu.DB));
                        break;
                    case CREATE_OPTION:
                        professionInputAdapterCli.create(leerEntidad(keyboard), ProfessionMenu.DB);
                        break;
                    case UPDATE_OPTION:
                        professionInputAdapterCli.update(leerEntidad(keyboard), ProfessionMenu.DB);
                        break;
                    case DELETE_OPTION:
                        professionInputAdapterCli.delete(leerIdentificacion(keyboard), ProfessionMenu.DB);
                        break;
                    case COUNT_OPTION:
                        System.out.println("Cantidad de profesiones: " + professionInputAdapterCli.count(ProfessionMenu.DB));
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
        System.out.println(DELETE_OPTION + " para eliminar un teléfono");
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
            System.out.print("Digite el item deseado: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Los valores deben ser numéricos.");
            return leerOpcion(keyboard);
        }
    }

    private int leerIdentificacion(Scanner keyboard) {
        try {
            System.out.print("Ingrese la identificacion: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Solo se permiten números.");
            return leerOpcion(keyboard);
        }
    }

    public ProfessionModelCLI leerEntidad(Scanner keyboard) {
        try {
            ProfessionModelCLI profesion = new ProfessionModelCLI();
            System.out.println("Ingrese la identificacion: ");
            profesion.setId(keyboard.nextInt());
            keyboard.nextLine();
            System.out.println("Ingrese el nombre: ");
            profesion.setName(keyboard.nextLine());
            System.out.println("Ingrese la descripcion: ");
            profesion.setDescription(keyboard.nextLine());
            profesion.setDb(ProfessionMenu.DB);
            return profesion;
        } catch (InputMismatchException e) {
            System.out.println("Los datos ingresados contienen un error, vuelva a ingresarlos.");
            return leerEntidad(keyboard);
        }
    }

}
