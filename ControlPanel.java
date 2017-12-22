
//Title:        SqlLoader
//Version:      1.0
//Copyright:    Copyright (c) 1999
//Author:       Kevin Rabito
//Company:      Sun Microsystems
//Description:  Control Panel for SqlLoader.

package SqlLoader;

import java.awt.*;
import java.awt.event.*;
//import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.util.*;

public class ControlPanel extends JPanel {
  private final int maxThreads = 25;   // Max number of threads to run.
  private final int minThreads = 1;    // Min number of threads to run.
  private final int maxIterations = 75; // Max number of iterations to run.
  private final int minIterations = 1;  // Min number of iterations to run.
  private final int maxSleepDuration = 60; // Max seconds to sleep.
  private final int minSleepDuration = 0;  // Min seconds to sleep.
  private boolean reConnect = false;
  private final boolean cpDebug = false;
  private String sleepString = "0";
  private String iterationString = "1";
  private String threadString = "1";
  private String reConnectString = "false";
  private ThreadGroup grpRun;
  private Thread [] goDb;
  private int thrCnt = 1;
  JButton jStartButton = new JButton();
  JButton jStopButton = new JButton();
  JLabel jHostLabel = new JLabel();
  JTextField jHostField = new JTextField();
  JLabel jLoginLabel = new JLabel();
  JTextField jLoginField = new JTextField();
  JTextArea jSqlArea = new JTextArea();
  JLabel jSqlLabel = new JLabel();
  JComboBox jThreadsBox = new JComboBox();
  JComboBox jIterationsBox = new JComboBox();
  JLabel jThreadLabel = new JLabel();
  JLabel jIterationLabel = new JLabel();
  JLabel jServerLabel = new JLabel();
  JTextField jServerField = new JTextField();
  JLabel jPortLabel = new JLabel();
  JTextField jPortField = new JTextField();
  JComboBox jSleepBox = new JComboBox();
  JLabel jSleepLabel = new JLabel();
  JCheckBox jReConnectBox = new JCheckBox();
  JButton jResetButton = new JButton();
  JLabel jPasswdLabel = new JLabel();
  JPasswordField jPasswordField1 = new JPasswordField();
  JLabel jStatusLabel = new JLabel();
  JProgressBar jProgressBar1 = new JProgressBar();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public ControlPanel() {
    try  {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    jStartButton.setText("Start");
    this.setLayout(gridBagLayout1);
    jStopButton.setEnabled(false);
    jStopButton.setText("Stop");
    jResetButton.setText("Reset");
    jHostLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jHostLabel.setToolTipText("Systems Hostname");
    jHostLabel.setLabelFor(jHostField);
    jHostLabel.setText("Hostname");
    jLoginLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jLoginLabel.setToolTipText("DB User");
    jLoginLabel.setLabelFor(jLoginField);
    jLoginLabel.setText("DB User");
    jSqlArea.setColumns(30);
    jSqlArea.setWrapStyleWord(true);
    jSqlArea.setRows(20);
    jSqlArea.setToolTipText("Sql Reconnect after Each Statement");
    jSqlArea.setBorder(BorderFactory.createLoweredBevelBorder());
    jSqlLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jSqlLabel.setLabelFor(jSqlArea);
    jSqlLabel.setText("Sql Statements");
    jThreadLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jThreadLabel.setToolTipText("Number of Sql Threads");
    jThreadLabel.setLabelFor(jThreadsBox);
    jThreadLabel.setText("Threads");
    jThreadsBox.setEditable(false);

    // Load Thread values for selection.
    for(int i = minThreads; i < maxThreads + 1; i++){
       jThreadsBox.addItem(intToString(i));
    }
    jIterationLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jIterationLabel.setToolTipText("Number of Iterations per Thread");
    jIterationLabel.setLabelFor(jIterationsBox);
    jIterationLabel.setText("Iterations");
    jIterationsBox.setEditable(false);

    //  Load Iteration values for selection.
    for(int i = minIterations; i < maxIterations + 1; i++){
      jIterationsBox.addItem(intToString(i));
    }
    jServerLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jServerLabel.setToolTipText("DB Server/SID/Instance Name");
    jServerLabel.setLabelFor(jServerField);
    jServerLabel.setText("Server");
    jPortLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jPortLabel.setToolTipText("Port Number");
    jPortLabel.setLabelFor(jPortField);
    jPortLabel.setText("Port");
    jSleepLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jSleepLabel.setText("Sleep (secs)");
    jSleepBox.setEditable(false);

    // Load Sleep values for selection.
    for(int i = 0; i < maxSleepDuration + 1; i++){
      jSleepBox.addItem(intToString(i));
    }
    jReConnectBox.setText("Reconnect");
    jReConnectBox.setFont(new java.awt.Font("Dialog", 1, 12));

    jPasswdLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jPasswdLabel.setToolTipText("DB Password");
    jPasswdLabel.setLabelFor(jPasswordField1);
    jPasswdLabel.setText("Password");

    jStatusLabel.setFont(new java.awt.Font("Dialog", 1, 12));
    jStatusLabel.setText("Status");
    this.add(jHostLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 13, 0, 0), 9, 3));
    this.add(jHostField, new GridBagConstraints(2, 0, 2, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(15, 0, 0, 0), 67, 2));
    this.add(jLoginLabel, new GridBagConstraints(4, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 27, 0, 0), 9, 7));
    this.add(jLoginField, new GridBagConstraints(6, 0, 3, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(15, 0, 0, 4), 156, 2));
    this.add(jSqlLabel, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 13, 0, 6), 6, 3));
    this.add(jServerField, new GridBagConstraints(2, 1, 2, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(21, 0, 0, 0), 67, 4));
    this.add(jServerLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(26, 13, 0, 0), 15, 2));
    this.add(jPortField, new GridBagConstraints(2, 3, 2, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 0), 67, 3));
    this.add(jSleepBox, new GridBagConstraints(8, 5, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 4), -16, -3));
    this.add(jSleepLabel, new GridBagConstraints(5, 5, 3, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 13, -1));
    this.add(jPortLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 13, 10, 0), 29, 6));
    this.add(jReConnectBox, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 23, 0, 0), 3, -2));
    this.add(jIterationsBox, new GridBagConstraints(7, 3, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(16, 8, 0, 4), 9, -2));
    this.add(jIterationLabel, new GridBagConstraints(5, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 7, 0, 0), 5, 3));
    this.add(jThreadsBox, new GridBagConstraints(7, 2, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 6, 0, 4), 11, -2));
    this.add(jThreadLabel, new GridBagConstraints(5, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 12, 0, 0), 7, 8));
    this.add(jPasswdLabel, new GridBagConstraints(4, 1, 2, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(16, 13, 8, 0), 7, 5));
    this.add(jPasswordField1, new GridBagConstraints(6, 1, 3, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(13, 0, 10, 4), 156, 2));
    this.add(jSqlArea, new GridBagConstraints(0, 7, 9, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 7, 0, 10), 173, -239));
    this.add(jStopButton, new GridBagConstraints(3, 8, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(7, 0, 0, 0), 14, 2));
    this.add(jStartButton, new GridBagConstraints(0, 8, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(7, 13, 0, 18), 22, 2));
    this.add(jStatusLabel, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(27, 15, 5, 0), 14, 6));
    this.add(jProgressBar1, new GridBagConstraints(1, 9, 8, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(28, 0, 5, 33), 150, 7));
    this.add(jResetButton, new GridBagConstraints(5, 8, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 13, 0, 10), 9, 2));

    // Start Button Listener
    jStartButton.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        sqlStart_actionPerformed(e);
      }
    });
    // Stop Button Listener
    jStopButton.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
      boolean stillActive = true;
      jStartButton.setEnabled(true);  // Reset buttons
      jStopButton.setEnabled(false);
      jResetButton.setEnabled(true);


      while( stillActive ){
        grpRun.list();
        for( int a = 0; a < thrCnt; a++){
          if(goDb[a].isAlive()){
           stillActive = true;
           goDb[a].interrupt();
           }
           else stillActive = false;
        }
        System.out.println("Sleeping");
        try {
           Thread.sleep(200);
          }
        catch (Exception s){
          }
        System.out.println("Active after sleep: " + grpRun.activeCount());
      }
      System.out.println("Active should be ZERO: " + grpRun.activeCount());
      //sqlStop_actionPerformed(e);
      }
    });
    // Reset Button Listener
    jResetButton.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        reset_actionPerformed(e);
      }
    });

    // Thread ComboBox Listener
    jThreadsBox.addItemListener(new ItemListener()   {

      public void itemStateChanged(ItemEvent e){
        // set thread number.
        Integer justAInt = new Integer((String) jThreadsBox.getSelectedItem());
        threadString = justAInt.toString();
        }
    });

    // Iterations ComboBox Listener
    jIterationsBox.addItemListener(new ItemListener()   {

      public void itemStateChanged(ItemEvent e){
        // set iterations number.
        Integer justAInt = new Integer((String) jIterationsBox.getSelectedItem());
        iterationString = justAInt.toString();
        }
    });

    // Sleep ComboBox Listener
    jSleepBox.addItemListener(new ItemListener()   {

      public void itemStateChanged(ItemEvent e){
        // set sleep duration.
        Integer justAInt = new Integer((String) jSleepBox.getSelectedItem());
        sleepString = justAInt.toString();
        }
    });
    // Reconnect Checkbox Listener
    jReConnectBox.addItemListener(new ItemListener()   {

      public void itemStateChanged(ItemEvent e){
              // Get checkbox state.
        if(jReConnectBox.isSelected()){
          reConnect = true;
          }
        else {
          reConnect = false;
          }
      }
    });
  }

  // Quick integer to String converter.

  private String intToString(int intNumber){
    Integer iNumber = new Integer(intNumber);
    return(iNumber.toString());
  }

  private int stringToInt(String strValue){
    Integer iNumber = new Integer(strValue);
    return(iNumber.intValue());
    }

  private void reset_actionPerformed(ActionEvent e){

  // Reset Fields to default.

    jHostField.setText(""); ;
    jServerField.setText("");
    jPortField.setText("");
    jLoginField.setText("");
    jSqlArea.setText("");
    jPasswordField1.setText("");
    sleepString = "0";
    jSleepBox.setSelectedIndex(0);
    threadString = "1";
    jThreadsBox.setSelectedIndex(0);
    iterationString = "1";
    jIterationsBox.setSelectedIndex(0);
    jReConnectBox.setSelected(false);
    reConnect = false;

  }

  private void sqlStart_actionPerformed(ActionEvent e){
    Vector dbFields = new Vector(8);
    boolean validVector = true;


    if(cpDebug){
    System.out.println("Sleep: " + sleepString);
    System.out.println("ReConnect: " + reConnect);
    System.out.println("Iterations: " +  iterationString);
    System.out.println("Threads: " +  threadString);
    System.out.println("Host: " + jHostField.getText());
    System.out.println("Server: " + jServerField.getText());
    System.out.println("Port: " + jPortField.getText());
    System.out.println("SqlArea: " + jSqlArea.getText());
    System.out.println("Login: " + jLoginField.getText());
    System.out.println("Password: " + jPasswordField1.getPassword());
    }

  // need to collect textfield, comboboxes data and
  // checkbox status to put into a vector.

    dbFields.addElement(jHostField.getText());
    dbFields.addElement(jServerField.getText());
    dbFields.addElement(jPortField.getText());
    dbFields.addElement(jLoginField.getText());
    dbFields.addElement(jSqlArea.getText());
    dbFields.addElement(sleepString);
    Boolean booMe = new Boolean(reConnect);
    dbFields.addElement(booMe.toString());  // Convert toString.
    dbFields.addElement(iterationString);
    String tmpStr = new String(jPasswordField1.getPassword());
    dbFields.addElement(tmpStr);
    tmpStr = null;
    dbFields.trimToSize();
    System.gc();  // Take out the trash.

    // Need to check if any elements are empty before
    // Kicking of the DB connection(s).
    for( int a = 0; a <= dbFields.size() - 1;a++){
      String testField = dbFields.elementAt(a).toString();
      if(testField.length() == 0){
        validVector = false;
        break;
        }
    }
    if( validVector == true ){
      // Kick off the dbconnections
      jStartButton.setEnabled(false); // Enable/disable buttons
      jStopButton.setEnabled(true);
      jResetButton.setEnabled(false);
      DbRunner runDb = new DbRunner(dbFields);
      grpRun = new ThreadGroup("running");
      thrCnt = stringToInt(threadString);
      goDb = new Thread[thrCnt];

      for(int i=0;i < thrCnt; i++){
        goDb[i] = new Thread(grpRun,runDb);
        goDb[i].setPriority(5);
        goDb[i].start();

      }
      grpRun.list();
    }
    else {  // Missing values.
      jStartButton.setEnabled(true);  // Reset buttons
      jStopButton.setEnabled(false);
      jResetButton.setEnabled(true);
      SqlMessageUI dlg = new SqlMessageUI(null,"ERROR");
      dlg.setMessage("Missing Fields, Please fill in all fields");
      Dimension dlgSize = dlg.getPreferredSize();
      Dimension frmSize = getSize();
      Point loc = getLocation();
      dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
      dlg.setModal(true);
      dlg.show();
    }
  }

}
