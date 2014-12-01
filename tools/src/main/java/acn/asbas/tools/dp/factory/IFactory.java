package acn.asbas.tools.dp.factory;

import acn.asbas.tools.dp.shared.IProduct;

public interface IFactory<T extends IProduct> {
	public T getInstance(); 
}
