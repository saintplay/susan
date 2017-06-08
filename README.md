<div align="center">
  <img src="https://cdn.rawgit.com/saintplay/susan/master/src/main/resources/static/img/susan.svg" width="1200">
</div>

## PRE-REQUISITOS ##

- Tener Java jdk (> 1.8) en el sistema
- Tener la variable JAVA_HOME en el PATH
- Tener instalado Heroku CLI
- Tener instalado Maven (`mvn`)

## PASOS PARA EJECUTAR ##

1.  Hacer una copia de la base de datos, el backup más reciente se puede descargar aquí: <https://drive.google.com/open?id=0B_Xq0VghEDqIYjBWdWcwTTI0WkE>
*(El script no crea la base de datos, solo crea y/o actualiza las tablas)*
2. Crear un archivo "**.env**" en la raíz del proyecto. Llenar los datos de acceso a la base de datos en dicho archivo. (*Guiarse de "**.env.example**"*)
3. `mvn install` (*ejecutar solo para descargar las librerias*)
4. `mvn package` (*ejecutar despues de cambiar el código*) 
5. `heroku local -f Procfile.windows`
6. **Listo!** El proyecto utilizará el puerto 3000 por defecto; para acceder a la aplicación entrar a <http://localhost:3000/> en un navegador.

<div align="center">
  <img src="https://cloud.githubusercontent.com/assets/9372893/16879913/501dca4a-4a78-11e6-9783-3600e0b260d8.png">
</div>
