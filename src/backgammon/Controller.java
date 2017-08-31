package backgammon;

import java.util.ArrayList;
public class Controller {
	private ArrayList<Checker> checkers = new ArrayList<>();
	private int[][] startPositionBlack = new Checker("",1,1).getStartPositionsBlack();
	private int[][] startPositionWhite = new Checker("",1,1).getStartPositionsWhite();

	public Controller(){
		DistributeCheckers();
	}
	
	public void DistributeCheckers(){
		for(int i = 0 ; i < 15; i++){
			checkers.add(new Checker("/img/brown_checker.jpg",startPositionBlack[i][0], startPositionBlack[i][1]));
			System.out.println(startPositionBlack[i][0]+ "   "   +  startPositionBlack[i][1]);
		}
		for(int i = 0 ; i < 15; i++){
			checkers.add(new Checker("/img/gray_checker.jpg",startPositionWhite[i][0], startPositionWhite[i][1]));
			System.out.println(startPositionWhite[i][0]+ "   "   +  startPositionWhite[i][1]);
		}
	}
	
	public ArrayList<Checker> getCheckers() {
		return checkers;
	}
	
	
}
