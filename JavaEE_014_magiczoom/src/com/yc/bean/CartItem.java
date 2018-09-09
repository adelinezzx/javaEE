package com.yc.bean;

import java.io.Serializable;

import com.yc.bean.Resfood;

/**
 * 一个购物项
 */
public class CartItem implements Serializable {
	private static final long serialVersionUID = -7267043439881456032L;

	private Resfood resfood;
	private Integer count;
	
	//el表达式调用的是  getXXX的方法，但  gson 生成json字符串是生成的属性，为了让客户端拿到smallCount的值，所以必须加入 属性  smallCount;
	private Double smallCount;

	/**
	 * 计算每种菜品的总价格
	 * @return
	 */
	public Double getSmallCount() {
		smallCount= resfood.getRealprice() * count;
		return smallCount;
	}

	public Resfood getResfood() {
		return resfood;
	}

	public void setResfood(Resfood resfood) {
		this.resfood = resfood;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
