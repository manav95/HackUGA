package com.example.manavdutta1.hackuga;
public class Champion{

	private String name;
	private double winRate;
	private double popularity;
	
	public Champion(String name, double winRate, double popularity){
		setName(name);
		setWinRate(winRate);
		setPopularity(popularity);
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setWinRate(double winRate){
		this.winRate = winRate;
	}
	public double getWinRate(){
		return winRate;
	}
	public void setPopularity(double popularity){
		this.popularity = popularity;	
		
	}
	public double getPopularity(){
		return popularity;	
	}
	
	public double calculateQuote(double wr, double gamesChampion, double gamesExpected, double gamesTotal, double popularityRate){
		//if the difference between the amount of games
		//played by the champion and total games
		double base = 20.0;
		double quote = 0.0;
		double gamesWithChampion = gamesExpected + gamesChampion;
		if(popularityRate < 20.0){
			return quote;	
		}
		
		if(gamesWithChampion < 100){
			return quote;
		}
		if(gamesTotal < 300){
			return quote;
		}
		double rateConstant = calculateConstant(wr);
		double championGamesConstant = calculateChampionConstant(gamesWithChampion);
		double totalGamesConstant = calculateTotalConstant(gamesTotal);
		
		quote = base * rateConstant + championGamesConstant + totalGamesConstant;
		return quote;
		
	}
	
	public double calculateConstant(double wr){
		double constantRate = 0.0;
	
		if(wr < 45.0){
			constantRate = .1;
		}
		else if(wr < 50.0 && wr>= 45.0){
			constantRate = .25;
		}
		else if(wr < 52.0 && wr >= 50.0){
			constantRate = .4;
		}
		else if(wr< 54.0 && wr>= 52.0){
			constantRate = .5;
		}
		else if (wr<59.0 && wr>= 54.0){
			constantRate = .8;
		}
		return constantRate;
	}
	
	public double calculateChampionConstant(double gamesWithChampion){
		double constantChampRate = 0.0;
	
		if(gamesWithChampion >= 100 && gamesWithChampion <200){
			constantChampRate = 1.0;
		}
		else if (gamesWithChampion >=200 && gamesWithChampion < 500){
			constantChampRate = 3.0;
		}
		else if (gamesWithChampion >= 500){
			constantChampRate = 10.0;
		
		}
		return constantChampRate;
	}
	
	public double calculateTotalConstant(double gamesTotal){
		double constantTotalRate = 0.0;
	
		if(gamesTotal >= 100 && gamesTotal <200){
			constantTotalRate = 1.0;
		}
		else if (gamesTotal >=200 && gamesTotal < 500){
			constantTotalRate = 3.0;
		}
		else if (gamesTotal >= 500){
			constantTotalRate = 10.0;
		
		}
		return constantTotalRate;
	}
}