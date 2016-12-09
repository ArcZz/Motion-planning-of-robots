/*
 * Project:    TestForMain
 * File:       Parser.java
 * Author:     Michael Esker
 * Date:       Dec 7, 2016
 * Time:       5:55:12 PM
 */
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author Michael Esker
 */
public class Parser {
    
    private FileChooser fileChooser;
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private Integer linesRead;
    private Integer roomSize;
    private Integer robotStartX;
    private Integer robotStartY;
    private Integer robotEndX;
    private Integer robotEndY;
    private Integer obstacle1X;
    private Integer obstacle1Y;
    private Integer obstacle1Speed;
    private Integer obstacle1XDirection;
    private Integer obstacle1YDirection;
    private Integer obstacle2X;
    private Integer obstacle2Y;
    private Integer obstacle2Speed;
    private Integer obstacle2XDirection;
    private Integer obstacle2YDirection;
    
    public Parser() {
        linesRead = 0;
    }
    
    /*
    line 1 : g gridSize
    line 2: (x,y) start location
    line 3: (x,y) end location
    line 4: (a,b) obstacle location
    line 5: c obstacle speed
    line 6: (e,f) obstacle direction
    line 7: (a,b)
    line 8: c
    line 9: (e,f)
    */

    public void parseFromFilePath(String path) {
        file = new File(path);
        parseFile(file);
    }
    
