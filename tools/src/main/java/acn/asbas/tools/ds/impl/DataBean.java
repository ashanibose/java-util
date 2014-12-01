package acn.asbas.tools.ds.impl;

public class DataBean {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Code: " + id;
	}

}
