package com.example.lru;

class LRU {

    private static final int N = 5;
    private Object[] mArray = new Object[N];
    private int mSize = 0;

    public LRU() {

    }

    public boolean isEmpty() {
        if (mSize == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOutOfBoundary() {
        if (mSize >= N) {
            return true;
        } else {
            return false;
        }
    }

    public int indexOfElement(Object o) {
        for (int i = 0; i < N; i++) {
            if (o == mArray[i]) {
                return i;
            }
        }
        return -1;
    }

    public Object push(Object o) {
        int target = indexOfElement(o);
        Object outObject = null;
        if (target == -1) {// no found same element
            if (!isOutOfBoundary()) {// and array not full
                mArray[mSize++] = o;// push it derectyly
            } else {// array is full
                outObject = mArray[0];// record the least recent used one
                for (int i = 0; i < mSize-1; i++) {// move data
                    mArray[i] = mArray[i+1];
                }
                mArray[mSize-1] = o;// push new one
            }
        } else {// same element found in array
            outObject = o;
            for (int i = target; i < mSize-1; i++) {
                mArray[i] = mArray[i+1];
            }
            mArray[mSize-1] = o;
        }

        return outObject;
    }

    public void dumpMemoryBlock() {
        for (int i = 0; i < mSize; i++) {
            System.out.print(mArray[i] + "\t");
        }
    }

    public int getMemorySize() {
        return mSize;
    }

    public static void main(String[] args) {
        Integer iter[] = {4, 7, 0, 7, 1, 0, 2, 1, 2, 6};
        LRU lru = new LRU();
        for (int i = 0; i < iter.length; i++) {
            lru.push(iter[i]);
            lru.dumpMemoryBlock();
            System.out.println();
        }
    }
}
