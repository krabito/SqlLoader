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
import java.io.*;
import java.util.Vector;


public class SqlDbPref extends JDialog {
  final String prefFile = "DbPref.ser";
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel jPanelOracle = new JPanel(); // Oracle
  JPanel jPanelSybase = new JPanel(); // Sybase
  JButton saveButton = new JButton();
  JButton cancelButton = new JButton();
  JButton defaultButton = new JButton();
  Border border1;
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JTabbedPane jTabbedPane1 = new JTabbedPane(2);
  JLabel jPerfBuffLabel = new JLabel();
  JCheckBox autoCommitOracle = new JCheckBox();
  JCheckBox autoCommitSybase = new JCheckBox();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  TitledBorder titledBorder1;


  public SqlDbPref(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    pack();
  }

  public SqlDbPref(Frame frame, String title) {
    this(frame, title, false);
  }

  public SqlDbPref(Frame frame) {
    this(frame, "", false);
  }

  private void jbInit() throws Exception {

    titledBorder1 = new TitledBorder("");
    this.setTitle("DataBase Preference");
    border1 = BorderFactory.createRaisedBevelBorder();
    jPanel1.setLayout(gridLayout1);
    panel2.setBorder(border1);
    saveButton.setText("Save");
    saveButton.addActionListener(new SqlDbPref_saveButton_actionAdapter(this));
    cancelButton.setText("Cancel");
    gridLayout1.setHgap(4);
    cancelButton.addActionListener(new SqlDbPref_cancelButton_actionAdapter(this));
    defaultButton.setText("Default");
    defaultButton.addActionListener(new SqlDbPref_defaultButton_actionAdapter(this));
    this.addWindowListener(new SqlDbPref_this_windowAdapter(this));
    panel1.setLayout(gridBagLayout1);
    jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane1.setMinimumSize(new Dimension(100, 100));
    jTabbedPane1.setPreferredSize(new Dimension(425, 320));

    jPanelOracle.setLayout(gridBagLayout2);

    jPanelOracle.setBorder(border1);
    panel1.add(jPanel1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 100, 4, 93), 0, 0));
    jPanel1.add(saveButton, null);
    jPanel1.add(cancelButton, null);
    jPanel1.add(defaultButton, null);
    panel1.add(panel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), -19, -67));
    panel2.add(jTabbedPane1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(8, 8, 0, 8), 0, 0));
    jTabbedPane1.addTab("Oracle", jPanelOracle);
    autoCommitOracle.setText("AutoCommit");
    jPanelOracle.add(autoCommitOracle, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 7, 241, 307), 9, -1));
    jTabbedPane1.addTab("Sybase", jPanelSybase);
    autoCommitSybase.setText("AutoCommit");    
    jPanelSybase.add(autoCommitSybase, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 7, 241, 307), 9, -1));
    getContentPane().add(panel1);
}

  // Save
  void saveButton_actionPerformed(ActionEvent e) {
    dispose();
  }

  // Cancel
  void cancelButton_actionPerformed(ActionEvent e) {
    dispose();
  }

  void defaultButton_actionPerformed(ActionEvent e) {
     //dispose();
  }

  void this_windowClosing(WindowEvent e) {
    dispose();
  }
}

class SqlDbPref_saveButton_actionAdapter implements ActionListener {
  SqlDbPref adaptee;

  SqlDbPref_saveButton_actionAdapter(SqlDbPref adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.saveButton_actionPerformed(e);
  }
}

class SqlDbPref_cancelButton_actionAdapter implements ActionListener {
  SqlDbPref adaptee;

  SqlDbPref_cancelButton_actionAdapter(SqlDbPref adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cancelButton_actionPerformed(e);
  }
}

class SqlDbPref_defaultButton_actionAdapter implements ActionListener {
  SqlDbPref adaptee;

  SqlDbPref_defaultButton_actionAdapter(SqlDbPref adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.defaultButton_actionPerformed(e);
  }
}

class SqlDbPref_this_windowAdapter extends WindowAdapter {
  SqlDbPref adaptee;

  SqlDbPref_this_windowAdapter(SqlDbPref adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}
 
