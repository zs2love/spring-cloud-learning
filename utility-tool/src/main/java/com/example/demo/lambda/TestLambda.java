package com.example.demo.lambda;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.example.demo.model.Person;

public class TestLambda {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person lambdaPerson = new Person();
		lambdaPerson.setAge(11);
		Person lambdaPerson2 = new Person();
		lambdaPerson2.setAge(12);

		List<Person> pList = Arrays.asList(lambdaPerson,lambdaPerson2);
		Person[] arrays = new Person[] {};
		Predicate<String> p = (String s) -> {
			return s == null;
		};
		Arrays.sort(arrays, (a, b) -> {
			return lambdaPerson.compareByAge(a.getAge(), b.getAge());
		});

		// Use lambda to demonstrate the instance.
		execute(() -> {
			return true;
		});

		System.out.println(p.test(null));


		// New way:
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		list.forEach(n -> System.out.print(n));

		evaluate(list, (n) -> n > 5);
		// or we can use :: double colon operator in Java 8
		list.forEach(System.out::println);

		/**
		 * This is equal to
		 * ```java
		 * Predicate<Integer> isKnownToo = param->list.contains(param);
		 * ```
		 **/

		Predicate<Integer> isKnown = list::contains;
		System.out.println(isKnown.test(2));

		Collections.sort(pList, new Comparator<Person>() {
			  public int compare(Person x, Person y) {
			    return x.getAge().compareTo(y.getAge());
			  }
			});
		Collections.sort(pList,
                (Person x, Person y) -> x.getAge().compareTo(y.getAge()));
		


		Collections.sort(pList, Comparator.comparing((Person pp) -> pp.getAge()));

		Collections.sort(pList, Comparator.comparing(Person::getAge).reversed());

		pList.forEach(pp->System.out.println(pp.getAge()));

		// Puting it together.
//		pList.sort(comparing(Person::getAge) );;

	}

	public static void execute(FunctionInterface worker) {
		worker.dosomething();
	}

	public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.println(n + " ");
			}
		}
	}

}
