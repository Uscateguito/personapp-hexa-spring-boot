package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import co.edu.javeriana.as.personapp.adapter.PersonInputAdapterRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.as.personapp.model.request.PersonRequest;
import co.edu.javeriana.as.personapp.model.response.PersonResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonControllerV1 {
	
	@Autowired
	private PersonInputAdapterRest personInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonResponse> personas(@PathVariable String database) {
		log.info("Into personas REST API");
			return personInputAdapterRest.historial(database.toUpperCase());
	}
	
	@ResponseBody
	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonResponse crearPersona(@RequestBody PersonRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return personInputAdapterRest.crearPersona(request);
	}

	@ResponseBody
	@PutMapping(path = "/edit/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonResponse editarPersona(@PathVariable Integer identification, @RequestBody PersonRequest request, @PathVariable String database) {
		log.info("Into editarPersona REST API");
		return personInputAdapterRest.editarPersona(identification, request, database.toUpperCase());
	}

	@ResponseBody
	@GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public long persona(@PathVariable String database) {
		log.info("Into persona REST API");
		return personInputAdapterRest.count(database.toUpperCase());
	}

	@ResponseBody
	@GetMapping(path = "/getById/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonResponse persona(@PathVariable Integer identification, @PathVariable String database) {
		log.info("Into persona REST API");
		return personInputAdapterRest.getById(identification, database.toUpperCase());
	}

	@ResponseBody
	@DeleteMapping(path = "/delete/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean borrarPersona(@PathVariable Integer identification, @PathVariable String database) {
		log.info("Into borrarPersona REST API");
		return personInputAdapterRest.borrarPersona(identification, database.toUpperCase());
	}

//	@ResponseBody
//	@GetMapping(path = "/{identification}/phones/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<String> telefonos(@PathVariable Integer identification, @PathVariable String database) {
//		log.info("Into telefonos REST API");
//		return personaInputAdapterRest.getPhones(identification, database.toUpperCase());
//	}

//	@ResponseBody
//	@GetMapping(path = "/{identification}/studies/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<String> estudios(@PathVariable Integer identification, @PathVariable String database) {
//		log.info("Into estudios REST API");
//		return personaInputAdapterRest.getStudies(identification, database.toUpperCase());
//	}

//	@ResponseBody
//	@DeleteMapping(path = "/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Boolean borrarPersona(@PathVariable Integer identification, @PathVariable String database) {
//		log.info("Into borrarPersona REST API");
//		return personaInputAdapterRest.borrarPersona(identification, database.toUpperCase());
//	}
}
