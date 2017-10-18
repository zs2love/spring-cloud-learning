/**
 * 
 */
package com.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

		List<Integer> nums2 = Arrays.asList(1, 2, 3, null, null, 3, 4, 10);
		List<Integer> distinctList = nums2.stream().filter(num -> num != null).distinct().collect(Collectors.toList());
		System.out.println("Distinct list contains: ");
		distinctList.forEach(e->System.out.print(e + ","));
		
		int value = Stream.of(1,2,3,4).reduce(100, (sum, ele) -> sum - ele);
		Optional<Integer> value2 = Stream.of(1,2,3,4).reduce((sum, ele) -> sum-ele);
		System.out.println(value );
		System.out.println(value2 );
	}

}
