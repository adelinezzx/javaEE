package com.yc.bean;

public class DataSourcePool {
	
	private static DataSourcePool   dsp = new DataSourcePool() ;
	private  DataSourcePool(){
		//读取数据配置
	}
	
	public  static  DataSourcePool  createInstance(){
		if(dsp == null){
			dsp = new  DataSourcePool() ;
		}
		return  dsp ;
	}

	public String getCon(){
		return "con";
	}
}
