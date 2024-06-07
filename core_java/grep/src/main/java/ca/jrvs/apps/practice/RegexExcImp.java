package main.java.ca.jrvs.apps.practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc{

    /**
     * Return true if filename extension is jpg or jpeg( case in_sensitive)
     * @param filename - a file name stored as String
     * @return boolean
     */
    public boolean matchJpeg(String filename){
        Pattern pattern = Pattern.compile(".*\\.(jpeg|jpg)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(filename);
        return matcher.find();
    }

    /**
     * return true if ip is valid
     * to simplify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip - an IP stored as String
     * @return boolean
     */
    public boolean matchIP(String ip){
        Pattern pattern = Pattern.compile("^\\b(\\d{3}\\.){3}\\d{3}\\b$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc..)
     * @param line - A String
     * @return boolean
     */
    public boolean isEmptyLine(String line){
        return line.trim().isEmpty();
    }

    public static void main(String[] args) {
        RegexExcImp grep = new RegexExcImp();
        System.out.println("test codes for IP");
        System.out.println(grep.matchIP("192.168.001.001"));
        System.out.println(grep.matchIP("256.256.256.256  d"));
        System.out.println(grep.matchIP("x999.999.999.999"));
        System.out.println(grep.matchIP("1000.1000.1000.1000"));
        System.out.println(grep.matchIP("abc.def.ghi.jkl"));
        System.out.println("test codes for empty line");
        System.out.println(grep.isEmptyLine("1000.1000.1000.1000"));
        System.out.println(grep.isEmptyLine("\t"));
        System.out.println(grep.isEmptyLine("      "));
        System.out.println("test codes for jpeg");
        System.out.println(grep.matchJpeg("xxx.Jpeg")); 
        System.out.println(grep.matchJpeg("xxx.jpeg.xxx"));
        System.out.println(grep.matchJpeg("      .jpeg"));
        System.out.println(grep.matchJpeg("      .jpeg.xxxx"));
    }
}
