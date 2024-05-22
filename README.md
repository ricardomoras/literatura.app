Sistema de Gestión de Libros y Autores
Este proyecto es una aplicación de consola en Java utilizando Spring Boot y JPA para la gestión de libros y autores. Proporciona funcionalidades para buscar libros, listar libros y autores registrados, y más.

Requisitos
Java 8 o superior
Maven
Base de datos compatible con JPA (por ejemplo, MySQL, PostgreSQL)
Instalación y Ejecución
Clonar el Repositorio:

bash
Copy code
git clone https://github.com/ricardomoras
cd literatura.app
Configurar la Base de Datos:

Configura las propiedades de conexión a la base de datos en application.properties.
Compilar y Ejecutar:

bash
Copy code
mvn clean package
java -jar target/literatura.app.jar
Uso
Al ejecutar la aplicación, se mostrará un menú interactivo donde podrás seleccionar diversas opciones:

Buscar libro por título
Buscar libros registrados por título
Listar libros registrados
Listar autores registrados
Listar autores vivos en un determinado año
Listar libros por idioma
Selecciona una opción ingresando el número correspondiente y sigue las instrucciones en pantalla.

Autores
Ricardo Mora
