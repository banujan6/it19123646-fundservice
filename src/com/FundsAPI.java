package com;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FundsAPI
 */
@WebServlet("/FundsAPI")
public class FundsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Fund fundObj = new Fund();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = fundObj.insertFunds(request.getParameter("fundCode"), request.getParameter("projectName"), 
				request.getParameter("fundAmount"), request.getParameter("fundDesc"));
		
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Map paras = getParasMap(request);
		
		System.out.print(paras.get("projectName").toString());
		
		String output = fundObj.updateFunds(paras.get("hidFundIDSave").toString(), paras.get("fundCode").toString(), paras.get("projectName").toString(), 
				paras.get("fundAmount").toString(), paras.get("fundDesc").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = fundObj.deleteFunds(paras.get("fundID").toString());
		
		response.getWriter().write(output);
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		 try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
					 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 
			 queryString = java.net.URLDecoder.decode(queryString, StandardCharsets.UTF_8.name());
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
