// Now, creating Full Robot model using rect()
void setup(){
size(500,500);
}
void draw(){
  fill(150,0,255);
  rect(225,100,50,50); // size of rect for robot's head
  fill(0,0,0);
  ellipse(235,115,15,15); // left eye of robot 
  ellipse(265,115,15,15); // right eye of robot 
  fill(150,0,255);
  rect(200,155,100,150);  //  size of robot's body
  rect(305,155,20,70); // left arm of robot
  rect(175,155,20,70); // right arm of robot
  rect(200,310,30,70); // left leg of robot
  rect(270,310,30,70); // right leg of robot
}