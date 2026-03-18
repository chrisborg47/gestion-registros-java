# 📚 Sistema de Gestión de Registros en Java

Aplicación desarrollada en **Java** que implementa un sistema **CRUD** (Crear, Leer, Actualizar y Eliminar) utilizando **arquitectura por capas** y **persistencia en archivos de texto (.txt)**, sin uso de bases de datos.

---

## 🎯 Objetivo del proyecto

El propósito de este sistema es demostrar el dominio de los siguientes conceptos:

- Arquitectura por capas
- Separación de responsabilidades
- Manejo de archivos de texto para persistencia
- Implementación de operaciones CRUD
- Validación de datos
- Manejo de errores en Java
- Uso documentado de Inteligencia Artificial como herramienta de apoyo

---

## 🏗 Arquitectura utilizada

El proyecto está organizado en capas, respetando la separación de responsabilidades:

### 📦 Entidades
Contiene las clases que representan la información del sistema.

- `Registro`

### 💾 Acceso a Datos
Se encarga de leer, guardar, actualizar y eliminar registros en archivos de texto.

- `RegistroDAO`

### 🧠 Lógica de Negocio
Contiene las validaciones y reglas que deben cumplirse antes de operar con los datos.

- `RegistroService`

### 🖥 Presentación
Gestiona la interacción con el usuario mediante consola.

- `MenuConsola`

### 🚀 Clase principal
Punto de entrada de la aplicación.

- `GestionRegistrosCapas`

---

## ⚙ Funcionalidades

El sistema permite realizar las siguientes operaciones:

- ➕ Agregar registro
- 📋 Listar registros
- 🔍 Buscar registro por ID
- ✏ Actualizar registro
- ❌ Eliminar registro

---

## 📁 Persistencia de datos

La información se almacena en un archivo de texto llamado:

```txt
registros.txt

✅ Validaciones y mejoras implementadas

El sistema incorpora validaciones y mejoras para asegurar un funcionamiento más robusto:

Verificación de campos vacíos

Validación de ID mayor que 0

Prevención de IDs repetidos

Normalización de textos con trim()

Manejo de errores con try-catch

Uso de try-with-resources para lectura y escritura de archivos

Lectura de enteros en consola con nextLine() + Integer.parseInt() para evitar errores comunes de Scanner

Ignora líneas inválidas en el archivo sin detener toda la operación

Saneamiento de textos para evitar que el separador | corrompa el formato del archivo

Uso de archivo temporal para actualización y eliminación segura de registros

🛠 Tecnologías utilizadas

Java

NetBeans

Java with Ant

Archivos .txt para persistencia de información

▶ Cómo ejecutar el proyecto

Abrir el proyecto en NetBeans.

Compilar el proyecto.

Ejecutar la clase principal:

GestionRegistrosCapas

Utilizar el menú en consola para realizar las operaciones CRUD.

📂 Estructura del proyecto
src/
├── entidades/
│   └── Registro.java
├── datos/
│   └── RegistroDAO.java
├── logica/
│   └── RegistroService.java
├── presentacion/
│   └── MenuConsola.java
└── GestionRegistrosCapas.java
🔍 Consideraciones técnicas

Durante la revisión y mejora del proyecto se verificó que:

La arquitectura por capas se mantiene correctamente

MenuConsola solo gestiona entrada y salida de datos del usuario

RegistroService concentra validaciones y reglas simples del sistema

RegistroDAO es la única clase con acceso directo al archivo registros.txt

No existe acceso directo a archivos fuera de la capa de datos

La aplicación se mantiene completamente por consola

No se utiliza base de datos ni interfaces gráficas

🤖 Uso de Inteligencia Artificial

Durante el desarrollo del proyecto se utilizaron herramientas de Inteligencia Artificial como apoyo para:

Proponer la estructura inicial del sistema

Generar clases base

Corregir errores de compilación y lógica

Mejorar validaciones y separación por capas

Optimizar el manejo de archivos

Revisar la robustez general del CRUD

El uso de IA fue documentado en el archivo:

IA_prompts.txt
📘 Consideraciones importantes

El proyecto no utiliza bases de datos.

Toda la persistencia se realiza mediante archivos de texto.

La aplicación fue desarrollada con un enfoque académico, priorizando claridad, orden y comprensión del código.

Se realizaron ajustes para que el manejo de registros.txt funcione correctamente en distintos contextos de ejecución del proyecto.
