package benchmarks.map.classes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import benchmarks.map.classes.Keys.*;

public class ObjectMap<T>
{
    private final ConcurrentMap<String, T> key1ToValue = new ConcurrentHashMap<>();
    private final ConcurrentMap<Key2, T>   key2ToValue = new ConcurrentHashMap<>();
    private final ConcurrentMap<Key3, T>   key3ToValue = new ConcurrentHashMap<>();
    private final ConcurrentMap<Key4, T>   key4ToValue = new ConcurrentHashMap<>();
    private final ConcurrentMap<Key5, T>   key5ToValue = new ConcurrentHashMap<>();
    private final T                        noKeyObject;
    private final ObjectFactory<T>         objectFactory;
    
    public ObjectMap(ObjectFactory<T> objectFactory)
    {
        this.noKeyObject = objectFactory.create();
        this.objectFactory = objectFactory;
    }
    
    public T getChildWithComputeIfAbsent(String... keys)
    {
        return switch (keys.length)
        {
            case 0 -> getComputeIfAbsent();
            case 1 -> getComputeIfAbsent(keys[0]);
            case 2 -> getComputeIfAbsent(keys[0], keys[1]);
            case 3 -> getComputeIfAbsent(keys[0], keys[1], keys[2]);
            case 4 -> getComputeIfAbsent(keys[0], keys[1], keys[2], keys[3]);
            case 5 -> getComputeIfAbsent(keys[0], keys[1], keys[2], keys[3], keys[4]);
            default -> throw new IllegalArgumentException("Expected 0–5 keys, got " + keys.length);
        };
    }
    
    public T getChildPutIfAbsent(String... keys)
    {
        return switch (keys.length)
        {
            case 0 -> getPutIfAbsent();
            case 1 -> getPutIfAbsent(keys[0]);
            case 2 -> getPutIfAbsent(keys[0], keys[1]);
            case 3 -> getPutIfAbsent(keys[0], keys[1], keys[2]);
            case 4 -> getPutIfAbsent(keys[0], keys[1], keys[2], keys[3]);
            case 5 -> getPutIfAbsent(keys[0], keys[1], keys[2], keys[3], keys[4]);
            default -> throw new IllegalArgumentException();
        };
    }
    
    public T getPutIfAbsent()
    {
        return noKeyObject;
    }
    
    public T getPutIfAbsent(String s1)
    {
        T existing = key1ToValue.get(s1);
        if (existing != null)
        {
            return existing;
        }
        
        T created = objectFactory.create(s1);
        T prior = key1ToValue.putIfAbsent(s1, created);
        
        return prior != null ? prior : created;
    }
    
    public T getPutIfAbsent(String s1, String s2)
    {
        Key2 key = new Key2(s1, s2);
        
        T existing = key2ToValue.get(key);
        if (existing != null)
        {
            return existing;
        }
        
        T created = objectFactory.create(s1, s2);
        T prior = key2ToValue.putIfAbsent(key, created);
        
        return prior != null ? prior : created;
    }
    
    public T getPutIfAbsent(String s1, String s2, String s3)
    {
        Key3 key = new Key3(s1, s2, s3);
        
        T existing = key3ToValue.get(key);
        if (existing != null)
        {
            return existing;
        }
        
        T created = objectFactory.create(s1, s2, s3);
        T prior = key3ToValue.putIfAbsent(key, created);
        
        return prior != null ? prior : created;
    }
    
    public T getPutIfAbsent(String s1, String s2, String s3, String s4)
    {
        Key4 key = new Key4(s1, s2, s3, s4);
        
        T existing = key4ToValue.get(key);
        if (existing != null)
        {
            return existing;
        }
        
        T created = objectFactory.create(s1, s2, s3, s4);
        T prior = key4ToValue.putIfAbsent(key, created);
        
        return prior != null ? prior : created;
    }
    
    public T getPutIfAbsent(String s1, String s2, String s3, String s4, String s5)
    {
        Key5 key = new Key5(s1, s2, s3, s4, s5);
        
        T existing = key5ToValue.get(key);
        if (existing != null)
        {
            return existing;
        }
        
        T created = objectFactory.create(s1, s2, s3, s4, s5);
        T prior = key5ToValue.putIfAbsent(key, created);
        
        return prior != null ? prior : created;
    }
    
    public T getComputeIfAbsent()
    {
        return noKeyObject;
    }
    
    public T getComputeIfAbsent(String s1)
    {
        return key1ToValue.computeIfAbsent(s1, objectFactory::create);
    }
    
    public T getComputeIfAbsent(String s1, String s2)
    {
        Key2 key = new Key2(s1, s2);
        return key2ToValue.computeIfAbsent(key, k -> objectFactory.create(s1, s2));
    }
    
    public T getComputeIfAbsent(String s1, String s2, String s3)
    {
        Key3 key = new Key3(s1, s2, s3);
        return key3ToValue.computeIfAbsent(key, k -> objectFactory.create(s1, s2, s3));
    }
    
    public T getComputeIfAbsent(String s1, String s2, String s3, String s4)
    {
        Key4 key = new Key4(s1, s2, s3, s4);
        return key4ToValue.computeIfAbsent(key, k -> objectFactory.create(s1, s2, s3, s4));
    }
    
    public T getComputeIfAbsent(String s1, String s2, String s3, String s4, String s5)
    {
        Key5 key = new Key5(s1, s2, s3, s4, s5);
        return key5ToValue.computeIfAbsent(key, k -> objectFactory.create(s1, s2, s3, s4, s5));
    }
}
