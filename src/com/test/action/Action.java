package com.test.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.action.ActionForward;
import com.test.model.SquareDAO;
import com.test.model.DTO;
import com.test.model.InfoDTO;

public interface Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
												throws IOException;
	

	int block = 5; // 아래에 보여질 페이지의 최대 수	
	public default List<DTO> paging(HttpServletRequest request,int mno, int accept, String nowPage){
		int size = 0; // 한 페이지당 보여질 게시물의 수
		
		
	      int page = 1;
	      if(mno==-1&&accept==1 || mno==-1 && accept==-1 || mno>0&&accept==-1) {
	    	  size = 10;
	      } else if(mno>=800&&accept==0) {
	    	  size = 8;
	      }
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

	      List<DTO> list = null;
	      SquareDAO dao = SquareDAO.getInstance();
	      total = dao.getInfoCount(mno, accept);
	      // 검색된 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 전체 페이지 수 구하기(나머지는 무조건 올림)
	      allPage = (int) Math.ceil(total / (double) size);
	      System.out.println(allPage);

	      if (allPage > 0) {
	         if (endBlock > allPage)
	            endBlock = allPage;
	         list = dao.getBaordList(mno, accept, startNo, endNo);// 현재 페이지에 해당하는 글 목록

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
