package oz.code.test;

public class Test
{

	public static void main(String[] args)
	{

		Student s = new Student();

		Teacher t = new Teacher();

		Proffesion p = new Proffesion();

		s = t = p;
		
		s.showInfo();

		t.showInfo();

		p.showInfo();

	}

}

class Person
{

	private String	name;

	protected int	age = 3;

	public char		sex = 'Ů';

	int				score = 60;

}

class Student extends Person
{

	public void showInfo()
	{
		int age = this.age;

		int score = this.score;

		int sex = this.sex;

		System.out.println(age + "  " + score + "  " + score);

	}

}

class Teacher extends Student
{

	public void showInfo()
	{
		int age = this.age;

		int score = this.score;

		int sex = this.sex;

		System.out.println(age + "  " + score + "  " + score);

	}

}


class Proffesion extends Teacher
{

	public void showInfo()
	{
		int age = this.age;

		int score = this.score;

		int sex = this.sex;

		System.out.println(age + "  " + score + "  " + score);
	}

}
