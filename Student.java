package HDT;
import java.util.Scanner;
public class Student {
		private int id;
		private String name;
		private static String college = "HUFI";
		
		public int getID() {
			return id;
			
		}
		public void setID(int id) {
			this.id=id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name=name;
		}
		public static String getCollege() {
			return college;
		}
		public static void setCollege(String college) {
			Student.college=college;
		}
//Hàm khởi tạo không tham số
		public Student()
		{
			id=0; name="Nguyen Van An";
		}
//Hàm khởi tạo có tham số
		public Student(int id,String name) {
			this.id=id;
			this.name=name;
		}
//Hàm khởi tạo sao chép
		public Student(Student s) {
			id=s.id;
			name=s.name;
		}
		public void nhap()
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Moi nhap ID:");
			id=sc.nextInt();
			System.out.println("Moi nhap ho ten:");
			name= sc.nextLine();
		}
		public void xuat() {
			System.out.println("id:" + id + "-" + "name:" + name + "college:" + college);
		}
		public static void thayDoiTruong(String college)
		{
			Student.college=college;
		}
		public static void main(String args[]){

			System.out.println(Student.getCollege());
			Student a = new Student();
			a.xuat();
			Student.setCollege("CNTP");
			System.out.println(Student.getCollege());
			Student b = new Student();
			System.out.println(Student.getCollege());
		}
}




