import java.util.ArrayList;
import java.util.Scanner;

public class Student{
	private String name;
	private int age;
	private String studentId;
	
	
	public Student(String name,int age,String studentId) {
		this.name=name;
		this.age=age;
		this.studentId=studentId;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getstudentId() {
		return studentId;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public void setstudentId(String studentId) {
		this.studentId=studentId;
	}
	
	public void displayStudent() {
		System.out.println("Student Id is : " + studentId + "Student Name is : " + name + "Student Age is : " + age);
	}
}



class StudentManager{
	private ArrayList<Student> students = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	
	
	public void addStudent() {
		System.out.print("Enter Student name :");
		String name=sc.nextLine();
		System.out.print("Enter Student age :");
		int age=sc.nextInt();
		System.out.print("Enter Student Id :");
		String studentId=sc.nextLine();
		students.add(new Student(name,age,studentId));
		System.out.println("Student added successfully\n");
	}
	
	public void updateStudent() {
		System.out.print("Enter Student Id to update:");
		String id=sc.nextLine();
		for(Student student:students) {
			if(student.getstudentId().equals(id)) {
				System.out.print("Enter new name :");
				student.setName(sc.nextLine());
				System.out.print("Enter new age :");
				student.setAge(sc.nextInt());
				sc.nextLine();
				System.out.println("Student details updated successfully\n");
				return;
			}
		}
		System.out.println("Student not found\n");
	}
	
	public void displayStudents() {
		if(students.isEmpty()) {
			System.out.println("No student records found.\n");
			return;
		}
		System.out.println("Student Records:");
		for(Student student:students) {
			student.displayStudent();
		}
		System.out.println();
	}
}


class StudentManagementSystem{
	public static void main(String args[]) {
		StudentManager manager=new StudentManager();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1. Add Student\n2. Update Student\n3. Display Students\n4. Exit ");
			System.out.print("Choose an option: ");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				manager.addStudent();
				break;
			case 2:
				manager.updateStudent();
				break;
			case 3:
				manager.displayStudents();
				break;
			case 4:
				System.out.println("Exiting.....");
				sc.close();
				return;
			default:
				System.out.println("Invalid choice. Try again.\n");
				
			}
		}
	}
}
    

