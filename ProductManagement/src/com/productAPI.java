package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/productAPI")
public class productAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   Product productobj =new Product();
	
    public productAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = productobj.insertproduct(request.getParameter("itemCode"), 
				 request.getParameter("itemName"), 
				request.getParameter("itemPrice"), 
				request.getParameter("itemDesc")); 
				response.getWriter().write(output); 
		
	
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = productobj.updateItem(paras.get("hidItemIDSave").toString(), 
		 paras.get("itemCode").toString(), 
		 paras.get("itemName").toString(), 
		paras.get("itemPrice").toString(), 
		paras.get("itemDesc").toString()); 
		response.getWriter().write(output); 
	
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		 String output = productobj.deleteItem(paras.get("id").toString()); 
		response.getWriter().write(output);
		
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	

}
