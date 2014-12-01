package acn.asbas.tools.ds;

import java.util.Map;

/**
 * A Decorator around the data object.
 * 
 */
interface IDataHandler<K extends Object, D extends Object> {

	/**
	 * Returns the wrapped data.
	 * 
	 * @return {@link Object}
	 */
	D getData();

	/**
	 * Sets the data to the handler.
	 * 
	 * @return {@link DataHandler}
	 */
	IDataHandler<K, D> setData(D data);

	/**
	 * Returns the wrapped data key.
	 * 
	 * @return {@link Object}
	 */
	K getKey();

	/**
	 * Returns JSON structure representing the wrapped data.
	 * 
	 * @return {@link Map}
	 */
	Map<String, Object> getJSONConstruct();
}