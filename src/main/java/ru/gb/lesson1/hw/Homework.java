package ru.gb.lesson1.hw;

import ru.gb.lesson1.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

//import static ru.gb.lesson1.hw.Homework.Department.groupByDepartment;

public class Homework {

  static List<Department> departments = new ArrayList<>();
  static List<Person> persons = new ArrayList<>();

  public static void main(String[] args) {

    dataGenerator();

    departments.stream().forEach(it -> System.out.println(it));
    persons.stream().forEach(it -> System.out.println(it));

    // Проверка Department.countPersons()

    System.out.println("=".repeat(100));
    System.out.println("Persons, age > 50, salary > 40 000: " +
            Department.countPersons(persons, 50, 40_000) +
            " persons");

    // Проверка Department.averageSalary()

    System.out.println("=".repeat(50) + " averageSalary(persons)");
    System.out.println("Department # " + 2 + ", average salary is: " +
            Department.averageSalary(persons,2).getAsDouble());

    // Проверка groupByDepartment(persons)

    System.out.println("=".repeat(50) + " groupByDepartment(persons)");
    Map<Department, List<Person>> mapGroup = Department.groupByDepartment(persons);
    for (Map.Entry item: mapGroup.entrySet()) {
      System.out.println(((Department) item.getKey()).getName());
      List<Person> list = (List<Person>) item.getValue();
      for (int i = 0; i < list.size(); i++) {
        System.out.println("     " + list.get(i).getName());
      }
    }

    // Проверка maxSalaryByDepartment(persons)

    System.out.println("=".repeat(50) + " maxSalaryByDepartment(persons)");
    Map<Department, Double> maxSalary = Department.maxSalaryByDepartment(persons);

    for (Map.Entry item: maxSalary.entrySet()) {
      System.out.println(((Department) item.getKey()).getName() + ": " + item.getValue());
    }
  }

  /**
   * Используя классы Person и Department, реализовать методы ниже:
   */

  private static class Person {
    private String name;
    private int age;
    private double salary;
    private Department department;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public double getSalary() {
      return salary;
    }

    public void setSalary(double salary) {
      this.salary = salary;
    }

    public Department getDepartment() {
      return department;
    }

    public void setDepartment(Department department) {
      this.department = department;
    }

    @Override
    public String toString() {
      return
        "name='" + name + '\'' +
        ", age=" + age +
        ", salary=" + salary +
        ", department=" + department +
        '}';
    }



  }

  private static class Department {
    private String name;

    public Department(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "Department{" +
              "name='" + name + '\'' +
              '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Department that = (Department) o;
      return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }



    /**
     * Найти количество сотрудников, старше x лет с зарплатой больше, чем d
     */
    static int countPersons(List<Person> persons, int x, double d) {
      // TODO: Реализовать метод

      return ((int) persons.stream()
              .filter(it -> it.getAge() > x)
              .filter(it -> it.getSalary() > d)
              .count());
//      throw new UnsupportedOperationException();
    }

    /**
     * Найти среднюю зарплату сотрудников, которые работают в департаменте X
     */
    static OptionalDouble averageSalary(List<Person> persons, int x) {
      // TODO: Реализовать метод
      String xStr = String.valueOf(x);
      OptionalDouble average;
       average =  persons.stream()
              .filter(it -> it.getDepartment().getName().contains(xStr))
              .mapToDouble(Person::getSalary)
              .average();
      return average;

//      throw new UnsupportedOperationException();
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> persons) {
      // TODO: Реализовать метод
      return persons.stream()
              .collect(Collectors.groupingBy(Person::getDepartment));
//      throw new UnsupportedOperationException();

    }

    /**
     * Найти максимальные зарплаты по отделам
     */
    static Map<Department, Double> maxSalaryByDepartment(List<Person> persons) {
      // TODO: Реализовать метод
      return persons.stream()
              .collect(Collectors.toMap(Person::getDepartment, Person::getSalary, new BinaryOperator<Double>() {
                @Override
                public Double apply(Double salaryFirst, Double salarySecond) {
                  if (salaryFirst > salarySecond) {
                    return salaryFirst;
                  } else if (salaryFirst < salarySecond) {
                    return salarySecond;
                  }
                  return salaryFirst;
                }
              }));

//      throw new UnsupportedOperationException();
    }

    /**
     * ** Сгруппировать имена сотрудников по департаментам
     */
    static Map<Department, List<String>> groupPersonNamesByDepartment(List<Person> persons) {
      // TODO: Реализовать метод
      throw new UnsupportedOperationException();
    }

    /**
     * ** Найти сотрудников с минимальными зарплатами в своем отделе
     */
    static List<Person> minSalaryPersons(List<Person> persons) {
      // TODO: Реализовать метод
      // В каждом департаменте ищем сотрудника с минимальной зарплатой.
      // Всех таких сотрудников собираем в список и возвращаем из метода.
      throw new UnsupportedOperationException();
    }

  }

  static void dataGenerator() {
//    List<Department> departments = new ArrayList<>();
//    List<Person> persons = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      departments.add(new Department("Department #" + i));
    }

    for (int i = 1; i <= 15; i++) {
      int randomDepartmentIndex = ThreadLocalRandom.current().nextInt(departments.size());
      Department department = departments.get(randomDepartmentIndex);

      Person person = new Person();
      person.setName("Person #" + i);
      person.setAge(ThreadLocalRandom.current().nextInt(20, 65));
      person.setSalary(ThreadLocalRandom.current().nextInt(20_000, 100_000) * 1.0);
      person.setDepartment(department);

      persons.add(person);
    }

  }
}
