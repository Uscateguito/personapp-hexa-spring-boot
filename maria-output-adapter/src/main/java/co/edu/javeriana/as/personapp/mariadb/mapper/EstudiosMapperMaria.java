package co.edu.javeriana.as.personapp.mariadb.mapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;

@Mapper
@Log4j2
public class EstudiosMapperMaria {

	@Autowired
	private PersonaMapperMaria personaMapperMaria;

	@Autowired
	private ProfesionMapperMaria profesionMapperMaria;

	public EstudiosEntity fromDomainToAdapter(Study study) {
		log.info("Into fromDomainToAdapter in EstudiosMapperMaria");

		EstudiosEntityPK estudioPK = new EstudiosEntityPK();
		estudioPK.setCcPer(study.getPerson().getIdentification());
		estudioPK.setIdProf(study.getProfession().getIdentification());

		EstudiosEntity estudio = new EstudiosEntity();
		estudio.setEstudiosPK(estudioPK);
		estudio.setFecha(validateFecha(study.getGraduationDate()));
		estudio.setUniver(validateUniver(study.getUniversityName()));
		estudio.setPersona(personaMapperMaria.fromDomainToAdapter(study.getPerson()));
		estudio.setProfesion(profesionMapperMaria.fromDomainToAdapter(study.getProfession()));
		return estudio;
	}

	private Date validateFecha(LocalDate graduationDate) {
		Date date = Date.from(graduationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}

	private String validateUniver(String universityName) {
		return universityName != null ? universityName : "";
	}

	public Study fromAdapterToDomain(EstudiosEntity estudiosEntity) {
		log.info("Into fromAdapterToDomain in EstudiosMapperMaria");
		log.info("EstudiosEntity: " + estudiosEntity);
		Study study = new Study();
		study.setPerson(personaMapperMaria.fromAdapterToDomain(estudiosEntity.getPersona()));
		study.setProfession(profesionMapperMaria.fromAdapterToDomain(estudiosEntity.getProfesion()));
		study.setGraduationDate(validateGraduationDate(estudiosEntity.getFecha()));
		study.setUniversityName(validateUniversityName(estudiosEntity.getUniver()));
		return study;
	}

	private LocalDate validateGraduationDate(Date fecha) {
		log.info("Fecha: " + fecha);
		Date fechaSafe = new Date(fecha.getTime());
		return fechaSafe != null ? fechaSafe.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
	}

	private String validateUniversityName(String univer) {
		return univer != null ? univer : "";
	}
}