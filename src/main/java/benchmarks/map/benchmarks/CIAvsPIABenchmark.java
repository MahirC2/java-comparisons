package benchmarks.map.benchmarks;

import benchmarks.map.classes.ObjectFactory;
import benchmarks.map.classes.ObjectMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 8, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 8, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@Threads(1)
public class CIAvsPIABenchmark
{
    record DummyMetric(String name) implements Metric
    {
    }
    
    interface Metric
    {
    }
    
    @State(Scope.Thread)
    public static class BenchmarkState
    {
        ObjectMap<Metric> piaMap;
        ObjectMap<Metric> ciaMap;
        
        String[] missKeys;
        int      missIndex;
        
        String s1 = "str1";
        String s2 = "str2";
        String s3 = "str3";
        String s4 = "str4";
        String s5 = "str5";
        
        @Setup(Level.Trial)
        public void setup()
        {
            ObjectFactory<Metric> factory =
                    (keys) -> new DummyMetric(keys.length > 0 ? keys[0] : "root");
            
            piaMap = new ObjectMap<>(factory);
            ciaMap = new ObjectMap<>(factory);
            
            // warm-up (hits only)
            piaMap.getChildPutIfAbsent(s1);
            ciaMap.getChildWithComputeIfAbsent(s1);
            piaMap.getChildPutIfAbsent(s1, s2);
            ciaMap.getChildWithComputeIfAbsent(s1, s2);
            piaMap.getChildPutIfAbsent(s1, s2, s3);
            ciaMap.getChildWithComputeIfAbsent(s1, s2, s3);
            piaMap.getChildPutIfAbsent(s1, s2, s3, s4);
            ciaMap.getChildWithComputeIfAbsent(s1, s2, s3, s4);
            piaMap.getChildPutIfAbsent(s1, s2, s3, s4, s5);
            ciaMap.getChildWithComputeIfAbsent(s1, s2, s3, s4, s5);
            
            // Pre-generating miss keys.
            missKeys = new String[8192];
            for (int i = 0; i < missKeys.length; i++)
            {
                missKeys[i] = "miss-" + i;
            }
        }
        
        String nextMissKey()
        {
            return missKeys[missIndex++ & (missKeys.length - 1)];
        }
    }
    
    @Benchmark
    public void pia_hit_s0(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent());
    }
    
    @Benchmark
    public void cia_hit_s0(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent());
    }
    
    @Benchmark
    public void pia_hit_s1(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.s1));
    }
    
    @Benchmark
    public void cia_hit_s1(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.s1));
    }
    
    @Benchmark
    public void pia_hit_s2(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.s1, s.s2));
    }
    
    @Benchmark
    public void cia_hit_s2(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.s1, s.s2));
    }
    
    @Benchmark
    public void pia_hit_s3(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.s1, s.s2, s.s3));
    }
    
    @Benchmark
    public void cia_hit_s3(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.s1, s.s2, s.s3));
    }
    
    @Benchmark
    public void pia_hit_s4(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.s1, s.s2, s.s3, s.s4));
    }
    
    @Benchmark
    public void cia_hit_s4(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.s1, s.s2, s.s3, s.s4));
    }
    
    @Benchmark
    public void pia_hit_s5(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.s1, s.s2, s.s3, s.s4, s.s5));
    }
    
    @Benchmark
    public void cia_hit_s5(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.s1, s.s2, s.s3, s.s4, s.s5));
    }
    
    @Benchmark
    public void pia_miss(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.piaMap.getChildPutIfAbsent(s.nextMissKey()));
    }
    
    @Benchmark
    public void cia_miss(BenchmarkState s, Blackhole bh)
    {
        bh.consume(s.ciaMap.getChildWithComputeIfAbsent(s.nextMissKey()));
    }
    
    
    public static void main(String[] args) throws RunnerException
    {
        Options opt = new OptionsBuilder()
                .include(CIAvsPIABenchmark.class.getSimpleName())
                .shouldFailOnError(true)
                .build();
        
        new Runner(opt).run();
    }
}