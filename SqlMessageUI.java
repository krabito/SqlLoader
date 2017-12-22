// This snippet creates a new dialog box
// with buttons on the bottom.

//Title:
//Version:
//Copyright:
//Author:
//Company:
//Description:

package  SqlLoader;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SqlMessageUI extends JDialog {
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JButton button1 = new JButton();
  Border border1;
  JPanel jPanel1 = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridLayout gridLayout1 = new GridLayout();
  private String dialogMsg = "";
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JLabel jLabel1 = new JLabel();

  public SqlMessageUI(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    pack();
  }

  public SqlMessageUI(Frame frame, String title) {
    this(frame, title, false);
  }

  public SqlMessageUI(Frame frame) {
    this(frame, "", false);
  }

  public void setMessage(String yourMessage){
      jLabel1.setText(yourMessage);
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createRaisedBevelBorder();
    jPanel1.setLayout(gridLayout1);
    button1.setText("OK");
    button1.addActionListener(new SqlMessage_button1_actionAdapter(this));
    gridLayout1.setHgap(4);
    this.addWindowListener(new SqlMessage_this_windowAdapter(this));
    panel1.setLayout(gridBagLayout1);
    panel1.setMinimumSize(new Dimension(250, 75));
    panel1.setPreferredSize(new Dimension(250, 75));
    panel2.setLayout(gridBagLayout2);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    panel1.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    panel2.add(jLabel1, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 99, -2));
    panel1.add(jPanel1, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 8, 4, 8), 0, 0));
    jPanel1.add(button1, null);
    getContentPane().add(panel1);
}

  // OK
  void button1_actionPerformed(ActionEvent e) {
    dispose();
  }

  void this_windowClosing(WindowEvent e) {
    dispose();
  }
}

class SqlMessage_button1_actionAdapter implements ActionListener {
  SqlMessageUI adaptee;

  SqlMessage_button1_actionAdapter(SqlMessageUI adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.button1_actionPerformed(e);
  }
}


class SqlMessage_this_windowAdapter extends WindowAdapter {
  SqlMessageUI adaptee;

  SqlMessage_this_windowAdapter(SqlMessageUI adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}

