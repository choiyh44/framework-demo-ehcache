# framework-demo-ehcache
## ehcache 설정방법
- pom.xml 파일에 디펜던시를 추가한다.
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
  <groupId>org.ehcache</groupId>
  <artifactId>ehcache</artifactId>
  <version>3.8.1</version><!-- 2021/05/23 현재 최신 버전 -->
</dependency>
```
ehcache의 spring boot 2.5.1 기준 managed version은 3.9.4인데, 해당버전 적용시 오류나서 정상동작하지 않는다. 그래서 ehcache 홈페이지의 최신버전인 3.8.1을 적용했다.

- application.yml 파일에 ehcache 설정파일 경로를 지정한다.
```
spring:
  cache:
    jcache:
      config: classpath:ehcache.xml
```

- echcache.xml 설정파일을 작성한다.
```
<config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
	xmlns='http://www.ehcache.org/v3'
	xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">

	<cache-template name="myDefaults">
		<key-type>java.lang.Object</key-type>
		<value-type>java.lang.Object</value-type>
		<expiry>
			<ttl unit="seconds">60</ttl>
		</expiry>
		<resources>
			<heap unit="entries">3</heap>
			<offheap unit="MB">10</offheap>
		</resources>
	</cache-template>

	<cache alias="userService" uses-template="myDefaults">
<!-- 		<key-type>java.lang.String</key-type> -->
<!-- 		<value-type>kr.co.ensmart.frameworkdemo.common.dto.User</value-type> -->
	</cache>

	<cache alias="simpleCache" uses-template="myDefaults" />

</config>
```

- Application 파일이나 Config 파일에 @EnableCaching 지정하여 캐시 사용을 활성화한다.
```
@SpringBootApplication
@EnableCaching
public class FrameworkDemoEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkDemoEhcacheApplication.class, args);
	}
}
```

- 캐시를 적용할 서비스 메소드에 @Cacheable을 지정한다.
```
	@Cacheable(value="userService", key="#userName")
	public User findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}
```

- 그외 기능(annotation) 요약 - [spring 문서](https://docs.spring.io/spring-framework/docs/5.3.8/reference/html/integration.html#cache-jsr-107) 참조

|Spring	|JSR-107	|Remark|
|---|---|---|
|@Cacheable |@CacheResult |Fairly similar. @CacheResult can cache specific exceptions and force the execution of the method regardless of the content of the cache. |
|@CachePut |@CachePut |While Spring updates the cache with the result of the method invocation, JCache requires that it be passed it as an argument that is annotated with @CacheValue. Due to this difference, JCache allows updating the cache before or after the actual method invocation.|
|@CacheEvict |@CacheRemove |Fairly similar. @CacheRemove supports conditional eviction when the method invocation results in an exception. |
|@CacheEvict(allEntries=true) |@CacheRemoveAll |See @CacheRemove. |
|@CacheConfig |@CacheDefaults |Lets you configure the same concepts, in a similar fashion. |
