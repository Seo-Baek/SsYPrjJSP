package com.test.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.model.MemberDAO;
import com.test.model.MemberDTO;

public class LoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인을 시도하는 클래스
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getmember(id, pwd);
		
		System.out.println(dto.getM_no());
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(dto.getM_name() != null) {
			session.setAttribute("userId", dto.getM_id());
			session.setAttribute("userPwd", dto.getM_pwd());
			session.setAttribute("userName", dto.getM_name());
			session.setAttribute("userNo", dto.getM_no());
			
			out.println("<script>");
			out.println("alert('로그인 성공')");
			out.println("location.href='main.do'");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('로그인에 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
