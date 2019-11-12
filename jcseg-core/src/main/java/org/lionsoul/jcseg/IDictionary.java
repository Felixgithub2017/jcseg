package org.lionsoul.jcseg;

import org.lionsoul.jcseg.dic.HashMapDictionary;
import org.lionsoul.jcseg.fi.DictionaryFunction;

/**
 * Common interface for Dictionary
 * 
 * @author lionsoul<chenxin619315@gmail.com>
*/
public interface IDictionary 
{
	/** Segmentation type constants */
	public static final Type HASHMAP = Type.HASHMAP;
	
	public static enum Type 
	{
		HASHMAP("hashmap", 1, HashMapDictionary::new);
		
		public final String name;
		public final int index;
		public final DictionaryFunction factory;
    	
    	
        /**
         * the type index and type mapping
         * for quick get the type by type index number. 
        */
        public final static Type[] MAPPING = new Type[]{
        	null, HASHMAP
        };
    	
    	private Type(String name, int index, DictionaryFunction factory)
    	{
    		this.name = name;
    		this.index = index;
    		this.factory = factory;
    	}
    	
        
        /**
         * get the Type with the specified string name
         * 
         * @param   type (All lowercase string)
         * @return  Type
        */
        public static Type fromString(String type, Type defaultValue)
        {
        	if ( type == null ) {
        		return defaultValue;
        	}
        	
            type = type.toLowerCase();
            if ( "hashmap".equals(type) ) {
                return Type.HASHMAP;
            }
            
            return defaultValue;
        }
        
		public static Type fromString(String type)
		{
		    return fromString(type, Type.HASHMAP);
		}
		
		public static Type fromIndex(int index)
		{
			assert index > 0;
			assert index < MAPPING.length;
			return MAPPING[index];
		}
    }
	
	
	/**
     * loop up the dictionary, check the given key is in the dictionary or not
     * 
     * @param t
     * @param key
     * @return true for matched false for not match.
     */
    public boolean match( int t, String key );
    
    /**
     * directly add a IWord item to the dictionary
     * 
     * @param   t
     * @param   word
    */
    public IWord add( int t, IWord word );
    
    /**
     * add a new word to the dictionary with its statistics frequency
     * 
     * @param   t
     * @param   key
     * @param   fre
     * @param   type
     * @param   entity
     * @return  IWord
     */
    public IWord add( int t, String key, int fre, int type, String[] entity );
    
    /**
     * add a new word to the dictionary
     * 
     * @param   t
     * @param   key
     * @param   fre
     * @param   type
     * @return  IWord
     */
    public IWord add( int t, String key, int fre, int type );
    
    /**
     * add a new word to the dictionary
     * 
     * @param   t
     * @param   key
     * @param   type
     * @return  IWord
     */
    public IWord add( int t, String key, int type );
    
    /**
     * add a new word to the dictionary
     * 
     * @param   t
     * @param   key
     * @param   type
     * @param   entity
     * @return  IWord
     */
    public IWord add( int t, String key, int type, String[] entity );
    
    /**
     * return the IWord associate with the given key.
     * if there is not mapping for the key null will be return
     * 
     * @param t
     * @param key
     */
    public IWord get( int t, String key );
    
    /**
     * remove the mapping associate with the given key
     * 
     * @param t
     * @param key
     */
    public void remove( int t, String key );
    
    /**
     * return the size of the dictionary
     * 
     * @param    t
     * @return int
     */
    public int size(int t);
    
}
