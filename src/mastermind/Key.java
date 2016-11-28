package mastermind;

import java.util.Arrays;
import java.util.Random;
import exceptions.*;

public class Key
{
	private Color[] colorArray;
	
	/**
	 * Constructor that accepts how many elements should be in the Key's array and populates the
	 * array
	 * @param size The number of elements to be in the array
	 */
	public Key(int size)
	{
		if (size <= 0)
			throw new InvalidDataException("Size must be greater than 0");
		
		colorArray = new Color[size];
		
		// for loop to populate each of the spaces in the array
		for (int index = 0; index < colorArray.length; index++)
		{
			// create a Random obj to generate a number randomly from 0 to
			// number of possible Colors
			Random rand = new Random();
			int num = rand.nextInt(Color.values().length);
			Color col = Color.values()[num];
			colorArray[index] = col;// set each location to the color determined
									// by the Random
		}
	}
	
	/**
	 * Returns a deep copy of the Color[] array within this Key
	 * @return A copy of this key's color array
	 */
	public Color[] getKey()
	{
		Color[] other = new Color[colorArray.length];
		for (int i = 0; i < colorArray.length; i++)
		{
			other[i] = colorArray[i];
		}
		return other;
	}
	
	/**
	 * Compares this Key object to a different Key object
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (!Arrays.equals(colorArray, other.colorArray))
			return false;
		return true;
	}
	
	/**
	 * Compares this Key object's color array to a different Color array
	 * @param other The other array to compare this to
	 * @return true if all elements in both arrays are the same, false if they are not
	 */
	public boolean equals(Color[] other)
	{
		if (other == null)
			return false;
		
		if (colorArray.length != other.length)
			return false;
		
		for (int i = 0; i < colorArray.length; i++)
		{
			if (!(colorArray[i] == other[i]))
				return false;
		}
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (Color c : colorArray)
			str.append(c.toString() + " ");
		
		return str.toString().trim();
	}
	
}
