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
