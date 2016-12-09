/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jay
 */
public class Gather {//This is similar to ParseTxtFile.java
    static String[] thingsToDo = new String[9];
    String[] direction = {"-1","+1"};
    
    private final int things = 9;
    String line;
    public int gSize;
    //robot F
    public int sFx;//Robot F starting location X
    public int sFy;//Robot F starting location Y
    //robot L
    public int eLx;//Robot L exiting location X
    public int eLy;//Robot L exiting location Y
    //First Obstacle
    public int sFstObX;//1st obstacle starting location X
    public int sFstObY;//1st obstacle starting location Y
    public int spdFstOb;//speed of the 1st obstacle
    public int dirFstObX;//direction X of the 1st obstacle
    public int dirFstObY;//direction Y of the 1st obstacle
    //Second Obstacle
    public int sSndObX;//2nd obstacle starting location X
    public int sSndObY;//2nd obstacle starting location Y
    public int spdSndOb;//speed of the 2nd obstacle
    public int dirSndObX;//direction X of the 2nd obstacle
    public int dirSndObY;//direction Y of the 2nd obstacle
    
    private void gatherSize(int gSize){
        this.gSize = gSize;
    }
    
    private void gatherStart(int sFx, int sFy){
        this.sFx = sFx;
        this.sFy = sFy;
    }
    
    private void gatherEnd(int eLx, int eLy){
        this.eLx = eLx;
        this.eLy = eLy;
    }
    
    private void gatherFirstOb(int sFstObX, int sFstObY, int spdFstOb, int dirFstObX, int dirFstObY){
        this.sFstObX = sFstObX;
        this.sFstObY = sFstObY;
        this.spdFstOb = spdFstOb;
        this.dirFstObX = dirFstObX;
        this.dirFstObY = dirFstObY;
    }
    
    private void gatherSecondOb(int sSndObX, int sSndObY, int spdSndOb, int dirSndObX, int dirSndObY){
        this.sSndObX = sSndObX;
        this.sSndObY = sSndObY;
        this.spdSndOb = spdSndOb;
        this.dirSndObX = dirSndObX;
        this.dirSndObY = dirSndObY;
    }
    
    public void collectYourData(FileReader fr) throws IOException{
        int i = 0;
        
        BufferedReader br = new BufferedReader(fr);
        
        while ((line = br.readLine()) != null){
            thingsToDo[i] = line;
            i += 1;
        }
        int j = 0;
        while (j < things){
            switch(j){
                case 0:
                    roomSize(thingsToDo[j]);
                    break;
                case 1:
                    location(thingsToDo[j], j);
                    break;
                case 2:
                    location(thingsToDo[j], j);
                    break;
                case 3:
                    location(thingsToDo[j], j);
                    break;
                case 4:
                    speed(thingsToDo[j], j);
                    break;
                case 5:
                    direction(thingsToDo[j], j);
                    break;
                case 6:
                    location(thingsToDo[j], j);
                    break;
                case 7:
                    speed(thingsToDo[j], j);
                    break;
                case 8:
                    direction(thingsToDo[j], j);
                    break;
            }
            j++;
        }
    }
    
    private void roomSize(String d){
        Integer num = Integer.parseInt(d);
        
        gSize = num;
    }
    
