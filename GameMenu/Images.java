package GameMenu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images 
{
	
	public static Node backimage() throws IOException{
		Image mback = new Image(Files.newInputStream(Paths.get("resources/images/menu0.png")));
		ImageView iView = new ImageView(mback);
		iView.setFitWidth(GameMenu.width);
		iView.setFitHeight(GameMenu.height);
		return iView;
		
	}	
	
	
	public static Node image1() throws IOException
	{
		InputStream c1 = Files.newInputStream(Paths.get("resources/images/cont1.png"));
		Image control1 = new Image(c1);
		ImageView control = new ImageView(control1);
		control.setFitWidth(100);
		control.setFitHeight(100);
		return control;
	}
	
	public static Node image2() throws IOException
	{
		InputStream c2 = Files.newInputStream(Paths.get("resources/images/arrow.png"));
		Image arrow = new Image(c2);
		ImageView ctrl = new ImageView(arrow);
		ctrl.setFitWidth(100);
		ctrl.setFitHeight(100);
		return ctrl;
	}
	
	
	public static Node image3() throws IOException
	{
		InputStream c3 = Files.newInputStream(Paths.get("resources/images/m.jpg"));
		Image fire1= new Image(c3);
		ImageView ctr = new ImageView(fire1);
		ctr.setFitWidth(40);
		ctr.setFitHeight(40);
		return ctr;
	
	}
	
	public static Node image4() throws IOException
	{
		InputStream c4 = Files.newInputStream(Paths.get("resources/images/space.png"));
		Image fire2= new Image(c4);
		ImageView contrl= new ImageView(fire2);
		contrl.setFitWidth(90);
		contrl.setFitHeight(90);
		return contrl;
	}
	
}