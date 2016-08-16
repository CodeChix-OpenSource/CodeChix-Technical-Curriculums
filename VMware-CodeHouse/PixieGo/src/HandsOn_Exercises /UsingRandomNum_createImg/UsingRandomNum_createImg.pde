 // Check random()
//Hint :- No using any image from net or pictures - just like art !
size(200, 200);

smooth();

background(399);

strokeWeight(8);



for(int i = 0; i < width; i++) {

  float r = random(255);

  float x = random(0, width);

  stroke(r, 123);

  line(i, 0, x, height);

}