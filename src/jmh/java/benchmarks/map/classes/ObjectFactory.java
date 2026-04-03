package benchmarks.map.classes;

interface ObjectFactory<T>
{
    T create(String... keyComponents);
}
