/* MIT License
 *
 * Copyright (c) 2017 Aeronica
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package tld.cfgtest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class ModLogger {

    private static Logger logger;

    public static void setLogger(Logger logger) {
        if (ModLogger.logger != null) {
            throw new IllegalStateException("Attempt to replace logger");
        }
        ModLogger.logger = logger;
    }

    public static void log(Level level, String format, Object... data) {
        logger.printf(level, format, data);
    }
    
    /**
     * Log a stack trace for &lt;T extends Throwable&gt; {@link e} at the specified log {@link level}
     * @param level     The specified log level
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void log(Level level, T e)
    {
        if (e != null) {
            log(level, "%s", e.getLocalizedMessage());
            for(StackTraceElement s: e.getStackTrace())
                log(level, "%s", s.toString());
        }
    }
    
    public static void info(String format, Object... data){
        log(Level.INFO, format, data);
    }
    
    public static void debug(String format, Object... data){
        log(Level.DEBUG, format, data);
    }
    
    public static void warning(String format, Object... data){
        log(Level.WARN, format, data);
    }

    public static void error(String format, Object... data){
        log(Level.ERROR, format, data);
    }

    public static void fatal(String format, Object... data){
        log(Level.FATAL, format, data);
    }

    /**
     * Log a stack trace for &lt;T extends Exception&gt; {@link e} at log Level.INFO
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void info(T e)
    {
        log(Level.INFO, e);
    }
    
    /**
     * Log a stack trace for &lt;T extends Throwable&gt; {@link e} at log Level.DEBUG
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void debug(T e)
    {
        log(Level.DEBUG, e);
    }
    
    /**
     * Log a stack trace for &lt;T extends Throwable&gt; {@link e} at log Level.WARM
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void warning(T e)
    {
        log(Level.WARN, e);
    }
    
    /**
     * Log a stack trace for &lt;T extends Throwable&gt; {@link e} at log Level.ERROR
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void error(T e)
    {
        log(Level.ERROR, e);
    }
    
    /**
     * Log a stack trace for &lt;T extends Throwable&gt; {@link e} at log Level.FATAL
     * @param e         The raw exception 
     */
    public static <T extends Throwable> void fatal(T e)
    {
        log(Level.FATAL, e);
    }
    
}
