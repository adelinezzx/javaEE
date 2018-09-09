package com.yc.print;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class YCPrinter {
	private SalesTicket scalesTicket;

	
	public YCPrinter() {
		super();
	}


	public YCPrinter(SalesTicket scalesTicket) {
		super();
		this.scalesTicket = scalesTicket;
	}
	
	public void printer() throws PrinterException{
		Book book = new Book();//要打印的文档
		PageFormat pf = new PageFormat();//初始化一个打印页面
		pf.setOrientation(PageFormat.PORTRAIT);//设置打印方向，从上往下，从左往右
		
		//设置打印纸页面信息
		//通过paper设置页面的空白边距和可打印区域
		Paper paper = new Paper();//必须与实际纸张大小一致
		paper.setSize(158, 30000);
		paper.setImageableArea(0, 0, 158, 30000);
		pf.setPaper(paper);
		
		book.append(scalesTicket, pf);
		
		PrinterJob job = PrinterJob.getPrinterJob();  //获取打印服务对象
		job.setPageable(book);//No print service found.
		job.print();//开始打印 
	}
	
}
