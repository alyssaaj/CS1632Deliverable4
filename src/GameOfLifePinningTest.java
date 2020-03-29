import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Method;

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
	Cell c;

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
		int sizeMP = 5;
		mp = new MainPanel(sizeMP); 
		c = Mockito.mock(Cell.class);
		ArrayList<String> blinker = new ArrayList<String>();
		for (int i = 0; i < sizeMP; i++){
			String cellpattern = "";
			for (int j = 0; j < sizeMP; j++){
				if(i == 2 && (j == 1 || j == 2 || j== 3)){
					cellpattern +="X";
				}
				else{
					cellpattern += ".";
				}
			}
			blinker.add(cellpattern);
		}

		mp.load(blinker);

	}

	@After
	public void tearDown() {
		mp = null;
		c = null;
	}

	/* TODO: Write the three pinning unit tests for the three optimized methods */
	@Test
	public void testIterateCellReturnFalse(){
		mp.run();
		mp.stop();

		assertEquals("false", mp.iterateCell(0,0));
	}
	
	@Test
	public void testIterateCellReturnTrue(){
		mp.run();
		mp.stop();

		assertEquals("true", mp.iterateCell(2,2));
	}

	@Test
	public void testCalculateNextIteration(){
		
	}

	@Test
	public void test3(){
		
	}


}
