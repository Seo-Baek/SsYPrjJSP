package com.test.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.SquareDAO;
import com.test.model.DTO;
import com.test.model.NoticeDTO;

public class GetListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 승인된 글 리스트를 불러오는 클래스
		
		String nowPage = null;
		if(request.getParameter("page") != null)
			nowPage = request.getParameter("page");
		else
			nowPage = "1";
		int mno = Integer.parseInt(request.getParameter("mno"));
		int accept = Integer.parseInt(request.getParameter("accept"));
		System.out.println("회원번호 : " +mno + "허가번호 : "+accept);
		
		// 페이징 처리를 하는 메소드 호출
		SquareDAO dao = SquareDAO.getInstance();
		List<NoticeDTO> notice = dao.showNotice(); 
		List<DTO> list = paging(request, mno, accept, nowPage);
		request.setAttribute("notice_list", notice);
		request.setAttribute("board_list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("01accept_board/info_list.jsp");
		return forward;
	}

}
