//Create Robot's head using rect() & ellipse

void setup(){
size(500,500);
}
void draw(){
  fill(150,0,255);
  rect(225,100,50,50); // size of rect
  fill(0,0,0);
  ellipse(235,115,15,15); // left eye of robot 
  ellipse(265,115,15,15); // right eye of robot 
  fill(150,0,255);
  
}