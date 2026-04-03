package benchmarks.map.classes;

public interface ObjectFactory<T>
{
    T create(String... keyComponents);
}
