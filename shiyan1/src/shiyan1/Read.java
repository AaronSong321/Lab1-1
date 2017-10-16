package shiyan1;

import java.io.*;
import java.util.regex.*;
import java.util.Scanner;

public class Read {
	public static void main(String[] args) throws IOException {
		show();
		/*
		 * String str = new String(); try { Reader reader = new
		 * FileReader("input_text.txt"); int ch = reader.read(); StringBuffer
		 * buffer = new StringBuffer(); while (ch != -1) { // ��ȡ�ɹ�
		 * buffer.append((char) ch); ch = reader.read(); } str =
		 * change(buffer.toString()); str = filter(str);
		 * System.out.println(str);
		 * 
		 * Picture myPicture = new Picture(str); myPicture.showPicture();
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("�������ŽӴʵ��������:"); String s1 = scanner.nextLine();
		 * String s2 = scanner.nextLine();
		 * 
		 * myPicture.Bridge(s1,s2); System.out.println("����������ŽӴ������ı��ĳ�ʼ�ı�:");
		 * String s3 = scanner.nextLine(); myPicture.newtext(s3);
		 * myPicture.emptyQueue(); System.out.println("�������ѯ���·������������:"); s1 =
		 * scanner.nextLine(); s2 = scanner.nextLine(); myPicture.shortroad(s1,
		 * s2); //if (myPicture.calcShortestPath(s1, s2) &&
		 * myPicture.showShortestPath(s1, s2) != 0)
		 * //System.out.println("���·��������" + myPicture.minlen); String s4 =
		 * myPicture.randomWalk(); System.out.println("������߽��:" + s4);
		 * write(s4); reader.close(); scanner.close(); } catch
		 * (FileNotFoundException e) { System.out.println("Ҫ��ȡ���ļ������ڣ�" +
		 * e.getMessage()); } catch (IOException e) {
		 * System.out.println("�ļ���ȡ����" + e.getMessage()); }
		 */
	}

	@SuppressWarnings("static-access")
	public static void show() {
		String str = new String();
		GraphViz myGraph = new GraphViz();
		try {
			Reader reader = new FileReader("input_text.txt");
			int ch = reader.read();
			StringBuffer buffer = new StringBuffer();
			while (ch != -1) { // ��ȡ�ɹ�
				buffer.append((char) ch);
				ch = reader.read();
			}
			str = change(buffer.toString());
			str = filter(str);
			System.out.println(str);

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ҫ��ȡ���ļ������ڣ�" + e.getMessage());
		} catch (IOException e) {
			System.out.println("�ļ���ȡ����" + e.getMessage());
		}
		Scanner scanner = new Scanner(System.in);
		String s, s1, s2, s3, s4;
		while (true) {
			System.out.println("��������:\n1.�˳�\n2.����ͼ\n3.չʾͼ\n4.��ѯ�ŽӴ�\n5.�������ı�\n6.��ѯ���·��\n7.��ѯһ�����ʵ������������·��\n8.�������");
			System.out.println("�����빦�����:");
			s = scanner.nextLine();
			Picture myPicture = new Picture(str);
			switch (s) {
			case "1":
				System.out.println("�˳��ɹ�!");
				scanner.close();
				return;
			case "2": {
				// System.out.println(myPicture.graph());
				myGraph.createDotGraph(myPicture.graph(), "Graph1");
				System.out.println("���ɳɹ�!");
				break;
			}
			case "3": {
				myPicture.showPicture();
				break;
			}
			case "4": {
				System.out.println("�������ŽӴʵ��������:");
				s1 = scanner.nextLine();
				s2 = scanner.nextLine();
				myPicture.Bridge(s1, s2);
				break;
			}
			case "5": {
				System.out.println("����������ŽӴ������ı��ĳ�ʼ�ı�:");
				s3 = scanner.nextLine();
				s3 = change(s3);
				s3 = filter(s3);
				myPicture.newtext(s3);
				break;
			}

			case "6": {
				System.out.println("�������ѯ���·������������:");
				s1 = scanner.nextLine();
				s2 = scanner.nextLine();
				myPicture.shortroad(s1, s2);
				System.out.println("�Ƿ�����ͼ��Y/N");
				s2 = scanner.nextLine();
				if (s2.equals("Y")) {
					myGraph.createDotGraph(myPicture.shortRoadGraph(), "Graph2");
					System.out.println("ͼ�����ɳɹ�!");
				}
				break;
			}
			case "7": {
				System.out.println("�������ѯ���·����һ������:");
				s1 = scanner.nextLine();
				myPicture.shortroad2(s1);
				break;
			}
			case "8": {
				System.out.println("��������ʼλ��:");
				s3 = scanner.nextLine();
				s4 = myPicture.randomWalk(s3);
				System.out.println("������߽��:" + s4);
				System.out.println("�Ƿ�д���ļ���Y/N");
				s2 = scanner.nextLine();
				if (s2.equals("Y"))
					write(s4);
				System.out.println("�Ƿ�����ͼ��Y/N");
				s2 = scanner.nextLine();
				if (s2.equals("Y"))
					myGraph.createDotGraph(myPicture.randomGraph(s4.split("\\s+")), "Graph3");
				break;
			}
			default:
				System.out.println("�������������ַ�!");
			}
		}
	}

	public static String change(String s) {
		// �滻����ĸ�ַ�Ϊ�ո�
		String REGEX = "[^A-Za-z]";
		String REPLACE = " ";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(s);
		s = m.replaceAll(REPLACE);
		// ��дת��Сд
		String Lowstr = s.toLowerCase();
		return Lowstr;
	}

	public static String filter(String s) {
		// ɾ������Ŀո�
		String REGEX = "  ";
		String REPLACE = " ";
		Pattern p = Pattern.compile(REGEX);
		while (true) {
			Matcher m = p.matcher(s);
			if (!m.find())
				break;
			s = m.replaceAll(REPLACE);
		}
		return s;
	}

	public static void write(String s) {
		try {
			File file = new File("output_text.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(s);
			bufferWritter.close();
			System.out.println("д��ɹ�!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}