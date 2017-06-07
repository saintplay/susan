# SUSAN #
## PRE-REQUISITOS ##

- Tener la variable JAVA_HOME en el PATH
- Tener instalado Heroku-CLI
- Tener instalado Maven

## PASOS PARA EJECUTAR ##

1. `mvn clean install` (*ejecutar 1 sola vez*)
2. `mvn package`
3. `heroku local -f Procfile.window`
4. Abrir el proyecto en localhost:3000
