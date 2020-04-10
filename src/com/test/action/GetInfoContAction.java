package com.test.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.InfoDTO;
import com.test.model.SquareDAO;

public class GetInfoContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글번호에 맞는 게시글을 조회하는 클래스
		int board_no = 0;
		
		if(request.getParameter("board_no") != null)
			board_no = Integer.parseInt(request.getParameter("board_no"));

		
		SquareDAO dao = SquareDAO.getInstance();
		InfoDTO cont = dao.getInfoContent(board_no);
		List<InfoDTO> list = dao.getPreNextCont(board_no);
		InfoDTO next = new InfoDTO();
		InfoDTO pre = new InfoDTO();
		for(int i = 0; i < list.size(); i ++) {
			if(i == 0) 
				next = list.get(0);
			else if(i == 1) 
				pre = list.get(1);
		}
	
		request.setAttribute("Cont", cont);
		request.setAttribute("next", next);
		request.setAttribute("pre", pre);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("01accept_board/info_cont.jsp");
		return forward;
	}

}
