package com.test.action;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiUtils;

public class CreateThumbnail {

	public String getThumbnail(HttpServletRequest request, HttpServletResponse response,
									String file_name) {
		System.out.println(file_name);
		StringTokenizer token = new StringTokenizer(file_name, "/");
		String compath = token.nextToken();
		String needpath = token.nextToken();
		String fileName = token.nextToken();
		System.out.println(compath);
		System.out.println(needpath);
		System.out.println(fileName);
		/*token = new StringTokenizer(path,"/");
		String needpath = token.nextToken();
		String fileName = token.nextToken();*/
		// 첨부 파일이 저장될 위치
		String saveFolder =
				"D:\\Ssangyong\\workspace(JSP)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\projectCAFEIN\\uploadFile"
						+"\\"+needpath;

			token = new StringTokenizer(fileName,".jpg");
			String useName = token.nextToken();
			System.out.println("사용할 이름 : "+useName);
			useName +="_thumb.PNG";
			
			System.out.println("원본 파일명 : " + fileName);
			System.out.println("저장할 파일명 : " + useName);
			
			/*******************************
			 * 파일을 저장할 폴더 등 파일 패스를 지정  *
			 *******************************/
			
			// dir 명칭 설정    ......../uploadFile/2020-04-06 etc...
			String homedir = saveFolder + "\\"+needpath+"_thumb";
			String thumbImg = homedir+"\\"+useName;
			
			System.out.println("파일경로 : " + homedir+"\n"+thumbImg);
			
			File f = new File(homedir);
			if (!f.exists()) {
				f.mkdirs();
			}
			System.out.println("저장할 이미지 경로 : " + saveFolder+"\\"+fileName);
			Image thumb = JimiUtils.getThumbnail(
					// 0 : 소스파일
					// 1 : 썸네일 파일
					// 2 : Width
					// 3 : Height
					// 4 : 비율고정여부
					saveFolder+"\\"+fileName, 
					50, 
					50, 
					Jimi.IN_MEMORY);
			
			try {
				Jimi.putImage(thumb, thumbImg);
				
			} catch (JimiException e) {
				e.printStackTrace();
			}
			
			String fileDBName = "uploadFile/"+needpath+"/"+needpath+"_thumb/"+useName;
			
			return fileDBName;
		}

	
}
