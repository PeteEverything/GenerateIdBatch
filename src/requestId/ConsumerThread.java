/**
 * Sample Thread to request a batch of IDs gets generated 
 */
package requestId;

public class ConsumerThread implements Runnable{
    long startMS;

	public void run(){
		if (generateIds.verboseInfo.booleanValue()) {
		    startMS = System.currentTimeMillis();
		}
		
		String batchIds[] = generateIds.getBatch();

		if (generateIds.verboseInfo.booleanValue()) {
		    System.out.println("Batch generated in : " + (System.currentTimeMillis() - startMS) + " Milliseconds" );	
	        for (int j=0; j<generateIds.batchSize; j++){
		        System.out.println("ID : "+ j + " : " + batchIds[j]);
		    }
		}
	}

	public static void main(String[] args) {
		(new Thread(new ConsumerThread())).start();
	}
}
