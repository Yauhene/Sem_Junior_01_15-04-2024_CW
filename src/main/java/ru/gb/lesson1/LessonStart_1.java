package ru.gb.lesson1;

public class LessonStart_1 {
    public static void main(String[] args) {

        Runnable object = () -> {
            System.out.println("Hello, bitch!");
        };

        /* Анонимный класс. По сути тот же MyRunnable без его объявления
         * Создан объект Runnable, но на самом деле у него есть анонимный класс
         */

//        Runnable object = new Runnable() {
//            @Override
//        public void run() {
//                System.out.println("Hello, bitch!");
//        }
//        };
        object.run();
        System.out.println(object.getClass().getName()); // ru.gb.lesson1.LessonStart$1 - нет явно указанного имени
                        // т.к. класс анонимный
    }
static class HelloWorldPrinter implements  Runnable {
    @Override
    public void run() {
        System.out.println("Hello, bitch!");
    }
}

//    static class MyRunnable implements Runnable {
//        @Override
//        public void run() {
//
//        }
//    }

    @FunctionalInterface // -- необязательная строка, она просто является маркером, напоминающим,
                          // что данный интерфейс является функциональным (имеет только один метод)
    interface Runnable {
        void run();
    }
}
