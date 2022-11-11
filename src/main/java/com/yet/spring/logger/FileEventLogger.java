package com.yet.spring.logger;

import com.yet.spring.bean.Event;
import com.yet.spring.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {

    private File file;
    private String fileName;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event){
        try{
            FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void init() throws IOException{
        this.file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write in file " + fileName);
        }else if (!file.exists()) {
            file.createNewFile();
        }
    }
}
