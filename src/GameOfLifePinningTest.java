import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Method;

import java.util.*;

public class GameOfLifePinningTest {
	/*
	 * READ ME: You may need to write pinning tests for methods from multiple
	 * classes, if you decide to refactor methods from multiple classes.
	 * 
	 * In general, a pinning test doesn't necessarily have to be a unit test; it can
	 * be an end-to-end test that spans multiple classes that you slap on quickly
	 * for the purposes of refactoring. The end-to-end pinning test is gradually
	 * refined into more high quality unit tests. Sometimes this is necessary
	 * because writing unit tests itself requires refactoring to make the code more
	 * testable (e.g. dependency injection), and you need a temporary end-to-end
	 * pinning test to protect the code base meanwhile.
	 * 
	 * For this deliverable, there is no reason you cannot write unit tests for
	 * pinning tests as the dependency injection(s) has already been done for you.
	 * You are required to localize each pinning unit test within the tested class
	 * as we did for Deliverable 2 (meaning it should not exercise any code from
	 * external classes). You will have to use Mockito mock objects to achieve this.
	 * 
	 * Also, you may have to use behavior verification instead of state verification
	 * to test some methods because the state change happens within a mocked
	 * external object. Remember that you can use behavior verification only on
	 * mocked objects (technically, you can use Mockito.verify on real objects too
	 * using something called a Spy, but you wouldn't need to go to that length for
	 * this deliverable).
	 */

	/* TODO: Declare all variables required for the test fixture. */
	MainPanel mp;

	@Mock
	Cell c_dead;

	@Mock
	Cell[][] blinker;

	int sizeMP = 5;

	@Mock
	Cell patternCell1, patternCell2, patternCell3, patternCell4, patternCell5;

	@Before
	public void setUp() {
		/*
		 * TODO: initialize the text fixture. For the initial pattern, use the "blinker"
		 * pattern shown in:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns
		 * The actual pattern GIF is at:
		 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_blinker.gif
		 * Start from the vertical bar on a 5X5 matrix as shown in the GIF.
		 */

		// create objects and mocks
		mp = new MainPanel(sizeMP); 
		c_dead = Mockito.mock(Cell.class);
		blinker = new Cell[sizeMP][sizeMP];

		patternCell1 = Mockito.mock(Cell.class);
		patternCell2 = Mockito.mock(Cell.class);
		patternCell3 = Mockito.mock(Cell.class);
		patternCell4 = Mockito.mock(Cell.class);
		patternCell5 = Mockito.mock(Cell.class);


		// Iterate through the cell array filling with dead cells to start
		for (int j = 0; j < sizeMP; j++){
			for (int k = 0; k < sizeMP; k++){
				blinker[j][k] = c_dead;
			}
		}

		// assign behavior and create blinker pattern
		Mockito.when(c_dead.getAlive()).thenReturn(false);
		blinker[2][1] = patternCell1;
		Mockito.when(blinker[2][1].getAlive()).thenReturn(true);
		blinker[1][2] = patternCell2;
		Mockito.when(blinker[1][2].getAlive()).thenReturn(false);
		blinker[2][2] = patternCell3;
		Mockito.when(blinker[2][2].getAlive()).thenReturn(true);
		blinker[3][2] = patternCell4;
		Mockito.when(blinker[3][2].getAlive()).thenReturn(false);
		blinker[2][3] = patternCell5;
		Mockito.when(blinker[2][3].getAlive()).thenReturn(true);

		// set the blinker pattern
		mp.setCells(blinker);

	}

	@After
	public void tearDown() {
		mp = null;
		c_dead = null;
		blinker = null;
	}

	/* TODO: Write the three pinning unit tests for the three optimized methods */
	@Test
	public void testIterateCellReturnFalse(){
		assertFalse("testIterateCellReturnFalse failed!", mp.iterateCell(2,3));
	}

	@Test
	public void testIterateCellReturnTrue(){
		assertTrue("testIterateCellReturnTrue failed!",mp.iterateCell(3,2));
	}

	@Test
	public void testIterateCellMiddle(){
		assertTrue("testIterateCellMiddlefailed!",mp.iterateCell(2,2));
	}

	
	@Test
	public void testCalculateNextIteration(){
		mp.calculateNextIteration();	// horizontal bar created
		int num_c_dead = 0;

		for (int j = 0; j < sizeMP; j++){
			for (int k = 0; k < sizeMP; k++){
				// check that the horizontal blinker cells are true
				if(k == 2 && (j == 1 || j == 2 || j == 3)){
					Mockito.verify(blinker[j][k], Mockito.times(1)).setAlive(true);
				}
				// check that the top and bottom vertical blinker cells are false
				else if (j == 2 && (k == 1 || k == 3)) {
					Mockito.verify(blinker[j][k], Mockito.times(1)).setAlive(false);
				}
				else{
					num_c_dead++;
				}
			}
		}
		// Verifies that none of the cells outside of the horizontal and vertical blinker pattern cells are changed/affected
		Mockito.verify(c_dead, Mockito.times(num_c_dead)).setAlive(false);

	}



	@Test
	public void testBackUpToString(){
		/*ArrayList<String> blinker_string = new ArrayList<String>();

		for (int j = 0; j < sizeMP; j++){
			String row_pattern = "";
			for (int k = 0; k < sizeMP; k++){
				if (j == 2 && (k == 1 || k == 2 || k == 3)){
					row_pattern += "X";
				}
				else{
					row_pattern += ".";
				}

			}
			blinker_string.add(row_pattern);
		}

		assertEquals(blinker_string.toString(), );*/

		assertEquals("X", patternCell1.toString());
		assertEquals(".", patternCell2.toString());

		
	}


}
