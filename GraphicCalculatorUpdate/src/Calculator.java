import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator {
	
	//Fields, that required in some methods	
	//Main panel
	JPanel panel;
	//Calculator display
	JTextArea display;
	//Array with names of buttons
	//Array with names for buttons
	// Order names in massive equals order of buttons on calculator
	//Procent, Square, Power, Inverted
	//Clear, ClearE, BackSpace, Devide
	//7, 8, 9, Multiple
	//4,5,6, minus
	//1,2,3, plus
	//+/-, 0, ., =
	String[] buttonsNames={"%", "\u02b8\u221A", "x\u02b8", "1/x",
			"C", "CE", "\u2190","/",
			"7", "8", "9", "X",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			"+/-", "0", ".", "="};
	//Contains name of button which was clicked
	String command;
	//Use to concat numbers with each other after pressing number buttons
	String previous="";
	//First number
	Double num1=0.0;
	//Second number;
	Double num2;
	//Choosen arifmetical operation
	String operation="";
	
	//Create graphic interface
	public void createGUI() {
		//Create frame
		JFrame frame=new JFrame("Graphic calculator");
		//Create main panel		
		panel=new JPanel();
		//This panel contains buttons
		//This panel has grid layout
		panel.setLayout(new GridLayout(6,9));		
		//Create calculator display
		//Set default value to null ("")
		display=new JTextArea("");
		//Enable to write commands in display
		display.setEditable(false);
		//Set opaque to see the display
		display.setOpaque(true);
		//Set color for display
		display.setBackground(Color.WHITE);
		//Set display size
		display.setPreferredSize(new Dimension(205,25));
		//Create font for display
		Font font=new Font("DS-Digital", Font.BOLD, 15);
		//Set display font
		display.setFont(font);	
		//Create array of created buttons
		JButton[] buttons=createButtons();
		//Add ActionListener to buttons
		addActionListeners(buttons);
		//Add buttons to panel
		addButtons(buttons);		
		//Add display to frame
		frame.add(display);
		//Add panel to frame
		frame.add(panel);
		//Set size of frame
		frame.setSize(270,250);
		//Set calculator background color as black
		frame.getContentPane().setBackground(Color.BLACK);
		//Place panel with buttons under the display
//TEST	//frame.setLayout(new BoxLayout(display, 0));
		frame.setLayout(new FlowLayout());
		//Finish programm by closing the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Make frame visible
		frame.setVisible(true);
		
	}
	
	public JButton[] createButtons() {		
		//Array for button storage
		//Size of that array is the same as buttonNames arrays's length		
		JButton[] buttons=new JButton[buttonsNames.length];	
		for (int i=0; i<buttons.length;i++) {
			//Button name equals name from buttonNames array
			//Create  new button with that name;
			//Put button into buttons array
			buttons[i]=new JButton(buttonsNames[i]);
		}
		//return buttons array to use in addButtons method
		return buttons;
	}
	
	public void addButtons(JButton[] buttons) {
		for(int i=0; i<buttons.length;i++) {
			panel.add(buttons[i]);
		}
	}
	public void addActionListeners(JButton[] buttons) {
		//Add ActionListener to every button from buttons array
		for(int i=0; i<buttons.length;i++) {
			//actionPerformed method is in ButtonActionsListener
			buttons[i].addActionListener(new ButtonsActionListener());
		}
	}	
	public class ButtonsActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			//Get name of clicked button
			 command=event.getActionCommand();			
			 getCommand();
		}
		
	}

	public void getCommand() {
		//if clicked on number button
		if (isNumber()) {
			if (isPrevious()) {
				clearDisplay();
				}
				displayNumber();
				
			}
		//if clicked on +-/x etc button 
		else if(isBinaryOperation()) {		
			setPrevious();
			setNum1();
			setOperation();			
			}
		//if clicked on unary buttons
		else {			
			switch(command) {
	
	case "+/-":num1=-setNum1(); displayNum1();break;
	case "%": num1=setNum1()/100;setPrevious();displayNum1();setOperation();break;
	case "1/x":setPrevious();setNum1();display.setText(1/num1+"");break;	
	case ".":display.append(command);break;
	case "CE":clearDisplay();break;
	case "C": clearDisplay(); num1=0.0;break;
	case "\u2190":display.setText(display.getText().substring(0,display.getText().length()-1));break;
	case "=": setNum2();calculate();}
			}	
	}
	public boolean isPrevious() {
	//compare display content with it previous content
		return display.getText().equals(previous);
	}
	public void clearDisplay() {
		//set display text to ""
		display.setText("");
	}
	public void displayNumber() {
		//add number from clicked button number to display
		display.append(command);
	}
	public Double setNum1() {	
		//write display content to num1
		return num1=Double.parseDouble(display.getText());
	}
	public void displayNum1() {
		//display value of num1
		display.setText(num1+"");
	}
	public void setPrevious() {
		//set previous as display content 
		previous=display.getText();
	}
	public void setOperation() {
		//set operation as command
		operation=command;
	}
	public Double setNum2() {
		//write display content to num2
		return num2=Double.parseDouble(display.getText());
	}
	public void calculate() {
		//make calculation with chosen operation		
	//	switch (operation)
		switch (operation)
		{
		  case "+": display.setText(num1+num2+"");break;	
		  case "-": display.setText(num1-num2+"");break;
		  case "X": display.setText(num1*num2+"");break;
		  case "/": display.setText(num1/num2+"");break;		  
		  case "x\u02b8": display.setText(Math.pow(num1, num2)+"");break;
		  case "\u02b8\u221A":display.setText(Math.pow(num1, 1/num2)+"");break;	  
		}
		setPrevious();
		//clear operation
		operation="";
	}
	public boolean isNumber() {
		//return true if number button was clicked
		return command.matches("[0-9]");
	}
	public boolean isBinaryOperation() {
		//return true if button with binary operation was clicked
		return command.matches("[-,+,X,/]")||command.matches("x\\u02b8")||command.matches("\u02b8\u221A");
	}	
	//Build Calculator and run it
	public void runCalculator() {
		//Create graphic interface
		createGUI();		
	}
}
