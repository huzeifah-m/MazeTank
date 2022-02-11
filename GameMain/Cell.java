package GameMain;
public class Cell {
	public byte[] walls = {1,1,1,1};
	Integer x;
	Integer y;

	/**
	 * This method checks if all 4 walls of each cell are up
	 * @return boolean
	 */
	public boolean checkWalls() {
		if(walls[0] == 1 && walls[1] == 1 && walls[2] == 1 && walls[3] == 1) {
			return true;
		} else {
			return false;
		}
	}

}