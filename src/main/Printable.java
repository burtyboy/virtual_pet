package main;

/**
 * 
 * Printable Interface. Provides the method declerations required to print to the command line and get input from command line
 *
 */
public interface Printable {
public String getInput();
public void printToScreen(String displayString);
public void printHeader();
}