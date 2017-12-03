/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.model;

import java.util.ArrayList;

/**
 * Created by User on 03/12/2017.
 */

public class Usuario {

    private String nombre;
    private String password;
    private String correo;
    private String tipo;
    private long telefono;
    private String username;
    private ArrayList<Tour> tours;
    private String foto;


    public Usuario(String username,String nombre,String password,String correo,String tipo,long telefono,ArrayList<Tour> tours,String foto){
        this.username=username;
        this.nombre=nombre;
        this.password=password;
        this.correo=correo;
        this.tipo=tipo;
        this.telefono=telefono;
        this.tours=tours;
        this.foto=foto;
    }


    public Usuario(){

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the tours
     */
    public ArrayList<Tour> getTours() {
        return tours;
    }
    
    public void setTours(ArrayList<Tour> tours) {
       this.tours=tours;
    }
}
