package acn.asbas.tools.ds;

/**
* 
 * {@link DataHandler} fanctory methods.
*/

public interface IDataHandlerFactory<K extends Object> {
       @SuppressWarnings("rawtypes")
       public IDataHandler getHandler(Object data);

       @SuppressWarnings("rawtypes")
       public ITreeDataHandler getTreeHandler(Object data);
}
