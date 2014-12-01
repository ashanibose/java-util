package acn.asbas.tools.dp.builder;

import acn.asbas.tools.dp.shared.IProduct;

public interface IDirector<T extends IBuilder<E>, E extends IProduct> {
	void construct();
}
