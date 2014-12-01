package acn.asbas.tools.dp.builder;

import acn.asbas.tools.dp.shared.IProduct;

public interface IBuilder<T extends IProduct> {
	IBuilder buildSomething();

	T getResult();
}
