package br.net.reichel.vivareal.domain.estate;

import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;
import gnu.trove.TIntProcedure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChristianReichel on 9/26/2016.
 */
public class PropertyRepositoryDefaultTest {

    @Test
    public void shouldLoadData() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault();
        //When
        repository.loadData();
        //Then
        assertFalse("no property loaded", repository.getPersistence().isEmpty());
        assertTrue(3 == repository.getPersistence().size());
    }

    @Test
    public void shouldQueryWithJSI() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault();
        //When
        //repository.loadData();
        //Then

        SpatialIndex si = new RTree();
        si.init(null);

        Rectangle[] rects = new Rectangle[]{
                new Rectangle(0, 0, 0, 0),
                new Rectangle(0, 1, 0, 1),
                new Rectangle(1, 0, 1, 0),
                new Rectangle(1, 1, 1, 1),
                new Rectangle(2, 2, 2, 2),
                new Rectangle(4, 4, 4, 4),
        };

        for (int i = 0; i < rects.length; i++) {
            si.add(rects[i], i + 1000);
        }

        class SaveToListProcedure implements TIntProcedure {
            private List<Integer> ids = new ArrayList<>();

            public boolean execute(int id) {
                ids.add(id);
                return true;
            }

            ;

            private List<Integer> getIds() {
                return ids;
            }
        }
        ;

        SaveToListProcedure myProc = new SaveToListProcedure();
        si.contains(new Rectangle(3, 3, 5, 5), myProc);

        List<Integer> ids = myProc.getIds();

        for (Integer id : ids) {
            System.out.println("id: " + id + " - " + rects[id].toString() + " was contained");
        }

        // Actually that was a really long winded (and inefficient) way of
        // printing out the rectangles. Would be better to use an anonymous
        // class to just do the printing inside the execute method. That is

    }

}