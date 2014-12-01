package acn.asbas.tools.ds;

/**
 * A type of {@link DataHandler} which has additional behavior to be used in
 * {@link DataTree}.
 */
interface ITreeDataHandler<T extends DataNode<K>, K extends Object, D extends Object>
		extends IDataHandler<K, D> {

	/**
	 * Returns the key of the rootnode in structure generated from the wrapped
	 * data.
	 * 
	 * @return {@link Object}
	 */
	K getRootNodeKey();

	/**
	 * Builds {@link DataNode} from the data.
	 * 
	 * @return DataNode
	 */
	T buildDataNode();

	/*
	 * Compare between the refData and targetData. Returns the taggetData after
	 * populating it with the additional details from refData.
	 * 
	 * @param {@link DataNode}
	 * 
	 * @param {@link DataNode}
	 * 
	 * @return {@link DataNode}
	 */
	T matchAndPopulateNode(T refData, T targetData);
}