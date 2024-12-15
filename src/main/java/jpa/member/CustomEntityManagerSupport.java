package jpa.member;

public interface CustomEntityManagerSupport<T, ID> {

    void flushAndClear();

    void clear();

}
