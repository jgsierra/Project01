package com.example.gsierra.project01.entidades;

import java.util.Date;

import java.util.Date;


/*
 * Ahora vamos a ver como parsear el objeto JSON en un objeto Java. Para ello primero deberemos crear una clase Java que incluya
 * los campos del objeto JSON. Usando esta librería no tendremos que especificar todos los atributos del objeto JSON que vamos
 * a parsear. Si echamos un vistazo a la información que obtenemos en cada petición, el objeto JSON que obtenemos consta
 * únicamente de string, números y arrays de string, a excepcion del atributo poster que es un objeto JSON anidado.
 * Por lo tanto deberemos crear dos clase una para la información de la película y otra para la del póster. El nombre de las
 * propiedades de las clases deben coincidir con el nombre de los atributos del objeto JSON que vamos a seleccionar: */
public class Clientes {


    private int Codigo;
    private String Nombre;
    private String Apellido;
    private String Fecha_Nac;
    private String Ciudad;
    private String Provincia;
    private int Edad;
    private String Direccion;
    private String Telef_fijo;
    private String Telef_movil;
    private String Sexo;
    private String Ocupacion;
    private String Email;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getFecha_Nac() {
        return Fecha_Nac;
    }

    public void setFecha_Nac(String fecha_Nac) {
        Fecha_Nac = fecha_Nac;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelef_fijo() {
        return Telef_fijo;
    }

    public void setTelef_fijo(String telef_fijo) {
        Telef_fijo = telef_fijo;
    }

    public String getTelef_movil() {
        return Telef_movil;
    }

    public void setTelef_movil(String telef_movil) {
        Telef_movil = telef_movil;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }



}
