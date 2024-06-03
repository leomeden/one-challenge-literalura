# Oracle One G6 - Challenge Literalura
<br>

![Literalura](images/literalura.png "Literalura")

<br><br>

### 1. Resumen del proyecto. [:arrow_right:](#Resumen-del-proyecto)
### 2. Guía de uso. [:arrow_right:](#Guía-de-uso)
### 3. Tratamiento de errores. [:arrow_right:](#Tratamiento-de-errores)
### 4. Licencia. [:arrow_right:](#Licencia)


  

<br><br><br>

----

<br>

## Resumen del proyecto
<br>

El presente documento, corresponde al Challenge LiterAlura del 
curso Back-End "Java y Spring Boot G6 - ONE". 

Esta es una pequeña solución informática que mediante un menú de consola de 
comandos permite la búsqueda de libros en la API [GUTENDEX.](https://gutendex.com/)

Una vez seleccionado un libro este se guarda en una Base de Datos relacional 
para ser consultados tanto los datos de los Libros como de los Autores.

Fue diseñada y construida en lenguaje Java utilizando el Framework Spring y 
en particular Spring-Data para la gestión de la comunicación con la Base de Datos.

La base de datos utilizada fue PostgreSQL.

Volver al Menú [:arrow_up:](#Oracle-One-G6---Challenge-Literalura)

<br><br>

----

<br>

## Guía de uso

### Menú Principal

##### [1- Buscar Libro por Título o Autor](#Buscar-Libro-por-Titulo-o-Autor)
##### [2- Listar Libros Registrados](#Listar-Libros-Registrados)
##### [3- Listar Autores Registrados](#Listar-Autores-Registrados)
##### [4- Listar Autores vivos según año](#Listar-Autores-por-año)
##### [5- Listar Libros por Idioma](#Listar-Libros-por-Idioma)
##### [0- Salir](#Salir)


Al iniciar la aplicación se despliega un menú con las opciones disponibles.

![Menú Principal](images/menu_principal.png "Menú Principal")

#### Buscar Libro por Titulo o Autor
Al ingresar un título o autor (o parte de éste) la aplicación buscará en la 
API [GUTENDEX](https://gutendex.com/) los resultados a listar. Luego imprimirá 
los mismos en pantalla numerados e indicando los titulos y primer autor de los mismos.

Luego de esto solicitará ingresar un número correspondiente a uno de los libros listados.

![Búsqueda](images/busqueda1.png "Busqueda con cantidad de resultados menor a 25")

Si la consulta a la API arroja mas de 25 resultados exibirá en pantalla un mensaje 
indicando esto y solicitará si se desea continuar o abortar la busqueda.

![Búsqueda cantidad mayor a 25](images/cant_mayor_25.png "Busqueda con cantidad de resultados mayor a 25")

En caso de que se indique continuar irá mostrando un progreso de porcentaje a medida que 
recibe los resultados de la API ya que puede demorar un tiempo considerable si la cantidad de 
libros a listar es demasiado extensa.

![Progreso de búsqueda](images/progreso_busqueda.png "Progreso de búsqueda")

Luego de ingresar el número correspondiente al libro que se desea guardar se imprimirá
en pantalla la confirmación de que fue guardado junto con la descripción del mismo.

![Guardado de libro](images/guardado_libro.png "Guardado de libro")

#### Listar Libros Registrados

Al elegir la opción 2 se muestra un listado de los libros registrados con el detalle de los mismos (Título / Autores / Cant. Descargas / Idiomas).

![Libros registrados](images/libros_registrados.png "Listado de Libros Registrados")

#### Listar Autores Registrados

Ingresando la opción 3 se muestra un listado de los autores con su detalle (Nombre / Año de Nacimiento 
/ Año de Fallecimiento / Libros ya registrados del Autor)

![Autores regitrados](images/listado_autores.png "Listado de Autores")

#### Listar Autores por año

En la opción 4 solicita el ingreso de una año (4 dígitos) y devuelve un listado de autores que se 
encontraban vivos en el año ingresado con sus respectivos detalles.

![Autores vivos en determminado año](images/autores_por_anio.png "Listado de Autores vivos en determinado año")

#### Listar Libros por Idioma

En esta opción solicita el ingreso de un idioma. Este puede ser ingresado en cualquier combinación de mayúsculas 
y minúsculas e incluso puede contener o no acentos). 

El idioma debe ser ingresado según con la configuración regional de la PC del usuario. 
Por ejemplo si la PC está configurada en español latino debería ingresar Inglés o ingles para buscar libros de 
este idioma. Si la PC está configurada con el dioma inglés para la misma búsqueda debería ingresar English o english.

![Libros por Idioma](images/busqueda_idioma.png "Listado de libros según Idioma")

#### Salir

Se muestra un salido en pantalla y finaliza la aplicación.

![Salir](images/salir.png "Salir")

Volver al Menú [:arrow_up:](#Oracle-One-G6---Challenge-Literalura)

<br><br>

----

<br>

## Tratamiento de errores

Los posibles errores, tanto por ingreso incorrecto desde el teclado como por 
búsquedas que no arrojen resultados o resultados inválidos fueron tratados para 
evitar romper el flujo normal del programa.

Se muestran algunos ejemplos:

Errores de ingreso incorrecto en el menú principal
<br>
<br>
![Salir](images/error_menu1.png "Salir")
![Salir](images/error_menu2.png "Salir")
<br>
<br>
<br>
Evita duplicación de libro en Base de Datos
<br>

![Salir](images/error_evita_duplicado.png "Salir")
<br>
<br>
Informa si no se encontró coincidencias según lo ingresado en la búsqueda
<br>
![Salir](images/error_libro_no_encontrado.png "Salir")
<br>
<br>
Informa diferentes errores en el ingreso del año que se desea buscar autores
<br>
![Salir](images/error_anio.png "Salir")
<br>
<br>

Volver al Menú [:arrow_up:](#Oracle-One-G6---Challenge-Literalura)

<br><br>

----

<br>

## Licencia

Licencia MIT

La licencia MIT fue la establecida al crear el repositorio en GitHub y está disponible 
para su lectura en el mismo repositorio a través de este [enlace](LICENSE).

Volver al Menú [:arrow_up:](#Oracle-One-G6---Challenge-Literalura)

<br><br>

