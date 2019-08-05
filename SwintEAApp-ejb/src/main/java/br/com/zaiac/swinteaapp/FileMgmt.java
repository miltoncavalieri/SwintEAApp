package br.com.zaiac.swinteaapp;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileMgmt {
    private static final Logger logger = Logger.getLogger(FileMgmt.class.getName());
    
    public static void createDirectoryStructure (String path) {
        File file;
        for (int i = 0; i < path.length(); i++ ) {
            if (path.charAt(i) == '/') {
//                System.out.println("Diretorio -->" + path.substring(0, i));
                file = new File(path.substring(0, i));
                if (file.exists()) {
                    if (!(file.isDirectory())) {
                        logger.log(Level.SEVERE, "Path is not a Directory: {0}", path.substring(0, i));
                    }
                } else {
                    file.mkdir();
                }
                
            }
        }
        file = new File(path);
        if (file.exists()) {
            if (!(file.isDirectory())) {
                logger.log(Level.SEVERE, "Path is not a Directory: {0}", path);
            }
        } else {
            file.mkdir();
        }
    }
    
}
