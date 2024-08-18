package bmi;

public class Executer {

	public static void main(String[] argv) {
		String name = "Osaka Taro";
		int age = 20;
		float height = 180.5f;
		float weight = 60.5f;
		Executer executer = new Executer();
		executer.run(name, age, height, weight);
	}
	
	public void run(String name, int age, float height, float weight) {
		Human human = new Human(name, age, height, weight);
		System.out.println(human.getBmi());
		System.out.println(human.getAppropriateWeight());
		System.out.println(human.getStatus());
	}
}
