package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanImplementTwo implements MyBean{
    @Override
    public void print() {
        System.out.println("mostrar implementacion propia del segundo bean");
    }
}
