import java.io.*;
import java.util.Scanner;

/**
 * Created by srait on 6/30/2015.
 */
public class FillMe {
	public static final String USER = System.getenv("USERNAME");
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        System.out.println("***CAUTION: DO NOT RUN ON OS DRIVES***");
        System.out.print("Select Drive Letter: ");
        String file=process();
        File f = new File(file); //create a file in the drive
        fill(f);
        Process p = Runtime.getRuntime().exec("attrib +A +S +H " + f.getPath());	//runs batch file to hide file
    	p.waitFor();
    	Process p2 = Runtime.getRuntime().exec("icacls "+f.getPath()+" /inheritance:r ");
    	p2.waitFor();
        System.out.println("All Done!");
    }

    //get file info
    public static String process(){
    	Scanner console=new Scanner(System.in);
        String c =console.next()+":\\"; //drive to be used
        console.close();
        if(c.equals("C:\\")){
            c+="Users\\"+USER+"\\Documents\\";
        }
        String file = c+"I'm_full";
        return file;
    }

    //fill the specified drive with data
    public static void fill(File f) throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();    //start time
        RandomAccessFile g = new RandomAccessFile(f, "rw"); //create a file stream inside file f
        g.setLength(50/*f.getUsableSpace()*/); //set the size of f to the total size of the drive
        

        //stats
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("Process completed in " + elapsedTimeSec + " seconds");  //runtime
        g.close();
    }
}