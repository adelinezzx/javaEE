package com.yc.bean;

public class Topic {
	
	private Integer tid ;
	private String contents ;
	private String publishtime ;
	private String pic ;
	private Integer uid ;
	private String beautifulContents ;
	
	private  String uname ;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	//显示发表的内容，大于10长度的内容只显示前10长度的内容
	public String getBeautifulContents(){
		String result = "" ;
		if(this.contents != null && this.contents.length() > 15 ){
			result = this.contents.substring(0, 15) + "...";
		}else{
			result = this.contents ;
		}
		return result ;
	}
	
	public void setBeautifulContents(String beautifulContents) {
		this.beautifulContents = beautifulContents;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	

}
