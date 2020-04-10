package com.test.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.DTO;
import com.test.model.InfoDTO;
import com.test.model.NoticeDTO;
import com.test.model.SquareDAO;

public class GetSearchInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색을 위해 넘겨진 값들을 받아 검색 결과를 가져오는 클래스
		
		String nowPage = null;
		if(request.getParameter("page") != null)
			nowPage = request.getParameter("page");
		else
			nowPage = "1";
		
		String req_box = request.getParameter("req_box");
		String req_text = request.getParameter("req_text");
		System.out.println("검색조건 : " + req_box+" 조건값 : "+req_text);
		
		// 페이징 처리를 하는 메소드 호출
		SquareDAO dao = SquareDAO.getInstance();
		List<NoticeDTO> notice = dao.showNotice(); 
		List<InfoDTO> list = paging(request, nowPage, req_box, req_text);
		request.setAttribute("notice_list", notice);
		request.setAttribute("board_list", list);
		request.setAttribute("req", req_text);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("01accept_board/info_search.jsp");
		return forward;
	}
	
	public List<InfoDTO> paging(HttpServletRequest request,String nowPage, String req_box, String req_text) {
		// 조건에 맞는 리스트를 기준으로 하기위해 Action 페이지의 paging 메소드 overload
			int block = 5; // 아래에 보여질 페이지의 최대 수	
			int size = 0; // 한 페이지당 보여질 게시물의 수
			
			
		      int page = 1;
		     
		    	  size = 10;
		      if (nowPage != null) {
		         page = Integer.parseInt(nowPage);
		      }
		      int total = 0; // DB 상의 레코드 전체 수 (게시물의 수)
		      int allPage = 0; // 전체 페이지 수
		      // 해당 페이지에서 시작 번호
		      int startNo = (page * size) - (size - 1);
		      // 해당 페이지의 끝 번호
		      int endNo = (page * size);
		      // 해당 페이지의 시작 블럭
		      int startBlock = (((page - 1) / block) * block) + 1;
		      // 해당 페이지의 마지막 블럭
		      int endBlock = (((page - 1) / block) * block) + block;

		      List<InfoDTO> list = null;
		      SquareDAO dao = SquareDAO.getInstance();
		      total = dao.getInfoSearchCount(req_box, req_text);
		      // 검색된 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 전체 페이지 수 구하기(나머지는 무조건 올림)
		      allPage = (int) Math.ceil(total / (double) size);
		      System.out.println(allPage);

		      if (allPage > 0) {
		         if (endBlock > allPage)
		            endBlock = allPage;
		         list = dao.getSearchList(startNo, endNo, req_box, req_text);// 현재 페이지에 해당하는 글 목록

		      }
		      
		      request.setAttribute("page", page);
		      request.setAttribute("block", block);
		      request.setAttribute("allPage", allPage);
		      request.setAttribute("startNo", startNo);
		      request.setAttribute("endNo", endNo);
		      request.setAttribute("startBlock", startBlock);
		      request.setAttribute("endBlock", endBlock);
		      
		      return list;
	
	}

}
