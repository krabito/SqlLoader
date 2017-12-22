//Title:        Db Tester
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Kevin Rabito
//Company:      Sun Microsystems
//Description:  Your description
package SqlLoader;

import java.sql.*;
import java.util.*;

public class DbRunner extends Thread {
private String iterationField = "";   // Number of Interations
private int iterationNumber = 1;  // Iteration integer
private boolean reConnect;       // Reconnect boolean.
private Connection dbCon;        // Database connection.
private Statement stmt;          // database process statement.
private Vector dbVector;         // Vector of config info.
private String hostField = "";   // System hostname
private String serverField = ""; // Database server/instance/SID
private String portField = "";   // listener port number
private String loginField = "";  // Database login string
private String passwdField = ""; // Password Field
private String sqlField = "";    // Sql statements to process
private ResultSet rs = null;     // To handle Returns from the database.
private String reconField = "";  // Reconnect String
private String sleepField = "";  // Sleep duration
private int sleepTime = 0;       // Sleep duration integer
private int loopCount = 0;       // Loop Counter for iterations


  public DbRunner(Vector dbScenerio) {
    this.dbVector = dbScenerio;
    String [] configInfo; // String Array to copy vector info.

    /** Stored in this order in Vector dbScenerio
    dbFields.addElement(jHostField.getText());
    dbFields.addElement(jServerField.getText());
    dbFields.addElement(jPortField.getText());
    dbFields.addElement(jLoginField.getText());
    dbFields.addElement(jSqlArea.getText());
    dbFields.addElement(sleepString);
    Boolean booMe = new Boolean(reConnect);
    dbFields.addElement(booMe.toString());  //Convert toString for reConnect.
    dbFields.addElement(iterationString);
    dbFields.addElement(jPasswordField1.getPassword());
    Must pull off vector in reverse order to get
    correct settings */

    configInfo = new String [dbVector.size()];
    dbVector.copyInto(configInfo);
    hostField = configInfo[0];
    serverField = configInfo[1];
    portField = configInfo[2];
    loginField = configInfo[3];
    sqlField = configInfo[4];
    sleepField = configInfo[5];
    sleepTime = stringToInt(sleepField);
    reconField = configInfo[6];
    iterationField = configInfo[7];
    iterationNumber = stringToInt(iterationField);
    passwdField = configInfo[8];


    if( reconField.equalsIgnoreCase("true")){
      reConnect = true;
      }
    else {
      reConnect = false;
      }
  }

  public void run() {

   // Try connecting to a database.
      String serverHost = "jdbc:oracle:thin:@" + hostField + ":" + portField + ":" + serverField;
    // No Reconnection, execute sql statement.
      try{    // Connect to the database.
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        dbCon = DriverManager.getConnection(serverHost, loginField, passwdField );
        dbCon.setAutoCommit(true);
        stmt = dbCon.createStatement();
        System.out.println(stmt.getConnection());
      }
      catch( SQLException c){
        System.out.println("Database Connect error");
        System.out.println("ErrorCode: " + c.getErrorCode());
        System.out.println("SQLState: " + c.getSQLState());
        c.printStackTrace(System.out);
        return;
      }

      while( !isInterrupted() && (loopCount < iterationNumber)){  // loop for number of iterations
           loopCount++;
           try{   // execute the sql statement.
              stmt.execute(sqlField);
              rs = stmt.getResultSet();
              rs = null;  // don't care about the result.
           }
           catch (NullPointerException d){}
           catch (SQLException e){
              System.out.println("Statement ERROR: " + e.getMessage());
           }

           try{
              Thread.sleep(sleepTime * 1000,10);
           }
           catch(InterruptedException t){
              this.interrupt();
              break;
           }
      } // end of loop
      System.out.println("loopCount: " + loopCount + " Interrupt: " + isInterrupted());
        // Done with iterations, close the database.
      try{
          dbCon.close();
          stmt = null;
          dbCon = null;
        }
        catch(SQLException e){
          System.out.println("db close error: " + e.getMessage());
        }
        catch(NullPointerException e){
          //System.out.println("Null Pointer: " + e.getMessage());
        }
      System.out.println("Closing Thread: " + this.getName());
  } // End of run()




  // Method to convert String to integer.
  private int stringToInt(String toConvert){
    Integer buckWheat = new Integer(toConvert);
    return(buckWheat.intValue());
  }
}