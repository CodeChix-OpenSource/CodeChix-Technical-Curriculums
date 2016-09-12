// Exercise - Move the arms of Robot Model
// 1. Set up the window size
// 2. Fill the background color
// 3. Create static Robot model 
// 4. For moving arms of Robot, use PushMatrix() , PopMatrix() & rotate()

float theta=0;
int flag=0;
void setup(){
size(500,500);
}
void draw(){
  background(0,0,0);
  fill(150,0,255);
  rect(225,100,50,50);
  fill(0,0,0);
  ellipse(235,115,15,15);
  ellipse(265,115,15,15);
  fill(150,0,255);
  rect(200,155,100,150);
  pushMatrix();
  translate(315,155);
 // rect(305,155,20,70);
  rotate(-theta);
  rect(-10,0,20,70);
  popMatrix();
  
  pushMatrix();
  translate(185,155);
 // rect(305,155,20,70);
  rotate(theta);
  rect(-10,0,20,70);
  popMatrix();
  
  if(theta >=PI/2.0)
  {
    flag = 1;
  }
  if(theta <=0)
  {
    flag =0;
  }
  if (flag ==0)
  {
    theta+=0.01;
  }
  else if(flag==1)
    theta-=0.01;
  //theta=abs(theta)-PI/2.0;
  rect(200,310,30,70);
  rect(270,310,30,70);
  
  
}