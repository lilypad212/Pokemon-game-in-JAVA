/* Lily and Farren Wang
 * CS230 Final Project
 * PokemonStartupPanel.java
 * 
 * Created by: Lily
 * The player can choose one of the three starter Pok�mon and start the game */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class PokemonStartupPanel extends JPanel
{
  private JButton startButton, quitButton;
  private JRadioButton pokemon1, pokemon2, pokemon3; // The starter Pokemon choices
  private JLabel charmander, squirtle, bulbasaur, label;
  private JFrame pokemonWindow;
  private ImageIcon charmanderImage, squirtleImage, bulbasaurImage;
  private JPanel choices, buttons; // Separate panels for the radiobuttons/images and the start and quit buttons
  private ButtonGroup bg; // For the radiobuttons
  
  //-----------------------------------------------------------------
  //  Constructor: Sets up the main GUI components.
  //-----------------------------------------------------------------
  public PokemonStartupPanel() {
    // Uses a custom font found on the Internet
    Font myFont = null;
    try {
      File fontFile = new File("Pokemon GB.ttf");
      try {
        myFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 22f);
      } catch (java.awt.FontFormatException e) {
        System.out.println("Error while creating font");
      }} catch (java.io.IOException e) {
        System.out.println("Error while creating font");
      }
      
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(myFont);
      
      // Plot and instructions
      label = new JLabel("<html><font size= 6><center><br>OH NO! You have been captured by the evil Team Rocket!"+
                         "<br><br>Thankfully one of your sources have told you that you can buy<br>a key from a" +
                           " corrupt Team Rocket Grunt to open the gate.<br><br>" +
                         "Earn enough gold from battling wild Pokemon to buy the key and escape!<br><br>" +
                         "Be aware that there are a limited number of wild Pokemon in your vicinity.<br><br>" +
                         "Choose a starter Pokemon, press 'START' and use your arrow keys to navigate the map."+
                         "<br> <br><br><br></font></html>");
      
      // Sets up all panels
      GridLayout g1 = new GridLayout(2, 3);
      g1.setHgap(200);
      choices = new JPanel(g1);
      GridLayout g2 = new GridLayout(1, 2);
      g2.setHgap(300);
      buttons = new JPanel(g2);
      
      // Sets up all necessary components to display starter Pokemon
      charmanderImage = new ImageIcon("img/Charmander.gif", "Charmander");
      squirtleImage = new ImageIcon("img/Squirtle.gif", "Squirtle");
      bulbasaurImage = new ImageIcon("img/Bulbasaur.gif", "Bulbasaur");
      
      charmander = new JLabel(charmanderImage);
      squirtle = new JLabel(squirtleImage);
      bulbasaur = new JLabel(bulbasaurImage);
      
      // Charmander is selected by default
      pokemon1 = new JRadioButton("<html><font size = 8>Charmander</font></html>", true);
      pokemon2 = new JRadioButton("<html><font size = 8>Squirtle</font></html>");
      pokemon3 = new JRadioButton("<html><font size = 8>Bulbasaur</font></html>");
      
      // Ensures user can't select more than one Pokemon
      bg = new ButtonGroup();
      bg.add(pokemon1);
      bg.add(pokemon2);
      bg.add(pokemon3);
      
      // Stylistic things such as font and font sizes
      startButton = new JButton("<html><font size = 10>START</font></html>");
      startButton.setFont(myFont);
      startButton.setBackground(Color.RED);
      quitButton = new JButton("<html><font size = 10>QUIT</font></html>");
      quitButton.setFont(myFont);
      quitButton.setBackground(Color.BLUE);
      
      label.setFont(myFont);
      pokemon1.setFont(myFont);
      pokemon2.setFont(myFont);
      pokemon3.setFont(myFont);
      
      // Adds functionality to the buttons
      ButtonListener listener = new ButtonListener();
      startButton.addActionListener(listener);
      quitButton.addActionListener(listener);
      
      // Adds all components onto the panel
      add(label);
      choices.add(charmander);
      choices.add(squirtle);
      choices.add(bulbasaur);
      choices.add(pokemon1);
      choices.add(pokemon2);
      choices.add(pokemon3);
      add(choices);
      buttons.add(startButton);
      buttons.add(quitButton);           
      add(buttons);
      
      buttons.setPreferredSize(new Dimension(1000, 100)); // Changes sizes of buttons
      setPreferredSize (new Dimension(2100, 1200));
      
      pokemonWindow = null;  // Until user presses start, there is no maze window.
  }
  
  //*****************************************************************
  //  Represents an action listener for the buttons.
  //*****************************************************************
  private class ButtonListener implements ActionListener
  {
    //--------------------------------------------------------------
    //  Determines which button was pressed and creates the map.
    public void actionPerformed (ActionEvent event)
    {
      if (pokemonWindow != null) pokemonWindow.dispose();  // Close existing map window, if it exists.
      
      // Quit button was pressed
      if (event.getSource() == quitButton) 
        System.exit(0); 
      
      String pokemonName = ""; // Used to store the name of the selected Pokemon
      String type = ""; // Used to store the type of the selected Pokemon
      // Start button was pressed
      if (event.getSource() == startButton){
        // Determines the name and type of the Pokemon selected by the user
        if (pokemon1.isSelected()){
          pokemonName = charmanderImage.getDescription();
          type = "Fire";
        }
        else if (pokemon2.isSelected()){
          pokemonName = squirtleImage.getDescription();
          type = "Water";
        }
        else {
          pokemonName = bulbasaurImage.getDescription();
          type = "Grass";
        }
      }
      
      // Create new Pokemon game with selected starter Pokemon
      pokemonWindow = new JFrame("Map");
      pokemonWindow.getContentPane().add (new MapPanel(10, 10, new Pokemon(pokemonName, type)));  // 10x10 map
      pokemonWindow.pack();
      pokemonWindow.setVisible(true);
    }
  }
}
