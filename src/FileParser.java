import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
	private int particleCount;
	private double length;
	private double height;
	private LinkedList<Particle> particles;

	public FileParser() {
		this.particles = new LinkedList<Particle>();
	}
	
    public List<Particle> getParticles(String filePath) throws FileNotFoundException {
        
//    	System.out.println(filePath);
    	FileInputStream fis = new FileInputStream(filePath);  
        Scanner sc = new Scanner(fis);
        particleCount = sc.nextInt();
        length = sc.nextDouble();
        height = sc.nextDouble();
        for (int i = 0; i < particleCount; i++) {
        	double x = sc.nextDouble();
        	double y = sc.nextDouble();
        	double vx = sc.nextDouble();
        	double vy = sc.nextDouble();
        	double mass = sc.nextDouble();
        	double r = sc.nextDouble();
            particles.add(new Particle(i, x, y, vx, vy, mass, r));
        }            
		return particles;       
	}

	public double getLength() {
		return length;
	}
	
	public double getHeight() {
		return height;
	}
	
	public int getN() {
		return particleCount;
	}
}
