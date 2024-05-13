package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.StudyInputAdapterRest;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/study")
public class StudyControllerV1 {

    @Autowired
    private StudyInputAdapterRest studyInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/getAll/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudyResponse> estudios(@PathVariable String database) {
        log.info("Into estudios REST API");
        return studyInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "/save/{database}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudyResponse save(@RequestBody StudyRequest request, @PathVariable String database) {
        log.info("Into save REST API");
        return studyInputAdapterRest.save(request, database.toUpperCase());
    }

    @ResponseBody
    @PutMapping(path = "/update/{database}/{profession_id}/{person_id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudyResponse update(@RequestBody StudyRequest request, @PathVariable String database, @PathVariable Integer profession_id, @PathVariable Integer person_id) throws NoExistException {
        log.info("Into update REST API");
        return studyInputAdapterRest.edit(profession_id, person_id, request,database.toUpperCase());
    }

//    @ResponseBody
//    @DeleteMapping(path = "/delete/{database}/{profession_id}/{person_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Boolean delete(@PathVariable String database, @PathVariable String profession_id, @PathVariable String person_id) {
//        log.info("Into delete REST API");
//        return studyInputAdapterRest.(id, database.toUpperCase());
//    }

    @ResponseBody
    @GetMapping(path = "/getById/{database}/{profession_id}/{person_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyResponse get(@PathVariable String database, @PathVariable Integer profession_id, @PathVariable Integer person_id) throws NoExistException {
        log.info("Into get REST API");
        return studyInputAdapterRest.getById(profession_id, person_id, database.toUpperCase());
    }

    @ResponseBody
    @GetMapping(path = "/count/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long count(@PathVariable String database) {
        log.info("Into count REST API");
        return studyInputAdapterRest.count(database.toUpperCase());
    }


}
