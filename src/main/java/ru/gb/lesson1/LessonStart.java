package ru.gb.lesson1;

public class LessonStart {
    public static void main(String[] args) {

        /* Анонимный класс. По сути тот же MyRunnable без его объявления
         * Создан объект Runnable, но на самом деле у него есть анонимный класс
         */

        Runnable object = new Runnable() {
            @Override
        public void run() {

        }
        };
        object.run();
    }


//    static class MyRunnable implements Runnable {
//        @Override
//        public void run() {
//
//        }
//    }

    interface Runnable {
        void run();
    }
}
