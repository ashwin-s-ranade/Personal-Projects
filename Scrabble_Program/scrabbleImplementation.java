package solved_basicChallegeComplete;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class RackManagement2_December7th2016 //#294 intermediate have a small bug perhaps 
{
	public static void main(String[] args) throws IOException //need to acknowledge "checked exceptions", w/ throws or try-catch-finally
	{
		System.out.println(highest("iogsvooely") );
		System.out.println(highest("seevurtfci") );
		System.out.println(highest("vepredequi") );
		System.out.println(highest("umnyeoumcp"));
		System.out.println(highest("orhvtudmcz"));
		System.out.println(highest("fyilnprtia"));
		System.out.println(highest("yleualaaoitoai??????"));
		System.out.println(highest("afaimznqxtiaar??????")); //this one has a bug E_E 
		System.out.println(highest("yrkavtargoenem??????"));
		System.out.println(highest("gasfreubevuiex??????"));
	}
	
	
	
	public static String highest (String scrambled) throws IOException //bonus 3 finds highest scoring scrabble word based on points :-DDDDDD
	{ 
		ArrayList<String> englishLanguage = new ArrayList<String>();
		englishLanguage.add(""); //this is for below 
		Scanner sc = new Scanner(new File("enable1.txt")); //one line baby! 
		while (sc.hasNextLine()) //basically, if you haven't reached the end of the file 
		{
			String line = sc.nextLine();
			englishLanguage.add(line);
		}
		sc.close(); // closing my scanner! ;-)
		
		int highestPoints = 0; //minimum is 0, because "" generates 0 points 
		String highest = "";
		for (String hello : englishLanguage)
		{
			if (scrabbleWithBlanks(scrambled, hello) && sumPoints(scrabbleWithBlanksString(scrambled,hello)) > highestPoints)
			{
				highestPoints = sumPoints(scrabbleWithBlanksString(scrambled,hello));
				highest = hello;
			}
		}
		return highestPoints + " " + highest;
	}
	
	public static final int[] pointValues = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10}; //goes a through z, a = 97 and z = 122

	public static int sumPoints (String word) //helper method, finds total # of points for a word, ? count as 0 
	{
		int sum = 0; 
		for (int i = 0; i < word.length(); i++)
		{
			//subtract 97 b/c a = 97, to correspond to index of pointValues array
			if (((int) word.charAt(i)) - 97 >= 0)
				sum += pointValues[((int) word.charAt(i)) - 97]*(i+1);
		}
		return sum; 
	}
	
	public static String scrabbleWithBlanksString (String scrambledEggs, String wordGames) 
	//helper method, should be used ONLY IF scrabbleWithBlanks(String scrambledEggs, String wordGames)
	//gets the word WITH blanks, for example, scrabbleWithBlanksString("progaaf????","program") should return prog?a?
	{
		if (scrabbleWithBlanks(scrambledEggs, wordGames) != true)//first, a check 
		{
			return "";
		}
		ArrayList<Character> scrambled = new ArrayList<Character>();
		ArrayList<Character> word = new ArrayList<Character>();
		for (int i = 0; i < scrambledEggs.length(); i++)
		{
			scrambled.add(scrambledEggs.charAt(i));
		}
		for (int i = 0; i < wordGames.length(); i++)
		{
			word.add(wordGames.charAt(i));
		}
		
		ArrayList<Character> result = new ArrayList<Character>();
		for (int i = 0; i < word.size(); i++) //lmao this is so ez why didn't I think of this b4, indexOf is OP <3 part 2? clutch?
		{
			if (scrambled.indexOf(word.get(i)) >= 0)
			{
				scrambled.remove(scrambled.indexOf(word.get(i)));
				result.add(word.get(i)); //fixed this right on this line :-) 
				word.remove(i);
				i = -1;
			}
		}		
		//now time to add '?' blank spaces in the result arrayList, word and result are already sorted
		char[] wordzzz = wordGames.toCharArray();

		for (int i = 0; i < wordzzz.length; i++)
		{
			if (i >= result.size() ) //if the result string doesn't have a letter in the word at the correct index 
			{
				result.add('?'); 
			}
			else if (wordzzz[i] != result.get(i))
			{
				result.add(i, '?');

			}
		}
		String temp = "";
		for (char i : result)
		{
			temp += i;
		}
		return temp;
	}
	
	public static boolean scrabbleWithBlanks (String scrambledEggs, String wordGames) //optional bonus 1, works with '?' tiles (blank tiles)
	{
		ArrayList<Character> scrambled = new ArrayList<Character>();
		ArrayList<Character> word = new ArrayList<Character>();
		for (int i = 0; i < scrambledEggs.length(); i++)
		{
			scrambled.add(scrambledEggs.charAt(i));
		}
		for (int i = 0; i < wordGames.length(); i++)
		{
			word.add(wordGames.charAt(i));
		}
		for (int i = 0; i < word.size(); i++) //lmao this is so ez why didn't I think of this b4, indexOf is OP <3
		{
			if (scrambled.indexOf(word.get(i)) >= 0)
			{
				scrambled.remove(scrambled.indexOf(word.get(i)));
				word.remove(i);
				i = -1;
			}
		}
		int count = 0; //counting # of ? 
		//now count num of '?' in scrambled (after some removal has been done)
		for (char i : scrambled)
		{
			if (i == '?')  
			{
				count++;
			}
		}	
		return  count >= word.size(); //if word.size() == 0 or if word.size() >= #of?s, then you can make the given word using the given tiles :-)
	}
}
