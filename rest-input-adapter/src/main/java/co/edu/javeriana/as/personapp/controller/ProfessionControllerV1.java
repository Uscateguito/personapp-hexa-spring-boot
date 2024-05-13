package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.ProfessionInputAdapterRest;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.model.request.ProfessionRequest;
import co.edu.javeriana.as.personapp.model.response.ProfessionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/profession")
public class ProfessionControllerV1 {

    @Autowired
    private ProfessionInputAdapterRest professionInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessionResponse> profesiones(@PathVariable String database) {
        log.info("Into profesiones REST API");
        return professionInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "/create/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessionResponse crearProfesion(@RequestBody ProfessionRequest request, @PathVariable String database) {
        log.info("Into crearProfesion REST API");
        return professionInputAdapterRest.crearProfesion(request, database.toUpperCase());
    }

    @ResponseBody
    @PutMapping(path = "/edit/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfessionResponse editarProfesion(@PathVariable Integer identification, @RequestBody ProfessionRequest request, @PathVariable String database) {
        log.info("Into editarProfesion REST API");
        return professionInputAdapterRest.actualizar(database.toUpperCase(), identification,request);
    }

    @ResponseBody
    @GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long profesion(@PathVariable String database) {
        log.info("Into profesion REST API");
        return professionInputAdapterRest.count(database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/getById/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfessionResponse profesion(@PathVariable Integer identification, @PathVariable String database) throws NoExistException {
        log.info("Into profesion REST API");
        return professionInputAdapterRest.getById(identification, database.toUpperCase());
    }

    @ResponseBody
    @DeleteMapping(path = "/drop/{identification}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean eliminarProfesion(@PathVariable Integer identification, @PathVariable String database) throws NoExistException {
        log.info("Into eliminarProfesion REST API");
        return professionInputAdapterRest.eliminar(database.toUpperCase(), identification);
    }



}
