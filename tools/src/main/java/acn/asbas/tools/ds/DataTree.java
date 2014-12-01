package acn.asbas.tools.ds;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Encapsulates the tree data structure.
 * 
 */
public class DataTree<K extends Object> {
	private final Map<K, DataNode<K>> dataNodeMap;
	private final List<DataNode<K>> rootDataNodes;
	private final IDataHandlerFactory<K> dataHandlerFactory;

	public DataTree(IDataHandlerFactory<K> dataHandlerFactory) {
		this.dataHandlerFactory = dataHandlerFactory;
		this.dataNodeMap = new LinkedHashMap<K, DataNode<K>>();
		this.rootDataNodes = new LinkedList<DataNode<K>>();
	}

	public Map<K, DataNode<K>> getDataNodeMap() {
		return dataNodeMap;
	}

	public List<DataNode<K>> getRootDataNodes() {
		return rootDataNodes;
	}

	/**
	 * Adds the data to the tree structure. <li>*It's required that the data
	 * should have a related {@link DataHandler} and configured such a way that
	 * the handler should returned through the tree associated factory.</li><li>
	 * *The {@link DataNode} generated by the handle should have the appropriate
	 * parent information.To locate the position where the new {@link DataNode}
	 * instance should be added to the tree it's required to have the full
	 * hierarchy path for the node.</li><li>*In case no parent is being
	 * provided, it will assumed that the {@link DataNode} instance is a root
	 * level element.</li>
	 * 
	 * @param Object
	 * @return {@link DataNode}
	 */
	@SuppressWarnings("unchecked")
	public final <T extends DataNode<K>> boolean add(Object data) {

		boolean exists = false;

		ITreeDataHandler<T, K, Object> dataHandler = dataHandlerFactory
				.getTreeHandler(data);
		dataHandler.setData(data);

		K dataKey = dataHandler.getKey();

		T selectNode = dataHandler.buildDataNode();
		if (isExists(dataKey)) {
			T changed = (T) updateAndSwap(selectNode, dataNodeMap.get(dataKey));
			selectNode = dataHandler.matchAndPopulateNode(selectNode, changed);
			exists = true;
		} else {
			dataNodeMap.put(selectNode.getKey(), selectNode);

			if (selectNode.getParent() == null) {
				rootDataNodes.add(selectNode);
			}
		}

		while (selectNode.getParent() != null) {
			selectNode = (T) selectNode.getParent();
			K selectNodeKey = selectNode.getKey();
			if (isExists(selectNodeKey)) {
				T t = (T) dataNodeMap.get(selectNodeKey);

				T changed = updateAndSwap(selectNode, t);
				selectNode = dataHandler.matchAndPopulateNode(selectNode,
						changed);

				rootDataNodes.remove(t);

			} else {
				dataNodeMap.put(selectNode.getKey(), selectNode);
			}

			if (selectNode.getParent() == null) {
				rootDataNodes.add(selectNode);
			}
		}

		return !exists;
	}

	/**
	 * Removes {@link DataNode} having the provided data from the tree.
	 * 
	 * @param Object
	 * @return boolean
	 */
	public final boolean remove(Object data) {
		@SuppressWarnings("unchecked")
		ITreeDataHandler<DataNode<K>, K, Object> dataHandler = dataHandlerFactory
				.getTreeHandler(data);
		dataHandler.setData(data);

		K dataKey = dataHandler.getKey();

		if (isExists(dataKey)) {
			DataNode<K> t = dataNodeMap.get(dataKey);

			rootDataNodes.remove(t);
			dataNodeMap.remove(dataKey);
			return true;

		}
		return false;
	}

	/**
	 * Returns the appropriate updated instance of {@link DataNode} between
	 * refData and targetData. It considers few factors like non-dummy more data
	 * reach node is preferred.
	 * 
	 * @param {@link DataNode}
	 * @param {@link DataNode}
	 * @return {@link DataNode}
	 */
	protected final <T extends DataNode<K>> T updateAndSwap(T refData,
			T targetNode) {

		if (!isDummy(refData) && isDummy(targetNode)) {
			if (refData.getParent() == null && targetNode.getParent() != null) {
				refData.setParent(targetNode.getParent());
			}

			@SuppressWarnings("unchecked")
			Set<T> child = (Set<T>) targetNode.getChild();

			for (T t : child) {
				refData.addChild(t);
			}
			return refData;
		}

		if (refData.getParent() != null && targetNode.getParent() == null) {
			targetNode.setParent(refData.getParent());
		}

		@SuppressWarnings("unchecked")
		Set<T> child = (Set<T>) refData.getChild();

		if (child != null) {
			for (T t : child) {
				targetNode.addChild(t);
			}
		}

		return targetNode;

	}

	public final boolean isExists(K key) {
		return dataNodeMap.containsKey(key);
	}

	public final DataNode<K> getNode(K key) {
		return dataNodeMap.get(key);
	}

	/**
	 * Returns true if the current node is a dummy node i.e. the node does not
	 * have any wrapped data rather it's only a place holder in the tree
	 * structure.
	 * 
	 * @param {@link DataNode}
	 * @return boolean
	 */
	public boolean isDummy(DataNode<K> node) {
		return node.isDummy();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("dataNodeMap:\n");
		for (Entry<K, DataNode<K>> entry : dataNodeMap.entrySet()) {
			builder.append("Key: ").append(entry.getKey().toString());
			builder.append(" Value: ").append(entry.getValue().toString());
		}

		builder.append("\nrootDataNodes:\n");
		for (DataNode<K> t : rootDataNodes) {
			builder.append(" Key: ").append(t.getKey());
		}

		return builder.toString();
	}
}