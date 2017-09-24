## This project to learn and understand Spring Cloud, all the material is getting from http://blog.didispace.com/springcloud3/, thanks for the great material for learning.

# Ribbon
Expose the interface and call the clients which have been registered in Eureka.
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

**hystrix默认是关闭状态，必须在application中配置feign.hystrix.enabled=true才能正常使用，否则断路器不生效**


# Config Server
1. 具体过程就是在git或者其他类似的地方创建一个项目，然后负责存放属性文件，文件的命名是以{application}-{profile}.properties这种形式。

2. 创建一个Config Server去读取这个在git上的项目，通过配置。
spring.cloud.config.server.git.uri=https://github.com/zs2love/spring-cloud-learning/
spring.cloud.config.server.git.searchPaths=config-repo

3. 将Config Server加入到Spring Cloud中，在server中注册，这样就可以被其他的用这个properties的程序所使用。

4. 在其他的注册在同意server中的程序，只需要指定application, profile还有label (branch)就可以使用相应的属性了。
```java
spring.application.name=app
spring.cloud.config.profile=dev
spring.cloud.config.label=master
```

# Zuul

## Zuul 配置
加入Zuul的POM引用spring-cloud-starter-zuul，接着加入路由来进行转发配置。

```
# routes to service id
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=service-A
zuul.routes.api-b.path=/api-b/**
zuul.routes.api-b.serviceId=service-B
eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka/
```

这样就可以通过相应的url进行相应service的调用了。

## Zuul Filter
我们可以利用Zuul对于路由进行过滤，只需要集成Zuul Filter，实现四个方法：

``` java
public class AccessFilter extends ZuulFilter  {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
```

1. filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
* pre：可以在请求被路由之前调用
* routing：在路由请求时候被调用
* post：在routing和error过滤器之后被调用
* error：处理请求时发生错误时被调用
2. filterOrder：通过int值来定义过滤器的执行顺序
* shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
3. run：过滤器的具体逻辑。
* 需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。


# 转载自：http://blog.didispace.com/springcloud3/