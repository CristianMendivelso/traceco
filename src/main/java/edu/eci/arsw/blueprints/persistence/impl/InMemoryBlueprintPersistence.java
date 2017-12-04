/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Fecha;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.model.Tour;
import edu.eci.arsw.blueprints.model.Usuario;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Tour> tours = new ArrayList<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        ArrayList<Tour> tourtemp= new ArrayList<Tour>();
        ArrayList<Fecha> fechatemp= new ArrayList<Fecha>();
        Fecha f= new Fecha(0, 0, "23-12-2017 18:00");
        Fecha f1= new Fecha(1, 0, "22-12-2017 18:00");
        Fecha f2= new Fecha(2, 0, "21-12-2017 18:00");
        fechatemp.add(f);
        fechatemp.add(f1);
        fechatemp.add(f2);
        HashMap<Integer,Long> preciotemp= new HashMap<Integer,Long> ();
        preciotemp.put(1,Long.parseLong("20000"));
        preciotemp.put(2,Long.parseLong("40000"));
        preciotemp.put(3,Long.parseLong("50000"));
        
        Tour t= new Tour(0, preciotemp, "Alumbrado Nocturno Cali", "Disfruta del recorrido por el alumbrado navideño", fechatemp, "carlosr", "https://i.ytimg.com/vi/NtCOOhxS1s8/maxresdefault.jpg", 60);
        Tour t2= new Tour(0, preciotemp, "Recorrido Diurno Cali", "Viaje En Coche Por la Ciudad", fechatemp, "carlosr", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Augusto_Ilian_Rio_Cali_Cali.jpg/210px-Augusto_Ilian_Rio_Cali_Cali.jpg", 60);
        tourtemp.add(t);
        tourtemp.add(t2);
        ArrayList<Tour> tourtemp2= new ArrayList<Tour>();
        ArrayList<Tour> tourtemp3= new ArrayList<Tour>();

        HashMap<Integer,Long> preciotemp2= new HashMap<Integer,Long> ();

        preciotemp2.put(1,Long.parseLong("50000"));
        preciotemp2.put(2,Long.parseLong("90000"));
        preciotemp2.put(3,Long.parseLong("135000"));
        Tour t3= new Tour(0, preciotemp2, "Cerrro de Monserrate Bogota", "Incluye Subida a telesférico", fechatemp, "sergioperez", "http://du4zwgdg3nwxa.cloudfront.net/empresas/imagenes/a/au/auzlibvr-1354910060-bg.jpg", 60);
        Tour t4= new Tour(0, preciotemp2, "Torre Colpatria Bogota", "Incluye Subida a telesférico", fechatemp, "sergioperez", "https://assets.metrolatam.com/co/2016/11/30/torre-colpatria-1200x600.jpg", 60);
        tourtemp3.add(t3);
        tourtemp3.add(t4);
        Usuario u= new Usuario("csoto", "Christian Soto", "password","csoto@mail.com", "viajero", Long.parseLong("320323342"),tourtemp2, "https://www.definicionabc.com/wp-content/uploads/social/Viajero-2-turismo.jpg");
        Usuario u1= new Usuario("carlosr", "Carlos Ramirez", "password","carlosr@mail.com", "guia", Long.parseLong("3145346578"),tourtemp, "http://www.cechotec.cl/web/media/producto/p/59/59-producto_large_92fb5485d7.jpg");
        Usuario u2= new Usuario("sergioperez", "Sergio Perez", "password","sergioperez@mail.com", "guia", Long.parseLong("3145346578"),tourtemp3, "http://hotelcenter.com.br/blog/wp-content/uploads/2016/07/4-dicas-para-se-tornar-um-bom-guia-turistico-1000x563.jpg");
        usuarios.add(u);
        usuarios.add(u1);
        usuarios.add(u2);
        tours.add(t);
        tours.add(t2);
        tours.add(t3);
        tours.add(t4);
        
    }

    @Override
    public void saveUser(Usuario u) throws BlueprintPersistenceException {
        for (Usuario temp:usuarios){
            if(temp.getUsername().equals(u.getUsername())){
                throw new BlueprintPersistenceException("Username Repetido");
            }
        }
        usuarios.add(u);
    }

    @Override
    public Usuario getUser(String username) throws BlueprintNotFoundException {
        Usuario ans=null;
        for (Usuario temp:usuarios){
            if(temp.getUsername().equals(username)){
                ans=temp;
                break;
            }
        }
        if(ans==null){
            throw new BlueprintNotFoundException("Usuario no encontrado");
        }
        return ans;
    }

    @Override
    public Tour getTour(String name, String username) throws BlueprintNotFoundException {
        Tour ans=null;
        for (Tour temp:tours){

            if(temp.getUsername().equals(username) && temp.getLugar().equals(name)){
                ans=temp;
                break;
            }
        }
        if(ans==null){
            throw new BlueprintNotFoundException("Tour no encontrado");
        }
        return ans;
    }

    @Override
    public List<Tour> getAllTours() {
        return tours;
    }

    @Override
    public void SaveTour(Tour t) {
        tours.add(t);
        int index=0;
        Usuario temp=null;
        for (int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getUsername().equals(t.getUsername())){
                temp=usuarios.get(i);
                ArrayList tourstemp= temp.getTours();
                tourstemp.add(t);
                index=i;
                temp.setTours(tourstemp);
                break;
            }
        }
        usuarios.remove(index);
        usuarios.add(temp);
        
    }

    @Override
    public void editUser(String username,String correo, Long telefono, String foto) throws BlueprintPersistenceException {
        int index=0;
        Usuario temp=null;
        for (int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getUsername().equals(username)){
                temp=usuarios.get(i);
                index=i;
                temp.setCorreo(correo);
                temp.setTelefono(telefono);
                temp.setFoto(foto);
                break;
            }
        }
        usuarios.remove(index);
        usuarios.add(temp);
        
        
    }
    
    @Override
    public void ComprarTour(Tour t,String username){
   
        int index=0;
        int indexfecha=0;
        int indextour=0;
        Usuario temp=null;
        Tour temp2=null;
        ArrayList<Fecha> fechas=null;

        for (int i=0;i<usuarios.size();i++){
            if(usuarios.get(i).getUsername().equals(username)){
                temp=usuarios.get(i);
                index=i;
                ArrayList tours= temp.getTours();
                tours.add(t);
                temp.setTours(tours);
                break;
            }
        }
        for (int i=0;i<tours.size();i++){
            if(tours.get(i).getUsername().equals(t.getUsername()) && tours.get(i).getLugar().equals(t.getLugar()) ){
                temp2=tours.get(i);
                indextour=i;
                fechas= temp2.getFechas();
                for (int j=0;j<fechas.size();j++){
                    if (fechas.get(j).getFecha().equals(t.getDescription())){
                        indexfecha=j;
                        break;
                    }
                }
                fechas.remove(indexfecha);
                temp2.setFechas(fechas);
                break;
            }
        }
        tours.remove(indextour);
        tours.add(temp2);
        usuarios.remove(index);
        usuarios.add(temp);
    }

    
   
}
