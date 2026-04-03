package benchmarks.map.classes;

import java.util.Objects;

class Keys
{
    static class Key2
    {
        private final String s1, s2;
        
        Key2(String s1, String s2)
        {
            this.s1 = s1;
            this.s2 = s2;
        }
        
        @Override
        public int hashCode()
        {
            int h$ = 1;
            h$ *= 1000003;
            h$ ^= (s1 == null ? 0 : s1.hashCode());
            h$ *= 1000003;
            h$ ^= (s2 == null ? 0 : s2.hashCode());
            h$ *= 1000003;
            return h$;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Key2 other))
                return false;
            
            return Objects.equals(s1, other.s1) && Objects.equals(s2, other.s2);
        }
    }
    
    static class Key3
    {
        private final String s1, s2, s3;
        
        Key3(String l1, String l2, String l3)
        {
            this.s1 = l1;
            this.s2 = l2;
            this.s3 = l3;
        }
        
        @Override
        public int hashCode()
        {
            int h$ = 1;
            h$ *= 1000003;
            h$ ^= (s1 == null ? 0 : s1.hashCode());
            h$ *= 1000003;
            h$ ^= (s2 == null ? 0 : s2.hashCode());
            h$ *= 1000003;
            h$ ^= (s3 == null ? 0 : s3.hashCode());
            return h$;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Key3 other))
                return false;
            
            return Objects.equals(s1, other.s1)
                    && Objects.equals(s2, other.s2)
                    && Objects.equals(s3, other.s3);
        }
    }
    
    static class Key4
    {
        private final String s1, s2, s3, s4;
        
        Key4(String l1, String l2, String l3, String l4)
        {
            this.s1 = l1;
            this.s2 = l2;
            this.s3 = l3;
            this.s4 = l4;
        }
        
        @Override
        public int hashCode()
        {
            int h$ = 1;
            h$ *= 1000003;
            h$ ^= (s1 == null ? 0 : s1.hashCode());
            h$ *= 1000003;
            h$ ^= (s2 == null ? 0 : s2.hashCode());
            h$ *= 1000003;
            h$ ^= (s3 == null ? 0 : s3.hashCode());
            h$ *= 1000003;
            h$ ^= (s4 == null ? 0 : s4.hashCode());
            return h$;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Key4 other))
                return false;
            
            return Objects.equals(s1, other.s1)
                    && Objects.equals(s2, other.s2)
                    && Objects.equals(s3, other.s3)
                    && Objects.equals(s4, other.s4);
        }
    }
    
    static class Key5
    {
        private final String s1, s2, s3, s4, s5;
        
        Key5(String l1, String l2, String l3, String l4, String l5)
        {
            this.s1 = l1;
            this.s2 = l2;
            this.s3 = l3;
            this.s4 = l4;
            this.s5 = l5;
        }
        
        @Override
        public int hashCode()
        {
            int h$ = 1;
            h$ *= 1000003;
            h$ ^= (s1 == null ? 0 : s1.hashCode());
            h$ *= 1000003;
            h$ ^= (s2 == null ? 0 : s2.hashCode());
            h$ *= 1000003;
            h$ ^= (s3 == null ? 0 : s3.hashCode());
            h$ *= 1000003;
            h$ ^= (s4 == null ? 0 : s4.hashCode());
            h$ *= 1000003;
            h$ ^= (s5 == null ? 0 : s5.hashCode());
            return h$;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (this == o)
                return true;
            if (!(o instanceof Key5 other))
                return false;
            
            return Objects.equals(s1, other.s1)
                    && Objects.equals(s2, other.s2)
                    && Objects.equals(s3, other.s3)
                    && Objects.equals(s4, other.s4)
                    && Objects.equals(s5, other.s5);
        }
    }
}
