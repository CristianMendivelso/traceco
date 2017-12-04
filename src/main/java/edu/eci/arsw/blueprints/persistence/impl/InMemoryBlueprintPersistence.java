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
        HashMap<Integer,Long> preciotemp= new HashMap<Integer,Long> ();
        Tour t= new Tour(0, preciotemp, "Alumbrado Nocturno Cali", "Disfruta del recorrido por el alumbrado navideño", fechatemp, "sergioperez", "https://i.ytimg.com/vi/NtCOOhxS1s8/maxresdefault.jpg", 60);
        tourtemp.add(t);
        Fecha f= new Fecha(0, 0, "23-12-2017 18:00");
        Fecha f1= new Fecha(1, 0, "22-12-2017 18:00");
        Fecha f2= new Fecha(2, 0, "21-12-2017 18:00");
        fechatemp.add(f);
        fechatemp.add(f1);
        fechatemp.add(f2);
        preciotemp.put(1,Long.parseLong("20000"));
        preciotemp.put(2,Long.parseLong("35000"));
        preciotemp.put(3,Long.parseLong("20000"));
        Usuario u= new Usuario("cristiansoto", "Christian Soto", "password","csoto@mail.com", "viajero", Long.parseLong("320323342"),tourtemp, "https://www.definicionabc.com/wp-content/uploads/social/Viajero-2-turismo.jpg");
        Usuario u1= new Usuario("sergioperez", "Sergio Perez", "password","sgperez@mail.com", "guia", Long.parseLong("3145346578"),tourtemp, "http://www.cechotec.cl/web/media/producto/p/59/59-producto_large_92fb5485d7.jpg");

        usuarios.add(u);
         usuarios.add(u1);
        tours.add(t);
        
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
        Usuario temp=null;
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
        usuarios.remove(index);
        usuarios.add(temp);
    }

    
   
}
