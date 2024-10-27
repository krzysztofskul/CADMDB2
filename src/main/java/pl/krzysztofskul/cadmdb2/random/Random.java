package pl.krzysztofskul.cadmdb2.random;

import org.springframework.stereotype.Service;

@Service
public class Random extends java.util.Random {

	public static int randomInt(int i, int j) {
		int x = 0;
		java.util.Random r = new java.util.Random();
		x = r.nextInt(j-i)+i;
		return x;
	}
	
}
