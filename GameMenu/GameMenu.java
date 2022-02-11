
package GameMenu;
import java.io.IOException;

import GameMain.Game;
import GameMain.GameAi;
import GameMain.GameEngine;
import GameMain.GameEngineAi;
import GameMain.Handler;
import GameMain.KeyInputHandler;
import GameMain.KeyInputHandlerAi;
import GameMain.Player;
import GameMain.Player2;
import GameMain.PlayerAi;
import GameMenu.Sound;
import Networking.KeyInput;
import Networking.NetGame;
import Networking.NetGameEngine;
import Networking.NetworkInterface;
import Networking.Server;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Sakinah
 * create the game menu using javaFx.
 *
 */

public class GameMenu extends Application
{
     boolean a = true;
     static boolean snd = true;  //true if sound is unmute.
     private Game game;
     private NetGame netGame;
     private GameEngine engine;
     private GameAi gameAi;
     private GameEngineAi engineAi;
     private KeyInputHandler inputHandler;
     private KeyInput netInput;
     private KeyInputHandlerAi inputHandlerAi;
     private Server netServer;
     public static int width = 1280;
     public static int height = 720;
     Handler handler;


      static Slider volslide = new Slider(); //slider for volume

      /**
       * create the game menu content
       * @return pane which content all items in game menu
       * @throws IOException
       */
    private Parent createContent() throws IOException
    {
        //play background sound
        Sound.playBack();
        
        Pane pane = new Pane();
        pane.setPrefSize(width,height);
        
        //add background image
        pane.getChildren().add(Images.backimage());

        //game title
        GTitle gTitle = new GTitle("MazeTank");
        gTitle.setTranslateX(width/2-163);
        gTitle.setTranslateY(60);

          //set volume slider position
           volslide.setTranslateX(width/2);
            volslide.setTranslateY(287);
            volslide.setVisible(false);
            volslide.setMax(100);

        volslide.setValue(Sound.vol() * 100);
        
        //add listener to volume slider
        volslide.valueProperty().addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable observable) {
                    Sound.mpSound().setVolume(volslide.getValue()/100);
                    Sound.explodeSound().setVolume(volslide.getValue()/100);
                    Sound.mediaplayerSound().setVolume(volslide.getValue()/100);
                    Sound.fireSound().setVolume(volslide.getValue()/100);

            }

        });

        //main menu items
        MenuItem exit = new MenuItem("EXIT");
        MenuItem play = new MenuItem("PLAY");
        MenuItem online = new MenuItem("2 PLAYER ONLINE GAME");
        MenuItem randmaze = new MenuItem ("RANDOM MAZE GAME");
        MenuItem settings = new MenuItem("SETTINGS");
        MenuItem gi = new MenuItem("GAME INSTRUCTION");


        //Play sub menu items
        MenuItem back = new MenuItem("BACK");
        MenuItem p1 = new MenuItem("PLAYER VS AI");
        MenuItem p2 = new MenuItem("2 PLAYER LOCAL");
        MenuItem server = new MenuItem("2 PLAYER ONLINE");

        //Settings sub menu items
        MenuItem soundOn = new MenuItem("Sound : ON");
        MenuItem soundOff = new MenuItem("Sound : OFF");
        MenuItem volume = new MenuItem("Volume :                  ");
        MenuItem back2 = new MenuItem("Back");

        //Local mode options menu items
        MenuItem m1 = new MenuItem("MAP 1");
        MenuItem m2 = new MenuItem("MAP 2");
        MenuItem m3 = new MenuItem("MAP 3");
        MenuItem randMap = new MenuItem("RANDOM MAP");
        MenuItem back4 =new MenuItem("BACK");
        
        //Ai mode options
        MenuItem easyAi = new MenuItem("EASY");
        MenuItem mediumAi = new MenuItem("MEDIUM");
        MenuItem hardAi = new MenuItem("HARD");
        MenuItem dieAi = new MenuItem("YOU WILL NOT SURVIVE");
        MenuItem backAi =new MenuItem("BACK");

        //online mode options
        MenuItem serverMap = new MenuItem("ONLINE ARENA MAP");
        MenuItem back4Server =new MenuItem("BACK");

        //In-game buttons
        
        //gameExit.setVisible(false);
        MenuItem newGameLocalMap1 = new MenuItem("NEW GAME");
        MenuItem newGameLocalMap2 = new MenuItem("NEW GAME");
        MenuItem newGameLocalMap3 = new MenuItem("NEW GAME");
        MenuItem newGameLocalRND = new MenuItem("NEW GAME");

      
        MenuItem newGameServerMap = new MenuItem("NEW GAME");
        MenuItem gameExitServerMap = new MenuItem("EXIT GAME");
        MenuItem gameExitLocalMap1 = new MenuItem("EXIT GAME");
        MenuItem gameExitLocalMap2 = new MenuItem("EXIT GAME");
        MenuItem gameExitLocalMap3 = new MenuItem("EXIT GAME");
        MenuItem gameExitLocalRND = new MenuItem("EXIT GAME");
        MenuItem newGameAiEasy = new MenuItem("NEW GAME");
        MenuItem newGameAiMed = new MenuItem("NEW GAME");
        MenuItem newGameAiHard = new MenuItem("NEW GAME");
        MenuItem newGameAiDead = new MenuItem("NEW GAME");
        MenuItem gameExitAiEasy = new MenuItem("EXIT GAME");
        MenuItem gameExitAiMed = new MenuItem("EXIT GAME");
        MenuItem gameExitAiHard = new MenuItem("EXIT GAME");
        MenuItem gameExitAiDead = new MenuItem("EXIT GAME");
        
        menubox gameboxAiEasy = new menubox(newGameAiEasy, gameExitAiEasy);
        gameboxAiEasy.setTranslateX(width/2+140);
        gameboxAiEasy.setTranslateY(200);
        gameboxAiEasy.setVisible(false);
        
        menubox gameboxAiMed = new menubox(newGameAiMed, gameExitAiMed);
        gameboxAiMed.setTranslateX(width/2+140);
        gameboxAiMed.setTranslateY(200);
        gameboxAiMed.setVisible(false);
        
        menubox gameboxAiHard = new menubox(newGameAiHard, gameExitAiHard);
        gameboxAiHard.setTranslateX(width/2+140);
        gameboxAiHard.setTranslateY(200);
        gameboxAiHard.setVisible(false);
        
        menubox gameboxAiDead = new menubox(newGameAiDead, gameExitAiDead);
        gameboxAiDead.setTranslateX(width/2+140);
        gameboxAiDead.setTranslateY(200);
        gameboxAiDead.setVisible(false);
        
        
         menubox soundOf = new menubox (soundOff);
         soundOf.setTranslateX(width/2-140);
         soundOf.setTranslateY(230);
         soundOf.setVisible(false);
         
        menubox gameboxLocalMap1 = new menubox(newGameLocalMap1, gameExitLocalMap1);
        gameboxLocalMap1.setTranslateX(width/2+140);
        gameboxLocalMap1.setTranslateY(200);
        gameboxLocalMap1.setVisible(false);
        
        menubox gameboxLocalMap2 = new menubox(newGameLocalMap2, gameExitLocalMap2);
        gameboxLocalMap2.setTranslateX(width/2+140);
        gameboxLocalMap2.setTranslateY(200);
        gameboxLocalMap2.setVisible(false);
        
        menubox gameboxLocalMap3 = new menubox(newGameLocalMap3, gameExitLocalMap3);
        gameboxLocalMap3.setTranslateX(width/2+140);
        gameboxLocalMap3.setTranslateY(200);
        gameboxLocalMap3.setVisible(false);
        
        menubox gameboxLocalRND = new menubox(newGameLocalRND, gameExitLocalRND);
        gameboxLocalRND.setTranslateX(width/2+140);
        gameboxLocalRND.setTranslateY(200);
        gameboxLocalRND.setVisible(false);
        
       
        menubox gameboxServerMap = new menubox(newGameServerMap, gameExitServerMap);
        gameboxServerMap.setTranslateX(width/2+140);
        gameboxServerMap.setTranslateY(200);
        gameboxServerMap.setVisible(false);
 
        
        menubox box = new menubox(play, settings, gi, exit);
        box.setTranslateX(width/2-140);
        box.setTranslateY(230);
        box.setVisible(true);

        
        MenuItem back3 = new MenuItem("Back");

         menubox box2 = new menubox (p1,p2,server,back);
         box2.setTranslateX(width/2-140);
         box2.setTranslateY(230);
         box2.setVisible(false);

         menubox box3 = new menubox (soundOn,volume,back2);
         box3.setTranslateX(width/2-140);
         box3.setTranslateY(230);
         box3.setVisible(false);

         menubox boxmap = new menubox (m1, m2 , m3, randMap, back4);
         boxmap.setTranslateX(width/2-140);
         boxmap.setTranslateY(230);
         boxmap.setVisible(false);

         menubox boxmapB = new menubox (easyAi,mediumAi,hardAi,dieAi, backAi);
         boxmapB.setTranslateX(width/2-140);
         boxmapB.setTranslateY(230);
         boxmapB.setVisible(false);

         menubox boxmapC = new menubox (serverMap, back4Server);
         boxmapC.setTranslateX(width/2-140);
         boxmapC.setTranslateY(230);
         boxmapC.setVisible(false);

         Desc desc = new Desc();
         desc.setTranslateX(450);
         desc.setTranslateY(175);
         desc.setVisible(false);

         menubox box4 = new menubox (back3);
         box4.setTranslateX(width/2-140);
         box4.setTranslateY(470);
         box4.setVisible(false);
         
 
         play.setOnMouseClicked(event -> {
             box.setVisible(false);
             box2.setVisible(true);
         });

         p1.setOnMouseClicked(event -> {
             box2.setVisible(false);
             boxmapB.setVisible(true);
             });

         p2.setOnMouseClicked(event -> {
             box2.setVisible(false);
             boxmap.setVisible(true);
             });

         server.setOnMouseClicked(event -> {
             box2.setVisible(false);
             boxmapC.setVisible(true);
             });

         back4.setOnMouseClicked(event -> {
             boxmap.setVisible(false);
             box2.setVisible(true);
             });

        m1.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on Map 1");
             }
            a=false;
          //Set other menu items invisible and in-game buttons visible
            gameboxLocalMap1.setVisible(true);
            boxmap.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engine = new GameEngine();
            game = new Game(engine);
            if(game.getTranslateX() != 50 || game.getTranslateY() != 50) {
                game.setTranslateX(50);
                game.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + game.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text player2Score = new Text("Player 2: " + game.player2Score);
            player2Score.setFill(Color.WHITE);
            player2Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player2Score.setTranslateX(width/2+280);
            player2Score.setTranslateY(100);
            player2Score.setVisible(true);

            engine.setPlayer1Score(player1Score);
            engine.setPlayer2Score(player2Score);

            inputHandler = new KeyInputHandler(Game.handler);

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandler);
            pane.getChildren().addAll(game,player1Score,player2Score);
            Sound.stopBack();
            game.start();
            game.newGameLocalMap1();
        
        });
        m2.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on Map 2");
             }
            a=false;
          //Set other menu items invisible and in-game buttons visible
            gameboxLocalMap2.setVisible(true);
            boxmap.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engine = new GameEngine();
            game = new Game(engine);
            if(game.getTranslateX() != 50 || game.getTranslateY() != 50) {
                game.setTranslateX(50);
                game.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + game.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text player2Score = new Text("Player 2: " + game.player2Score);
            player2Score.setFill(Color.WHITE);
            player2Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player2Score.setTranslateX(width/2+280);
            player2Score.setTranslateY(100);
            player2Score.setVisible(true);

            engine.setPlayer1Score(player1Score);
            engine.setPlayer2Score(player2Score);

            inputHandler = new KeyInputHandler(Game.handler);

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandler);
            pane.getChildren().addAll(game,player1Score,player2Score);
            Sound.stopBack();
            game.start();
            game.newGameLocalMap2();
        });
        m3.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on Map 3");
             }
            a=false;
          //Set other menu items invisible and in-game buttons visible
            gameboxLocalMap3.setVisible(true);
            boxmap.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engine = new GameEngine();
            game = new Game(engine);
            if(game.getTranslateX() != 50 || game.getTranslateY() != 50) {
                game.setTranslateX(50);
                game.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + game.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text player2Score = new Text("Player 2: " + game.player2Score);
            player2Score.setFill(Color.WHITE);
            player2Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player2Score.setTranslateX(width/2+280);
            player2Score.setTranslateY(100);
            player2Score.setVisible(true);

            engine.setPlayer1Score(player1Score);
            engine.setPlayer2Score(player2Score);

            inputHandler = new KeyInputHandler(Game.handler);

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandler);
            pane.getChildren().addAll(game,player1Score,player2Score);
            Sound.stopBack();
            game.start();
            game.newGameLocalMap3();
            
        });

        randMap.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on a randomly generated map");
             }
            a=false;

            //Set other menu items invisible and in-game buttons visible
            gameboxLocalRND.setVisible(true);
            boxmap.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engine = new GameEngine();
            game = new Game(engine);
            if(game.getTranslateX() != 50 || game.getTranslateY() != 50) {
                game.setTranslateX(50);
                game.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + game.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text player2Score = new Text("Player 2: " + game.player2Score);
            player2Score.setFill(Color.WHITE);
            player2Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player2Score.setTranslateX(width/2+280);
            player2Score.setTranslateY(100);
            player2Score.setVisible(true);

            engine.setPlayer1Score(player1Score);
            engine.setPlayer2Score(player2Score);

            inputHandler = new KeyInputHandler(Game.handler);

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandler);
            pane.getChildren().addAll(game,player1Score,player2Score);
            Sound.stopBack();
            game.start();
        });

        //Ai Mode
        backAi.setOnMouseClicked(event -> {
             boxmapB.setVisible(false);
             box2.setVisible(true);
             });

        easyAi.setOnMouseClicked(event -> {

            if (a==true){
                System.out.println("Starting game on a randomly generated map(Ai mode)");
             }
            a=false;

            //Set other menu items invisible and in-game buttons visible
            gameboxAiEasy.setVisible(true);
            boxmapB.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engineAi = new GameEngineAi();
            gameAi = new GameAi(engineAi);
            if(gameAi.getTranslateX() != 50 || gameAi.getTranslateY() != 50) {
                gameAi.setTranslateX(50);
                gameAi.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + gameAi.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text playerAiScore = new Text("Player Ai: " + gameAi.playerAiScore);
            playerAiScore.setFill(Color.WHITE);
            playerAiScore.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            playerAiScore.setTranslateX(width/2+280);
            playerAiScore.setTranslateY(100);
            playerAiScore.setVisible(true);

            engineAi.setPlayer1Score(player1Score);
            engineAi.setPlayerAiScore(playerAiScore);

            inputHandlerAi = new KeyInputHandlerAi(GameAi.handler);
            
            gameAi.playerAi.speed = 3;

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandlerAi);
            pane.getChildren().addAll(gameAi,player1Score,playerAiScore);
            Sound.stopBack();
            gameAi.start();
            GameMain.GameAi.easyAi = true;
            

        });
        
        mediumAi.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on a randomly generated map(Ai mode)");
             }
            a=false;

            //Set other menu items invisible and in-game buttons visible
            gameboxAiMed.setVisible(true);
            boxmapB.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engineAi = new GameEngineAi();
            gameAi = new GameAi(engineAi);
            if(gameAi.getTranslateX() != 50 || gameAi.getTranslateY() != 50) {
                gameAi.setTranslateX(50);
                gameAi.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + gameAi.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text playerAiScore = new Text("Player Ai: " + gameAi.playerAiScore);
            playerAiScore.setFill(Color.WHITE);
            playerAiScore.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            playerAiScore.setTranslateX(width/2+280);
            playerAiScore.setTranslateY(100);
            playerAiScore.setVisible(true);

            engineAi.setPlayer1Score(player1Score);
            engineAi.setPlayerAiScore(playerAiScore);

            inputHandlerAi = new KeyInputHandlerAi(GameAi.handler);

            gameAi.playerAi.speed = 6;

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandlerAi);
            pane.getChildren().addAll(gameAi,player1Score,playerAiScore);
            Sound.stopBack();
            gameAi.start();
            GameMain.GameAi.mediumAi = true;

        });
        
        hardAi.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on a randomly generated map(Ai mode)");
             }
            a=false;

            //Set other menu items invisible and in-game buttons visible
            gameboxAiHard.setVisible(true);
            boxmapB.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engineAi = new GameEngineAi();
            gameAi = new GameAi(engineAi);
            if(gameAi.getTranslateX() != 50 || gameAi.getTranslateY() != 50) {
                gameAi.setTranslateX(50);
                gameAi.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + gameAi.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text playerAiScore = new Text("Player Ai: " + gameAi.playerAiScore);
            playerAiScore.setFill(Color.WHITE);
            playerAiScore.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            playerAiScore.setTranslateX(width/2+280);
            playerAiScore.setTranslateY(100);
            playerAiScore.setVisible(true);

            engineAi.setPlayer1Score(player1Score);
            engineAi.setPlayerAiScore(playerAiScore);

            inputHandlerAi = new KeyInputHandlerAi(GameAi.handler);

            gameAi.playerAi.speed = 9;

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandlerAi);
            pane.getChildren().addAll(gameAi,player1Score,playerAiScore);
            Sound.stopBack();
            gameAi.start();
            GameMain.GameAi.hardAi = true;

        });
        
        dieAi.setOnMouseClicked(event -> {
            if (a==true){
                System.out.println("Starting game on a randomly generated map(Ai mode)");
             }
            a=false;

            //Set other menu items invisible and in-game buttons visible
            gameboxAiDead.setVisible(true);
            boxmapB.setVisible(false);
            gTitle.setVisible(false);


            //Create game and engine
            engineAi = new GameEngineAi();
            gameAi = new GameAi(engineAi);
            if(gameAi.getTranslateX() != 50 || gameAi.getTranslateY() != 50) {
                gameAi.setTranslateX(50);
                gameAi.setTranslateY(50);
            }

            //In-Game score texts
            Text player1Score = new Text("Player 1: " + gameAi.player1Score);
            player1Score.setFill(Color.WHITE);
            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            player1Score.setTranslateX(width/2+140);
            player1Score.setTranslateY(100);
            player1Score.setVisible(true);
            Text playerAiScore = new Text("Player Ai: " + gameAi.playerAiScore);
            playerAiScore.setFill(Color.WHITE);
            playerAiScore.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
            playerAiScore.setTranslateX(width/2+280);
            playerAiScore.setTranslateY(100);
            playerAiScore.setVisible(true);

            engineAi.setPlayer1Score(player1Score);
            engineAi.setPlayerAiScore(playerAiScore);

            inputHandlerAi = new KeyInputHandlerAi(GameAi.handler);

            gameAi.playerAi.speed = 12;

            pane.getScene().addEventHandler(KeyEvent.ANY,inputHandlerAi);
            pane.getChildren().addAll(gameAi,player1Score,playerAiScore);
            Sound.stopBack();
            gameAi.start();
            GameMain.GameAi.dieAi = true;

        });

       
        serverMap.setOnMouseClicked(event -> {
            if (a==true){
                NetworkInterface.networkOptions();
                System.out.println("Starting game on a randomly generated map(Server mode)");
             }
            a=false;
            //Set other menu items invisible and in-game buttons visible
            gameboxServerMap.setVisible(true);

            boxmapC.setVisible(false);
            gTitle.setVisible(false);



            //Create game and engine
        //    engineNet = new NetGameEngine();
            System.out.println("starting");

            String host = NetworkInterface.getHost();
            int port = NetworkInterface.getPort();
            System.out.println(host + "  " + port);
            netGame = new NetGame(host,port);

            if(netGame.getTranslateX() != 50 || netGame.getTranslateY() != 50) {

                netGame.setTranslateX(50);
                netGame.setTranslateY(50);

            }

            //In-Game score texts
//           Text player1Score = new Text("Player 1: " + netGame.player1Score);
//            player1Score.setFill(Color.WHITE);
//            player1Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
//            player1Score.setTranslateX(800);
//            player1Score.setTranslateY(100);
//            player1Score.setVisible(true);
//            Text player2Score = new Text("Player 2: " + netGame.player2Score);
//            player2Score.setFill(Color.WHITE);
//            player2Score.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));
//            player2Score.setTranslateX(950);
//            player2Score.setTranslateY(100);
//            player2Score.setVisible(true);
//
//            engineNet.setPlayer1Score(player1Score);
//            engineNet.setPlayer2Score(player2Score);

            netInput = new KeyInput(NetGame.handler, NetGame.playerID);
            System.out.println("test 3");
            pane.getScene().addEventHandler(KeyEvent.ANY,netInput);
            System.out.println("test 4");
            pane.getChildren().addAll(netGame);
            System.out.println("test 5");
            Sound.stopBack();
            System.out.println("test 6");
            netGame.start();
              
        });

        back4Server.setOnMouseClicked(event -> {
             boxmapC.setVisible(false);
             box2.setVisible(true);
             });

        newGameLocalMap1.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if(snd == true)
            {
                Sound.onSound();
            }
            game.newGameLocalMap1();
            Player.resetTank();
            Player2.resetTank();
        });
        
        newGameLocalMap2.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if(snd == true)
            {
                Sound.onSound();
            }
            game.newGameLocalMap2();
            Player.resetTank();
            Player2.resetTank();
        });
        
        newGameLocalMap3.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if(snd == true)
            {
                Sound.onSound();
            }
            game.newGameLocalMap3();
            Player.resetTank();
            Player2.resetTank();
        });
        
        newGameLocalRND.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if(snd == true)
            {
                Sound.onSound();
            }
            game.newGameLocalRND();
            Player.resetTank();
            Player2.resetTank();
        });
        
        
        
      
        newGameServerMap.setOnMouseClicked(event -> {
            if(snd == true)
            {
                Sound.onSound();
            }
            Sound.stopexplode();
            Sound.explode();
           netGame.newGame();
        });
        
        gameExitServerMap.setOnMouseClicked(event -> {
            if(netGame != null)
                netGame.stop();
            System.exit(0);
        });
       
        exit.setOnMouseClicked(event -> {

         });

         gameExitLocalMap1.setOnMouseClicked(event -> {
             game.stop();
             System.exit(0);
     });
         gameExitLocalMap2.setOnMouseClicked(event -> {
             game.stop();
             System.exit(0);
     });
         gameExitLocalMap3.setOnMouseClicked(event -> {
             game.stop();
             System.exit(0);
     });
         gameExitLocalRND.setOnMouseClicked(event -> {
             game.stop();
             System.exit(0);
     });
        
         newGameAiEasy.setOnMouseClicked(event -> {
             Sound.stopexplode();
             if(snd == true)
             {
                 Sound.onSound();
             }
             gameAi.newGameAi(); //CHANGE THIS TO CREATE DIFFICULTIES
             Player.resetTank();
             PlayerAi.resetTank();
     });
        gameExitAiEasy.setOnMouseClicked(event -> {
            gameAi.stop();
            System.exit(0);
        });
        newGameAiMed.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if (snd == true) {
                Sound.onSound();
            }
            gameAi.newGameAiMed();
            Player.resetTank();
            PlayerAi.resetTank();
        });
        gameExitAiMed.setOnMouseClicked(event -> {
            gameAi.stop();
            System.exit(0);
        });
        newGameAiHard.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if (snd == true) {
                Sound.onSound();
            }
            gameAi.newGameAiHard();
            Player.resetTank();
            PlayerAi.resetTank();
        });
        gameExitAiHard.setOnMouseClicked(event -> {
            gameAi.stop();
            System.exit(0);
        });
        newGameAiDead.setOnMouseClicked(event -> {
            Sound.stopexplode();
            if (snd == true) {
                Sound.onSound();
            }
            gameAi.newGameAiDead();
            Player.resetTank();
            PlayerAi.resetTank();
        });
        
        gameExitAiDead.setOnMouseClicked(event -> {
            gameAi.stop();
            System.exit(0);
        });

        back.setOnMouseClicked(event -> {
            box2.setVisible(false);
            box.setVisible(true);

        });

        back2.setOnMouseClicked(event -> {
             box3.setVisible(false);
             box.setVisible(true);
             soundOf.setVisible(false);
             volslide.setVisible(false);

         });


             settings.setOnMouseClicked(event -> {
             box2.setVisible(false);
             box.setVisible(false);
             box3.setVisible(true);
             volslide.setVisible(true);
             if (snd == true)
             {
                 soundOn.setVisible(true);
             }

             else
             {
                 soundOf.setVisible(true);
             }

                     });

            soundOn.setOnMouseClicked(event -> {
            soundOf.setVisible(true);
            soundOn.setVisible(false);
            Sound.offSound();

            snd = false;

                     });

             soundOf.setOnMouseClicked(event ->{
             soundOn.setVisible(true);
             soundOf.setVisible(false);
             Sound.onSound();
             snd = true;
         });

             gi.setOnMouseClicked(event -> {
             box.setVisible(false);
             desc.setVisible(true);
             box4.setVisible(true);
         });

         back3.setOnMouseClicked(event -> {
             desc.setVisible(false);
             box4.setVisible(false);
             box.setVisible(true);
         });

        pane.getChildren().addAll(gTitle,box,box2,box3,desc,box4,boxmap,gameboxLocalMap1,gameboxLocalMap2,gameboxLocalMap3,gameboxLocalRND,gameboxServerMap,gameboxAiEasy,gameboxAiMed,gameboxAiHard,gameboxAiDead,soundOf,volslide,boxmapB,boxmapC);
        return pane;

    }

    @Override
    public void start(Stage stage) throws Exception
    {

        Scene scene = new Scene(createContent());
        stage.setTitle("MazeTank");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * create the title for the game
     * @author Sakinah
     *
     */
    private static class GTitle extends StackPane
    {
        public GTitle(String name)
        {
            Rectangle rect = new Rectangle(350,90);
            rect.setStroke(Color.WHITE);
            rect.setStrokeWidth(2);
            rect.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Tw Cen MT Condensed",FontWeight.SEMI_BOLD,100));
            setAlignment(Pos.CENTER);
            getChildren().addAll(rect,text);

        }
    }
    /**
     * create the game description layout
     * @author Sakinah
     *
     */
    private static class Desc extends StackPane
    {
        public Desc()
        {
            Rectangle rect = new Rectangle(350,90);
            rect.setStroke(Color.WHITE);
            rect.setStrokeWidth(2);
            rect.setFill(null);

            Text text = new Text("Win this game by destroying the enemy's tank.");
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
            setAlignment(Pos.CENTER);

            HBox dbox = new HBox();
            HBox ebox = new HBox();
            HBox mbox = new HBox();
            HBox sbox = new HBox();

            dbox.setTranslateX(50);
            dbox.setTranslateY(100);

            ebox.setTranslateX(300);
            ebox.setTranslateY(100);

            mbox.setTranslateX(280);
            mbox.setTranslateY(90);

            sbox.setTranslateX(100);
            sbox.setTranslateY(180);

            Text dtext = new Text("Player 1");
            dtext.setFill(Color.WHITE);
            dtext.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
            dtext.setTranslateX(-200);
            dtext.setTranslateY(100);

            Text etext = new Text("Player 2");
            etext.setFill(Color.WHITE);
            etext.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
            etext.setTranslateX(250);
            etext.setTranslateY(100);

            try{

                dbox.getChildren().addAll(Images.image1());
                ebox.getChildren().addAll(Images.image2());
                mbox.getChildren().addAll(Images.image3());
                sbox.getChildren().addAll(Images.image4());
            }
            catch (IOException e)
            {
                System.out.println("Couldn't load image");


            }
            getChildren().addAll(etext,dtext,text,dbox,ebox,mbox,sbox);


        }
    }
    
    /**
     * create the menu box for the game menu
     * @author Sakinah
     *
     */
    private static class menubox extends VBox
    {
        private Line separator()
        {
            Line sepline = new Line();
            sepline.setEndX(300);
            sepline.setStroke(Color.BLACK);
            return sepline;

        }
       
        public menubox(MenuItem...items)
        {
            getChildren().add(separator());

            for(MenuItem item:items)
            {
                getChildren().addAll(item,separator());
            }
        }

    }

    /**
     * create the menuitem for the game menu
     * @author Sakinah
     *
     */
    private static class MenuItem extends StackPane
    {
    public MenuItem(String name)
    {
        LinearGradient grad = new LinearGradient(0,0,1,0,true,CycleMethod.NO_CYCLE,new Stop[]{
        new Stop(0,Color.MAROON),

    });

    Rectangle rect = new Rectangle(300,40);
    rect.setOpacity(0.4);

    Text text = new Text(name);
    text.setFill(Color.WHITE);
    text.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));

    setAlignment(Pos.CENTER);
    getChildren().addAll(rect,text);


    setOnMouseEntered(event -> {
        rect.setFill(grad);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Bell MT",FontWeight.BOLD,22));

    });

    setOnMouseExited(event ->{
        rect.setFill(Color.BLACK);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Bell MT",FontWeight.SEMI_BOLD,22));

    });

    setOnMousePressed(event -> {
        rect.setFill(Color.GREY);
        Sound.clicksound();

    });

    setOnMouseReleased(event ->{
        rect.setFill(grad);

    });


    }

    }
    
   
    public static void main(String[] args)
    {
        launch(args);
    }
}





