package com.timelessapps.javafxtemplate.services;

import com.timelessapps.javafxtemplate.abstractsandenums.Coordinates;
import static com.timelessapps.javafxtemplate.abstractsandenums.Coordinates.X;
import static com.timelessapps.javafxtemplate.abstractsandenums.Coordinates.Y;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class RobotService extends Robot
{
    public RobotService() throws AWTException
    {
        //super();
    }
    
    /** Section below is for typing related functions. **/
    private boolean isUpperCase(char c)
    {
        char[] capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (char a:capitalLetters)
        {
            if (a == c)
            {
                return true;
            }
        }
        return false;
    }
    
    public void type (String text, int delayBetweenKeyPress)
    {
        String textUpper = text.toUpperCase();
        
        for (int i=0; i < text.length(); ++i)
        {
            if (isUpperCase(text.charAt(i)))
            {
                //For typing capital letters.
                keyType((int)text.charAt(i), KeyEvent.VK_SHIFT);
            }
            else
            {
                //For typing symbols and lowercase. 
                typeChar(textUpper.charAt(i));
            }
            delay(delayBetweenKeyPress);
        }
    }
    
    //Used if getting text from a source that may contain weird symbols and other stuff that is not covered by the normal typing function. 
    public void typeFromClipboard(String text)
    {
        writeToClipboard(text);
        pasteFromClipboard();
    }
    
    public void pasteFromClipboard()
    {
        keyPress(KeyEvent.VK_CONTROL);
        keyPress(KeyEvent.VK_V);
        delay(50);
        keyRelease(KeyEvent.VK_V);
        keyRelease(KeyEvent.VK_CONTROL);
    }
        
    public void writeToClipboard(String s)
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(s);
        clipboard.setContents(transferable, null);
    }
    
    private void keyType (int keyCode)
    {
        keyPress(keyCode);
        delay(10);
        keyRelease(keyCode);
    }
	
    private void keyType(int keyCode, int keyCodeModifier)
    {
        keyPress(keyCodeModifier);
        keyPress(keyCode);
        delay(10);
        keyRelease(keyCode);
        keyRelease(keyCodeModifier);
    }

    private void typeChar (char c)
    {
        boolean shift = true;
        int keyCode;

        switch (c)
        {
        case '~':
            keyCode = (int)'`';
            break;
        case '!':
            keyCode = (int)'1';
            break;
        case '@':
            keyCode = (int)'2';
            break;
        case '#':
            keyCode = (int)'3';
            break;
        case '$':
            keyCode = (int)'4';
            break;
        case '%':
            keyCode = (int)'5';
            break;
        case '^':
            keyCode = (int)'6';
            break;
        case '&':
            keyCode = (int)'7';
            break;
        case '*':
            keyCode = (int)'8';
            break;
        case '(':
            keyCode = (int)'9';
            break;
        case ')':
            keyCode = (int)'0';
            break;
        case ':':
            keyCode = (int)';';
            break;
        case '_':
            keyCode = (int)'-';
            break;
        case '+':
            keyCode = (int)'=';
            break;
        case '|':
            keyCode = (int)'\\';
            break;
        case '?':
            keyCode = (int)'/';
            break;
        case '{':
            keyCode = (int)'[';
            break;
        case '}':
            keyCode = (int)']';
            break;
        case '<':
            keyCode = (int)',';
            break;
        case '>':
            keyCode = (int)'.';
            break;
        default:
            keyCode = (int)c;
            shift = false;
        }

        if (shift)
        {
            keyType (keyCode, KeyEvent.VK_SHIFT);
        }
        else
        {
            keyType(keyCode);
        }
    }
    /** End typing section. **/
    
    /** Sections below is for mouse movement functions. **/
    
    public void mouseGlide(int x1, int y1, int x2, int y2, int t, int n) 
    {
        double dx = (x2 - x1) / ((double) n);
        double dy = (y2 - y1) / ((double) n);
        double dt = t / ((double) n);
        for (int step = 1; step <= n; step++) 
        {
            delay((int) dt);
            mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
        }
    } 
    
    public int getCurrentMousePosition(Coordinates coord)
    {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        
        int x = (int) b.getX();
        int y = (int) b.getY();
        
        if (coord.equals(X))
        {
            return x;
        }
        else if (coord.equals(Y))
        {
            return y;
        }
        return 0;
    }
    
}
    
    /** End mouse movement section. **/