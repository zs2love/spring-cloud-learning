# Ribbon
* Expose the interface and call the clients which have been registered in Eureka.
```java
/** 
    @LoadBalanced to implement the load balance of calling the consumer.
    The controller is used to call the consumer registered in Eureka server. The load balance is auto implemented.
**/
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}

```


# Feign
* 包含Ribbon的功能，是一个声明式的Web Service客户端，它使得编写Web Serivce客户端变得更加简单。

* Declare an interface and name the annotation @FeignClient to the register name in the Eureka registration center.

```java
@FeignClient("compute-service")
public interface ComputeClient {
       @RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
```

# Circuit Breaker
在微服务架构中，存在着那么多的服务单元，若一个单元出现故障，就会因依赖关系形成故障蔓延，最终导致整个系统的瘫痪，这样的架构相较传统架构就更加的不稳定。为了解决这样的问题，因此产生了断路器模式。

## Netflix Hystrix
在Spring Cloud中使用了Hystrix 来实现断路器的功能。Hystrix是Netflix开源的微服务框架套件之一，该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备拥有回退机制和断路器功能的线程和信号隔离，请求缓存和请求打包，以及监控和配置等功能




转载自：http://blog.didispace.com/springcloud3/