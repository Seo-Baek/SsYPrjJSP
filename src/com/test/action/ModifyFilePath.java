package com.test.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ModifyFilePath implements Action{

	@Override
	public ActionForward execute
		(HttpServletRequest request, HttpServletResponse response) 
												throws IOException {
		// AJAX로 base64 이름을 바꾸어 editor로 재송신하는 클래스
		//response.setContentType("text/html;charset=utf-8"); front를 거치니까..
		
		/** AJAX에 보낼 printwriter객체 생성*/
		PrintWriter out = response.getWriter();	
		
		// 업로드할 폴더 경로
		// 첨부 파일이 저장될 위치
		String saveFolder = 
				"C:\\NCS\\workspace(Project)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\projectCAFEIN\\uploadFile";
		
		// 첨부 파일의 최대 크기
		int fileSize = 10 * 1024 * 1024;	// 10MB
		
		/******************************
		 * 업로드 할 파일 이름을 재설정하는 작업 *
		 ******************************/
		// 파일 업로드 진행 시 이진 파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request,						// 일반적인 request 
				saveFolder,						// 업로드 파일 저장 위치 
				fileSize,						// 업로드할 파일의 최대 크기
				"UTF-8",						// 문자 인코딩 방식
				new DefaultFileRenamePolicy()	// 파일 이름 중복이 안 되게 하는 설정
		);
		
		// UUID 객체를 통한 랜덤 uuid 생성(보안)
		UUID uuid = UUID.randomUUID();
		
		String mname = request.getParameter("mname");
		
		// 업로드할 파일 이름
		File upload_file = multi.getFile("file");
		if(upload_file != null) {	// 첨부파일이 존재하는 경우
			// getName() : 첨부파일의 이름을 문자열로 반환하는 메소드
			String fileName = upload_file.getName();
			String str_filename = uuid.toString()+ mname + fileName;
	
			System.out.println("원본 파일명 : " + fileName);
			System.out.println("저장할 파일명 : " + str_filename);
			
			/*******************************
			 * 파일을 저장할 폴더 등 파일 패스를 지정  *
			 *******************************/
			//날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// dir 명칭 설정    ......../uploadFile/2020-04-06 etc...
			String homedir = saveFolder + "/" + year + "-" + month + "-" +day;
			System.out.println("파일경로 : " + homedir);
	
			File f = new File(homedir);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			/********************
			 * 본격 넘길 파일명 생성 ! *
			 ********************/
			String refileName = str_filename;
			upload_file.renameTo(new File(homedir+"/"+refileName));
			String fileDBName =
					"/"+year+"-"+month+"-"+day+"/"+refileName;
			request.setAttribute("file_name", fileDBName);
			out.println("uploadFile"+fileDBName);
			out.close();
		}
		return null;
	}
}

