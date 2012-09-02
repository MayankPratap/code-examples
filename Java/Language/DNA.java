/** by Sean McKenna on February 4th, 2010
 *  Last modified on February 7th, 2010.
 *
 *  This program takes user input of DNA and outputs the complementary strand
 *  or it can also output the appropriate strand from RNA transcription.
 *
 *  The program also contains a GUI which was copied code from an assembler
 *  program that I constructed last semester.  A pairs with T, C with G.
 *
 *  For messenger RNA transcription, U replaces T.
 *  
 *  Any errors in the input strand the user with an error box and then allow
 *  the user to change/fix their input appropriately.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class DNA extends JFrame implements ActionListener {
    private JButton translate;		    //Button for sequencing
    private JButton rna;		    //Button for RNA transcription
    private JTextArea inputText;	    //Area for user input
    private JTextArea outputText;	    //Area for sequence output
    private Container frame;		    //The frame of the GUI (for error box)
    private char[][] table;		    //Character array for sequencing

    //Main method, calls the DNA object
    public static void main(String[] args) {
	DNA dna = new DNA();
    }

    //Creates the main GUI window and creates the DNA sequence array
    public DNA(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(800,600);
	this.setTitle("DNA Sequencer");
	this.setVisible(true);
	inputText = new JTextArea();
	outputText = new JTextArea();
	JScrollPane scrollerLeft = new JScrollPane(inputText);
	JScrollPane scrollerRight = new JScrollPane(outputText);
        frame = this.getContentPane();
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		scrollerLeft,scrollerRight);
	splitPane.setOneTouchExpandable(true);
	splitPane.setDividerLocation(400);
	translate = new JButton("Sequence");
	translate.addActionListener((ActionListener) this);
	translate.setActionCommand("translate");
	rna = new JButton("RNA Transcription");
	rna.addActionListener((ActionListener) this);
	rna.setActionCommand("transcription");
	JPanel buttons = new JPanel();
	buttons.add(translate);
	buttons.add(rna);
	frame.add(splitPane,BorderLayout.CENTER);
	frame.add(buttons,BorderLayout.NORTH);
	this.validate();
	this.makeArray();
    }

    //This method creates an array for the sequencing (0 is for input, 1 & 2 is for output)
    private void makeArray(){
	table = new char[4][3];
	table[0][0] = 'A';
	table[0][1] = 'T';
	table[0][2] = 'U';
	table[1][0] = 'G';
	table[1][1] = 'C';
	table[1][2] = 'C';
	table[2][0] = 'C';
	table[2][1] = 'G';
	table[2][2] = 'G';
	table[3][0] = 'T';
	table[3][1] = 'A';
	table[3][2] = 'A';
    }

    //Action when a button is hit
    public void actionPerformed(ActionEvent evt){
	if(evt.getActionCommand().equals("translate")){
	    readInput(1);
	}
	if(evt.getActionCommand().equals("transcription")){
	    readInput(2);
	}
    }

    //reads the input DNA strand and converts it to the appropriate strand
    // x = 1: convert to complementary DNA strand      x = 2: RNA transcription
    private void readInput(int x){
	Scanner inputStream = null;
	Scanner outputStream = null;
	outputText.setText("");
	try{
	    inputStream = new Scanner(inputText.getText());
	    String parse = "";
	    String sequence = "";
	    String out = "";
	    boolean error = false;

	    //Processes user input, first to make sure input is proper
	    //and then to replace any unneeded characters for the sequencing
	    while(inputStream.hasNext()){
		parse = inputStream.nextLine();
		parse = parse.toUpperCase();
		parse = parse.replace(" ","");
		String test = parse.replaceAll("[A||G||C||T]","");
		if(!test.isEmpty()){
		    error = true;
		}
		parse = parse.replaceAll("[^A||G||C||T]","");
		sequence = this.sequencer(parse, x);
		outputText.append(sequence+"\n");
	    }

	    //processes the output and if the input was empty or the input
	    //was incorrectly typed then an error box is displayed
	    outputStream = new Scanner(outputText.getText());
	    while(outputStream.hasNext()){
		out = out + outputStream.nextLine();
	    }
	    if(out.equals("") || error){
		JOptionPane.showMessageDialog(frame,
		"Please type a proper DNA sequence in the pane on the left.",
		"Input Error",JOptionPane.ERROR_MESSAGE);
	    }

	//closes stream
	}finally{
	    if(inputStream != null){
		inputStream.close();
	    }
	    if(outputStream != null){
		outputStream.close();
	    }
	}
    }

    //sequencer takes the input string and outputs the correct sequence
    private String sequencer(String in, int x){
	String out = "";
	int length = in.length();
	for(int i=0;i<length;i++){
	    out = out + this.getChar(in.charAt(i), x);
	}
	return out;
    }

    //getChar takes some character and finds its complementary letter (or U for RNA)
    private char getChar(char a, int x){
	char b='q';
	for(int i=0;i<4;i++){
	    if(a==table[i][0]){
		b = table[i][x];
	    }
	}
	return b;
    }
}
