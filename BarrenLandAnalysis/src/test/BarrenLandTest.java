package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.target.barrenlandanalysis.BarrenLand;

public class BarrenLandTest {

	 
    BarrenLand bla = new BarrenLand();
    
    public BarrenLandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     *This was an original sample input and output provided in case study 
     */
    @Test
    public void testFindFertileLand() {
        String[] strSTDIN = {"0 292 399 307"};
        String STDOUT = "116800 116800";
            
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
    }
    
    /**
     *This was another original sample input and output provided in case study 
     */
    @Test
    public void testFindFertileLand2() {
        String[] strSTDIN = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};
        String STDOUT = "22816 192608";
         
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
    }
    
    @Test
    public void testFindFertileLand3() {
        String[] strSTDIN = {"0 0 1 1"};
        String STDOUT = "239996";
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
  
    }
    
    @Test
    public void testFindFertileLand4() {
        String[] strSTDIN = {"0 0 0 599", "2 0 2 599"};
        String STDOUT = "600 238200";
          
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
    }
    

    @Test
    public void testFindFertileLand5() {
        String[] strSTDIN = {"0 0 0 599", "2 0 2 599", "3 2 399 3"};
        String STDOUT = "600 794 236612";
           
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
    }
    
    @Test
    public void testFindFertileLand6() {
        String[] strSTDIN = {"0 0 0 599", "2 0 2 599", "3 2 399 3", "5 0 5 1"};
        String STDOUT = "4 600 788 236612";
        
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
    }

    @Test
    public void testFindFertileLand7() {
        String[] strSTDIN = {"0 0 0 599", "2 0 2 599", "3 2 399 3", "5 0 5 1", "10 4 12 599"};
        String STDOUT = "4 600 788 4172 230652";
        
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
  
    }
    
    @Test
    public void testFindFertileLand8() {
        String[] strSTDIN = {"0 0 399 599"};
        String STDOUT = "No fertile land available.";
        
        assertEquals(STDOUT, BarrenLand.findFertileLand(strSTDIN));
  
    }
    
}
