package com.lyl.function;

import java.util.function.Supplier;

/**
 * Suppler 供给型接口  没有参数，只有返回值
 */
public class SupplierDemo {
    public static void main(String[] args) {
        // Supplier<String> objectSupplier = new Supplier<String>() {
        //     @Override
        //     public String get() {
        //         System.out.println("1024");
        //         return "1024";
        //     }

        Supplier<String> supplier = () -> {
            System.out.println("1024");
            return "1024";
        };
        supplier.get();
    }
}


