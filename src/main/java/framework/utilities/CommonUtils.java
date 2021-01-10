package framework.utilities;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtils {
	
	static Logger logger = LogManager.getLogger(CommonUtils.class);
	
	public static String getStacktraceAsString(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String getFormattedStacktraceAsString(Throwable throwable, String oldChar, String newChar) {
		String stacktrace = getStacktraceAsString(throwable);
		return stacktrace.replace(oldChar, newChar);
	}
	
	public static String convertMillisecondsToDateFormat(long milliseconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		return formatter.format(calendar.getTime());
	}
	
	public static void deleteFileOrDirectory(String path) {
		File file = new File(path);
		if(file.exists()) {
			if(file.isDirectory()) {
				File files[] = file.listFiles();
				for (File file2 : files) {
					deleteFileOrDirectory(file2.getPath());
				}
				deleteFile(file);
			}
			else {
				deleteFile(file);
			}
		}
		else {
			logger.info("file not present at path --> " + path);
		}
	}
	
	public static void deleteFile(File file) {
		while(file.exists()) {
			file.delete();
		}
	}

}
