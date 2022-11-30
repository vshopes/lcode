# Conceptos de CI

Integración Continua

## Origen
SDLC Software development life cycle
- Planificación
- Desarrollo
- Test
- Despliegue

1->Análisis de Requisitos, 2->Diseño, 3->Implementación, 4->Test, 5->Evolución, 1->

**Waterfall**, 
inconvenientes: el software solo funciona al final del proceso, incertidumbre, el cambio constante es un problema, la ingración es al final del proceso, no existen referecnias pasadas
ventajas: los requisitos están bien documentados, se opta por tecnología fija

**Agile**, Entregas mas cortas, integraciones mas rápidas 
Principios Agile:
- entregas tempranas del software
- los cambios de requisitos son bienvenidos 
- el software es puesto en producción frecuentamente
- cooperación diaria entre negocio y áreas técnicas.
- proyectos son construidos de manera individual
- el cara a cara es la mejor forma de comunicación, no email
- el software que funciona es la principal medida de desarrollo
- Desarrollo sostenible, desarrollo constante
- Atención continua a la excelecnia técnica
- simplicidad a través de la automatización
- los equipos se autogestionan, los mejores productos son los que salen de equipos autogestionados
- adaptación regular a los cambios de circunstancias

Cómo funciona Agile:
- Definir req de alto nivel
- se desarrolla caracteristica1
- integración y test (git )
- se desarrolla característica2
- integración y test
- Release 
- Review
- No
- Incorporate changes
  
Ventajas Agile
- La funcionalidad puede ser desarrollada y presentada rápidamente
- Se encesitan menos recursos
- Promueve el trabajo en equipo y el conocimiento transversal
- encaja con proyectos que cambian frecuentemente
- Documentación minimalista
- Requiere de poca planificación
- Desarrollo en paralelo

## Definir CI

Integración continua: Cómo funciona
se realiza un build de un artefacto

- feature 
- develop and commit (rama en git)
- build & test --> sierror en build o test volvemos a desarrollo
- integración --> Si conflictos volver a desarrollo
- build 
- Integration testing
- Package - Artefacto finalizado
Ojo CD Continuous Delivery es otra cosa, sería la entrega del artefacto en productivo. Para el CD hay mul titud de herarmientas, tambien depende de la arquitectura. 

### Agile & CI

El motor de agile es la integración continua.

## Elemtos ue componen CI

- Sistema de control de versiones, la más usada GIT
- Estrategia de Branching, ejemplo Git Flow, ojo con proveedores externos como github puede dar problemas
- Herramienta de CI: Jenkins, Travis, GitHub Actions
- self-triggered builds
- code coverage tools: JaCoCo, Codecov, Coveralls
- static code analysis (white box test): SonarQube
- Automated Testing
- Binary repository tools: Nexus, Artifactory
- Automated packaging

### CI Tool


  
  

  
