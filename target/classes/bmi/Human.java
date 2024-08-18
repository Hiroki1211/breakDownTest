package bmi;

public class Human {

	// 基本情報
	private String name;
	private int age;
	private float height;	//cm
	private float weight; //kg
	
	// BMI
	private float bmi;
	private float appropriateWeight; 
	private String status; // 1. 痩せすぎ, 2. 痩せ, 3. 痩せぎみ, 4. 普通体重, 5. 前肥満, 6. 肥満（１度）, 7. 肥満（２度）, 8. 肥満（３度）
	
	public Human(String n, int a, float h, float w) {
		name = n;
		age = a;
		height = h;
		weight = w;
		bmi = this.calculateBMI();
		appropriateWeight = this.calculateAppropriateWeight();
		status = this.calculateStatus();
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public float getBmi() {
		return bmi;
	}
	
	public float getAppropriateWeight() {
		return appropriateWeight;
	}
	
	public String getStatus() {
		return status;
	}
	
	private float calculateBMI() {
		return weight / height / height * 10000;
	}
	
	private float calculateAppropriateWeight() {
		return height * height * 22 / 10000; 
	}
	
	private String calculateStatus() {
		String status;
		
		if( bmi < 16.0 ) {
			status = "痩せすぎ";
		}else if( bmi < 17.0 ) {
			status = "痩せ";
		}else if( bmi < 18.5 ) {
			status = "痩せぎみ";
		}else if( bmi < 25.0 ) {
			status = "普通体重";
		}else if( bmi < 30.0 ) {
			status = "前肥満";
		}else if( bmi < 35.0 ) {
			status = "肥満（１度）";
		}else if( bmi < 40.0 ) {
			status = "肥満（２度）";
		}else {
			status = "肥満（３度）";
		}
		
		return status;
	}
	
}
