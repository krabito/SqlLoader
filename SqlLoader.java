
//Title:      SqlLoader
//Version:    
//Copyright:  Copyright (c) 1999
//Author:     Kevin Rabito
//Company:    Sun Microsystems
//Description:Your description
package SqlLoader;

import javax.swing.UIManager;
import java.awt.*;

public class SqlLoader {
  boolean packFrame = false;

  //Construct the application
  public SqlLoader() {
    Frame1 frame = new Frame1();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
      frame.pack();
    else
      frame.validate();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
      frameSize.height = screenSize.height;
    if (frameSize.width > screenSize.width)
      frameSize.width = screenSize.width;
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {
    try  {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
    }
    new SqlLoader();
  }
} 