/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 01/12/2017.
 */

public class Tour implements Serializable{
    private HashMap<Integer,Long> precios;
    private String lugar;
    private String description;
    private ArrayList<Fecha> fechas;
    private String username;
    private String foto;
    private  int duracion;
    private long id;

    public Tour(long id,HashMap<Integer,Long> precios,String lugar,String description,ArrayList<Fecha> fechas,String username,String foto, int duracion){
        this.id=id;
        this.precios=precios;
        this.lugar=lugar;
        this.description=description;
        this.fechas=fechas;
        this.username=username;
        this.foto=foto;
        this.duracion=duracion;
    }

    public Tour(){}


    public HashMap<Integer, Long> getPrecios() {
        return precios;
    }

    public void setPrecios(HashMap<Integer, Long> precios) {
        this.precios = precios;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Fecha> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<Fecha> fechas) {
        this.fechas = fechas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
