/**
 * 
 */
package com.example.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.demo.model.Person;

/**
 * @author shuai.b.zhang
 *
 */
public class StreamDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Construct a person object.
        Person person = new Person();
        person.setAge(11);
        person.setName("fuck you");

        Person person2 = new Person();
        person2.setAge(11);
        person2.setName("fuck you 2");

        Person person3 = new Person();
        person3.setAge(12);
        person3.setName("fuck you 3");

        // Add the person to Stream.
        Stream<Person> people = Stream.of(person, person2, person3);
        Stream<Person> people2 = Stream.of(person, person2, person3);

        // Convert the Stream to the list.
        List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        System.out.println(list.stream().count());

        // Get the average value of the list.
        Double avarage = list.stream().collect(Collectors.averagingInt(item -> item));
        System.out.println(avarage);

        // Filter the distinct values which are not null.
        List<Integer> nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
        nums.stream().filter(num -> num != null).distinct().forEach(e -> System.out.print(e + ", "));

        // Filter the values and add them to the list using collect function.
        Stream<Integer> stream = Stream.of(1, 2, 3, 4).filter(p -> p > 2);
        List<Integer> result = stream.collect(() -> new ArrayList<>(), (list2, item) -> list2.add(item),
                (one, two) -> one.addAll(two));

        // Add the Stream to a map using collect.
        Map<String, Integer> personMap = people.collect(HashMap::new, (map, p) -> map.put(p.getName(), p.getAge()),
                Map::putAll);

        System.out.println(personMap.toString());

        /**
         * Map<String, Integer> personMap2 = people .collect(Collectors.toMap(p ->
         * p.getName(), p -> p.getAge(), (exsit, newv) -> newv));
         * 
         **/

        // StringJoiner added in Java8.
        StringJoiner sj = new StringJoiner(":", "{", "}");
        sj.add("Fuck You!").add("Fuck You!").add("Fuck You!");

        System.out.println(sj);

        Map<String, Integer> sumAgeByName = people2.collect(Collectors.groupingBy(p -> p.getName(),
                Collectors.reducing(0, (Person p) -> p.getAge(), Integer::sum)));

        // DoubleSummaryStatistics dss =
        // people2.collect(Collectors.summarizingDouble((Person p) -> p.getAge()));
        System.out.println(sumAgeByName);

    }

}
