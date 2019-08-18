package producer;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        final String LOG_FOLDER = args[0];
        LogFileReader logFileReader = new LogFileReader(LOG_FOLDER);
        SimpleProducer simpleProducer = new SimpleProducer(args[1]);

        File temp;
        ArrayList<String> buffer= new ArrayList<String>();
        while(true){
            temp=getLatestFilefromDir(LOG_FOLDER);
            try {
                if (temp.lastModified() != logFileReader.getLastmodified()) {
                    buffer = logFileReader.readFile(LOG_FOLDER + "/" + temp.getName());
                    simpleProducer.sendMessage(buffer);
                    logFileReader.setLastmodified(temp.lastModified());
                    buffer.clear();
                }
            }
            catch (NullPointerException e){
                System.out.println("Directory is empty...");
            }
            Thread.sleep(1000);
        }
    }

    private static File getLatestFilefromDir(String dirPath){
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

}
