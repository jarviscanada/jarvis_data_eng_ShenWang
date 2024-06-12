package ca.jrvs.apps.practice;

public interface RegexExc {

    /**
     * Return true if filename extension is jpg or jpeg( case in_sensitive)
     * @param filename - a file name stored as String
     * @return boolean
     */
    boolean matchJpeg(String filename);

    /**
     * return true if ip is valid
     * to simplify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip - an IP stored as String
     * @return boolean
     */
    boolean matchIP(String ip);

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc..)
     * @param line - A String
     * @return boolean
     */
    boolean isEmptyLine(String line);
}
