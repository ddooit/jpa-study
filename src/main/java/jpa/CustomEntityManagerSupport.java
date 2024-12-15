package jpa;

public interface CustomEntityManagerSupport<T, ID> {

    void flushAndClear();

    void clear();

}
