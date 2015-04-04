package requestId;

/**
 * @author pete
 *
 */
public class PopulateThread implements Runnable{
    String slice[];
    StringBuilder sbWork;
    int sliceNum, sliceStart, sliceEnd, nodeId;
	/**
	 * Generate unique IDs into this one slice of main string array
	 */
	public PopulateThread(String[] mySlice, int sliceNum, int sliceStart, int sliceEnd) {
		System.out.println("PT : Slice " + sliceNum + " Start "+ sliceStart + " End " + sliceEnd);
		this.slice = mySlice;
		this.sliceNum = sliceNum;
		this.sliceStart = sliceStart;
		this.sliceEnd = sliceEnd;
	}
	
	public void run(){
		long myOffset = System.currentTimeMillis() - generateIds.seedOffset;
        for (int popSlice = sliceStart; (popSlice <= sliceEnd); popSlice++){
        	sbWork = new StringBuilder(Long.toString(myOffset + popSlice));
        	sbWork.append(":");
        	sbWork.append(sliceNum);
        	sbWork.append(":");
        	sbWork.append(nodeId);
        	slice[popSlice] = sbWork.toString();
        }
	}

	public static void main(String[] args) {
	}

}
