package main.java.com.timelessapps.javafxtemplate.app.supportingthreads;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.timelessapps.javafxtemplate.app.businesslogic.MainBotRoutine;
import main.java.com.timelessapps.javafxtemplate.helpers.abstractsandenums.Buff;
import main.java.com.timelessapps.javafxtemplate.helpers.services.LoggingService;

public class BuffTimer extends Thread
{
    LoggingService log = new LoggingService();
     
    MainBotRoutine mainBotRoutine;
    int timeToWait;
    Buff buff;
    
    public BuffTimer(MainBotRoutine mainBotRoutine, int timeToWait, Buff buff)
    {
				this.mainBotRoutine = mainBotRoutine;
				this.timeToWait = timeToWait;
				this.buff = buff;
    }
    
    @Override
    public void run()
    {
    	
		try 
		{
		    log.appendToEventLogsFile("The timer for " + buff + " has started for " + timeToWait + " milliseconds. ");
		} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		
		try 
		{
		    Thread.sleep(timeToWait);
		} catch (InterruptedException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		
		try 
		{
		    log.appendToEventLogsFile("The timer for " + buff + " has expired. ");
		} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		
		String mainBotState = mainBotRoutine.getState().toString();
		
		switch (buff)
		{
		    case ABSORB:
		    	while (!(mainBotState.toString().equals("TIMED_WAITING")))
		    	{
		    		try 
		    		{
		    		    Thread.sleep(100);
		    		} catch (InterruptedException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		    		
		    		mainBotState = mainBotRoutine.getState().toString();
		    	}
		    	mainBotRoutine.setShouldAbsorb(true);
				
				try 
				{
								log.appendToEventLogsFile("Setting shouldAbsorb to true. Currently shouldAbsorb is: " + mainBotRoutine.getShouldAbsorb() + " (Should be true)");
				} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
				
				break;
			
		    case OVERLOAD:
		    	while (!(mainBotState.toString().equals("TIMED_WAITING")))
		    	{
		    		try 
		    		{
		    		    Thread.sleep(100);
		    		} catch (InterruptedException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
		    		
		    		mainBotState = mainBotRoutine.getState().toString();
		    	}
		    	mainBotRoutine.setShouldOverload(true);
							
				try 
				{
								log.appendToEventLogsFile("Setting shouldAbsorb to true. Currently shouldOverload is: " + mainBotRoutine.getShouldOverload() + " (Should be true)");
				} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
				
				break;
			
		    default:
			try 
			{
			    log.appendToEventLogsFile("The current buff (" + buff.toString() + ") could not be handled. ");
			} catch (FileNotFoundException ex) {Logger.getLogger(BuffTimer.class.getName()).log(Level.SEVERE, null, ex);}
			break;
		}
	
    }
}
