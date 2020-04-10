package com.test.action;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiUtils;
import com.test.model.InfoDTO;
import com.test.model.MemberDAO;
import com.test.model.SquareDAO;

public class UploadBoard extends CreateThumbnail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 폼에서 받아온 정보들을 DB에 저장하는 클래스
		InfoDTO dto = new InfoDTO();
		int group = 0;
		if(request.getParameter("group") != null)
			group = Integer.parseInt(request.getParameter("group"));
		
		int step = 0;
		if(request.getParameter("step") != null)
			step = Integer.parseInt(request.getParameter("step"))+1;
			
		int mno = Integer.parseInt(request.getParameter("mno"));
		String mname = request.getParameter("mname");
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		String file_name = null;
		
		/**********************
		 * 첨부 파일을 받아오는 작업  *
		 **********************/
		if(request.getParameter("thumbnail") != "") {
			file_name = request.getParameter("thumbnail");
			String fileDBName = getThumbnail(request,response,file_name);
				// DB에 저장될 파일 이름을 dto에 저장
			dto.setBoard_file(fileDBName);
		}
		
		dto.setBoard_group(group);
		dto.setBoard_step(step);
		dto.setBoard_mno(mno);
		dto.setBoard_writer(mname);
		dto.setBoard_title(title);
		dto.setBoard_cont(cont);
		if(request.getParameter("parent") != null)
			dto.setBoard_parent(Integer.parseInt(request.getParameter("parent")));
			
		MemberDAO mem = MemberDAO.getInstance();
		String pwd = mem.getUserPwd(mno);
		dto.setBoard_pwd(pwd);
			
		SquareDAO dao = SquareDAO.getInstance();
		int res = dao.uploadBoard(dto);
			
		PrintWriter out = response.getWriter();
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('글 등록 성공')");
				out.println("location.href='accept_list.do?mno=-1&&accept=1'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('글 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
			return null;
	}
	

}
