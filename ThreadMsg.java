
//Title:        SqlLoader
//Version:      
//Copyright:    Copyright (c) 1999
//Author:       Kevin Rabito
//Company:      Sun Microsystems
//Description:  Your description
package SqlLoader;

import java.util.*;



class ThreadMsg {

private Vector thrdList;

  public ThreadMsg() {
  }

  public void setActiveThread(String threadName){
    upDateList(true, threadName);
  }

  
  public void setInactiveThread(String threadName){
    upDateList(false, threadName);
  }

  // Get the active count of threads.
  public int getActiveCount(){
    return(thrdList.size());
  }

  // For Debugging.
  public Vector getActiveList(){
    return(thrdList);
  }

  // set true to add a thread, false to remove a thread)
  private synchronized void upDateList(boolean addOrRemove, String threadName){
    String aThread = threadName;
    boolean addTrue = addOrRemove;

    if( addTrue )thrdList.addElement(aThread);
    else {
            thrdList.contains(aThread);
            thrdList.removeElement(aThread);
    }
  }
}