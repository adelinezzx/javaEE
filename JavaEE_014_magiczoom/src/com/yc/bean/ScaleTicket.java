package com.yc.bean;

import java.awt.*;
import java.awt.print.PageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ScaleTicket {
	
	private List<Resorderitem> goods ;
	private String operatorName = "adeline" ;  //操作员
	private String orderId ;                      //商品列表
	private String totalGoodsNum ;//商品总数
	private String totalPrice;//总金额
	private String actualCollection;//实收款
	private String giveChange ;//找零
	private String cardNumber ;//会员编号
	private String integeral ;//积分
	
	private String companyName="adeline" ;

	private SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss ");
	
	public ScaleTicket(String companyName) {
		this.companyName = companyName;
	}
	
	public ScaleTicket(List<Resorderitem> goods, String operatorName,
			String orderId, String totalGoodsNum, String totalPrice,
			String actulCollection, String giveChange, String cardNumber,
			String integeral) {
		this.goods = goods;
		this.operatorName = operatorName;
		this.orderId = orderId;
		this.totalGoodsNum = totalGoodsNum;
		this.totalPrice = totalPrice;
		this.actualCollection = actulCollection;
		this.giveChange = giveChange;
		this.cardNumber = cardNumber;
		this.integeral = integeral;
	}



	public List<Resorderitem> getGoods() {
		return goods;
	}

	public void setGoods(List<Resorderitem> goods) {
		this.goods = goods;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTotalGoodsNum() {
		return totalGoodsNum;
	}

	public void setTotalGoodsNum(String totalGoodsNum) {
		this.totalGoodsNum = totalGoodsNum;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getActulCollection() {
		return actualCollection;
	}

	public void setActulCollection(String actulCollection) {
		this.actualCollection = actulCollection;
	}

	public String getGiveChange() {
		return giveChange;
	}

	public void setGiveChange(String giveChange) {
		this.giveChange = giveChange;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIntegeral() {
		return integeral;
	}

	public void setIntegeral(String integeral) {
		this.integeral = integeral;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	 



	public ScaleTicket(List<Resorderitem> goods, String operatorName, String orderId, String totalGoodsNum,
			String totalPrice, String actualCollection, String giveChange) {
		super();
		this.goods = goods;
		this.operatorName = operatorName;
		this.orderId = orderId;
		this.totalGoodsNum = totalGoodsNum;
		this.totalPrice = totalPrice;
		this.actualCollection = actualCollection;
		this.giveChange = giveChange;
	}
	 
}
