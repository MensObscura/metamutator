package configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// Configuration class
public class Config {

	//unique instance of configuration
	public static Config instance ;

	//Read and Maped config
	private Map<String,Map> config;

	//File Writer
	private PrintWriter outputFile ;
	
	//File Reader
	private BufferedReader inputFile;


	//Path to the config file
	private String file = "config.txt";

	//to test if init as been done
	private Boolean init =false;
	
	//to test is the file was already read
	private Boolean read = false;
	
	//to test if the file was changed
	private Boolean changed = false;
	
	

	/**private constructor cause of singleton pattern
	 * 
	 */
	private Config(){
		
		//Init writer and reader
		initWriter();
		//Init the Map
		config = new HashMap();

	}

	/**
	 * init the writer and read of the config file
	 * @return true if no ioexception , false else. 
	 */
	public boolean initWriter(){


		//lecture du fichier texte	
		try{

			if (new File(file).exists()) {
				//reader
				FileInputStream ips = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(ips);
				inputFile = new BufferedReader(isr);
			}
			else {
				//writer
				FileWriter fw = new FileWriter (file); 
				BufferedWriter bw = new BufferedWriter (fw);
				outputFile= new PrintWriter (bw); 
			}
						

		}		
		catch (Exception e){
			System.err.println("Error in opening Config file"+e.toString());
			return false;
		}
		
		// we init the writer so notice it
		init=true;
		return init;

	}

	/**
	 * Clear the config file and write the paramter all, and set it to true
	 * @return true if the init append well , false else.
	 */
	public boolean initConfig(){
		
		// we init the writer to clear the file
		initWriter();
		

		// the init is ok only if the init and the firste write return true
		
		if(init)	
			return write("All:true");

		//in cas of initWriterReader false , init
		return init;
	}


	/**
	 * We will read the config file, and set the config map.
	 * @return true if everythings append well, false else.
	 */
	public boolean readConfig(){

		//if already read and no changed we already got the data , dont read it again !
		if(read && !changed){
			
			return true;
		}

		try{
			//reader
			
			String line ="";
			
			//The option of the selector 0 by default
			int selOption = 0;
		

			//Boucle de lecture du fichier
			while ((line=inputFile.readLine())!=null){
				System.out.println(line);
				
				
				//On envoie la ligne s'enregistrer vers la map
				selOption = mapLine(line,selOption);
			}
				
			inputFile.close();

			// we read the file we remain it
			read =true;
			//So there is no changed since the last read, so make the world know
			changed = false;
		}		
		catch (Exception e){
			System.err.println("Error in reading Config file"+e.toString());
			return false;
		}
		return true;
	}

	/**
	 * We transform the line to map for the program.
	 * @param line the reading line
	 * @param selOption the nbOptionOf the current selector, -1 if one true already found.
	 * @return selOption, for the next line. 
	 */
	private int mapLine(String line, int selOption){
		String tabSelector[];
		//we split the line
		tabSelector = line.split(":");
		
		//If we detect a new class, we built a new map
		if(tabSelector.length == 1){
			config.put(tabSelector[0], new HashMap<>());
		}
		
		if (tabSelector.length == 2 && tabSelector[0].equals("All")) {
			config.put(tabSelector[0], new HashMap<>());
		}
		
		//If we detect a new selector, we stock it. Option 0 by default
		if(tabSelector.length == 2){
			config.get(tabSelector[0]).put(tabSelector[1], 0);
			
			selOption = 0;
		}

		//if we found the arg ture off one option for a selector, we put it on the map
		if(tabSelector.length == 4 && tabSelector[3].equals("true")){
			config.get(tabSelector[0]).put(tabSelector[1], 0);
			selOption = -1;
		
		}else if(tabSelector.length == 3 &&  selOption > -1 ){
			
				selOption ++;
			
		}
		
		return selOption;
	
	}
	
	/**
	 * According the singleton parttern we got only one instance of Config (Cause of multiple access risk)
	 * @return the instance of Config
	 */
	public static Config getInstance(){
		
		if (instance == null){
			//if instance is null, we create the instance
			instance = new Config();
		}

		return instance;
	}

	/**
	 * Same as getInstance, but we initialize the instance. 
	 * @return the initialized instance
	 */
	public static Config getInitInstance() {
		if (instance == null){
			//if instance is null, we create the instance
			instance = new Config();
		}
		// we initialize the instance
		instance.initConfig();
		
		return instance;
	}
	/**
	 * write the mutant to the config file and set it to false by default.
	 * @param mutant
	 * @return
	 */
	public boolean write(String mutant){

		//we change the file so we said it
		changed = true;
		
		if(!init){
			//in case of not init, we init the reader writer
			initWriter();
		}
		//add mutant in config file
		try {

			outputFile.println (mutant); 
			outputFile.flush();
			String value = (mutant.split(":"))[mutant.split(":").length - 1];
			System.out.println("Inserting \""+mutant+"\" performed, default setting = "+value);

		}

		catch (Exception e){
			System.err.println("Error when writing in config file "+e.toString());
			return false;
		}	
		return true;
	}

	/**
	 * To get the config wraped in a map, the key is the id of the Selector and the value is a tab with the number of the mutation and the value of the activation TODO
	 * @return the map which contain the config
	 */
	public Map getConfig(){
	
		readConfig();
		
		
		return config;
	}

	/**
	 * To get the config wraped in a map, filtered by a selector TODO or a class
	 * @param selector the selector wished
	 * @return the map which contain the config for the selector
	 */
	public Map getConfig(String selector){
		//TODO
		return new HashMap();
	}

	/**
	 * A little help to the JVM, and because the streamWriter must be closed.
	 */
	public void close(){
		
		//We close the streams to make the jvm a pretty clean place
		outputFile.close();	
	
		//try destroy the object when we  finally don't need him ever.
		try {
			instance.finalize();
		} catch (Throwable e) {
			System.err.println("Garbage failed is job : "+e);
		}
	}

}
