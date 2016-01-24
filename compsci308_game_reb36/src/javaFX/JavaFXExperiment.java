package javaFX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class JavaFXExperiment extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Mystery of A Murdered Megastar" );
        // root has everything on outside
        ArrayList<String> input = new ArrayList<String>();
    	GameMode introAndMainMap = new GameMode(input, 6700, 5300);
    	GameMode interior = new GameMode(input, Color.BLACK, 500, 500);
        GameMode combat = new GameMode(input, 500, 500);

        Rectangle titleImage = new Rectangle(500, 500, new ImagePattern(new Image("titleScreen.png")));
        Rectangle plotImage = new Rectangle(500, 500, new ImagePattern(new Image("introTextStory.png")));
        Rectangle combatImage = new Rectangle(500, 500, new ImagePattern(new Image("introTextCombat.png")));
        Rectangle controlsImage = new Rectangle(500, 500, new ImagePattern(new Image("introTextControls.png")));
        
        
        // create player character
        Player player = new Player();
        player.setImage("Red.png");
        double playerX = 250;
        double playerY = 250;
        player.setPosition(playerX, playerY);
        player.initItems();
        player.initDisplay();

        
        // create map  
        Map mainMap = new Map(introAndMainMap.getGC(), player);

        theStage.setScene(introAndMainMap.getScene());
		introAndMainMap.getChildren().add(titleImage);
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        
        // inventory
        Inventory inventory = new Inventory(player);
        
        new AnimationTimer()
        {
        	boolean inGame = false;
        	boolean inBuilding = true;
            Building buildingInsideOf = mainMap.getRealBuildings().get(0);
            boolean inBattle = false;
            BattleText battle = null;
            boolean martOpen = false;
            boolean enterPressed = false;
            GameMode currGameMode;
                        
            public void handle(long currentNanoTime) {
            	//cheat code - sets enemies health to 0
            	if(input.contains("V") && inBattle) {
            		battle.getEnemy().setCurrHP(0);
            	}
            	setScene();
           	
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.getValue()) / 1000000000.0;
                lastNanoTime.setValue(currentNanoTime);

                player.setVelocity(0,0);
                playGame(input, combat, player, playerX, playerY, mainMap, inventory, elapsedTime);
                renderEnvironment(introAndMainMap, interior, combat, player, mainMap);
            }
            
            private void nextIntroScreen(GameMode map, Rectangle currentImage, Rectangle nextImage) {
        		if (map.getChildren().contains(currentImage) && enterPressed && !input.contains("ENTER")) {
        			enterPressed = false;
        			if (nextImage == null) {
            			inBuilding = true;
            			buildingInsideOf = mainMap.getRealBuildings().get(0);
            			inGame = true;
            			player.resetGame();
                		currGameMode = interior;
        			}
        			else {
            			map.getChildren().add(nextImage);        				
        			}
        			map.getChildren().remove(currentImage);
        		}
            }
            
            private void setScene(GameMode setting) {
        		if (!theStage.getScene().equals(setting.getScene()))
        			theStage.setScene(setting.getScene());
        		currGameMode = setting;
            }
            
            private void navigateIntro(GameMode setting, Rectangle titleImage, Rectangle plotImage, Rectangle combatImage, Rectangle controlsImage) {
        		nextIntroScreen(setting, titleImage, plotImage);
        		nextIntroScreen(setting, plotImage, combatImage);
        		nextIntroScreen(setting, combatImage, controlsImage);
        		nextIntroScreen(setting, controlsImage, null);
            }
            
            private void setScene() {
            	if (inBattle) {
            		setScene(combat);
            		battle.setBackground();            		
            	}
            	else if (inBuilding && inGame) {
            		setScene(interior);
            	}
            	else if (inGame) {
            		setScene(introAndMainMap);
            	}
            	else {
            		if (input.contains("ENTER")) enterPressed = true;
               		if (!theStage.getScene().equals(introAndMainMap.getScene())) {
               			introAndMainMap.getChildren().add(titleImage); 
            		}
            		setScene(introAndMainMap);
            		navigateIntro(introAndMainMap, titleImage, plotImage, combatImage, controlsImage);
            	}
            }
            
            private void stopNPC(boolean textboxShowing, NPC npc, Player player) {
        		if (textboxShowing && (npc.getVelocityX() != 0 || npc.getVelocityY() != 0)) {
        			player.setNpcVX(npc.getVelocityX());
        			player.setNpcVY(npc.getVelocityY());
        			npc.setVelocity(0, 0);
        		}
            }

			private void playGame(ArrayList<String> input, GameMode combat, Player player, double playerX,
					double playerY, Map mainMap, Inventory inventory, double elapsedTime) {
				if (inBattle) {
        	        playBattle(input, combat);
                }
                else if (inGame){
                    playWalkAround(input, combat, player, playerX, playerY, mainMap, inventory, elapsedTime);
                }
			}

			private void playWalkAround(ArrayList<String> input, GameMode combat, Player player, double playerX,
					double playerY, Map mainMap, Inventory inventory, double elapsedTime) {
				if (!martOpen && !getInventoryOpen())
					player.move(input);
				if (getInventoryOpen()) {
					inventory.getArrow().moveArrow(input, inventory);
				}
				moveAroundObstacles(input, combat, player, mainMap, getInventoryOpen());
				moveCharacters(player, playerX, playerY, mainMap, elapsedTime);
			}

			private void playBattle(ArrayList<String> input, GameMode combat) {
				addOrRemoveArrow(input);
				battle.combat(input);
				endBattle(combat);
				currGameMode.getGC().clearRect(0, 0, 500, 500);
			}

			private void renderEnvironment(GameMode introAndMainMap, GameMode interior, GameMode combat, Player player,
					Map mainMap) {
				if(!inBuilding && !inBattle && inGame) {
                	renderOutsideAndStartRandomEncounter(introAndMainMap, combat, player, mainMap);
                    }
                else if (inBuilding && inGame){
                	renderInside(interior, player);
                }
			}

			private void moveAroundObstacles(ArrayList<String> input, GameMode combat, Player player, Map mainMap,
					boolean inventoryOpen) {
				if (!inBuilding && !inBattle && inGame) {                	                	
                	moveAroundObjects(input, combat, player, mainMap);
                }
                else if (inBuilding && inGame) {
                	inBuilding = player.moveInsideBuilding(buildingInsideOf.getInside(), input, buildingInsideOf.getDoorPosX()+1, buildingInsideOf.getDoorPosY()+20);
                	if (inBuilding) {
	                	moveInBuilding(input, player, inventoryOpen);
	                	for (NPC npc : buildingInsideOf.getInside().getNPCs()) {
	                		interactWithInsideNPC(input, combat, player, npc);
	                	}
                	}
                }
			}

			private boolean getInventoryOpen() {
				if (!inBattle) {
                	return inventory.showTextbox(input, currGameMode);
                }
				return false;
			}
			
			private void renderInside(GameMode interior, Player player) {
				buildingInsideOf.getInside().render(interior.getGC());
				for (NPC npc : buildingInsideOf.getInside().getNPCs()) {
					npc.render(currGameMode.getGC());
				}
				player.render(currGameMode.getGC());
			}

			private void renderOutsideAndStartRandomEncounter(GameMode introAndMainMap, GameMode combat, Player player,
					Map mainMap) {
				mainMap.setMapBackgrounds(introAndMainMap.getGC());
				startWildEncounter(combat, player, mainMap);
				renderOutsideMap(mainMap, introAndMainMap, player);
				player.render(currGameMode.getGC());
			}

			private void moveCharacters(Player player, double playerX, double playerY, Map mainMap,
					double elapsedTime) {
				player.update(elapsedTime);
				for (NPC npc : mainMap.getNpcs()) {
					npc.move(elapsedTime);
					npc.update(elapsedTime);
				}
				currGameMode.getCanvas().setTranslateX((-player.updateAmount(elapsedTime)[0])+playerX);
				currGameMode.getCanvas().setTranslateY((-player.updateAmount(elapsedTime)[1])+playerY);
				currGameMode.getGC().clearRect(player.getLeftX()-300, player.getTopY()-300, player.getRightX()+300, player.getBotY()+300);
			}

			private void addOrRemoveArrow(ArrayList<String> input) {
				if (needArrow() && !(battle.getStack().getChildren().contains(battle.getArrow()))) {
					battle.getArrow().addArrow(battle.getStack());
				}
				else if (needArrow()) {
					battle.getArrow().moveArrow(input, battle);	
				}
				else if (battle.getStack().getChildren().contains(battle.getArrow())) {
					battle.getStack().getChildren().remove(battle.getArrow());
				}
			}

			private void startWildEncounter(GameMode combat, Player player, Map mainMap) {
				for (Grass grassBlock : mainMap.getGrassBlocks()) {
					if (player.beginBattle(grassBlock)) {
						inBattle = true;
						battle = new BattleText(player, grassBlock, combat);
					}
				}
			}

            private boolean needArrow() {
            	return (battle.getText().equals(battle.selectActionType) || battle.getText().equals(battle.listOfMoves) ||
    	        		battle.getText().equals(battle.getItemMenu()));
            }
            
			private void moveInBuilding(ArrayList<String> input, Player player, boolean inventoryOpen) {
				for(int i=0; i<buildingInsideOf.getInside().getFurniture().size(); i++) {
					if (player.inInteractionRange(buildingInsideOf.getInside().getFurniture().get(i), buildingInsideOf.getInside().getInteractionSides().get(i))) {              			
						TextBox furnishing = buildingInsideOf.getInside().getInteractions().get(i);
						if (furnishing.getClass().getName() == "javaFX.MartText") {
							openMart(input, inventoryOpen, furnishing);
						}
						else
							buildingInsideOf.getInside().getInteractions().get(i).showTextbox(input, currGameMode);
					}
					player.moveOutsideObject(buildingInsideOf.getInside().getFurniture().get(i));	
				}
			}

			private void openMart(ArrayList<String> input, boolean inventoryOpen, TextBox furnishing) {
				MartText counter = (MartText) furnishing;
				if (!inventoryOpen) {
					martOpen = counter.showTextbox(input, currGameMode);
					if (currGameMode.getChildren().contains(counter.getStack()))
						counter.getArrow().moveArrow(input, counter);
					if (martOpen) {
						counter.changeText(input, currGameMode);
					}
				}
			}

			private void interactWithInsideNPC(ArrayList<String> input, GameMode combat, Player player, NPC npc) {
				if (player.inInteractionRange(npc, null)) {
					boolean textOpen = npc.getDialogue().showTextbox(input, currGameMode);
					if (textOpen && npc.getDialogue().getText().startsWith("NURSE"))
						player.setCurrHP(player.getMaxHP());
					startNPCBattle(input, combat, player, npc);	
				}
				player.moveOutsideObject(npc);
			}

			private void endBattle(GameMode combat) {
				if (battle.isEnemyDead() || battle.isPlayerDead()) {
					combat.getChildren().remove(battle.getEnemy().getDisplay());
					battle.getStack().getChildren().remove(battle.getEnemy().getBattleImage());
					battle.getStack().getChildren().remove(battle);
					if (battle.isPlayerDead() || (battle.isEnemyDead() && battle.isFightingNPC())) {
						battle.getEnemy().setCurrHP(battle.getEnemy().getMaxHP());
						inGame = false;
					}
					battle = null;
					inBattle = false;
				}
			}

			private void moveAroundObjects(ArrayList<String> input, GameMode combat, Player player, Map mainMap) {
				moveOutsideRealBuildings(player, mainMap);
				moveOutsideNPCs(input, combat, player, mainMap);
				moveOutsideForest(player, mainMap);
				moveOutsideDecorativeBuilding(player, mainMap);
			}

			private void moveOutsideDecorativeBuilding(Player player, Map mainMap) {
				for (Sprite decBuilding : mainMap.getDecorativeBuildings()) {
					if (decBuilding.getRightX() > player.getLeftX()-300 && decBuilding.getLeftX() < player.getLeftX()+300)
						player.moveOutsideObject(decBuilding);
				}
			}

			private void moveOutsideForest(Player player, Map mainMap) {
				for(Forest forestBlock : mainMap.getForestBlocks()) {
					if (forestBlock.getRightX() > player.getLeftX()-300 && forestBlock.getLeftX() < player.getLeftX()+300)
						player.moveOutsideObject(forestBlock);
				}
			}

			private void moveOutsideNPCs(ArrayList<String> input, GameMode combat, Player player, Map mainMap) {
				for (NPC npc : mainMap.getNpcs()) {
					if (player.inInteractionRange(npc, null)) {
						boolean textboxShowing = npc.getDialogue().showTextbox(input, currGameMode);
						stopNPC(textboxShowing, npc, player);
						restartNPCMovement(player, npc, textboxShowing); 
						startNPCBattle(input, combat, player, npc);
					}
					player.moveOutsideObject(npc);
				}
			}

			private void moveOutsideRealBuildings(Player player, Map mainMap) {
				for (Building buildingName : mainMap.getRealBuildings()) {
					player.moveOutsideBuilding(buildingName);     
					if(buildingName.bordersDoorB(player) && !inBuilding) { 
						inBuilding = true;
						buildingInsideOf = buildingName;
						if (buildingName.getClass().getName() != "javaFX.BuildingMart")
							player.setPosition(103, 336);
						else 
							player.setPosition(150, 336);
						}  
				}
			}

			private void startNPCBattle(ArrayList<String> input, GameMode combat, Player player, NPC npc) {
				if (npc.getDialogue().getText().equals(npc.getDialogue().accuseNPC)) {
					player.setVelocity(0, 0);
					npc.getDialogue().getArrow().moveArrow(input, npc.getDialogue());
				}
				if (npc.getDialogue().isStartBattle()) {
					inBattle = true;
					inBuilding = false;
					battle = new BattleText(player, npc, combat);
					npc.getDialogue().setStartBattle(false);
				}
			}

			private void restartNPCMovement(Player player, NPC npc, boolean textboxShowing) {
				if (!textboxShowing && (player.getNpcVX() != 0 || player.getNpcVY() != 0)) {
					npc.setVelocity(player.getNpcVX(), player.getNpcVY());
					player.setNpcVX(0);
					player.setNpcVY(0);
				}
			}
        }.start();

        theStage.show();
    } 
        
        
    
    public void renderOutsideMap(Map map, GameMode setting, Player player) {
    	renderGrass(map, player);
    	renderAndMoveAroundForest(map, player);
    	renderAndMoveAroundRealBuildings(map, setting, player);
    	renderAndMoveAroundDecorativeBuildings(map, setting, player);
    	renderAndMoveAroundOutsideNPCs(map, setting, player);
    }
    
    public void renderGrass(Map map, Player player) {
    	for (Grass grassBlock : map.getGrassBlocks()) {
    		if (isInWindow(grassBlock, player)) {
    			grassBlock.drawGrass();
    		}
    	}
    }
    
    public void renderAndMoveAroundForest(Map map, Player player) {
    	for (Forest forestBlock : map.getForestBlocks()) {
    		if (isInWindow(forestBlock, player)) {
    			forestBlock.drawForest();
    		}
    		player.moveOutsideObject(forestBlock);
    	}
    }
    
    public void renderAndMoveAroundRealBuildings(Map map, GameMode setting, Player player) {
    	for (Building buildingName : map.getRealBuildings()) {
    		if (isInWindow(buildingName, player)) {
    			buildingName.render(setting.getGC());
    		}
    		player.moveOutsideBuilding(buildingName);
    	}
    }
    
    public void renderAndMoveAroundOutsideNPCs(Map map, GameMode setting, Player player) {
    	for (NPC npc : map.getNpcs()) {
    		npc.render(setting.getGC());
        	player.moveOutsideObject(npc);
    	}
    }
    
    public void renderAndMoveAroundDecorativeBuildings(Map map, GameMode setting, Player player) {
    	for (Sprite decBuilding : map.getDecorativeBuildings()) {
    		if (isInWindow(decBuilding, player)) {
    			decBuilding.render(setting.getGC());
    		}
    		player.moveOutsideObject(decBuilding);
    	}    	
    }
    
    public boolean isInWindow(Sprite object, Player player) {
    	return (object.getRightX() > player.getLeftX()-300 && object.getLeftX() < player.getLeftX()+300
				&& object.getBotY() > player.getTopY()-300 && object.getTopY() < player.getTopY()+300);
    }
}