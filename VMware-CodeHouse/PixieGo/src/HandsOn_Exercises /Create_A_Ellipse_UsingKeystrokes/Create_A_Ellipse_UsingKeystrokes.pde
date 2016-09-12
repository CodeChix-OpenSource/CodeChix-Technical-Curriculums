float rotationX = 0;
float rotationY = 0;
float velocityX = 0;
float velocityY = 0;

boolean[] keys = new boolean[255];

void setup(){
  size(500, 500, P3D);
}

void draw() {
  background(350);

  if(keys[LEFT]) {
    velocityY += 0.04;
  }
  if(keys[RIGHT]) {
    velocityY -= 0.04;
  }
  if(keys[UP]) {
    velocityX += 0.04;
  }
  if(keys[DOWN]) {
    velocityX -= 0.04;
  }

  translate(width/2, height/2);
  rotateX( radians(-rotationX) );
  rotateY( radians(-rotationY) );
  ellipse(0,0,220,220);
 rotationX += velocityX;
  rotationY += velocityY;
  velocityX *= 0.95;
  velocityY *= 0.95;
}

void keyPressed() {
  keys[keyCode] = true;
}

void keyReleased() {
  keys[keyCode] = false;
}