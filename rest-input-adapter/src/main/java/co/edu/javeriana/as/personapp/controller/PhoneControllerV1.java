package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.PhoneInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.PhoneRequest;
import co.edu.javeriana.as.personapp.model.response.PhoneResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/phone")
public class PhoneControllerV1 {

    @Autowired
    private PhoneInputAdapterRest phoneInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneResponse> telefonos(@PathVariable String database) {
        log.info("Into telefonos REST API");
        return phoneInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse crearTelefono(@RequestBody PhoneRequest request) {
        log.info("esta en el metodo crearTarea en el controller del api");
        return phoneInputAdapterRest.crearTelefono(request);
    }

    @ResponseBody
    @PutMapping(path = "/edit/{numero}/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse editarTelefono(@PathVariable String numero, @RequestBody PhoneRequest request, @PathVariable String database) {
        log.info("Into editarTelefono REST API");
        return phoneInputAdapterRest.actualizarTelefono(numero, request, database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long telefono(@PathVariable String database) {
        log.info("Into telefono REST API");
        return phoneInputAdapterRest.count(database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/getByNumber/{number}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PhoneResponse telefono(@PathVariable String number, @PathVariable String database) {
        log.info("Into telefono REST API");
        return phoneInputAdapterRest.getByNumber(number, database.toUpperCase());
    }

}
