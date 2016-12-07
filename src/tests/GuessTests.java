package tests;

import static org.junit.Assert.*;
import org.junit.*;

import enums.Color;
import mastermind.*;
import exceptions.*;

public class GuessTests {
	private Color[] keyAllDiff = {Color.GREEN, Color.WHITE, Color.ORANGE, Color.YELLOW};
	private Color[] keyWithRepeats = {Color.RED, Color.WHITE, Color.BLUE, Color.RED};
	
	@Test
	public void testCheckGuessAllPegsSameColor()
	{
		Color[] guessAllSame = {Color.RED, Color.RED, Color.RED, Color.RED};
		Guess g = new Guess(keyAllDiff, guessAllSame);
		g.checkForReds();
		g.checkForWhites();
		assertEquals(0, g.getNumReds());
		assertEquals(0, g.getNumWhites());		
	}
	
	@Test
	public void testCheckGuessAllPegsDiffColor()
	{
		Color[] guessAllDiff = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
		Guess g = new Guess(keyAllDiff, guessAllDiff);
		g.checkForReds();
		g.checkForWhites();
		assertEquals(0, g.getNumReds());
		assertEquals(2, g.getNumWhites());		
	}
	
	@Test
	public void testCheckGuessRepeatingPegColors()
	{
		Color[] guessWithRepeats = {Color.RED, Color.BLUE, Color.RED, Color.BLUE};
		Guess g = new Guess(keyWithRepeats, guessWithRepeats);
		g.checkForReds();
		g.checkForWhites();
		assertEquals(1, g.getNumReds()); //1st RED is in right place
		assertEquals(2, g.getNumWhites()); //last 2 RED and BLUE are in wrong places
	}
	
	@Test
	public void testCheckGuessReturnsFourRedsGameWon()
	{
		Color[] guessMatchesKey = {Color.RED, Color.WHITE, Color.BLUE, Color.RED};
		Guess g = new Guess(keyWithRepeats, guessMatchesKey);
		g.checkForReds();
		g.checkForWhites();
		assertEquals(4, g.getNumReds());
		assertEquals(0, g.getNumWhites());		
		assertTrue(g.isAllRed());
	}
	
	@Test
	public void checkResultsAllFourReds()
	{
		Color[] guessMatchesKey = {Color.RED, Color.WHITE, Color.BLUE, Color.RED};
		Guess g = new Guess(keyWithRepeats, guessMatchesKey);
		g.checkForReds();
		g.checkForWhites();
		assertArrayEquals(new int[] {1,1,1,1}, g.getResults()); //all 4 spots should be red
	}
	
	@Test
	public void checkResultsAllFourWhites()
	{
		Color[] guessMatchesKey = {Color.WHITE, Color.GREEN, Color.YELLOW, Color.ORANGE};
		Guess g = new Guess(keyAllDiff, guessMatchesKey);
		g.checkForReds();
		g.checkForWhites();
		assertArrayEquals(new int[] {2,2,2,2}, g.getResults()); //all 4 spots should be white
	}
	
	@Test
	public void checkResultsNoRedsOrWhites()
	{
		Color[] guessDoesntMatchKey = {Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE};
		Guess g = new Guess(keyAllDiff, guessDoesntMatchKey);
		g.checkForReds();
		g.checkForWhites();
		assertArrayEquals(new int[] {0,0,0,0}, g.getResults());
	}
	
	@Test
	public void checkResultsMixtureOfRedsAndWhites()
	{
		Color[] guessMatchesKeyALittle = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.BLUE};
		Guess g = new Guess(keyAllDiff, guessMatchesKeyALittle);
		g.checkForReds();
		g.checkForWhites();
		assertArrayEquals(new int[] {0,2,1,0}, g.getResults());
	}
	
	//NM
	@Test
	public void checkResultsMixtureOfRedsAndWhitesWithRepeats()
	{
		Color[] guessMatchesKeyALittle = {Color.WHITE, Color.GREEN, Color.RED, Color.RED};
		Guess g = new Guess(keyWithRepeats, guessMatchesKeyALittle);
		g.checkForReds();
		g.checkForWhites();
		assertArrayEquals(new int[] {2,0,2,1}, g.getResults());		
	}
	
	@Test
	public void testGamesCheckGuessMethod()
	{
		Game game = new Game();
		Color[] guessWithRepeats = {Color.RED, Color.BLUE, Color.RED, Color.BLUE};
		game.checkGuess(guessWithRepeats);
		//can't really check it because it doesn't return anything
	}
	
	@Test
	public void testGetSequenceReturnsSequenceIPassedIt()
	{
		Color[] sequence = new Color[] {Color.RED, Color.BLUE, Color.RED, Color.BLUE};
		Guess guess = new Guess(keyWithRepeats, sequence);
		assertArrayEquals(sequence, guess.getSequence());
	}
	
	@Test (expected = InvalidDataException.class)
	public void testConstructorThrowsExceptionWhenKeyIsNull()
	{
		Color[] attempt = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
		Guess guess = new Guess(null, attempt);
	}
	
	@Test (expected = InvalidDataException.class)
	public void testConstructorThrowsExceptionWhenAttemptIsNull()
	{
		Guess guess = new Guess(keyAllDiff, null);
	}
	
	@Test
	public void getSequenceReturnsArrayCorrectly()
	{
		Color[] attempt = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
		Guess guess = new Guess(keyAllDiff, attempt);
		assertArrayEquals(attempt, guess.getSequence());
	}
	
	//This makes sure that the toString() works
	public static void main(String[] args)
	{
		Color[] attempt = {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};
		Color[] key = new Color[10];
		Guess guess = new Guess(key, attempt);
		System.out.println(guess);
	}
}
