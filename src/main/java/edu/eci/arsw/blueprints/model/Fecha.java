/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.model;

/**
 *
 * @author User
 */
public class Fecha {
    
    private long idFecha;
    private long idTour;
    private String fecha;

    
    
    
    public Fecha(){}
    
     public Fecha(long idFecha,long idTour,String fecha){
         this.idFecha=idFecha;
         this.idTour=idTour;
         this.fecha=fecha;
     }
    
    
    public long getIdFecha() {
        return idFecha;
    }

    /**
     * @param idFecha the idFecha to set
     */
    public void setIdFecha(long idFecha) {
        this.idFecha = idFecha;
    }

    /**
     * @return the idTour
     */
    public long getIdTour() {
        return idTour;
    }

    /**
     * @param idTour the idTour to set
     */
    public void setIdTour(long idTour) {
        this.idTour = idTour;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
