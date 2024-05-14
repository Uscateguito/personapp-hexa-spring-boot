# Taller de Arquitectura de Software
## Arquitectura Hexagonal

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

#### Puntos Adicionales

- **Estructura de Directorios**: El proyecto está organizado en múltiples módulos como `domain`, `application`, y adaptadores específicos (`rest-input-adapter`, `cli-input-adapter`, `mongo-output-adapter`, `maria-output-adapter`), facilitando la modularidad y mantenibilidad.
- **Control de Versiones**: El proyecto incluye configuración de `.git` para manejo de versiones.
- **Documentación**: La documentación de la API está disponible mediante Swagger, lo que facilita la interacción y prueba de los endpoints desarrollados.
- **Licencia**: El proyecto está bajo una licencia especificada en el archivo `LICENSE`, importante revisar para entender los derechos de uso y distribución.

Estos puntos abarcan las recomendaciones típicas para una buena documentación de proyecto, asegurando que los usuarios y desarrolladores tengan la información necesaria para entender, configurar y ejecutar el proyecto adecuadamente.
