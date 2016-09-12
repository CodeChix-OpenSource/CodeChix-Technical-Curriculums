//any variable that changes with each frame should be a global variable
int i=1;
//called only at the start of the execution
void setup(){
//sets the size of the output screen
  size(200,200);
//sets no. of frames displayed per second
  frameRate(1);
}
//called with each frame
void draw(){
  //sets the background color of the screen in each frame
  background((i*10)+30,(i*20)+20,(i*10));
  if(i==255)
  {
    i=1;
  }
  i+=1;
}