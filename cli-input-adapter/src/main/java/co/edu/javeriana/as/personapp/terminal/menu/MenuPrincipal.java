package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.terminal.adapter.PhoneInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfessionInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.adapter.StudyInputAdapterCLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.javeriana.as.personapp.terminal.adapter.PersonInputAdapterCLI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuPrincipal {
	
	//Beans
	@Autowired
	private PersonInputAdapterCLI personInputAdapterCli;

	@Autowired
	private PhoneInputAdapterCLI phoneInputAdapterCLI;

	@Autowired
	private ProfessionInputAdapterCLI professionInputAdapterCLI;

	@Autowired
	private StudyInputAdapterCLI studyInputAdapterCLI;

	private static final int SALIR = 0;
	private static final int MODULO_PERSONA = 1;
	private static final int MODULO_PROFESION = 2;
	private static final int MODULO_TELEFONO = 3;
	private static final int MODULO_ESTUDIO = 4;

	//Menus
	private final PersonMenu personMenu;
	private final PhoneMenu phoneMenu;
	private final ProfessionMenu professionMenu;
	private final StudyMenu studyMenu;
	private final Scanner keyboard;

    public MenuPrincipal() {
        this.personMenu = new PersonMenu();
		this.phoneMenu = new PhoneMenu();
		this.professionMenu = new ProfessionMenu();
		this.studyMenu = new StudyMenu();
        this.keyboard = new Scanner(System.in);
    }

	public void inicio() {
		
		//personaMenu = new PersonaMenu(personaInputAdapterCli);
		boolean isValid = false;
		do {
			mostrarMenu();
			int opcion = leerOpcion();
			switch (opcion) {
			case SALIR:
				isValid = true;
				break;
			case MODULO_PERSONA:
				personMenu.iniciarMenu(personInputAdapterCli, keyboard);
				log.info("volvio");
				break;
			case MODULO_PROFESION:
				professionMenu.iniciarMenu(professionInputAdapterCLI, keyboard);
				log.info("volvio");
				break;
			case MODULO_TELEFONO:
				phoneMenu.iniciarMenu(phoneInputAdapterCLI, keyboard);
				log.info("volvio");
				break;
			case MODULO_ESTUDIO:
				studyMenu.iniciarMenu(studyInputAdapterCLI, keyboard);
				log.info("volvio");
				break;
			default:
				log.warn("La opción elegida no es válida.");
			}

		} while (!isValid);
		keyboard.close();
	}

	private void mostrarMenu() {
		System.out.println("----------------------");
		System.out.println(MODULO_PERSONA + " para trabajar con el Modulo de Personas");
		System.out.println(MODULO_PROFESION + " para trabajar con el Modulo de Profesiones");
		System.out.println(MODULO_TELEFONO + " para trabajar con el Modulo de Telefonos");
		System.out.println(MODULO_ESTUDIO + " para trabajar con el Modulo de Estudios");
		System.out.println(SALIR + " para Salir");
	}

	private int leerOpcion() {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			log.warn("Solo se permiten números.");
			return leerOpcion();
		}
	}

}
