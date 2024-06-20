package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep{
    static final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;



    public static void main(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
        //Use default logger config
        BasicConfigurator.configure();
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);
        try{
            javaGrepImp.process();
        } catch (Exception ex){
            logger.error("Error: unable to process", ex);
        }
    }
    /**
     * Top level search workflow
     * @throws IOException
     */
    @Override
    public void process() throws IOException{
        List<String> linesToWrite = new ArrayList<>();
        List<File> files = listFiles(this.rootPath); // Assuming listFiles returns a list of all files and directories in the rootPath

        ListIterator<File> iterator = files.listIterator();
        while (iterator.hasNext()) {
            File file = iterator.next();
            if (file.isFile()) {
                List<String> lines = readLines(file);
                for (String line : lines) {
                    if (containsPattern(line)) {
                        linesToWrite.add(line);
                    }
                }
            } else if (file.isDirectory()) {
                List<File> newFiles = listFiles(file.getPath()); // Assuming listFiles can list files in a given directory
                for (File newFile : newFiles) {
                    iterator.add(newFile); // Add new files and directories to the list
                    iterator.previous();
                }
            }
        }
        for (String str : linesToWrite) {
            logger.info(str);
        }
        writeToFile(linesToWrite);
    }


    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    @Override
    public  List<File> listFiles(String rootDir){
        File dir = new File(rootDir);
        List<File> fileNames = new ArrayList<>();
        if(dir.isFile()){
            fileNames.add(dir);
            return fileNames;
        }
        File[] files = dir.listFiles();
        if( files != null) {
            fileNames.addAll(Arrays.asList(files));
            return fileNames;
        }else{
            return Collections.emptyList();
        }
    }

    /**
     * read a file and return all the lines
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile files to be read
     * @return lines
     * @throws IllegalArgumentException if a given input is not a file
     */
    @Override
    public List<String> readLines(File inputFile){
        List<String> lines = new ArrayList<>();

        try(FileReader fr = new FileReader(inputFile)){
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) !=null){
                lines.add(line);
            }
        } catch(IOException e){
            logger.error("An error occurred while executing readlines function on {}", inputFile, e);
        }
        return lines;
    }

    /**
     * check if a line contains the regex pattern( passed by user)
     * @param line input string
     * @return true if there is a match
     */
    @Override
    public boolean containsPattern(String line){
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    /**
     * write lines to a file
     *
     * explore: fileoutputstream, outputstreamwrite, and bufferedwriter
     * @param lines matched lines
     * @throws IOException if write failed
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException{
        File inputFile = new File(this.outFile);
        if(!inputFile.isFile()){
            logger.error("Error occurred while writing to {}", this.outFile);
            throw new IOException(this.outFile+ " is not file");
        }
        try(FileWriter writer = new FileWriter(this.outFile)) {
            for (String line : lines) {
                writer.write(line.trim());
                writer.write('\n');
            }
        } catch (IOException e){
            logger.error("Error occurred while writing to {}", this.outFile, e);
        }
    }
    @Override
    public String getRootPath(){
       return this.rootPath;
    }
    @Override
    public void setRootPath(String rootPath){
        this.rootPath = rootPath;
    }
    @Override
    public String getRegex(String rootPath){
        return this.regex;
    }
    @Override
    public void setRegex(String regex){
        this.regex = regex;
    }
    @Override
    public String getOutFile(){
        return this.outFile;
    }
    @Override
    public void setOutFile(String outFIle){
        this.outFile = outFIle;
    }


}
