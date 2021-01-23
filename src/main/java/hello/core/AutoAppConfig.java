package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // ComponentScan => @Component 가 붙은 클래스를 찾아서 자동으로 스프링빈으로 등록해준다.
    // basePackages에 작성된 패키지를 포함해서 하위 패키지를 모두 탐색한다. (ex: basePAckages = "hello.core.member")
    // basePackagesClasses = AutoAppConfig.class
    // basePackagesClasses 를 정의하지않으면 ComponentScan에 지정해준 클래스의 패키지를 탐색 시작 위로 지정한다.
    // 권장하는 방법 : 패키지 위치를 지정하지않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
    // 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 떄문에 프로젝트 시작 루트 위치에 두는 것이 좋다고 생각. (ex @SpringBootApplication)
}
