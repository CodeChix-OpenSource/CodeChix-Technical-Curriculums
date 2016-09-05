
float time =1.0;
void setup() {
  
  size(800, 800, P3D);  // must use 3D here !!!
  noStroke();     // do not draw the edges of polygons
  
}
// Draw a scene with a cylinder, a sphere and a box
void draw() {
  
  time += 1;
  resetMatrix();  // set the transformation matrix to the identity (important!)
  background(0,0,0);  // clear the screen to black
  smooth();
  // set up for perspective projection
  perspective (PI * 0.333, 1.0, 0.01, 1000.0);
  
  // place the camera in the scene (just like gluLookAt())
  
  camera (0.0, 0.0, 80, 0.0, 0.0, -1.0, 0.0, 1, 0);
    
  // create an ambient light source
  ambientLight (102, 102, 102);
  
  // create two directional light sources
  //lightSpecular (204, 204, 204);
  directionalLight (102, 102, 102, -0.7, -0.7, -1);
  directionalLight (152, 152, 152, 0, 0, -1);
  
 
  fill(255,255,0);
  sphere(6);
  
  
  pushMatrix();
  
  rotateY(radians(time));
  translate(30, 0,0);
  
  pushMatrix();
  
  rotateZ(radians(time));
  translate(0,8,0);
  fill(255,255,255);
  sphere(1);
  popMatrix();
  
  fill(0,0,255);
  sphere(3);
  popMatrix();
    
  
}