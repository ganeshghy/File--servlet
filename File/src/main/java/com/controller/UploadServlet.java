package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
 

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	Part file = request.getPart("uploadFile");
    	String name = file.getSubmittedFileName();
    	Properties p = new Properties();
    	p.load(new FileInputStream("C:\\Users\\GANESH\\eclipse-java\\File\\myConfig.properties"));
    	
    	
    	String fileLocation = p.getProperty("file_location");
    	
    	String uploadPath = fileLocation+name;
    	String filepath = p.getProperty("filepath");
//    	System.out.println(uploadPath);
    	try {
    		FileOutputStream fo = new FileOutputStream(uploadPath);
        	InputStream inputStream = file.getInputStream();
        	byte[] data = new byte[inputStream.available()];
        	inputStream.read(data);
        	fo.write(data);
        	fo.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	p.setProperty("filepath", fileLocation+name);
    	p.store(new FileOutputStream("C:\\Users\\GANESH\\eclipse-java\\File\\myConfig.properties"), null);
    	FileReader fr = new FileReader(filepath);
		int i;
		while ((i = fr.read()) != -1)
			System.out.print((char) i);
		fr.close();
    }
}