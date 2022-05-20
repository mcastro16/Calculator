import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {
  private TextField txField = new TextField();
  private Panel buttonPanel = new Panel();
  Evaluator calculate = new Evaluator();

  // total of 20 buttons on the calculator,
  // numbered from left to right, top to bottom
  // bText[] array contains the text for corresponding buttons
  private static final String[] bText = {
    "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
    "*", "0", "^", "=", "/", "(", ")", "C", "CE"
  };

  private Button[] buttons = new Button[ bText.length ];

  public static void main(String[] args) {
    EvaluatorUI calc = new EvaluatorUI();
  }

  public EvaluatorUI() {
    setLayout( new BorderLayout() );

    add( txField, BorderLayout.PAGE_START );
    txField.setEditable( false );

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ) );

    //create 20 buttons with corresponding text in bText[] array
    //add buttons to button panel
    //set up buttons to listen for mouse input
    for ( int i = 0; i < bText.length; i++ ) {
      buttons[ i ] = new Button( bText[ i ] );
      buttonPanel.add( buttons[ i ] );
      buttons[ i ].addActionListener( this );
    }

    setTitle( "Calculator" );
    setSize( 400, 400 );
    setLocationByPlatform( true );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );
  }

  public void actionPerformed( ActionEvent arg0 ) {
    //System.out.print(arg0.getSource());
    if(arg0.getSource() != buttons[14] && arg0.getSource() != buttons[18] && arg0.getSource() != buttons[19]) {
      for (int i = 0; i < buttons.length; i++) {
        if(arg0.getSource() == buttons[i]) {
          txField.setText(txField.getText() + bText[i]);
          //System.out.println(txField.getText().toString());
        }
      }
    }
    else if(arg0.getSource() == buttons[18]) {
      String currentText = txField.getText();
      if(!currentText.equals("")) {
        txField.setText(currentText.substring(0, currentText.length()-1));
      }
    }
    else if(arg0.getSource() == buttons[19]) {
      txField.setText("");
    }
    else if(arg0.getSource() == buttons[14]) {
      String string = txField.getText().toString();
      String result = Integer.toString(calculate.eval(string));
			txField.setText(result);
    }
  }
}
