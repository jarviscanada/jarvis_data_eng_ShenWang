package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.File;
import java.util.List;

public class JavaGrepLambdaImp extends JavaGrepImp{


    @Override
    public List<String> readLines(File inputFile){
        try(BufferedReader br = new BufferedReader( new FileReader(inputFile))){
            return br.lines().collect(Collectors.toList());
        } catch(IOException e){
            logger.error("An error occurred while executing readlines function on {}", inputFile, e);
            return Collections.emptyList();
        }
    }

    @Override
    public  List<File> listFiles(String rootDir){
        File dir = new File(rootDir);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + rootDir);
        }
        if (dir.isFile()) {
            return Collections.singletonList(dir);
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(files)
                .filter(File::isFile)
                .collect(Collectors.toList());
    }
}
