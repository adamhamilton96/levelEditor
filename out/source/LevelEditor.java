import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LevelEditor extends PApplet {

int[][] grid; //Grid Allocations 0 = EMPTY, 1 = MOUSE, 2 = WALL, 3 = CHEESE, 4 = KEY
int gridHeight;
int gridWidth;
int gridSize;
int choice = 0;

Table level;

public void setup() {
    gridSize = 20;
    gridHeight = height / gridSize;
    gridWidth = width / gridSize;
    grid = new int[width / gridWidth][height / gridHeight];
    
}

public void draw() {
    for(int j = 0; j < gridSize; j++) {
        for(int i = 0; i < gridSize; i++) {
            if (grid[i][j] == 0) fill(255);
            else if (grid[i][j] == 1) fill(255, 0, 0);
            else if (grid[i][j] == 2) fill(0);
            else if (grid[i][j] == 3) fill(0, 0, 255);
            else if (grid[i][j] == 4) fill(0, 255, 0);
            rect(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
        }
    }
}

public void dispose() {
    level = new Table();
    for(int j = 0; j < gridSize; j++) {
        if(j < 10) level.addColumn(str(j));
        else level.addColumn(str(j));
    }
    for(int j = 0; j < gridSize; j++) {
        TableRow newRow = level.addRow();
        for(int i = 0; i < gridSize; i++) {
            newRow.setInt(str(i), grid[i][j]);
        }
    }
    saveTable(level, "level/level1.csv");
}

public void mouseDragged() {
    for(int j = 0; j < gridSize; j++) {
        for(int i = 0; i < gridSize; i++) {
            if (mouseX > i * gridWidth && mouseX < i * gridWidth + gridWidth && mouseY > j * gridHeight && mouseY < j * gridHeight + gridHeight) {
                if (choice == 0) grid[i][j] = 0;
                else if (choice == 1) grid[i][j] = 1;
                else if (choice == 2) grid[i][j] = 2;
                else if (choice == 3) grid[i][j] = 3;
                else if (choice == 4) grid[i][j] = 4;
            }
        }
    }
}

public void mousePressed() {
  for (int j = 0; j < gridSize; j++) {
    for (int i = 0; i < gridSize; i++) {
      if (mouseX > i * gridWidth && mouseX < i * gridWidth + gridWidth && mouseY > j * gridHeight && mouseY < j * gridHeight + gridHeight) {
        if (choice == 0) grid[i][j] = 0;
        else if (choice == 1) grid[i][j] = 1;
        else if (choice == 2) grid[i][j] = 2;
        else if (choice == 3) grid[i][j] = 3;
        else if (choice == 4) grid[i][j] = 4;
      }
    }
  }
}

public void keyPressed() {
  if (key == 'e') choice = 0; // EMPTY
  else if (key == 'm') choice = 1; // MOUSE
  else if (key == 'w') choice = 2; // WALL
  else if (key == 'c') choice = 3; // CHEESE
  else if (key == 'k') choice = 4; // KEY
  else if (key == 'z') exit();
}
  public void settings() {  size(640, 640); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LevelEditor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
