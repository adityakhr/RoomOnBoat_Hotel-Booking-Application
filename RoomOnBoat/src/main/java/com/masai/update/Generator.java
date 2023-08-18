//package com.masai.update;
//
//import java.io.Serializable;
//import java.util.concurrent.atomic.AtomicLong;
//
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//
//public class Generator implements IdentifierGenerator {
//    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) {
//        long timestamp = System.currentTimeMillis();
//        long sequence = counter.incrementAndGet();
//        return timestamp * 100000 + sequence; // Combine timestamp and sequence
//    }
//}
