/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestrobot;

import java.io.*;


/**
 *
 * @author David
 */
public class IOFile {
    String nameFile;
    String path;
    File file;
    BufferedWriter bw;
    
    public IOFile(String name) throws IOException{
        nameFile = name;
        path = BestRobot.resultsPath+nameFile+".txt";
        file = new File(path);
        try{
            bw = new BufferedWriter(new FileWriter(file));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void WriteLine(String line) throws IOException{
        bw.write(line);
        bw.newLine();
    }
    
    public void CloseFile() throws IOException{
        bw.close();
    }
    
}
