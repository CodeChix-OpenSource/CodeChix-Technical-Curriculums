


float time = 0;  // keep track of passing of time

void setup() {
  size(800, 800, P3D);  // must use 3D here !!!
  noStroke();           // do not draw the edges of polygons
}

// Draw a scene with a cylinder, a sphere and a box
void draw() {
  
  resetMatrix();  // set the transformation matrix to the identity 

  background(255,255,255);  // clear the screen to black
  smooth();
  // set up for perspective projection
  perspective (PI * 0.333, 1.0, 0.01, 1000.0);
  
  // place the camera in the scene (just like gluLookAt())
  camera (0.0, 0.0, 80.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0);
    
  // create an ambient light source
  ambientLight (102, 102, 102);
  
  // create two directional light sources
  //lightSpecular (204, 204, 204);
  directionalLight (102, 102, 102, -0.7, -0.7, -1);
  directionalLight (152, 152, 152, 0, 0, -1);
  
  // Draw a minion
  pushMatrix();
  
  translate(0,-10,0);
  //the following are some suggested helper functions that you can create
  //feel free to design some other way to build minion
  body();
  legs();
  feet();
  arms();
  eyes();
  glass();
  mouse();
 
  popMatrix(); 
}
void body(){
   //body
  pushMatrix();
  fill (245, 220, 80);
  ambient (51, 26, 0);
   sphereDetail (40);
  //head
  sphere (10);
  cylinder (10.0, 10.0, 32);
  fill(0,76,153);
  translate(0.0, 10,0);
  cylinder(10.5, 2, 32);
  
  fill (245, 220, 80);
  translate(0, 2,0);
  cylinder(10, 8, 32);
  fill(0,76,153);
  translate(0, 0,3);
  cylinder(8.5, 8, 32);
  translate(0,0, -6);
  cylinder(8.5, 8, 32);
  popMatrix();
  
  pushMatrix();
  translate (0.0, 21, 0.0);
  scale(1.0,0.6,1.0);
  sphere (10);
  popMatrix();  
}
void mouse(){
  fill(0,0,0);
  pushMatrix();
  translate(0,8,10);
  rotateX(radians(90));
  scale(1,1,1.2);
  cylinder(1.2,0.1,32);
  popMatrix();
}
void glass(){
  //band
  fill(51, 25,0);
  pushMatrix();
  cylinder(11,3,32);
  popMatrix();
  
  fill(192,192,192);
  pushMatrix();
  translate(3,1.5,10);
  rotateX(radians(90));
  cylinder(4,1,32);
  popMatrix();
  
  pushMatrix();
  translate(-3,1.5,10);
  rotateX(radians(90));
  cylinder(4,1,32);
  popMatrix();
  
}
void eyes(){
  fill (255, 255, 255);
  pushMatrix();
  translate(3,1.5,10);
  rotateX(radians(90));
  cylinder(2.5,1.1,32);
  popMatrix();
  
  pushMatrix();
  translate(-3,1.5,10);
  rotateX(radians(90));
  cylinder(2.5,1.1,32);
  popMatrix();
  
  fill(153, 76,0);
  pushMatrix();
  translate(2,1.5,10);
  rotateX(radians(90));
  cylinder(1,1.2,32);
  popMatrix();
  
  pushMatrix();
  translate(-2,1.5,10);
  rotateX(radians(90));
  cylinder(1,1.2,32);
  popMatrix();
  
  fill(0, 0,0);
  pushMatrix();
  translate(2,1.5,10);
  rotateX(radians(90));
  cylinder(0.3,1.3,32);
  
  popMatrix();  
  pushMatrix();
  translate(-2,1.5,10);
  rotateX(radians(90));
  cylinder(0.3,1.3,32);
  popMatrix();
}

void legs(){
  pushMatrix();
  translate(-2.5, 23, 0);
  cylinder(2,5, 32);
  popMatrix();
  pushMatrix();
  translate(2.5, 23, 0);
  cylinder(2,5, 32);
  popMatrix();
}

void feet(){
  fill(0,0,0);
  pushMatrix();
  translate(-2.5,28.5,1);
  scale(0.7,0.4,1.2);
  sphere(2.5);
  popMatrix();
  
  pushMatrix();
  translate(2.5,28.5,1);
  scale(0.7,0.4,1.2);
  sphere(2.5);
  popMatrix();
  
}
void arms(){
  fill (245, 220, 80);
  pushMatrix();
  translate(14,10,0);
  rotateZ(radians(30));
  scale(3.0, 1, 1);
  sphere(2);
  popMatrix();
  pushMatrix();
  translate(-14,10,0);
  rotateZ(radians(-30));
  scale(3.0, 1, 1);
  sphere(2);
  popMatrix();
  pushMatrix();
  translate(15.3,14.5,0);
  rotateZ(radians(-30));
  scale(3, 1, 1);
  sphere(1.5);
  popMatrix();
  
  pushMatrix();
  translate(-15.3,14.5,0);
  rotateZ(radians(30));
  scale(3.0, 1, 1);
  sphere(1.5);
  popMatrix();
  hands();
}
void hands(){
  fill(0,0,0);
  pushMatrix();
  translate(11,18,0);
  rotateZ(radians(-20));
  scale(1, 0.5,1);
  sphere(2);
  popMatrix();
  
  pushMatrix();
  translate(-11,18,0);
  rotateZ(radians(20));
  scale(1, 0.5,1);
  sphere(2);
  popMatrix();
}
// Draw a cylinder of a given radius, height and number of sides.
// The base is on the y=0 plane, and it extends vertically in the y direction.
void cylinder (float radius, float height, int sides) {
  int i,ii;
  float []c = new float[sides];
  float []s = new float[sides];

  for (i = 0; i < sides; i++) {
    float theta = TWO_PI * i / (float) sides;
    c[i] = cos(theta);
    s[i] = sin(theta);
  }  
  // bottom end cap
  normal (0.0, -1.0, 0.0);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, 0.0, s[ii] * radius);
    vertex (c[i] * radius, 0.0, s[i] * radius);
    vertex (0.0, 0.0, 0.0);
    endShape();
  }
  // top end cap
   normal (0.0, 1.0, 0.0);
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape(TRIANGLES);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    vertex (0.0, height, 0.0);
    endShape();
  }  
  // main body of cylinder
  for (i = 0; i < sides; i++) {
    ii = (i+1) % sides;
    beginShape();
    normal (c[i], 0.0, s[i]);
    vertex (c[i] * radius, 0.0, s[i] * radius);
    vertex (c[i] * radius, height, s[i] * radius);
    normal (c[ii], 0.0, s[ii]);
    vertex (c[ii] * radius, height, s[ii] * radius);
    vertex (c[ii] * radius, 0.0, s[ii] * radius);
    endShape(CLOSE);
  }
}