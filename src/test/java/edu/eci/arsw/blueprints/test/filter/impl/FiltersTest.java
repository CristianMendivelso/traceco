/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.filter.impl;

import edu.eci.arsw.blueprints.filters.impl.RedundanceFilter;
import edu.eci.arsw.blueprints.filters.impl.SubFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author 2105461
 */
public class FiltersTest {
    
    @Test
    public void RedundanceFilterTest() throws BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10),new Point(0, 0), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        RedundanceFilter rf = new RedundanceFilter();
        Blueprint bpAns = rf.filtrar(bp);
        Point [] esperado = new Point[]{new Point(0, 0), new Point(10, 10),new Point(0, 0), new Point(10, 10)};
        assertEquals(Arrays.asList(esperado),bpAns.getPoints());
    }
    
    
    @Test
    public void RedundanceFilterConsecutivoRepetidoTest() throws BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts = new Point[]{new Point(0, 0), new Point(0, 0),new Point(0, 0),new Point(0, 1), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        RedundanceFilter rf = new RedundanceFilter();
        Blueprint bpAns = rf.filtrar(bp);
        Point [] esperado = new Point[]{new Point(0, 0), new Point(0, 1),new Point(10, 10)};
        assertEquals(Arrays.asList(esperado),bpAns.getPoints());
    }

    @Test
    public void SubFilterTest() throws BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts = new Point[]{new Point(0, 0), new Point(10, 10),new Point(0, 1), new Point(10, 10)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        SubFilter sf = new SubFilter();
        Blueprint bpAns = sf.filtrar(bp);
        Point [] esperado = new Point[]{new Point(0, 0), new Point(0, 1)};
        assertEquals(Arrays.asList(esperado),bpAns.getPoints());
    }
}
