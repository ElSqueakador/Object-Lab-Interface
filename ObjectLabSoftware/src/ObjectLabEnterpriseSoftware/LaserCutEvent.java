/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectLabEnterpriseSoftware;



/**
 * object for managing and logging a OLI ULS print event 
 * @author Sam Scheiner
 * @version 0.1
 * @since 2/11/17
 *
 */
public final class LaserCutEvent extends AbstractEvent{

	//laser cutter extends AbstractEvent with the following data
	private double materialThickness;
	private String material;
	private int hours, minutes, seconds;
	
	
	
	public LaserCutEvent(int IDnumber, String studentName, String monitorName, String date, String course, double materialThickness, String material, int hours, int minutes, int seconds){
							//laser cutter specific 

		
			//call supercontructor for abstract object fields 
			super(studentName, monitorName, date, course, IDnumber);
			
			//lasercut specific fields
			this.materialThickness = materialThickness;
			this.material = material;
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
				
	}



	public double getMaterialThickness() {
		return materialThickness;
	}



	public void setMaterialThickness(double materialThickness) {
		this.materialThickness = materialThickness;
	}



	public String getMaterial() {
		return material;
	}



	public void setMaterial(String material) {
		this.material = material;
	}



	public int getHours() {
		return hours;
	}



	public void setHours(int hours) {
		this.hours = hours;
	}



	public int getMinutes() {
		return minutes;
	}



	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}



	public int getSeconds() {
		return seconds;
	}



	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	
}
