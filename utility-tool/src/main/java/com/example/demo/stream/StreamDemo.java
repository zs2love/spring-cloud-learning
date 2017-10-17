/**
 * 
 */
package com.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shuai.b.zhang
 *
 */
public class StreamDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntStream.range(0, 9).forEach(e -> {
			System.out.println(e);
			System.out.println("fuck+" + e);
		});

		List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
		System.out.println(list.stream().count());
		Double avarage = list.stream().collect(Collectors.averagingInt(item -> item));

		List<Integer> nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
		nums.stream().filter(num -> num != null).distinct().forEach(System.out::println);

	}

}
