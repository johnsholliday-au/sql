package com.jsh.sql;



import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

import com.citec.util.servers.IServer;
import com.citec.util.servers.ServerManager;


public class XFTP {

	// ftp the jar file to TEST
	// 

	public static final void main(String[] args) throws Exception
	{ 
		// Get userid and password from Server Manager  
		ServerManager sm = new ServerManager();
		String server = "TEST";
		IServer s = sm.getServerByName(server);
		
		FTPClient ftp;
		String remote = "/usr/local/lib/com.jsh.sql.jar";
		String local  = "com.jsh.sql.jar";
			
		ftp = new FTPClient();
		String replyText;
	
		try {

			// 1. Connect to the server
			ftp.connect(server);
			replyText = ftp.getReplyString();
			System.out.println(replyText);

			// 2. Login 
			ftp.login(s.getUserid(), s.getPassword());
			replyText = ftp.getReplyString();
			System.out.println(replyText);
			
			//3 set the mode
			ftp.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
			replyText = ftp.getReplyString();
			System.out.println(replyText);
			
			//4.do the transfer
			InputStream input;
			input = new FileInputStream(local);
			ftp.storeFile(remote,input);
			replyText = ftp.getReplyString();
			System.out.println(replyText);

			input.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ftp.disconnect();

		}

		System.out.println("!! Finished, ftp'd to "+server);
	}
	
}

