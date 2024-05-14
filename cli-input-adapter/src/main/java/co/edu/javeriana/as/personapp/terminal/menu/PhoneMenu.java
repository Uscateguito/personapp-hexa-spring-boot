package co.edu.javeriana.as.personapp.terminal.menu;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.adapter.PhoneInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.model.PersonModelCLI;
import co.edu.javeriana.as.personapp.terminal.model.PhoneModelCLI;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class PhoneMenu {

    private static final int OPCION_REGRESAR_MODULOS = 0;
    private static final int PERSISTENCIA_MARIADB = 1;
    private static final int PERSISTENCIA_MONGODB = 2;

    private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
    private static final int GETALL_OPTION = 1;
    private static final int GET_BY_ID_OPTION = 2;
    private static final int CREATE_OPTION = 3;
    private static final int UPDATE_OPTION = 4;
    private static final int COUNT_OPTION = 5;

//    private static final int DELETE_OPTION = 6;
    public static String DB = "MARIA";

    public void iniciarMenu(PhoneInputAdapterCLI phoneInputAdapterCLI, Scanner keyboard) {
        boolean isValid = false;
        do {
            mostrarMenuMotorPersistencia();
            int opcion = leerOpcion(keyboard);
            switch (opcion) {
                case OPCION_REGRESAR_MODULOS:
                    isValid = true;
                    break;
                case PERSISTENCIA_MARIADB:
                    PersonMenu.DB = "MARIA";
                    phoneInputAdapterCLI.setPhoneOutputPortInjection("MARIA");
                    menuOpciones(phoneInputAdapterCLI,keyboard);
                    break;
                case PERSISTENCIA_MONGODB:
                    PersonMenu.DB = "MONGO";
                    phoneInputAdapterCLI.setPhoneOutputPortInjection("MONGO");
                    menuOpciones(phoneInputAdapterCLI,keyboard);
                    break;
                default:
                    log.warn("La opción elegida no es válida.");
            }
        } while (!isValid);
    }

    private void menuOpciones(PhoneInputAdapterCLI phoneInputAdapterCLI, Scanner keyboard) {
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
                        System.out.println(phoneInputAdapterCLI.getAll(PersonMenu.DB));
                        break;
                    case GET_BY_ID_OPTION:
                        System.out.println(phoneInputAdapterCLI.getById(leerNumero(keyboard), PersonMenu.DB));
                        break;
                    case CREATE_OPTION:
                        System.out.println(phoneInputAdapterCLI.create(leerEntidad(keyboard), PersonMenu.DB));
                        break;
                    case UPDATE_OPTION:
                        System.out.println(phoneInputAdapterCLI.edit(leerEntidad(keyboard), PersonMenu.DB));
                        break;
                    case COUNT_OPTION:
                        System.out.println("El total de teléfonos es: " + phoneInputAdapterCLI.count(PersonMenu.DB));
                        break;
//                    case DELETE_OPTION:
//                        System.out.println(phoneInputAdapterCLI.delete(leerIdentificacion(keyboard), PersonMenu.DB));
//                        break;

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
            System.out.print("Digite el item deseado: ");
            return keyboard.nextInt();
        } catch (InputMismatchException e) {
            log.warn("Los valores deben ser numéricos.");
            return leerOpcion(keyboard);
        }
    }

    public PhoneModelCLI leerEntidad(Scanner keyboard) {
        try {
            PhoneModelCLI phoneModelCLI = new PhoneModelCLI();
            keyboard.nextLine();
            System.out.print("Ingrese el numero: ");
            phoneModelCLI.setNumber(keyboard.nextLine());
            System.out.print("Ingrese la compañia: ");
            phoneModelCLI.setCompany(keyboard.nextLine());
            System.out.print("Ingrese el id de la persona: ");
            phoneModelCLI.setIdPerson(keyboard.nextLine());
            phoneModelCLI.setDb(PersonMenu.DB);
            return phoneModelCLI;
        } catch (InputMismatchException e) {
            System.out.println("Los datos ingresados contienen un error, vuelva a ingresarlos.");
            return leerEntidad(keyboard);
        }
    }

    private String leerNumero(Scanner keyboard) {
        try {
            keyboard.nextLine();
            System.out.print("Ingrese el numero: ");
            String numero = keyboard.nextLine();
            return numero;
        } catch (Exception e) {
            log.warn("Solo se permiten números.");
            return leerNumero(keyboard);
        }
    }

}
