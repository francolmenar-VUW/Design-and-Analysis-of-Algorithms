package part1;

import javax.swing.SwingUtilities;

import part0.ChessBoard;
import part0.Controller;

public class Main {

	 public static void main(String[] args) {
		 Model model = new Model();//I create the model
		 Controller controller = new Controller(model);//I create the controller
		 SwingUtilities.invokeLater(()->new ChessBoard(model, controller));
	    }
}
