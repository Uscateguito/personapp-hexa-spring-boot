# Taller de Arquitectura de Software
## Arquitectura Hexagonal

![Diseño sin título (2)](https://github.com/Uscateguito/personapp-hexa-spring-boot/assets/103542486/21989ad5-4de8-415d-9442-850f66dd6741)

### 1. Contexto

La **arquitectura hexagonal**, también conocida como arquitectura de puertos y adaptadores, permite una separación clara entre la lógica de negocio de una aplicación y los dispositivos con los que se comunica, tales como bases de datos, sistemas de archivos y interfaces de usuario. En este proyecto, se utilizó la arquitectura hexagonal para organizar el código en distintas capas que interactúan a través de puertos y adaptadores definidos. Los componentes clave son:
- **Dominio**: Contiene la lógica de negocio.
- **Puertos de Entrada/Salida**: Interfaces que definen cómo se puede interactuar con la lógica de negocio.
- **Adaptadores**: Implementan los puertos. Los adaptadores de entrada manejan las solicitudes desde diferentes fuentes (como REST y CLI), y los adaptadores de salida interactúan con sistemas externos como bases de datos (MariaDB y MongoDB).

#### 2. Librerías Necesarias

Para ejecutar el proyecto se requieren varias dependencias, incluyendo:
- **Spring Boot**: Facilita la creación de aplicaciones Spring basadas en microservicios.
- **Lombok**: Biblioteca para Java que usa anotaciones para reducir código repetitivo como getters y setters.
- **MariaDB**: Sistema de gestión de bases de datos.
- **MongoDB**: Base de datos NoSQL orientada a documentos.
- **Swagger**: Herramienta para documentar y consumir APIs REST de manera interactiva.

Las dependencias específicas se deben definir en el archivo `pom.xml` del proyecto.

#### 3. Ejecución del Proyecto

Para ejecutar el proyecto, se deben seguir los siguientes pasos:
1. **Instalar MariaDB** en el puerto 3307.
2. **Instalar MongoDB** en el puerto 27017.
3. Ejecutar los **scripts de base de datos** proporcionados en la carpeta `scripts`.
4. Asegurar que el **Lombok** esté configurado en el IDE utilizado.
5. Iniciar los adaptadores de entrada:
    - El adaptador REST se ejecuta en el puerto 3000. Swagger está disponible en `http://localhost:3000/swagger-ui.html`.
6. Adicionalmente es posible realizar el siguiente procedimiento para ejecutar la aplicación desde la consola:
   - Ejecutar el comando `mvn clean install` para compilar el proyecto.
   - Ejecutar el comando `java -jar .\rest-input-adapter\target\rest-input-adapter-0.0.1-SNAPSHOT.jar` para iniciar la aplicación desde el formato REST.
   - Ejecutar el comando `java -jar .\cli-input-adapter\target\cli-input-adapter-0.0.1-SNAPSHOT.jar` para iniciar la aplicación desde el formato CLI.

#### Puntos Adicionales

- **Estructura de Directorios**: El proyecto está organizado en múltiples módulos como `domain`, `application`, y adaptadores específicos (`rest-input-adapter`, `cli-input-adapter`, `mongo-output-adapter`, `maria-output-adapter`), facilitando la modularidad y mantenibilidad.
- **Control de Versiones**: El proyecto incluye configuración de `.git` para manejo de versiones.
- **Documentación**: La documentación de la API está disponible mediante Swagger, lo que facilita la interacción y prueba de los endpoints desarrollados.
- **Licencia**: El proyecto está bajo una licencia especificada en el archivo `LICENSE`, importante revisar para entender los derechos de uso y distribución.

#### Endpoints 

**`PersonControllerV1.java`**:
- `/getAll/{database}`
- `/create`
- `/edit/{identification}/{database}`
- `/count/{database}`
- `/getById/{identification}/{database}`
- `/delete/{identification}/{database}`
- `/{identification}/phones/{database}`
- `/{identification}/studies/{database}`
- `/{identification}/{database}`

**`PhoneControllerV1.java`**:
- `/getAll/{database}`
- `/create`
- `/edit/{numero}/{database}`
- `/count/{database}`
- `/getByNumber/{number}/{database}`

**`ProfessionControllerV1.java`**:
- `/getAll/{database}`
- `/create/{database}`
- `/edit/{identification}/{database}`
- `/count/{database}`
- `/getById/{identification}/{database}`
- `/drop/{identification}/{database}`

**`StudyControllerV1.java`**:
- `/getAll/{database}`
- `/save/{database}`
- `/update/{database}/{profession_id}/{person_id}`
- `/delete/{database}/{profession_id}/{person_id}`
- `/getById/{database}/{profession_id}/{person_id}`
- `/count/{database}`

#### Funciones del CLI

### Menú Principal (`MenuPrincipal.java`)
- Trabajar con el Módulo de Personas
- Trabajar con el Módulo de Profesiones
- Trabajar con el Módulo de Teléfonos
- Trabajar con el Módulo de Estudios
- Salir del programa

### Menú de Personas (`PersonMenu.java`)
- Ver todas las personas
- Buscar una persona por identificación
- Crear una persona
- Actualizar una persona
- Eliminar una persona
- Seleccionar base de datos (MariaDB o MongoDB)

### Menú de Teléfonos (`PhoneMenu.java`)
- Ver todos los teléfonos
- Buscar información de un teléfono
- Crear un teléfono
- Actualizar un teléfono
- Eliminar un teléfono
- Seleccionar base de datos (MariaDB o MongoDB)

### Menú de Profesiones (`ProfessionMenu.java`)
- Ver todas las profesiones
- Buscar información de una profesión
- Crear una profesión
- Actualizar una profesión
- Eliminar una profesión
- Seleccionar base de datos (MariaDB o MongoDB)

### Menú de Estudios (`StudyMenu.java`)
- Ver todos los estudios
- Buscar información de un estudio
- Crear un estudio
- Actualizar un estudio
- Eliminar un estudio
- Seleccionar base de datos (MariaDB o MongoDB)

## 4. Autores

Alejandro Uscátegui Torres <br>
Javier Santana <br>
César Maldonado
