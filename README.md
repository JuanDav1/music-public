# Music App

Instala Music App para visualizar los artistas Top junto a sus respectivos datos proporcionados por la api de Last Fm

## Logo de la App
<img src="https://user-images.githubusercontent.com/31577004/91366692-10d49a00-e7ca-11ea-9d0c-53e96c5b3c4c.png" widh="200" />

## Requerimientos
La App tiene como propocito presentar la prueba tecnica nivel I junto con funcionalidades adicionales

* EL usuario puede visualizar una lista a doble columna de los artistas Top, cada item es traido de http://ws.audioscrobbler.com/2.0 (Url Root de la Api de Last Fm) del metodo GEO propio de la APi
* EL usuario visualiza cada item con una imagen (proporcionada por la APi) y un titulo que corresponde al nombre del artista.
<img src="https://user-images.githubusercontent.com/31577004/91367700-c30d6100-e7cc-11ea-8788-6403010b1619.jpeg" width="200" />

* EL usuario puede presionar cada item para visulizar una nueva ventana con mas informacion del artista en donde se incluye una url para ir directamente a la pagina del arista en Last Fm
<img src="https://user-images.githubusercontent.com/31577004/91367742-e0422f80-e7cc-11ea-8d5d-c4a62b38ec4e.jpeg" width="200" />

## Requerimientos implicitos 
* Cuando se genera un fallo al consumir los datos de la Api, se visualiza un mensaje de error

<img src="https://user-images.githubusercontent.com/31577004/91368268-4085a100-e7ce-11ea-9739-f3eea21248f7.jpeg" width="200" />

* Cuando por primera Vez consume los datos de la APi se visualiza un icono de cargando en la mitad de la pantalla 
* Despues de verificar que no hay registros almacenados la App trae los datos de la Api y los almacena en la base de datos

<img src="https://user-images.githubusercontent.com/31577004/91368506-e76a3d00-e7ce-11ea-86e1-754f728090e7.PNG"/>

* Cuando la base de datos se encuentra con registros, se hace una consulta para traer los datos y mostrarlos los items en la Aplicación

## Configuración del proyecto
* Clonar el proyecto y abrirlo en Android Studio
* Construir el proyecto para generar todos los archivos propios de Dagger y Ejecutar el proyecto

## Arquitectura
La arquitectura de la aplicacion es basada en clean Architecture y esta compuesta por varias capas, utiliza el patron de arquitectura **MVVM** 

* **Injection**: Los modulos y el componente correspondientes a la inyeccion de dependencias con DAgger 2.
* **Repositories**: Contiene el repositorio local con un flujo de datos hacia la  base de datos y el repositorio remoto con un flujo de datos hacia la Api.
* **DataBase**: Contiene la base de datos y la Interfaz para todos los metodos correspondientes a la base de datos (Insert , Query).
* **Api**: Contiene el metodo con la url para traer los datos de la APi
* **Models**: Todos los objetos para modelar los datos traidos de la APi y el modelo para guardar registros en la base de datos.
* **ViewModels**: En donde se conecta la vista con el modelo, recibe los datos de los repositorios (Base de datos - Api) y los envia a la vista.
* **Views**: La informacion del Activity y el Fragment ligados a la interfaz

## Tecnologia Utilizada

Aplicacion nativa de Android, Android Studio version 4.0, version minima de Android 5 lollipop.

* minSdkVersion 22
* targetSdkVersion 29
* Kotlin Version 1.3.72


## Librerias

```
Dagger 2

Material Component

Coroutines

Retrofit2

Room

LiveData

```

## Testing
En la arquitectura se tuvo en cuenta los principios SOLID, se intento cumplir a grandes rasgos con cada principio

* Test en el ViewModel, evaluando el repositorio remoto, se crea un fake repositorio para los diferentes estados de los datos consumidos por la Api
    * Repositorio cuando trae datos de la Api
    * Repositorio cuando existe un error al traer datos de la APi
    * Repositorio cuando trae datos vacios de la Api
En cualquiera de los 3 casos anteriores se evaluan los LiveData de cada condición y se verifica que los estados los el metodo del ViewModel se encuentra bien estructurado

## Librerias


```
Mock

Kotlinx-Coroutines

Junit

```
 
## Diseño 
Basado en Material Component



#### by Juan David Gutiérrez



  
