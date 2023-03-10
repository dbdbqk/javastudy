package ex03_singleton;  // 객체를 하나만 만들 수 있음    싱글 톤 만들때는 보통 메소드명을 getInstance라 만든다

public class User {

	// static 필드 (미리 User를 만들어 둔다.)
	private static User user = new User();
	
	// private 생성자 (외부에서는 생성자를 호출할 수 없다.)
	private User() {
		
	}
	
	// static 메소드(static 필드를 사용하기 위해서)
	// user 필드를 외부에서 사용할 수 있도록 반환하는 일종의 Getter
	public static User getInstance() {
		return user;
	}
	
	
}
