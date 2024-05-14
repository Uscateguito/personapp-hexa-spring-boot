package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.model.PersonModelCLI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonMenu {

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

	public void iniciarMenu(PersonInputAdapterCLI personInputAdapterCli, Scanner keyboard) {
		boolean isValid = false;
		do {
			try {
				mostrarMenuMotorPersistencia();
				int opcion = leerOpcion(keyboard);
				switch (opcion) {
				case OPCION_REGRESAR_MODULOS:
					isValid = true;
					break;
				case PERSISTENCIA_MARIADB:
					PersonMenu.DB = "MARIA";
					personInputAdapterCli.setPersonOutputPortInjection("MARIA");
					menuOpciones(personInputAdapterCli,keyboard);
					break;
				case PERSISTENCIA_MONGODB:
					PersonMenu.DB = "MONGO";
					personInputAdapterCli.setPersonOutputPortInjection("MONGO");
					menuOpciones(personInputAdapterCli,keyboard);
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			}  catch (InvalidOptionException e) {
				log.warn(e.getMessage());
			}
		} while (!isValid);
	}

	private void menuOpciones(PersonInputAdapterCLI personInputAdapterCli, Scanner keyboard) {
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
						System.out.println(personInputAdapterCli.getAll(PersonMenu.DB));
					break;
					case GET_BY_ID_OPTION:
						System.out.println(personInputAdapterCli.getById(leerIdentificacion(keyboard), PersonMenu.DB));
					break;
					case CREATE_OPTION:
						System.out.println(personInputAdapterCli.create(leerEntidad(keyboard), PersonMenu.DB));
					break;
					case UPDATE_OPTION:
						System.out.println(personInputAdapterCli.edit(leerEntidad(keyboard), PersonMenu.DB));
					break;
					case DELETE_OPTION:
						System.out.println(personInputAdapterCli.delete(leerIdentificacion(keyboard), PersonMenu.DB));
					break;
					case COUNT_OPTION:
						System.out.println("El total de personas es: " + personInputAdapterCli.count(PersonMenu.DB));
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
		System.out.println(GETALL_OPTION + " para ver todas las personas");
		System.out.println(GET_BY_ID_OPTION + " para buscar una persona por identificación");
		System.out.println(CREATE_OPTION + " para crear una persona");
		System.out.println(UPDATE_OPTION + " para actualizar una persona");
		System.out.println(DELETE_OPTION + " para eliminar una persona");
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

	public PersonModelCLI leerEntidad(Scanner keyboard) {
		try {
			PersonModelCLI personaModelCli = new PersonModelCLI();
			System.out.print("Ingrese la identificacion: ");
			personaModelCli.setCc(keyboard.nextInt());
			keyboard.nextLine();
			System.out.print("Ingrese el nombre: ");
			personaModelCli.setNombre(keyboard.nextLine());
			System.out.print("Ingrese el apellido: ");
			personaModelCli.setApellido(keyboard.nextLine());
			System.out.println("Ingrese el genero (M/F): ");
			personaModelCli.setGenero(keyboard.nextLine());
			System.out.println("Ingrese la edad: ");
			personaModelCli.setEdad(keyboard.nextInt());
			keyboard.nextLine();
			personaModelCli.setDb(PersonMenu.DB);
			return personaModelCli;
		} catch (InputMismatchException e) {
			System.out.println("Los datos ingresados contienen un error, vuelva a ingresarlos.");
			return leerEntidad(keyboard);
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



}
