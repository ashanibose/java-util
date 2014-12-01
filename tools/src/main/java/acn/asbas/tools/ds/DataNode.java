package acn.asbas.tools.ds;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class DataNode<K extends Object> {
    private DataNode<K> parent;
    private Set<DataNode<K>> child;
    private K key;
    private boolean dummy = false;

    public DataNode(K key) {
           this.key = key;
    }

    public DataNode<K> getParent() {
           return parent;
    }

    public void setParent(DataNode<K> parent) {
           this.parent = parent;
    }

    public Set<DataNode<K>> getChild() {
           return child;
    }

    /**
    * Adds child Node.
    * 
     * @param {@link DataNode}
    */
    public void addChild(DataNode<K> node) {
           if (child == null) {
                  this.child = new LinkedHashSet<DataNode<K>>();
           }

           this.child.add(node);
    }

    /**
    * Removes child Node.
    * 
     * @param {@link DataNode}
    */
    public void removeChild(DataNode<K> node) {
           this.child.remove(node);
    }

    public K getKey() {
           return key;
    }

    public boolean isDummy() {
           return dummy;
    }

    public void setDummy(boolean dummy) {
           this.dummy = dummy;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
           if (obj != null && obj instanceof DataNode && super.equals(obj)
                        && this.getKey().equals(((DataNode) obj).getKey())) {
                  return super.equals(obj);
           }

           return false;
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
           StringBuffer buffer = new StringBuffer();

           buffer.append("KeyInt: ").append(key);

           buffer.append(" Parent: ").append(
                        parent == null ? null : parent.getKey());

           buffer.append(" Children: ");

           if (child != null) {

                  for (DataNode<K> dn : child) {
                        buffer.append(dn.key).append(" | ");
                  }
           } else {
                  buffer.append("[] ");
           }
           buffer.append(" isdummy: ").append(dummy).append("||\n");

           return buffer.toString();
    }

    /**
    * Created the JSON representation of the node.
    * 
     * @param level
    *            , in case the implementation needs to be aware of the level of
    *            the tree at which the current node resides.
    * @return {@link Map}
    */
    public abstract Map<String, Object> getJSONConstruct(int level);
}
