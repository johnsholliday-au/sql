package com.jsh.sql;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.ibm.jzos.FileFactory;

public class _Tester {

	public static void main(String[] args) throws Exception {
		
		String inFile;
		
		if (System.getProperty("os.name").equals("z/OS") ) {
			// ok we're on the mainframe 
			inFile    = "//DD:SYSIN";

		} else {
			// We're on the Jump Box 
			inFile    = "Files\\Test Data\\ATSH04  .1132.TXT";
		}
		
		InputStream fis = FileFactory.newInputStream(inFile);
		String file = IOUtils.toString(fis, "UTF-8");
		
		System.out.println(file);
		
		
	}
}
