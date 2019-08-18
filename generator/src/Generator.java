import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Generator {
    public static void main(String[] args) {
       Date date= new Date();
       String[] level = {"[INFO]","[WARNING]","[ERROR]"};
       String[] server = {"Istanbul","Tokyo","Paris","London"};
       String detail = "This is a test";
       String fileSeparator = System.getProperty("file.separator");
       Random rand = new Random();

        //absolute file name with path
        String absoluteFilePath = args[0];
        File file = new File(absoluteFilePath);
        while(true) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));


            for (int i = 0; i < 5; i++) {
                try {

                    date.setTime(date.getTime() + 60000);
                    bw.write(new Timestamp(date.getTime()).toString() + "\t" + level[rand.nextInt(3)] + "\t" + server[rand.nextInt(4)] + "\t" + detail);
                    bw.newLine();

                    System.out.println("file written");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("ioexception");
                }

            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ioexception");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

}
