package com.Fertilizer.quartz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

public class AutoDatabaseBackupJob {

	public File mailDatabaseBackup() throws IOException {
		File f = null;
		try { 
			List cmdAndArgs1 = Arrays.asList("cmd", "/c", "DBBackup.bat");
			File dir1 = new File("C:/barcose");

			ProcessBuilder pb1 = new ProcessBuilder(cmdAndArgs1);
			pb1.directory(dir1);
			try {
				Process p1 = pb1.start();


			} catch (IOException e) {

				e.printStackTrace();
			}

			f = new File("E:/fertilizer.sql");
		} 
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage());
		}
		return f;
	}
}

