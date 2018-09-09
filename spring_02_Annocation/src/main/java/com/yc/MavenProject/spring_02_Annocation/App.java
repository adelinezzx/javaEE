package com.yc.MavenProject.spring_02_Annocation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.yc.bean.Student;

//@Configuration  //表明这是一个beans.xml的配置文件
@ComponentScan(basePackages="com.yc")
public class App {
	 
//	@Bean
	public Student stu(){
		return new Student();
	}
}
