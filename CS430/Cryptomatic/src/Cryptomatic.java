
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Cryptomatic {
	/**
	 * Initializes an integer choice
	 * Initializes a HashMap dictionary
	 * Calls a menu() submethod which allows the user to choose operation
	 * After choice the user is placed in a while loop which ends when exit is selected from the menu
	 * @param args  unused
	 */
	public static void main(String[] args) {
		int choice;
		HashMap<String, Integer> dictionary= createDictionary();
		choice=menu();
		while(choice!=4) {
		if (choice==1) {
			boolean done=encryptFile();
			if(done) {
				System.out.println("File was encrypted and written as \"EncryptedFile.txt\"");
			}else {
				System.out.println("File was not written\nRestarting Selection");
			}choice=menu();
		}else if(choice==2) {
			boolean decrypted=decryptFile();
			if(decrypted) {
				System.out.println("File was decrypted and written as \"DecryptedFile.txt\"");
			}else {
				System.out.println("File was not written\nRestarting Selection");
			}choice=menu();
		}else if(choice==3) {
			char[] keyFound=bruteForce(dictionary);
			if(keyFound[0]==127&&keyFound[1]==127) {
				System.out.println("Key was not found\nRestarting Selection");
			}else if(keyFound.length>2){
				System.out.println("Restarting Selection");
			}else if(keyFound[0]==0&&keyFound[1]==0) {
				System.out.println("The file is not encrypted");
			}
				else {
			
				String key=String.copyValueOf(keyFound);
				System.out.println("The key found is "+key+".");
				System.out.println("The decrypted file has been written as \"BruteDecrypted.txt\"");
			}choice=menu();
		}else
			choice=menu();
		}System.out.println("Exiting");

	}
	/**
	 * 
	 * @return returns an int which is used to determine the path the user wishes to take
	 */
	public static int menu() {
		System.out.println("Enter the number corresponding with the action you would like to perform\n1. Enter the filename and key to encrypt\n2. Enter the filename and key to decrypt\n3. Enter the filename to brute force\n4. Exit");
		Scanner aScan = new Scanner(System.in);
		int toReturn=0;
		if(aScan.hasNextInt()){
			toReturn=aScan.nextInt();
		}else {
			System.out.println("Entered text is not an option, restarting selection.");
		}
		

		
			return toReturn;

	}
	/**This method is used to decrypt and encrypt an array of characters pulled from the file
	 * @param encryptors this is the array used to encrypt or decrypt
	 * @param toEncrypt this the string to be encrypted or decrypted
	 * @return this returns an de/encrypted character array*/
	public static String crypt(char[] encryptors, String stringEncrypt) {
		char[] toEncrypt=stringEncrypt.toCharArray();
		for (int i = 0; i < toEncrypt.length; i++) {
			if (i%2==0){
				int test=toEncrypt[i]^encryptors[0];
				toEncrypt[i]=(char) test;
			}
			else {
				int test=toEncrypt[i]^encryptors[1];
				toEncrypt[i]=(char) test;
			}

		}return String.valueOf(toEncrypt);
	}
	/**
	 * This is the bruteforce method which cycles through each possible character combination attempts to decrypt then uses a test method to ensure it is correct
	 * If a file which has no encryption is tested it should return that it is not encrypted
	 * @param dictionary a HashMap dictionary which was created in the main program and is passed here to minimize timing
	 * @return returns a character array which contains the characters found while brute forcing, if the array contains blanks the main thread decides that the decryption was unsuccessful
	 */
	public static char[] bruteForce(HashMap<String, Integer> dictionary) {
		char[] toReturn= {' ',' '};
		int loop=32;
		String bruteDecrypt="";
		boolean wordTest=false;
		Scanner aScan = new Scanner(System.in);
		System.out.println("Please enter the name of the file to Brute Force");
		String filename=aScan.nextLine();
		long startTime=System.currentTimeMillis();
		int lengthChecked=25;
		String fileContent=readFile(filename,lengthChecked);
		char[] testArray=new char [2];
		testArray[1]=(char)0;
		testArray[0]=(char)0;
		if(decryptCheck(crypt(testArray, fileContent), dictionary)) {
			return testArray;
		}
		if (fileContent.equals("error")){
			return fileContent.toCharArray();
		}while(!wordTest) {
			if(lengthChecked>fileContent.length()) {
				wordTest=true;
			}
			bruteDecrypt=crypt(toReturn,fileContent);
			
			//check if contains real words
			
			wordTest=decryptCheck(bruteDecrypt,dictionary);
			if(loop%2==0&&!wordTest) {
				toReturn[0]+=1;
				if((int) toReturn[0]==128) {
					loop++;
					toReturn[0]=(char) loop;
				}
			}else if(loop%2==1&&!wordTest) {
				toReturn[1]+=1;
				if((int) toReturn[1]==128) {
					loop++;
					toReturn[1]=(char) (loop);
					
				}//System.out.print(loop);
				if(loop==128) {
					lengthChecked*=2;
					loop=31;
					fileContent=readFile(filename,lengthChecked);
					if(lengthChecked>=801) {
						wordTest=true;
					}
				}
			}
			
			


		}
		if((int) toReturn[0]>=127||(int) toReturn[1]>=127) {
			toReturn[0]=(char) 127;
			toReturn[1]=(char) 127;
			return toReturn;
		}
		fileContent=readFile(filename,0);
		bruteDecrypt=crypt(toReturn,fileContent);
		boolean write=writeFile("BruteDecrypted.txt", bruteDecrypt);
		if(write) {
			long endTime=System.currentTimeMillis();
			System.out.println(endTime-startTime+" milliseconds");
			return toReturn;
		}else {
			char[] notWritten= {'a','b','c'};
			return notWritten;
		}

	}
	/**
	 * This method reads the file and accepts an int to determine the number of characters to read
	 * @param filename the filename which is passed and correlates to the operation performed, could be rewritten to accept user input
	 * @return this returns a String containing the files contents 
	 */
	public static String readFile(String filename,int toBeRead){
		String toReturn="";
		try {
			//FileReader fr=new FileReader(filename);
			
			File file=new File(filename);
			if(toBeRead==0||toBeRead>file.length()) {
				toBeRead=(int)file.length();
			}
			FileInputStream fis=new FileInputStream(file);
			byte fileContent[]=new byte[toBeRead];
			fis.read(fileContent);
			toReturn= new String(fileContent);
			
//			BufferedReader reader = new BufferedReader(new FileReader(filename));
//			String line=reader.readLine();
//			while(line!=null) {
//				toReturn+=line;
//				line=reader.readLine();
//				
//			}

		} catch (IOException e) {
			System.out.println("File Not Found");
			return "error";
			// TODO: handle exception
		}


		return toReturn;
	}
	/**
	 * this method has no inputs and is a wrapper for the encryption and writing of a file it is almost identical to the decryptFile method
	 * @return this returns a boolean true: if the file is written false: if the file fails to write
	 */
	public static boolean encryptFile() {
		char[] forEncryption= {' ',' '};
		Scanner aScan=new Scanner(System.in);
		System.out.println("Please enter the name of the file to Encrypt");
		String filename=aScan.nextLine();
		String fileContent=readFile(filename,0);
		if (fileContent.equals("error")){
			return false;
		}System.out.println("enter the characters to use for encryption(if either is empty then \"aa\" is assumed)");
		String encryptString=aScan.nextLine();
		if(!encryptString.contains(" ")) {
			forEncryption=encryptString.toCharArray();
		}
		String encryptedString=crypt(forEncryption, fileContent);
		boolean written=writeFile("EncryptedFile.txt",encryptedString);
		return written;
	}
	/**
	 * this method has no inputs and is a wrapper for the decryption and writing of a file it is almost identical to the encryptFile method
	 * @return this returns a boolean true: if the file is written false: if the file fails to write
	 */
	public static boolean decryptFile() {
		char[] forDecryption= {'a','a'};
		Scanner aScan=new Scanner(System.in);
		System.out.println("Please enter the name of the file to Decrypt");
		String filename=aScan.nextLine();
		String fileContent=readFile(filename,0);
		if (fileContent.equals("error")){
			return false;
		}System.out.println("enter the characters to use for Decryption(if either is empty then \"aa\" is assumed)");
		String encryptString=aScan.nextLine();
		if(!encryptString.contains(" ")) {
			forDecryption=encryptString.toCharArray();
		}
		String encryptedString=crypt(forDecryption, fileContent);
		boolean written=writeFile("DecryptedFile.txt",encryptedString);
		return written;
	}
	/**
	 * This method is a wrapper to convert from character Array to String but isn't used many times
	 * @param arrayToConvert the array which is read to convert to a String
	 * @return this returns a String which contains the characters from the array
	 */
	public static String charToString(char[] arrayToConvert) {
		String toReturn=String.valueOf(arrayToConvert);
		return toReturn;
	}
	/**
	 * This creates the Dictionary used to check if the BruteDecrypt has happened correctly it first checks to see if a dictionary file exists and if none is found it uses one that is hard coded
	 * @return this returns a HashMap<String, Integer> which contains all words for the dictionary
	 */
	public static HashMap<String, Integer> createDictionary(){
		HashMap<String, Integer> dictMap=new HashMap<String, Integer>();
		File file=new File("dictionary.txt");
		String dictString="";
		if(!file.exists()) {
		dictString="version\n"+"an\n"+"not\n"+"of\n"+"am\n"+"by\n"+"We\n"+"The\n"+
				"of\n" + 
				"to\n" + 
				"and\n" + 
				"in\n" + 
				"is\n" + 
				"it\n" + 
				"you\n" + 
				"that\n" + 
				"was\n" + 
				"for\n" +  
				"are\n" + 
				"with\n" + 
				"his\n" + 
				"they\n"   + 
				"one\n" + 
				"have\n" + 
				"this\n" + 
				"from\n" + 
				"or\n" + 
				"had\n" + 
				"by\n" + 
				"hot\n" + 
				"but\n" + 
				"some\n" + 
				"what\n" + 
				"there\n" + 
				"we\n" + 
				"can\n" + 
				"out\n" + 
				"other\n" + 
				"were\n" + 
				"all\n" + 
				"your\n" + 
				"when\n" + 
				"up\n" + 
				"use\n" + 
				"word\n" + 
				"how\n" + 
				"said\n" + 
				"each\n" + 
				"she\n" + 
				"which\n" + 
				"do\n" + 
				"their\n" + 
				"time\n" + 
				"if\n" + 
				"will\n" + 
				"way\n" + 
				"about\n" + 
				"many\n" + 
				"then\n" + 
				"them\n" + 
				"would\n" + 
				"write\n" + 
				"like\n" + 
				"so\n" + 
				"these\n" + 
				"her\n" + 
				"long\n" + 
				"make\n" + 
				"thing\n" + 
				"see\n" + 
				"him\n" + 
				"two\n" + 
				"has\n" + 
				"look\n" + 
				"more\n" + 
				"day\n" + 
				"could\n" + 
				"go\n" + 
				"come\n" + 
				"did\n" + 
				"my\n" + 
				"sound\n" + 
				"no\n" + 
				"most\n" + 
				"number\n" + 
				"who\n" + 
				"over\n" + 
				"know\n" + 
				"water\n" + 
				"than\n" + 
				"call\n" + 
				"first\n" + 
				"people\n" + 
				"may\n" + 
				"down\n" + 
				"side\n" + 
				"been\n" + 
				"now\n" + 
				"find\n" + 
				"any\n" + 
				"new\n" + 
				"work\n" + 
				"part\n" + 
				"take\n" + 
				"get\n" + 
				"place\n" + 
				"made\n" + 
				"live\n" + 
				"where\n" + 
				"after\n" + 
				"back\n" + 
				"little\n" + 
				"only\n" + 
				"round\n" + 
				"man\n" + 
				"year\n" + 
				"came\n" + 
				"show\n" + 
				"every\n" + 
				"good\n" + 
				"me\n" + 
				"give\n" + 
				"our\n" + 
				"under\n" + 
				"very\n" + 
				"through\n" + 
				"just\n" + 
				"form\n" + 
				"much\n" + 
				"great\n" + 
				"think\n" + 
				"say\n" + 
				"help\n" + 
				"low\n" + 
				"line\n" + 
				"before\n" + 
				"turn\n" + 
				"cause\n" + 
				"same\n" + 
				"mean\n" + 
				"differ\n" + 
				"move\n" + 
				"right\n" + 
				"boy\n" + 
				"old\n" + 
				"too\n" + 
				"does\n" + 
				"tell\n" + 
				"sentence\n" + 
				"set\n" + 
				"three\n" + 
				"want\n" + 
				"air\n" + 
				"well\n" + 
				"also\n" + 
				"play\n" + 
				"small\n" + 
				"end\n" + 
				"put\n" + 
				"home\n" + 
				"read\n" + 
				"hand\n" + 
				"port\n" + 
				"large\n" + 
				"spell\n" + 
				"add\n" + 
				"even\n" + 
				"land\n" + 
				"here\n" + 
				"must\n" + 
				"big\n" + 
				"high\n" + 
				"such\n" + 
				"follow\n" + 
				"act\n" + 
				"why\n" + 
				"ask\n" + 
				"men\n" + 
				"change\n" + 
				"went\n" + 
				"light\n" + 
				"kind\n" + 
				"off\n" + 
				"need\n" + 
				"house\n" + 
				"picture\n" + 
				"try\n" + 
				"us\n" + 
				"again\n" + 
				"animal\n" + 
				"point\n" + 
				"mother\n" + 
				"world\n" + 
				"near\n" + 
				"build\n" + 
				"self\n" + 
				"earth\n" + 
				"father\n" + 
				"head\n" + 
				"stand\n" + 
				"own\n" + 
				"page\n" + 
				"should\n" + 
				"country\n" + 
				"found\n" + 
				"answer\n" + 
				"school\n" + 
				"grow\n" + 
				"study\n" + 
				"still\n" + 
				"learn\n" + 
				"plant\n" + 
				"cover\n" + 
				"food\n"  + 
				"four\n" + 
				"thought\n" + 
				"let\n" + 
				"keep\n" + 
				"eye\n" + 
				"never\n" + 
				"last\n" + 
				"door\n" + 
				"between\n" + 
				"city\n" + 
				"tree\n" + 
				"cross\n" + 
				"since\n" + 
				"hard\n" + 
				"start\n" + 
				"might\n" + 
				"story\n" + 
				"saw\n" + 
				"far\n" + 
				"draw\n" + 
				"left\n" + 
				"late\n" + 
				"run\n" + 
				"don't\n" + 
				"while\n" + 
				"press\n" + 
				"close\n" + 
				"night\n" + 
				"real\n" + 
				"life\n" + 
				"few\n" + 
				"stop\n" + 
				"open\n" + 
				"seem\n" + 
				"together\n" + 
				"next\n" + 
				"white\n" + 
				"children\n" + 
				"begin\n" + 
				"got\n" + 
				"walk\n" + 
				"example\n" + 
				"ease\n" + 
				"paper\n" + 
				"often\n" + 
				"always\n" + 
				"music\n" + 
				"those\n" + 
				"both\n" + 
				"mark\n" + 
				"book\n" + 
				"letter\n" + 
				"until\n" + 
				"mile\n" + 
				"river\n" + 
				"car\n" + 
				"feet\n" + 
				"care\n" + 
				"second\n" + 
				"group\n" + 
				"carry\n" + 
				"took\n" + 
				"rain\n" + 
				"eat\n" + 
				"room\n" + 
				"friend\n" + 
				"began\n" + 
				"idea\n" + 
				"fish\n" + 
				"mountain\n" + 
				"north\n" + 
				"once\n" + 
				"base\n" + 
				"hear\n" + 
				"horse\n" + 
				"cut\n" + 
				"sure\n" + 
				"watch\n" + 
				"color\n" + 
				"face\n" + 
				"wood\n" + 
				"main\n" + 
				"enough\n" + 
				"plain\n" + 
				"girl\n" + 
				"usual\n" + 
				"young\n" + 
				"ready\n" + 
				"above\n" + 
				"ever\n" + 
				"red\n" + 
				"list\n" + 
				"though\n" + 
				"feel\n" + 
				"talk\n" + 
				"bird\n" + 
				"soon\n" + 
				"body\n" + 
				"dog\n" + 
				"family\n" + 
				"direct\n" + 
				"pose\n" + 
				"leave\n" + 
				"song\n" + 
				"measure\n" + 
				"state\n" + 
				"product\n" + 
				"black\n" + 
				"short\n" + 
				"numeral\n" + 
				"class\n" + 
				"wind\n" + 
				"question\n" + 
				"happen\n" + 
				"complete\n" + 
				"ship\n" + 
				"area\n" + 
				"half\n" + 
				"rock\n" + 
				"order\n" + 
				"fire\n" + 
				"south\n" + 
				"problem\n" + 
				"piece\n" + 
				"told\n" + 
				"knew\n" + 
				"pass\n" + 
				"farm\n" + 
				"top\n" + 
				"whole\n" + 
				"king\n" + 
				"size\n" + 
				"heard\n" + 
				"best\n" + 
				"hour\n" + 
				"better\n" + 
				"true\n" + 
				"during\n" + 
				"hundred\n" + 
				"am\n" + 
				"remember\n" + 
				"step\n" + 
				"early\n" + 
				"hold\n" + 
				"west\n" + 
				"ground\n" + 
				"interest\n" + 
				"reach\n" + 
				"fast\n" + 
				"five\n" + 
				"sing\n" + 
				"listen\n" + 
				"six\n" + 
				"table\n" + 
				"travel\n" + 
				"less\n" + 
				"morning\n" + 
				"ten\n" + 
				"simple\n" + 
				"several\n" + 
				"vowel\n" + 
				"toward\n" + 
				"war\n" + 
				"lay\n" + 
				"against\n" + 
				"pattern\n" + 
				"slow\n" + 
				"center\n" + 
				"love\n" + 
				"person\n" + 
				"money\n" + 
				"serve\n" + 
				"appear\n" + 
				"road\n" + 
				"map\n" + 
				"science\n" + 
				"rule\n" + 
				"govern\n" + 
				"pull\n" + 
				"cold\n" + 
				"notice\n" + 
				"voice\n" + 
				"fall\n" + 
				"power\n" + 
				"town\n" + 
				"fine\n" + 
				"certain\n" + 
				"fly\n" + 
				"unit\n" + 
				"lead\n" + 
				"cry\n" + 
				"dark\n" + 
				"machine\n" + 
				"note\n" + 
				"wait\n" + 
				"plan\n" + 
				"figure\n" + 
				"star\n" +  
				"noun\n" + 
				"field\n" + 
				"rest\n" + 
				"correct\n" + 
				"able\n" + 
				"pound\n" + 
				"done\n" + 
				"beauty\n" + 
				"drive\n" + 
				"stood\n" + 
				"contain\n" + 
				"front\n" + 
				"teach\n" + 
				"week\n" + 
				"final\n" + 
				"gave\n" + 
				"green\n" + 
				"quick\n" + 
				"develop\n" + 
				"sleep\n" + 
				"warm\n" + 
				"free\n" + 
				"minute\n" + 
				"strong\n" + 
				"special\n" + 
				"mind\n" + 
				"behind\n" + 
				"clear\n" + 
				"tail\n" + 
				"produce\n" + 
				"fact\n" + 
				"street\n" + 
				"inch\n" + 
				"lot\n" + 
				"nothing\n" + 
				"course\n" + 
				"stay\n" + 
				"wheel\n" + 
				"full\n" + 
				"force\n" + 
				"blue\n" + 
				"object\n" + 
				"decide\n" + 
				"surface\n" + 
				"deep\n" + 
				"moon\n" + 
				"island\n" + 
				"foot\n" + 
				"yet\n" + 
				"busy\n" + 
				"test\n" + 
				"record\n" + 
				"boat\n" + 
				"common\n" + 
				"gold\n" + 
				"possible\n" + 
				"plane\n" + 
				"age\n" + 
				"dry\n" + 
				"wonder\n" + 
				"laugh\n" + 
				"thousand\n" + 
				"ago\n" + 
				"ran\n" + 
				"check\n" + 
				"game\n" + 
				"shape\n" + 
				"yes\n" + 
				"hot\n" + 
				"miss\n" + 
				"brought\n" + 
				"heat\n" + 
				"snow\n" + 
				"bed\n" + 
				"bring\n" + 
				"sit\n" + 
				"perhaps\n" + 
				"fill\n" + 
				"east\n" + 
				"weight\n" + 
				"language\n" + 
				"among\n"+"four\n"+"text\n"+"test\n"+"error\n";
		}else {
			dictString=readFile("dictionary.txt",0);
			
		}
		String[] stringArray=dictString.split("\n");
		for (int i = 0; i < stringArray.length; i++) {
			dictMap.put(stringArray[i], i);
		}
		return dictMap;
	}
	/**
	 * This is the method used to write files and checks if a file exists then overwrites if the user wishes
	 * @param filename The name to be written which is passed from the method the user selected
	 * @param fileContent The content of the file which is to be written
	 * @return This returns a boolean which corresponds to successfully writing the file
	 */
	public static boolean writeFile(String filename, String fileContent) {
		File file=new File(filename);
		Scanner aScan=new Scanner(System.in);
		boolean written=false;
		byte[] fileBytes=fileContent.getBytes();
		if(file.exists()) {
			System.out.println("File with the name "+filename+" already exists, would you like to overwrite?(y/n)");
			String overWrite=aScan.nextLine();
			if(overWrite.equals("n")) {
				return false;
			}
		}
			try {
				FileOutputStream fos=new FileOutputStream(filename);   
	            fos.write(fileBytes);    
	            fos.close();    
					written=true;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return false;
				}
				

		
		return written;
	}
	/**
	 * This checks if a file was successfully brute decrypted
	 * @param decryptedString The String which the bruteDecrypt algorithm is testing
	 * @param dictionary The dictionary which is used to test the String againts
	 * @return returns if the attempted Decryption is correct or not
	 */
	public static boolean decryptCheck(String decryptedString,HashMap<String, Integer> dictionary) {
		boolean isDecrypted=false;
		
		int wordCount=0;
		char[] decryptedArray=decryptedString.toCharArray();
		int goodCount=0;
		int badCount=0;
		String previousWord="";
		for (int i = 0; i < decryptedArray.length; i++) {
			if(Character.isLetterOrDigit(decryptedArray[i])) {
				goodCount++;
			}if(goodCount>decryptedArray.length*0.4) {
				decryptedString=charToString(decryptedArray);
				String[] decryptedStringList=decryptedString.split("\\s");
				for (int j = 0; j < decryptedStringList.length; j++) {					
					if(dictionary.containsKey(decryptedStringList[j])) {
						if(dictionary.size()>700) {
							if(decryptedStringList[j]==previousWord) {
								wordCount--;
							}
							previousWord=decryptedStringList[j];
						}
						wordCount++;
						if(wordCount>=(decryptedStringList.length*0.8)) {
							return true;
						}
						}
						}
					}
			else if(!Character.isLetterOrDigit(decryptedArray[i])){
				badCount++;
				if(badCount>decryptedArray.length*0.3) {
					return false;
				}
			}
			}
		return isDecrypted;
		}
}


