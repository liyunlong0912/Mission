package com.lyl.cas;

/**
 *
 */
public class CasDemo {


    public static void main(String[] args) {

        Integer integer = 128;
        Integer integer1 = 128;

        // 如果泛型是包装类,注意对象的引用类型
       /* AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(10, 1);

        new Thread(() -> {
            // 获得版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println("A1=====>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            atomicStampedReference.compareAndSet(10, 12, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            System.out.println("A2=====>" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(12, 10, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            System.out.println("A3=====>" + atomicStampedReference.getStamp());
        }, "A").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("B1=====>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            atomicStampedReference.compareAndSet(10, 16, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            System.out.println("B2=====>" + atomicStampedReference.getStamp());
        }, "B").start();*/
        System.out.println(integer.equals(integer1));
    }


}
