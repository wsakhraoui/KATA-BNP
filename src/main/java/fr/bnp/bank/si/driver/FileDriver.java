package fr.bnp.bank.si.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileDriver {
	
	@Value("${bucket.path}")
	private String bucket;
	 @Async("asyncExecutor")
	public <T>  void    writeToFile(List<T> data ) {
		 log.info("writing to file "+ bucket );
		 
		 File dir = new File(bucket);
		    if (!dir.exists()) dir.mkdirs();
		    
		    
		try( PrintWriter out = new PrintWriter(bucket+"/persons_"+new Date().getTime())) {
		 
			for(T t :data) {
				out.println(t.toString())	;
			}
			  
		} catch (FileNotFoundException e) {
			 
			e.printStackTrace();
		}
		 

		 
		 log.info("end writing to file "+ bucket );
			
			 
		   
		 
		
	}

}
