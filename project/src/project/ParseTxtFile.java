
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Jay
 */
public class ParseTxtFile {
    static String[] demands = new String[9];
    String[] direction = {"-1","+1"};
    
    int demandslist = 9;
    String line;
    Integer gSize;
    
    public boolean IsItEmpty(FileReader filePath) throws IOException{
        
        BufferedReader br = new BufferedReader(filePath);
        return br.readLine() != null;
    }
    
    public boolean CorrectNumLines(FileReader filePath) throws IOException{
        int lines = 0;
        
        BufferedReader br = new BufferedReader(filePath);
        
        while (br.readLine() != null)
            lines++;
        
        return lines == demandslist;
    }
    
    //I guess you have to provide the binary yes or no function in this.
    public boolean IsItValid(FileReader filePath) throws IOException{
        int j = 0;
        
        BufferedReader br = new BufferedReader(filePath);
        
        while ((line = br.readLine()) != null){
            demands[j] = line;
            j += 1;
        }
        int i = 0;
        boolean result = true;
        while (i < demandslist && result == true){
            switch(i){
                //first line (this is where you get n)
                case 0:
                    result = isThisNonNeg(demands, i);
                    break;
                //second line (this is where you set the starting location of the robot F)
                case 1:
                    result = isThisBetween(demands[i]);
                    break;
                //third line (exit location)
                case 2:
                    result = isThisBetween(demands[i]);
                    break;
                //fourth line (starting location of the first obstacle)
                case 3:
                    result = isThisBetween(demands[i]);
                    break;
                //fifth line (the speed of the first obstacle)
                case 4:
                    result = isThisNonNeg(demands, i);
                    break;
                //sixth line (the diection of the first obstable)
                case 5:
                    result = isThisMove(demands[i]);
                    break;
                //seven line (the starting location of the second obstacle)
                case 6:
                    result = isThisBetween(demands[i]);
                    break;
                //eigth line (the speed of the second obstacle)
                case 7:
                    result = isThisNonNeg(demands, i);
                    break;
                //ninth line (the direction of the second obstacle)
                case 8:
                    result = isThisMove(demands[i]);
                    break;
            }
            i++;
        }
        return result;
    }
    
    //Is this non-negative integer?
    private boolean isThisNonNeg(String d[], int i){
        Integer num = Integer.parseInt(d[i]);
        
        if (num > 0){
            if (i == 0){
                gSize = num;
            }
            return true;
        }
        return false;
    }
    //Is this between 1 and n?
    private boolean isThisBetween(String d){
        int length = (int)(Math.log10(gSize) + 1);
        int index = 0;
        int first = 0, second = 0;
//      int overLength = 0;
        char[] location = d.toCharArray();
        char[] number;
        char check = '.';
        char[] firstNum = new char[length + 1];
        char[] secondNum = new char[length + 1];
        for (int i = 0; i < 3; i++){
            number = new char[length + 1];
            switch (i){
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
            
            if (location[index] != check){
                return false;
            }
            
            if (i != 2){
                if (!Character.isDigit(location[++index])){
                    return false;
                } else {
                    number[0] = location[index];
                }
                
                int j;
                
                for (j = 0; Character.isDigit(location[++index]) && j < length; j++){
                    number[j + 1] = location[index];
                }
                
                if (j > length){
                    return false;
                } else {
                    //number[j + 1] = '\0';
                    if (i == 0){
                        firstNum = number;
                    } else if (i == 1){
                        secondNum = number;
                    }
                }

                if (i == 0)
                    if (j > 0){
                        //first = Integer.parseInt(firstNum.toString());//There is something wrong with Integer.parseInt()
                        first = createNumber(firstNum);
                    } else {//single digit
                        first = Character.getNumericValue(firstNum[0]);
                    }
                if (i == 1)
                    if (j > 0){
                        //second = Integer.parseInt(secondNum.toString());
                        second = createNumber(secondNum);
                    } else {//single digit
                        second = Character.getNumericValue(secondNum[0]);
                    }
            }
        }
        
        return !(first > gSize && second > gSize);
    }
    //Is this -1, 0, or +1?
    private boolean isThisMove(String d){//I guess similar to the one from above
        
        int index = 0;
        char check = '.';
        char[] first = new char[2]; 
        char[] second = new char[2];
        char[] action = d.toCharArray();
        boolean f = false;
        boolean s = false;
        for (int i = 0; i < 3; i++){
            switch(i){
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
            
            if (action[index] != check){
                return false;
            }
            
            if (i != 2){
                index++;
                if (action[index] != '0'){//this is either a positive or negative 1
                    if (i == 0){//first action
                        int j = 0;
                        while (j < 2){
                            first[j] = action[index++];
                            j++;
                        }
                    } else if (i == 1){//second action
                        int j = 0;
                        while (j < 2){
                            second[j] = action[index++];
                            j++;
                        }
                    } 
                } else {
                    if (i == 0){
                        f = true;
                        first[0] = '0';
                        first[1] = '\0';
                    } else if (i == 1){
                        s = true;
                        second[0] = '0';
                        second[1] = '\0';
                    }
                    index++;
                }
            }
        }
        
        if (f == true && s == true){//if both of them are zero
            return true;
        }
        
        if (f == true){
            return correctAction(String.valueOf(second));
        } else if (s == true){
            return correctAction(String.valueOf(first));
        } 
        
        return correctAction(String.valueOf(first), String.valueOf(second));
    }
    
    private boolean correctAction(String action){

        boolean correct = false;
        int i = 0;
        
        while (i < 2 && correct == false){
            if (action.equals(direction[i])){
                correct = true;
            }
            i++;
        }
                
        return correct != false;
    }
    
    private boolean correctAction(String action1, String action2){

        boolean correct = false;
        int i = 0;
        
        while (i < 2 && correct == false){
            if (action1.equals(direction[i])){
                correct = true;
            }
            i++;
        }
        
        if (correct == false){
            return false;
        }
        
        i = 0;
        correct = false;

        while (i < 2 && correct == false) {
            if (action2.equals(direction[i])){
                correct = true;
            }
            i++;
        }
        
        return correct != false;
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
