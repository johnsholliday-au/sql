package com.jsh.sql;



import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

import com.citec.util.servers.IServer;
import com.citec.util.servers.ServerManager;


public class XFTP {

	// ftp the jar file to Mainframe 
	// 

	public XFTP() {}
	
	public void deploy(String server, String local, String remote) throws Exception	{

		FTPClient ftp = null;

		try {
			// 0. Get userid and password from Server Manager  
			ServerManager sm = new ServerManager();
			IServer s = sm.getServerByName(server);

			ftp = new FTPClient();
			String replyText;


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

		
	
	public static final void main(String[] args) throws Exception {
		XFTP ftp = new XFTP();
//		String server = "TEST";
		String local    = "d:\\eclipse\\JARS\\com.jsh.sql.jar";
		String remote   = "/usr/local/lib/com.jsh.sql.jar";
		String remoteQT = "/var/lib/com.jsh.sql.jar";

		ftp.deploy( "TEST", local, remote );
		ftp.deploy( "PROD", local, remote );
		ftp.deploy( "TRAC", local, remoteQT );
//    	ftp.deploy( "TRDV", local, remoteQT );    // no direct FTP to TRDV - use Jxx jobs in tso.cntl on TRAC
//		ftp.deploy( "TRPT", local, remoteQT ); 	  // no direct FTP to TRPT - use Jxx jobs in tso.cntl on TRAC	
		ftp.deploy( "DV00", local, remote );
		ftp.deploy( "PD00", local, remote );
		
		
	}
 
	
}

