package requestId;
/**
 * @author pete
 *
 */
import requestId.ConsumerThread;
import requestId.PopulateThread;

import java.util.*;
import java.io.*;

public class generateIds {
	
  static Properties properties = new Properties();	
  static Boolean verboseInfo;
  static int batchSize, sliceSize, threadCount, nodeId;
  static long seedOffset;
  static generateIds self = null;
  	
  public static String[] getBatch(){
	  String batchIdArray[] = new String[batchSize];
/*
 *    Split batch into slices, call separate threads to populate slices concurrently	  
 */
	  for (int slice = 0, sliceNum = 0;(slice < batchSize);slice += sliceSize, sliceNum++){
		  (new Thread(new PopulateThread(batchIdArray, sliceNum, slice, (slice + sliceSize - 1)))).start();
	  }
	  return batchIdArray;
  }
  	
  public static boolean getApplicationProperties(){
	  try {
	      properties.load(new FileInputStream( "config.properties"));
	  } catch (IOException e) {
	      System.out.println("Couldn't find config.properties file");
	  }

	  verboseInfo = new Boolean(properties.getProperty("verbose"));
	  batchSize = Integer.parseInt(properties.getProperty("batchsize"));
	  threadCount = Integer.parseInt(properties.getProperty("threadcount"));
	  nodeId = Integer.parseInt(properties.getProperty("nodeid"));
	  seedOffset = Long.parseLong(properties.getProperty("seedoffset"));
	  sliceSize = batchSize / threadCount;
	  
	  if (verboseInfo.booleanValue()) {
	    System.out.println("Showing verbose information...");
	    System.out.println("nodeid is : " + properties.getProperty("nodeid"));
	    System.out.println("threadcount is : " + properties.getProperty("threadcount"));
	    System.out.println("batchsize is : " + properties.getProperty("batchsize"));
	    System.out.println("seedoffset is : " + properties.getProperty("seedoffset"));
	  }
	  return true;
	}	

	public static void main(String[] args) {
		self = new generateIds();
		if (getApplicationProperties() == true) {
		  ConsumerThread cons1 = new ConsumerThread();
		  cons1.run();
		}
	}
}
