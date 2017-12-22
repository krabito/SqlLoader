
//Title:      SqlLoader
//Version:    
//Copyright:  Copyright (c) 1999
//Author:     Kevin Rabito
//Company:    Sun Microsystems
//Description:Your description
package SqlLoader;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame1 extends JFrame {
  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenuItem menuControlPanel = new JMenuItem();
  JMenuItem menuDbPrefPanel = new JMenuItem();
  JMenuItem menuFileExit = new JMenuItem();
  JMenu menuHelp = new JMenu();
  JMenuItem menuHelpAbout = new JMenuItem();
  ControlPanel sqlConfig = new ControlPanel();
  BorderLayout borderLayout1 = new BorderLayout();

  //Construct the frame
  public Frame1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    this.getContentPane().setLayout(borderLayout1);
    this.setResizable(false);
    this.setSize(new Dimension(430, 469));
    this.setTitle("SqlLoader");
    menuFile.setText("File");
    menuDbPrefPanel.setText("Db Preference");
    menuDbPrefPanel.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        dbPrefPanel_actionPerformed(e);
      }
    });
    menuControlPanel.setText("ControlPanel");
    menuControlPanel.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        menuControlPanel.setEnabled(false);
        //controlPanel_actionPerformed(e);
      }
    });
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        fileExit_actionPerformed(e);
      }
    });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()  {

      public void actionPerformed(ActionEvent e) {
        helpAbout_actionPerformed(e);
      }
    });
    menuFile.add(menuDbPrefPanel);
    menuFile.add(menuControlPanel);
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
    menuBar1.add(menuHelp);
    this.setJMenuBar(menuBar1);
    this.getContentPane().add(sqlConfig, BorderLayout.CENTER);
  }

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //DbPrefs
  public void dbPrefPanel_actionPerformed(ActionEvent e) {
    SqlDbPref dlg = new SqlDbPref(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  //Help | About action performed
  public void helpAbout_actionPerformed(ActionEvent e) {
    Frame1_AboutBox dlg = new Frame1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  //Overridden so we can exit on System Close
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      fileExit_actionPerformed(null);
    }
  }
} 