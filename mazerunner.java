import java.util.*;

import java.util.Stack;
import java.util.Arrays;


public class Mazerunner{

    
    public static void main(String[] args){
        int lives = 200;

        Scanner sc = new Scanner(System.in);

        String[] input = new String[20];

        for(int i=0;i<20;i++){

            input[i]=sc.nextLine();

        }

        

        int posX=Integer.parseInt(sc.nextLine());

        int posY=Integer.parseInt(sc.nextLine());

        

        boolean[][] maze = new boolean[20][20];

        for(int i=0;i<20;i++){

            for(int j=0;j<20;j++){

                if(input[i].charAt(j)=='X'){

                    maze[i][j]=false;

                }else{

                    maze[i][j]=true;

                }

            }

        }

        Brain myBrain = new Brain();

        

        

       while(lives>0){

            String move =myBrain.getMove(maze[posX-1][posY],maze[posX+1][posY],maze[posX][posY+1],maze[posX][posY-1]);

            if(move.equals("north")&&maze[posX-1][posY]){

                posX--;

            }else if(move.equals("south")&&maze[posX+1][posY]){

                posX++;

            }else if(move.equals("east")&&maze[posX][posY+1]){

                posY++;

            }else if(move.equals("west")&&maze[posX][posY-1]){

                posY--;

            }

            lives--;
            if(posY%19==0||posX%19==0){

                System.out.println(posX+","+posY);

                System.exit(0);

            }

        }

        System.out.println("You died in the maze!");

}
}

class Brain{

        Stack<String> stacks = new Stack<String>(); // direction north south east west stacks (reversed direction)
        boolean [][] map =new boolean [41] [41]; // track your x and y coordinates

        int x = 20;
        int y = 20;
    public String getMove(boolean north, boolean south, boolean east, boolean west){

        if(north&& !map[x-1][y])
        {
            x--;
            map[x] [y] = true;
            stacks.push("south");
            return "north";
        }
        else if(south&& !map[x+1][y])
        {
            x++;
            map[x] [y] = true;
            stacks.push("north");
            return "south";
        }
        else if(east&& !map[x][y+1])
        {
            y++;
            map[x] [y] = true;
            stacks.push("west");
            return "east";
        }
        else if(west&& !map[x][y-1])
        {
            y--;
            map[x] [y] = true;
            stacks.push("east");
            return "west";
        }
      
        String backup = stacks.pop();
        if(backup.equals("north")){
            x--;
        }
        else if(backup.equals("south")){
            x++;
        }
        else if(backup.equals("east")){
            y++;
        }
        else if(backup.equals("west")){
            y--;
        }
        return backup;


    }
}