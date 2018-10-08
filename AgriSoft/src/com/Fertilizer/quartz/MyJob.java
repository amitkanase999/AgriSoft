package com.Fertilizer.quartz;
import java.io.File;
import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

public class MyJob implements Job {
	File backup = null;
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try { 

			String to="embelbackup@gmail.com";
			String subject = "Fertilizer total sale amount" ;
			String msg = "";
			//for fertlizer
			DataToMail dm=new DataToMail();
			Double ferti= dm.getTotalSaleAmountOfTheDay();
			//for pesticide
			Double pesti = dm.getTotalSaleAmountOfPesticide();
			//for seed
			Double seedAmount = dm.getTotalSaleAmountOfSeed();

			Double totalSaleAmount = ferti + pesti + seedAmount;
			//for database backup
			try{
				AutoDatabaseBackupJob data = new AutoDatabaseBackupJob();
				backup = data.mailDatabaseBackup();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			com.Fertilizer.quartz.Mailer.send(to, subject,"Your Fertilizer total sale amount is = Rs"+ferti);
			com.Fertilizer.quartz.Mailer.send(to, subject,"Your Pesticide total sale amount is = Rs"+pesti);
			com.Fertilizer.quartz.Mailer.send(to, subject,"Your Seed total sale amount is = Rs"+seedAmount);
			com.Fertilizer.quartz.Mailer.send(to, subject,"Your total sale amount(Including All Category) is = Rs"+totalSaleAmount);
			try{
				com.Fertilizer.quartz.Mailer.sendFile(to,backup);

			}
			catch(Exception e){
				e.printStackTrace();

			}
			System.out.println("mail sent...");

		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage());
		}
	}
}
