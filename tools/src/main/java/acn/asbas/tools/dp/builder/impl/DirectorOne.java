package acn.asbas.tools.dp.builder.impl;

import acn.asbas.tools.dp.builder.IDirector;
import acn.asbas.tools.dp.shared.impl.ProductOne;

public class DirectorOne implements IDirector<BuilderOne, ProductOne> {
	private BuilderOne builderOne;

	public DirectorOne(BuilderOne builder) {
		this.builderOne = builder;
	}
	
	public void construct() {
		builderOne.buildSomething();
	}

}
