package com.test.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.SquareDAO;

public class AddHitInfocont implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 조회수를 증가시키는 메소드
		int board_no = 0;
		if(request.getParameter("board_no") != null)
			board_no = Integer.parseInt(request.getParameter("board_no"));
		SquareDAO dao = SquareDAO.getInstance();
		dao.add1ToHit(board_no);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href='getInfo_Cont.do?board_no="+board_no+"'");
		out.println("</script>");
		
		return null;
	}

}
