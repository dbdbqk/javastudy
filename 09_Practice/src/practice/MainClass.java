package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainClass {

	// 문제1. 현재 시간을 이용하여 디렉터리를 생성하시오.
	// 예시)
	// C:\14\31\01 라는 디렉터리 생성
	public static void ex01() {
		LocalTime now = LocalTime.now();
		
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		
		String sep = File.separator;
		File dir = new File("C:" + sep + hour + sep + minute + sep  + second);
		if(dir.exists() == false) {
			dir.mkdirs();
			System.out.println("C:" + sep + hour + sep + minute + sep  + second + " 파일 생성 완료");
		}
		
	}
	// 문제2. C:\Program Files\Java\jdk-11.0.17 경로의 파일 목록을 아래와 같이 출력하시오.
		/*
		2023-01-04  오후 02:19    <DIR>          bin
		2023-01-04  오후 02:19    <DIR>          conf
		2023-01-04  오후 02:19    <DIR>          include
		2023-01-04  오후 02:19    <DIR>          jmods
		2023-01-04  오후 02:19    <DIR>          legal
		2023-01-04  오후 02:19    <DIR>          lib
		2023-01-04  오후 02:19               160 README.html
		2023-01-04  오후 02:19             1,302 release
		               2개 파일               1,462 바이트
		*/
	public static void ex02() {
		File dir = new File("C:" + File.separator + "Program Files" + File.separator + "Java" + File.separator + "jdk-11.0.17");
		if(dir.exists()) {
			int fileCount = 0;
			long totalFileSize = 0;
			File[] list = dir.listFiles();
			for(File file : list) {
				if(file.isHidden()) {
					continue;
				}
				String lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd  a hh:mm").format(file.lastModified());
				String direcctory = file.isDirectory() ? "<DIR>" : "";
				String size = "";
				if(file.isFile()) {
					long length = file.length();
					size = new DecimalFormat("#,##0").format(length);
					fileCount++;
					totalFileSize += length;
					
				}
				String name = file.getName();
				String result = String.format("%s%9s%9s %s\n", lastModifiedDate, direcctory, size, name);
				System.out.print(result);
			}
			System.out.println(fileCount + "개 파일 " + new DecimalFormat("#,##0").format(totalFileSize) + "바이트");
		}
			
		/*File[] files = dir.listFiles();
		int total = 0;
		long fileTotal = 0;
		for(int i = 0; i < files.length; i++) {
			long lastModified = files[i].lastModified();
			String lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd  a  HH:mm").format(lastModified);
			if(files[i].isDirectory()) {
				System.out.println(lastModifiedDate + " <DIR> " + files[i].getName());
			}else {System.out.println(lastModifiedDate + " <DIR> " + files[i].length() + " " + files[i].getName());
			total++;
			fileTotal += files[i].length();
			}
		}
		System.out.println(total + "개 파일 " + fileTotal + " 바이트");
		*/
		
	}
	
	// 문제3. C:\storage 디렉터리를 삭제하시오.
	// 파일이 저장된 디렉터리는 지워지지 않으므로 먼저 디렉터리에 저장된 파일을 삭제해야 한다.
	
	public static void ex03() {
		
		File dir = new File("C:" + File.separator + "storage");
		
		File file = new File(dir, "myfile.txt");
		if(file.exists()) {
			file.delete();
		}
		
		if(dir.exists()) {
			dir.delete();
		}
		
	}
	
	
	// 문제4. 사용자로부터 입력 받은 문자열을 C:\storage\diary.txt 파일로 보내시오.
	// 총 5개 문장을 입력 받아서 보내시오.
	public static void ex04() {
		String[] sentences = new String[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("5문장을 입력하세요.");
		File dir = new File("C:" + File.separator + "storage");
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		File file = new File(dir, "diary.txt");
		
			for(int i = 0; i < sentences.length; i++) {
			sentences[i] = sc.nextLine();
			}
		try (PrintWriter out = new PrintWriter(file)){
			for(int i = 0; i < sentences.length; i++) {
				out.println(sentences[i]);
			}
			System.out.println("diary.txt 파일이 생성 되었다.");
		}catch(IOException e) {
			e.printStackTrace();
		}
		sc.close();
		
	}
	
	// 문제5. 예외가 발생한 경우 예외 메시지와 예외 발생시간을 저장한 C:\storage\log.txt
	// 2023-01-26 
	public static void ex05() {
	
		try{
		Scanner sc = new Scanner(System.in);
			
		System.out.print("첫 번째 정수를 입력하세요 >>>>>");
		int number1 = sc.nextInt();
			
		System.out.print("두 번째 정수를 입력하세요 >>>>>");
		int number2 = sc.nextInt();
		
		int add = number1 + number2;
		int sub = number1 - number2;
		int mul = number1 * number2;
		int div = number1 / number2;
	
		System.out.println(number1 + "+" + number2 + "=" + add);
		System.out.println(number1 + "-" + number2 + "=" + sub);
		System.out.println(number1 + "*" + number2 + "=" + mul);
		System.out.println(number1 + "/" + number2 + "=" + div);
		sc.close();
		
		}catch(Exception e) {
			// 날짜
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String dateTime = dtf.format(now);
			
			// 예외 클래스 이름
			String className = e.getClass().getName();
			
			// 예외 메시지
			String message = e.getMessage();
			
			// 로그 파일 만들기
			File dir = new File("C:" + File.separator + "storage");
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			File file = new File(dir, "log.txt");
			
			// 생성 모드(언제나 새로 만든다.) new FileWriter(file)
			// 추가 모드(기존 내용에 추가한다.) new FileWriter(file, true)
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
				
				bw.write(dateTime + " " + className + " " + message + "\n");
				 // bw.newLine();	\n을 대신 할 수 있는 코드 , 버퍼드라이터에서만 지원
				
				System.out.println("예외 메시지가 log.txt 파일에 기록 되었습니다.");
				
			}catch(IOException e2) {
				e2.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	// 문제6. C:\storage\diary.txt 파일을 C:\diary.txt 파일로 이동하시오.
	// 이동에 소요된 시간을 출력하시오.
	public static void ex06() {
		File file = new File("C:" + File.separator + "storage", "diary.txt");
		File dir = new File("C:" + File.separator + "storage2");
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		File file2 = new File(dir, "diary.txt");
		String line = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		long startTime = 0;
		long doneTime = 0;
		try {
			startTime = System.currentTimeMillis();
			br = new BufferedReader(new FileReader(file));
			bw = new BufferedWriter(new FileWriter(file2));
			while((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}doneTime = System.currentTimeMillis();
		}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(br != null) {br.close();}
					if(bw != null) {bw.close();}
				}
			catch(IOException e) {
				e.printStackTrace();
			}
					}
			if(file.length() == file2.length()) {	//	복사 성공 삭제 (데이터의 크기 비교)
				file.deleteOnExit();
			}
			System.out.println("이동에 걸린 시간" + (doneTime - startTime) + "밀리초");
		}
		
		
	
		// 	문제7. sysout.in은 키보드로부터 바이트 데이터를 입력 받는 InputStream이다.
		//	System.in으로부터 문장 1개를 입력 받아서 출력 하시오.
		// Scanner 대신 BufferedReader를 사용하시오.
			
		public static void ex07() {

			BufferedReader br = null;
			
			try {
				
				br = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("문장입력 >>> ");
				String sentence = br.readLine();
				
				System.out.println("입력된 문장 : " + sentence);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(br != null) {
						br.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	
		
		//	문제8. C:\GDJ\installer\eclipse-jee-2021-03-R-win32-x86_64.zip 파일을
		//	C:\storage\eclipse.zip으로 복사하시오.
		
		public static void ex08() {	//	복사 프로그램
			File to = new File("C:" + File.separator + "storage", "eclipse.zip");
			File from = new File("C:" + File.separator + "GDJ61" + File.separator + "installer", "eclipse-jee-2021-03-R-win32-x86_64.zip");
			
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			
			try {
				bin = new BufferedInputStream(new FileInputStream(from));
				bout = new BufferedOutputStream(new FileOutputStream(to));
				
				byte[] b = new byte[1024];	//	1킬로바이트
				int readByte = 0;
				while((readByte = bin.read(b)) != -1) {
					bout.write(b, 0, readByte);
				}
				
				System.out.println("복사 완료 되었습니다,");
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(bout != null) { bout.close();}
					if(bin != null) { bin.close();}
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
				
		}
		
	
	public static void main(String[] args) {
		ex08();
	}

}
