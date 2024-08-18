package bmi;

public class Human {

	private String name;
	private int age;
	private float height;	//cm
	private float weight; //kg
	
	// BMI
	private float bmi;
	private float appropriateWeight; 
	private String status; 
	
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
		bmi = bmi;
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
			status = "thin2";
		}else if( bmi < 17.0 ) {
			status = "thin1";
		}else if( bmi < 18.5 ) {
			status = "thin0";
		}else if( bmi < 25.0 ) {
			status = "normal";
		}else if( bmi < 30.0 ) {
			status = "fat0";
		}else if( bmi < 35.0 ) {
			status = "fat1";
		}else if( bmi < 40.0 ) {
			status = "fat2";
		}else {
			status = "fat3";
		}
		
		return status;
	}
	
}
