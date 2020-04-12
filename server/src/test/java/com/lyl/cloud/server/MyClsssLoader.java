package com.lyl.cloud.server;

import java.io.*;

/**
 * MyClassLoader
 *
 * @author liyl
 * @date 2019-12-5
 */

public class MyClsssLoader extends ClassLoader {

    // 类加载器名字
    private String name;

    // 加载类路径
    private String path = "D:\\";

    // class文件扩展名
    private final String fielTye = ".class";

    public MyClsssLoader(String name) {

        // 让系统加载器成为该类父加载器
        super();
        this.name = name;
    }

    public MyClsssLoader(ClassLoader parent, String name) {

        // 显示制定该类加载器的父加载器
        super(parent);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFielTye() {
        return fielTye;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = this.loadClassData(name);
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {

        InputStream inputStream = null;
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = null;
        try {
            this.name = this.name.replace(".", "\\");
            inputStream = new FileInputStream(new File(path + name + fielTye));
            outputStream = new ByteArrayOutputStream();
            int index = 0;
            while (-1 != (index = inputStream.read())) {
                outputStream.write(index);
            }
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static void main(String[] args) {
        MyClsssLoader loader1 = new MyClsssLoader("loader1");
        MyClsssLoader loader2 = new MyClsssLoader(loader1, "loader2");
        MyClsssLoader loader3 = new MyClsssLoader(null, "loader1");
    }
}
