package acn.asbas.tools.dp.builder.impl;

import acn.asbas.tools.dp.builder.IBuilder;
import acn.asbas.tools.dp.shared.impl.ProductOne;

public class BuilderOne implements IBuilder<ProductOne> {
	private ProductOne productOne;

	public BuilderOne buildSomething() {
		productOne = new ProductOne();
		System.out.println("BuilderOne: in buildSomething");

		return this;
	}

	public ProductOne getResult() {
		return productOne;
	}

}
