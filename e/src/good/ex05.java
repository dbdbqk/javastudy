package good;

public class ex05 {

	public static void main(String[] args) {
		
		for(int dan = 2; dan <= 5; dan++) {
			for(int n = 1; n <= 9; n++) {
				System.out.println(dan + " X " + n + " = " + dan * n);
				if (dan == 5 && n == 5) {
					break;
				}
				
				}
			
			}
		}
		
	}

