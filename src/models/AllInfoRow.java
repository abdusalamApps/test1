package models;

public class AllInfoRow {
	
	public String name;
	public int answer00, answer01,
				answer11, answer12, answer13, answer14;
	
	public AllInfoRow(String name, int answer00, int answer01,
									int answer11,int answer12, int answer13, int answer14) {
		
		this.name = name;
		this.answer00 = answer00;
		this.answer01 = answer01;
		this.answer11 = answer11;
		this.answer12 = answer12;
		this.answer13 = answer13;
		this.answer14 = answer14;
	}

}