    public void parseFile(File file) {
        //fileChooser = new FileChooser();
        //file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                fileReader = new FileReader(file.getPath());
                bufferedReader = new BufferedReader(fileReader);
                
                //String input = "";
                String line;
                linesRead = roomSize = robotStartX = robotStartY = robotEndX = robotEndY = obstacle1X = obstacle1Y = obstacle1Speed = obstacle1XDirection 
                        = obstacle1YDirection = obstacle2X = obstacle2Y = obstacle2Speed = obstacle2XDirection = obstacle2YDirection = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    parseLine(line);//json += line;
                    ++linesRead;
                }
                bufferedReader.close();
                fileReader.close();
                
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (linesRead != 9) {
                System.out.println("Invalid number of lines read from input text file");
            }
        }
    }
    
    private void parseLine(String input) throws ParseException{
        Integer lineLength = input.length();
        //int index = 0;
        char previousChar = '\n';
        char coordinate = 'x';
        int value;
        for (char character : input.toCharArray()) {
            switch(character) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    //get digit
                    value = getDigit(character);
                    switch(linesRead) {
                        case 0:
                            roomSize = roomSize * 10 + value;
                            if (roomSize < 1) {
                                throw new ParseException("Invalid roomSize");
                            }
                            break;
                        case 1:
                            if (coordinate == 'x') {
                                robotStartX = robotStartX * 10 + value;
                                if (robotStartX < 1 || robotStartX > roomSize) {
                                    throw new ParseException("Invalid robotStartX");
                                }
                            } else {
                                robotStartY = robotStartY * 10 + value;
                                if (robotStartY < 1 || robotStartY > roomSize) {
                                    throw new ParseException("Invalid robotStartY");
                                }
                            }
                            break;
                        case 2:
                            if (coordinate == 'x') {
                                robotEndX = robotEndX * 10 + value;
                                if (robotEndX < 1 || robotEndX > roomSize) {
                                    throw new ParseException("Invalid robotEndX");
                                }
                            } else {
                                robotEndY = robotEndY * 10 + value;
                                if (robotEndY < 1 || robotEndY > roomSize) {
                                    throw new ParseException("Invalid robotEndY");
                                }
                            }
                            break;
                        case 3:
                            if (coordinate == 'x') {
                                obstacle1X = obstacle1X * 10 + value;
                                if (obstacle1X < 1 || obstacle1X > roomSize) {
                                    throw new ParseException("Invalid obstacle1X");
                                }
                            } else {
                                obstacle1Y = obstacle1Y * 10 + value;
                                if (obstacle1Y < 1 || obstacle1Y > roomSize) {
                                    throw new ParseException("Invalid obstacle1Y");
                                }
                            }
                            break;
                        case 4:
                            obstacle1Speed = obstacle1Speed * 10 + value;
                                if (obstacle1Speed < 0 || obstacle1Speed > roomSize) {
                                    throw new ParseException("Invalid obstacle1Speed");
                                }
                            break;
                        case 5:
                            if (value == 0 || value == 1) {
                                if (coordinate == 'x') {
                                    obstacle1XDirection *= value;
                                } else {
                                   obstacle1YDirection *= value;
                                }
                            } else {
                                throw new ParseException("Invalid obstacle direction");
                            }
                            break;
                        case 6:
                            if (coordinate == 'x') {
                                obstacle2X = obstacle2X * 10 + value;
                                if (obstacle2X < 1 || obstacle2X > roomSize) {
                                    throw new ParseException("Invalid obstacle2X");
                                }
                            } else {
                                obstacle2Y = obstacle2Y * 10 + value;
                                if (obstacle2Y < 1 || obstacle2Y > roomSize) {
                                    throw new ParseException("Invalid obstacle2Y");
                                }
                            }
                            break;
                        case 7:
                            obstacle2Speed = obstacle2Speed * 10 + value;
                                if (obstacle2Speed < 0 || obstacle2Speed > roomSize) {
                                    throw new ParseException("Invalid obstacle2Speed");
                                }
                            break;
                        case 8:
                            if (value == 0 || value == 1) {
                                if (coordinate == 'x') {
                                    obstacle2XDirection *= value;
                                } else {
                                   obstacle2YDirection *= value;
                                }
                            } else {
                                throw new ParseException("Invalid obstacle direction");
                            }
                            break;
                        default:
                            throw new ParseException("Invalid length of text file");
                    }
                    break;
                case '+':
                    if (previousChar == '(') {
                        if (linesRead == 5) {
                            obstacle1XDirection = 1;
                        } else if (linesRead == 8) {
                            obstacle2XDirection = 1;
                        } else {
                            throw new ParseException("Parsing Invalid Format");
                        }
                    } else if (previousChar == ',') {
                        if (linesRead == 5) {
                            obstacle1YDirection = 1;
                        } else if (linesRead == 8) {
                            obstacle2YDirection = 1;
                        } else {
                            throw new ParseException("Parsing Invalid Format");
                        }
                    }
                    break;
                case '-':
                    if (previousChar == '(') {
                        if (linesRead == 5) {
                            obstacle1XDirection = -1;
                        } else if (linesRead == 8) {
                            obstacle2XDirection = -1;
                        } else {
                            throw new ParseException("Parsing Invalid Format");
                        }
                    } else if (previousChar == ',') {
                        if (linesRead == 5) {
                            obstacle1YDirection = -1;
                        } else if (linesRead == 8) {
                            obstacle2YDirection = -1;
                        } else {
                            throw new ParseException("Parsing Invalid Format");
                        }
                    }
                    break;
                case '(':
                    if (previousChar != '\n') {
                        throw new ParseException("Parsing Invalid Format");
                    } else {
                        coordinate = 'x';
                    }
                    break;
                case ',':
                    if (!isDigit(previousChar)) {
                        throw new ParseException("Parsing Invalid Format");
                    } else {
                        coordinate = 'y';
                    }
                    break;
                case ')':
                    if (!isDigit(previousChar)) {
                        throw new ParseException("Parsing Invalid Format");
                    }
                    break;
                case '\n':
                    break;
                default:
                    throw new ParseException("Parsing Invalid Character");
            }
            previousChar = character;
        }
    }
    
    private boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }
    
    private Integer getDigit(char character) {
        return character - '0';
    }
    
    public void printOutput(){
        System.out.println(roomSize);
        System.out.println("(" + robotStartX + "," + robotStartY + ")");
        System.out.println("(" + robotEndX + "," + robotEndY + ")");
        System.out.println("(" + obstacle1X + "," + obstacle1Y + ")");
        System.out.println(obstacle1Speed);
        System.out.println("(" + obstacle1XDirection + "," + obstacle1YDirection + ")");
        System.out.println("(" + obstacle2X + "," + obstacle2Y + ")");
        System.out.println(obstacle2Speed);
        System.out.println("(" + obstacle2XDirection + "," + obstacle2YDirection + ")");
    }
    
    public Integer getRoomSize() {
        return roomSize;
    }
    
    public Integer getRobotStartX() {
        return robotStartX;
    }
    
    public Integer getRobotStartY() {
        return robotStartY;
    }
    public Integer getRobotEndX() {
        return robotEndX;
    }
    
    public Integer getRobotEndY() {
        return robotEndY;
    }
    
    public Integer getObstacle1X() {
        return obstacle1X;
    }
    
    public Integer getObstacle1Y() {
        return obstacle1Y;
    }
    
    public Integer getObstacle1Speed() {
        return obstacle1Speed;
    }
    
    public Integer getObstacle1XDirection() {
        return obstacle1XDirection;
    }
    
    public Integer getObstacle1YDirection() {
        return obstacle1YDirection;
    }
    
    public Integer getObstacle2X() {
        return obstacle2X;
    }
    
    public Integer getObstacle2Y() {
        return obstacle2Y;
    }
    
    public Integer getObstacle2Speed() {
        return obstacle2Speed;
    }
    
    public Integer getObstacle2XDirection() {
        return obstacle2XDirection;
    }
    
    public Integer getObstacle2YDirection() {
        return obstacle2YDirection;
    }
}
