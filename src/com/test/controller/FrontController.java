package com.test.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.action.Action;
import com.test.action.ActionForward;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length() + 1);

		Action action = null;
		ActionForward forword = null;

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"D:\\Ssangyong\\workspace(JSP)\\projectCAFEIN\\src\\com\\test\\controller\\mapping.properties");

		prop.load(fis);

		String value = prop.getProperty(command);
		System.out.println(value);
		
		StringTokenizer st = new StringTokenizer(value, "|");
		String keyword = st.nextToken(); 
		String nextaction = st.nextToken();

		if (keyword.equals("action")) {
			try {
				Class url = Class.forName(nextaction);
				action = (Action) url.newInstance();
				forword = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(keyword.equals("page")) {
			forword = new ActionForward();
			forword.setRedirect(false);
			forword.setPath(nextaction);
		} else {
			forword = new ActionForward();
			forword.setRedirect(false);
			forword.setPath("notfound.jsp");
		}

		if (forword != null) {
			if (forword.isRedirect()) {
				response.sendRedirect(forword.getPath());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(forword.getPath());
				rd.forward(request, response);
			}
		}

	}
}
