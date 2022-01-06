package org.songkun.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.function.Executable;

//import org.junit.runner.RunWith;


//@SpringBootTest(classes = DemoApplication.class)
//@RunWith(SpringRunner.class)
class DemoApplicationTests {
	private DemoApplication.Person person;

	/* 测试准备与销毁 */
	@BeforeEach
	public void init() {
		System.out.println("init person");
		person = new DemoApplication.Person("songkun", 34);
	}

	@AfterEach
	public void destroy() {
		System.out.println("Destory person");
		person = null;
	}

	@BeforeAll
	public static void initAll() {
		System.out.println("init all");
	}

	@AfterAll
	public static void destoryAll() {
		System.out.println("destory all");
	}

	@Disabled
	@Test
	void contextLoads1() {
		System.out.println("hi1");
		System.out.println(person);
	}

	/*
	* 条件测试
	* 执行TestClass上的绿色按钮可以执行Class下所有测试用例
	* Disabled是已经识别，但暂时不执行，与直接删除还是有区别的
	* */
	@Disabled
	@Test
	void contextLoads2() {
		System.out.println("hi2");
		System.out.println(person);
	}

	@EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true")
	@Test
	void testEnviromentVariable() {
		System.out.println("debug true");
	}

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void testOnOs() {
		System.out.println("OS WINDOWS");
	}

	@Test
	@EnabledOnJre(JRE.JAVA_8)
	void testJRE() {
		System.out.println("JAVA 8");
	}

	@Test
	@EnabledIfSystemProperty(named = "server.port", matches = "8000")
	void testOnSystemProperty() {
		System.out.println("server.port = 8000");
	}

	/* 异常测试 */
	@Disabled
	@Test
	void testFactorial() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				DemoApplication.Factorial.fact(-1);
			}
		});
	}
	/* 超过20 long类型会overflow得到一个负数
	static class Factorial {
		public static long fact(long n) {
			if (n < 0) {
				throw new IllegalArgumentException("argument is not negative");
			} else if(n > 20) {
				throw new ArithmeticException("over flow");
			}
			long r = 1;
			for (long i = 1; i <= n; i++) {
				r = r * i;
			}
			return r;
		}
	}*/
	@Disabled
	@Test
	void testFactorial2() {
		Assertions.assertThrows(ArithmeticException.class, () -> {
			DemoApplication.Factorial.fact(21);
		});
	}

	/* 参数测试
	@ParameterizedTest
	@ValueSource(ints = {11})
	void testParamater1(int i) {
		Assertions.assertEquals(i, DemoApplication.Factorial.fact(i));
	}

	@ParameterizedTest
	@MethodSource
	void testParamater2(String result, String input) {
		Assertions.assertEquals(result, DemoApplication.StringUtils.capitalize(input));
	}

	// MethodSource 支持方法名，同名可不写
	static List<Arguments> testParamater() {
		return Arrays.asList(
				Arguments.arguments("abc", "Abc"),
				Arguments.arguments("APPLE", "Apple"),
				Arguments.arguments("gooD", "Good"));
	}

	@ParameterizedTest
	@CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
	void testParamater3(String result, String input) {
		Assertions.assertEquals(result, DemoApplication.StringUtils.capitalize(input));
	}
	*/

}
