package acn.asbas.tools.dp.factory.impl;

import acn.asbas.tools.dp.factory.IFactory;
import acn.asbas.tools.dp.shared.impl.ProductOne;

public class FactoryOne implements IFactory<ProductOne> {

	public ProductOne getInstance() {
		return new ProductOne();
	}

}
