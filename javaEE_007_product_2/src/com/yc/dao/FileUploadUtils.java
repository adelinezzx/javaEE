package com.yc.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;

public class FileUploadUtils {
	private String allwedFileList = "gif,jpg,png,bmp";
	private String  deniedFilesList = "jsp,asp,php,aspx,html,htm,exe,bat,js,py,sh";
	private long singleFileSize = 20 * 1024 * 1024 ;
	private long totalMaxFileSize = 5 * singleFileSize ;
	//������ҳ����<img src ="" />����·�� ��һ�����·��
	private String dirName = "../upload/" ;
	
	private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  //ʱ���ʽ��
	
	public Map<String,String> uploadFiles(PageContext pageContext) throws Exception{
		Map<String,String> result = new HashMap<String,String>();
		SmartUpload su = new SmartUpload();
		su.setCharset("utf-8");
		su.initialize(pageContext);  //ҳ���ʼ��
		//���������ϴ��ļ�����
		su.setAllowedFilesList(allwedFileList);
		//�������ϴ��ļ�����
		su.setDeniedFilesList(deniedFilesList);
		//�����ļ��������
		su.setMaxFileSize(singleFileSize);
		//�����ϴ��ļ�������������
		su.setTotalMaxFileSize(totalMaxFileSize);
		su.upload();
		
		//ȡ��jspsmartupload�е�request����  ��ȡ��uname  message
		com.jspsmart.upload.Request req = su.getRequest();
		Enumeration<String> enu = req.getParameterNames() ;
		while(enu.hasMoreElements()){
			String key = enu.nextElement();
			result.put(key, req.getParameter(key));
		}
		
		String relativepath = "" ;
		if(su.getFiles().getCount() > 0 ){    //�ļ������������0
			Files files= su.getFiles();
			int count  = files.getCount();
			
			for(int i =0 ; i< count ; i++){
				//��ȡ�����ϴ��ļ�
				com.jspsmart.upload.File file = files.getFile(i);
				String oldname = file.getFileName();   //��ȡ�ļ�ԭ��������
				//           ������ =                      20180103205700 .         �ļ�  ��׺��
				String newname = df.format(new Date())  +"." + file.getFileExt()  ;
				relativepath = dirName + newname ;    //...webapp/upload/xxx.jpg   Ҫ����ĵ�ַ
				//�����ļ��ڷ������ı���λ��
				//ȡ������û·��
				//ȡTomcat�µ�webapp�µľ���·��  �������ڴ�ͼƬ
				
				String rootpath = pageContext.getRequest().getRealPath("/");  //  webapp/talkroom
				String filepath = rootpath + dirName ; // webapp/talkroom/../upload/xxx.jpg
				java.io.File f = new java.io.File(filepath);
				if(f.exists() == false){
					f.mkdirs();// ����ļ������ڣ��½��ļ�
				}
				String absolutepath = filepath + newname ;
				//�ļ����Ϊ
				file.saveAs(absolutepath,SmartUpload.SAVE_PHYSICAL);
				
				//������ļ�����ϢѰ��result�еļ��Ĺ���ʱ�� ����_relativepath
				//����_absolutepath
				result.put(file.getFieldName() + "_relativepath", relativepath);
				result.put(file.getFieldName() + "_absolutepath", absolutepath);
				result.put(file.getFieldName() + "_oldname", oldname);
				result.put(file.getFieldName() + "_newname", newname);
				
			}
		}
		return result ;
	}

	public String getAllwedFileList() {
		return allwedFileList;
	}

	public void setAllwedFileList(String allwedFileList) {
		this.allwedFileList = allwedFileList;
	}

	public String getDeniedFilesList() {
		return deniedFilesList;
	}

	public void setDeniedFilesList(String deniedFilesList) {
		this.deniedFilesList = deniedFilesList;
	}

	public long getSingleFileSize() {
		return singleFileSize;
	}

	public void setSingleFileSize(long singleFileSize) {
		this.singleFileSize = singleFileSize;
	}

	public long getTotalMaxFileSize() {
		return totalMaxFileSize;
	}

	public void setTotalMaxFileSize(long totalMaxFileSize) {
		this.totalMaxFileSize = totalMaxFileSize;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	
}
