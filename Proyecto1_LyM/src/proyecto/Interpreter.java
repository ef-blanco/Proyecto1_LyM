package proyecto;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import robot.kernel.RobotWorld;
import robot.kernel.RobotWorldDec;
import robot.view.Console;




/**
 * Receives commands and relays them to the Robot. 
 */

public class Interpreter   {
	
	/**
	 * Robot's world
	 */
	private RobotWorldDec world;  
	private Console callingConsole;
	

	//private interfasePAnel
	
	
	/**
	 * Parser for a simple language 
	 */
	private RobotGrammar parser;

	/**
	 *  Builds a new Interprer 
	 */
	public Interpreter() {
		parser = new RobotGrammar(System.in);
	}

	/**
	 * Creates a parameterized interpreter with a robot world and the parser
	 * @param world, the robot world that will be used.
	 */
	public Interpreter(RobotWorld w, Console c) {
		world =  (RobotWorldDec) w;
		parser = new RobotGrammar(System.in);
		callingConsole = c;
		c.printOutput("Enter Commands\n");
		parser.setWorld(world);
	}
	
	


	
	/**
	 * sets the world and the console
	 * @param world 
	 * @paramc c
	 */

	public void setWorld(RobotWorld m, Console c) 
	{
		world = (RobotWorldDec) m;
		callingConsole =c;
		c.printOutput("Enter Commands\n");
		parser.setWorld(world);
		
	}
	  
	
	
	
	/**
	 *  Processes a sequence of commands. A command is a letter  followed by a ";"
	 *  
	 *  
	 * @param input Contiene una cadena de texto enviada para ser interpretada
	 */
	
	/**
	 *  Inicia el procesamiento de �rdenes en formato texto
	 *  Recibe una cadena con  comandos, Le envia al parser esta  cadena para 
	 *  ser procesada. Inicia el timer para que se procese una cadena una a una
	 * @param input Contiene una cadena de texto enviada para ser interpretada
	 */
	public String process(String input) {   
		//Manda la cadena como stream al parser que procesa
		parser.ReInit(new java.io.StringReader(input));
		//StringBuffer salida=new StringBuffer("Sistema: \n-->");	
		String salida="Sistema: \n-->";
		callingConsole.printOutput(salida);
		// mundo.resetStacks();
		try {                  		
			while (parser.command(callingConsole)){
				//mundo.resetStacks();	
				salida= " \n--> ";
				callingConsole.printOutput(salida);
			}
		}  catch(ParseException pex) {		
			//System.out.println(mundo.getValueStack());
			salida = "Error de sintaxis:"+pex.getMessage()+" \n--> ";
			callingConsole.printOutput(salida);
			//mundo.cleanTemp();
		} catch (Error err) {
			//		 System.out.println("stack: "+mundo.getValueStack());
			salida = "Error "+err.getMessage()+" \n--> ";
			callingConsole.printOutput(salida);
			//	 mundo.cleanTemp();
		} catch (Exception error) {
			// System.out.println(mundo.getValueStack());
			salida = "Execution error!!"+error.getStackTrace()+" \n--> ";
			callingConsole.printOutput(salida);
			//mundo.cleanTemp();
		}
	   salida =	" End Input!!! \n";
	   callingConsole.printOutput(salida);
	   return salida;
		//mundo.cleanTemp();
	}


	
}
	    