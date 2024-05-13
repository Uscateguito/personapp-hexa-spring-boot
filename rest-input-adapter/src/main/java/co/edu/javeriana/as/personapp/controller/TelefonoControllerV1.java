package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.PhoneRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import co.edu.javeriana.as.personapp.model.response.PhoneResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/telefono")
public class TelefonoControllerV1 {

//    @Autowired
//    private PersonaInputAdapterRest personaInputAdapterRest;
//
//    @ResponseBody
//    @GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<PersonaResponse> personas(@PathVariable String database) {
//        log.info("Into personas REST API");
//        return personaInputAdapterRest.historial(database.toUpperCase());
//    }
//
//    @ResponseBody
//    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public PersonaResponse crearPersona(@RequestBody PersonaRequest request) {
//        log.info("esta en el metodo crearTarea en el controller del api");
//        return personaInputAdapterRest.crearPersona(request);
//    }
//
//    @ResponseBody
//    @PutMapping(path = "/edit/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public PersonaResponse editarPersona(@PathVariable Integer identification, @RequestBody PersonaRequest request, @PathVariable String database) {
//        log.info("Into editarPersona REST API");
//        return personaInputAdapterRest.editarPersona(identification, request, database.toUpperCase());
//    }
//
//    @ResponseBody
//    @GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public long persona(@PathVariable String database) {
//        log.info("Into persona REST API");
//        return personaInputAdapterRest.count(database.toUpperCase());
//    }
//
//    @ResponseBody
//    @GetMapping(path = "/getById/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public PersonaResponse persona(@PathVariable Integer identification, @PathVariable String database) {
//        log.info("Into persona REST API");
//        return personaInputAdapterRest.getById(identification, database.toUpperCase());
//    }
//
//    @ResponseBody
//    @DeleteMapping(path = "/delete/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Boolean borrarPersona(@PathVariable Integer identification, @PathVariable String database) {
//        log.info("Into borrarPersona REST API");
//        return personaInputAdapterRest.borrarPersona(identification, database.toUpperCase());
//    }

    @Autowired
    private TelefonoInputAdapterRest telefonoInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneResponse> telefonos(@PathVariable String database) {
        log.info("Into telefonos REST API");
        return telefonoInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse crearTelefono(@RequestBody PhoneRequest request) {
        log.info("esta en el metodo crearTarea en el controller del api");
        return telefonoInputAdapterRest.crearTelefono(request);
    }

    @ResponseBody
    @PutMapping(path = "/edit/{numero}/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse editarTelefono(@PathVariable String numero, @RequestBody PhoneRequest request, @PathVariable String database) {
        log.info("Into editarTelefono REST API");
        return telefonoInputAdapterRest.actualizarTelefono(numero, request, database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long telefono(@PathVariable String database) {
        log.info("Into telefono REST API");
        return telefonoInputAdapterRest.count(database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/getById/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse telefono(@PathVariable String identification, @PathVariable String database) {
        log.info("Into telefono REST API");
        return telefonoInputAdapterRest.getByNumber(identification, database.toUpperCase());
    }

}
