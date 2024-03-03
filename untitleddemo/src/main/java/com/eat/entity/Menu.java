package com.eat.entity;
//
public class Menu {
	String xgname;
	float xgprice;
	String xgremark;
	String xgpic;
	int xgsellid;

	public String getXgname() {
		return xgname;
	}

	public void setXgname(String xgname) {
		this.xgname = xgname;
	}

	public float getXgprice() {
		return xgprice;
	}

	public void setXgprice(float xgprice) {
		this.xgprice = xgprice;
	}

	public String getXgremark() {
		return xgremark;
	}

	public void setXgremark(String xgremark) {
		this.xgremark = xgremark;
	}

	public String getXgpic() {
		return xgpic;
	}

	public void setXgpic(String xgpic) {
		this.xgpic = xgpic;
	}

	public int getXgsellid() {
		return xgsellid;
	}

	public void setXgsellid(int xgsellid) {
		this.xgsellid = xgsellid;
	}

	@Override
	public String toString() {
		return "Menu{" +
				"xgname='" + xgname + '\'' +
				", xgprice=" + xgprice +
				", xgremark='" + xgremark + '\'' +
				", xgpic='" + xgpic + '\'' +
				", xgsellid=" + xgsellid +
				'}';
	}
}