    private void location(String d, int i){
        int length = (int)(Math.log10(gSize) + 1);
        int index = 0;
        int first, second;
        
        char[] loc = d.toCharArray();
        char[] number;
        char check = '.';
        char[] firstNum = new char[length + 1];
        char[] secondNum = new char[length + 1];
        
        for (int j = 0; j < 3; j++){
            number = new char[length + 1];
            switch (j){
                case 0:
                    check = '(';
                    break;
                case 1:
                    check = ',';
                    break;
                case 2:
                    check = ')';
                    break;
            }
            
            if (loc[index] == check){
                if (j != 2){
                    if (Character.isDigit(loc[++index])){
                        number[0] = loc[index];
                        int k;
                        
                        for (k = 0; Character.isDigit(loc[++index]) && k < length; k++){
                            number[k + 1] = loc[index];
                        }
                        
                        if (j == 0){
                            firstNum = number;
                        } else if(j == 1){
                            secondNum = number;
                        }
                        
                        //2nd line
                        if (i == 1){
                            if (j == 0){
                                if (k > 0){
                                    first = createNumber(firstNum);
                                } else {
                                    first = Character.getNumericValue(firstNum[0]);
                                }
                                sFx = first;
                            } else if (j == 1){
                                if (k > 0){
                                    second = createNumber(secondNum);
                                } else {
                                    second = Character.getNumericValue(secondNum[0]);
                                }
                                sFy = second;
                            }
                        }
                        //3rd line
                        if (i == 2){
                            if (j == 0){
                                if (k > 0){
                                    first = createNumber(firstNum);
                                } else {
                                    first = Character.getNumericValue(firstNum[0]);
                                }
                                eLx = first;
                            } else if (j == 1){
                                if (k > 0){
                                    second = createNumber(secondNum);
                                } else {
                                    second = Character.getNumericValue(secondNum[0]);
                                }
                                eLy = second;
                            }
                        }
                        //4th line
                        if (i == 3){
                            if (j == 0){
                                if (k > 0){
                                    first = createNumber(firstNum);
                                } else {
                                    first = Character.getNumericValue(firstNum[0]);
                                }
                                sFstObX = first;
                            } else if (j == 1){
                                if (k > 0){
                                    second = createNumber(secondNum);
                                } else {
                                    second = Character.getNumericValue(secondNum[0]);
                                }
                                sFstObY = second;
                            }
                        }
                        //7th line
                        if (i == 6){
                            if (j == 0){
                                if (k > 0){
                                    first = createNumber(firstNum);
                                } else {
                                    first = Character.getNumericValue(firstNum[0]);
                                }
                                sSndObX = first;
                            } else if (j == 1){
                                if (k > 0){
                                    second = createNumber(secondNum);
                                } else {
                                    second = Character.getNumericValue(secondNum[0]);
                                }
                                sSndObY = second;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void speed(String d, int i){
        int num = Integer.parseInt(d);
        //5th line (speed of FIRST obstacle)
        if (i == 4){
            spdFstOb = num;
        }
        //8th line (speed of SECOND obstacle)
        if (i == 7){
            spdSndOb = num;
        }
    }
    
    private void direction(String d, int i){
        int index = 0;
        char check = '.';
        char[] first = new char[2]; 
        char[] second = new char[2];
        char[] action = d.toCharArray();
        boolean f = false;
        boolean s = false;

        for (int j = 0; j < 3; j++){
            switch (j){
                case 0:
                    check = '(';
                    break;
                case 1:
                    check = ',';
                    break;
                case 2:
                    check = ')';
                    break;
            }
            
            if (action[index] == check){
                if (j != 2){
                    index++;
                    if (action[index] != '0'){//this is either a positive or negative 1
                        if (j == 0){//first action
                            int k = 0;
                            while (k < 2){
                                first[k] = action[index++];
                                k++;
                            }
                        } else if (j == 1){//second action
                            int k = 0;
                            while (k < 2){
                                second[k] = action[index++];
                                k++;
                            }
                        } 
                    } else {
                        if (j == 0){
                            f = true;
                            first[0] = '0';
                            first[1] = '\0';
                        } else if (j == 1){
                            s = true;
                            second[0] = '0';
                            second[1] = '\0';
                        }
                        index++;
                    }
                }
            }
        }
        
        int n = 0;
        //6th line (direction of the FIRST obstacle)
        if (i == 5){
            if (f == true && s == true){
                dirFstObX = 0;
                dirFstObY = 0;
            }
            
            if (f == true){
                dirFstObX = 0;
                while (n < 2){
                    if (String.valueOf(second).equals(direction[n])){
                        if (n == 0){
                            System.out.print("hahah");
                            dirFstObY = -1;
                        } else {
                            dirFstObY = 1;
                        }
                    }
                    n++;
                }
            } else if (s == true){
                dirFstObY = 0;
                while (n < 2){
                    if (String.valueOf(first).equals(direction[n])){
                        if (n == 0){
                            dirFstObX = -1;
                        } else {
                            dirFstObX = 1;
                        }
                    }
                    n++;
                }
            }
            
            if (f == false && s == false){
                while(n < 2){
                    if (String.valueOf(first).equals(direction[n])){
                        if (n == 0){
                            dirFstObX = -1;
                        } else {
                            dirFstObY = 1;
                        }
                    }
                    n++;
                }
                n = 0;
                while(n < 2){
                    if (String.valueOf(second).equals(direction[n])){
                        if (n == 0){
                            dirFstObX = -1;
                        } else {
                            dirFstObY = 1;
                        }
                    }
                    n++;
                }
            }
        }
        //9th line (direction of the SECOND obstacle)
        if (i == 8){
            if (f == true && s == true){
                dirSndObX = 0;
                dirSndObY = 0;
            }
            
            if (f == true){
                dirSndObX = 0;
                while (n < 2){
                    if (String.valueOf(second).equals(direction[n])){
                        if (n == 0){
                            dirSndObY = -1;
                        } else {
                            dirSndObY = 1;
                        }
                    }
                    n++;
                }
            } else if (s == true){
                dirSndObY = 0;
                while (n < 2){
                    if (String.valueOf(first).equals(direction[n])){
                        if (n == 0){
                            dirSndObX = -1;
                        } else {
                            dirSndObX = 1;
                        }
                    }
                    n++;
                }
            }
            
            if (f == false && s == false){
                while(n < 2){
                    if (String.valueOf(first).equals(direction[n])){
                        if (n == 0){
                            dirSndObX = -1;
                        } else {
                            dirSndObY = 1;
                        }
                    }
                    n++;
                }
                n = 0;
                while(n < 2){
                    if (String.valueOf(second).equals(direction[n])){
                        if (n == 0){
                            dirSndObX = -1;
                        } else {
                            dirSndObY = 1;
                        }
                    }
                    n++;
                }
            }
        }
    }
    
    private int createNumber(char[] d){
        int num = 0;
        int length = (int)(Math.log10(gSize) + 1);
        int power = length;
        
        for (int i = 0; i < length; i++){
            num += Character.getNumericValue(d[i]) * Math.pow(10, power - 1);
        }
        return num;
    }
    
}
