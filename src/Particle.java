
public class Particle implements Comparable<Particle>{
	
    private int id;
    private double x;
    private double y;
    private double radius;
    private double mass;
    private double vx;
    private double vy;
    static int K = 0;

	private Event lastE;
    private Event newE;
    private int counter;
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    public Particle(int id, double x, double y, double vx, double vy, double mass, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
        this.newE = null;
        this.lastE = null;
        this.counter = 0;
    }
    
    public double yCollision(double height) {
        if(vy > 0) {
            return (height - radius - y)/vy;
        }
        if(vy < 0) {
            return (radius - y)/vy;
        }
        else
            return INFINITY;
    }


    //checks if it will collide with partition wall or pass through gap
    public double xCollision(double length, double height, double gap) {
        if((y > (height/2 - gap/2) && y < (height/2 + gap/2)) || gap == 0.0) {
            if(vx > 0) 
                return (length - radius - x)/vx;
            if(vx < 0) 
                return (radius - x)/vx;
        }
        else {
            if(x < length/2) {
                if(vx > 0) 
                    return (length/2 - radius - x)/vx;
                if(vx < 0)
                    return (radius - x)/vx;
            }
            else {
                if(vx < 0)
                    return (length/2 + radius - x)/vx;
                if(vx > 0)
                    return (length - radius - x)/vx;
            }
        }
        return INFINITY;
    }

    public double timeToCollision(Particle j) {
    	if (this == j) {
    		return INFINITY;
    	}
    	double deltaX = j.getX() - x;
    	double deltaY = j.getY() - y;
    	double deltaVx = j.getVelocityX() - vx;
    	double deltaVy = j.getVelocityY() - vy;
    	double deltaVR = (deltaVx * deltaX) + (deltaVy * deltaY);
    	double dValue = Math.pow(deltaVR, 2) - 
        ((Math.pow(deltaVx,2) + Math.pow(deltaVy,2))*
            (Math.pow(deltaX,2) + Math.pow(deltaY,2) - 
                Math.pow(j.getRadius() + radius, 2)));
        if(deltaVR >= 0 || dValue < 0) {
            return INFINITY;
        }
        else {
        	double aux = -((deltaVR + Math.sqrt(dValue))/(Math.pow(deltaVx,2) + Math.pow(deltaVy,2))); 
            if (aux <= 0) {
//            	System.out.println("deltaVR: " + deltaVR + "dValue: " + Math.sqrt(dValue));
//            	System.out.println("Vix: " + vx + "	Vjx: " + j.getVelocityX());
//            	System.out.println("ix: " + x + "	jx: " + j.getX());
//            	System.out.println("Viy: " + vy + "	Vjy: " + j.getVelocityY());
//            	System.out.println("iy: " + y + "	jy: " + j.getY());
//            	System.out.println();
            	return INFINITY;
            }
            return aux;
        }
    }    
    
    public void bounceBackX() {
        this.vx = -vx;
    }

    public void bounceBackY() {
        this.vy = -vy;
    }

    public void bounce(Particle b) {
    	double deltaX = b.getX() - x;
    	double deltaY = b.getY() - y;
    	double deltaVx = b.getVelocityX() - vx;
    	double deltaVy = b.getVelocityY() - vy;
    	double deltaVR = (deltaVx * deltaX) + (deltaVy * deltaY);
    	double jValue = 2*mass*b.getMass()*deltaVR/((radius + b.getRadius())*(mass + b.getMass()));
    	double jX = jValue*deltaX/(radius + b.getRadius());
    	double jY = jValue*deltaY/(radius + b.getRadius());
        vx = vx + jX/mass;
        vy = vy + jX/mass;
        b.setVelocityX(b.getVelocityX() - jX/b.getMass());
        b.setVelocityY(b.getVelocityY() - jY/b.getMass());

    }
    
    public int getId() {
        return id;
    }

    public double getVelocityX() {
        return vx;
    }

    public double getVelocityY() {
        return vy;
    }
    
    public double getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMass() {
        return mass;
    }

    public Event getNewEvent() {
        return newE;
    }

    public Event getLastEvent() {
        return lastE;
    }

    public int getCounter() {
        return counter;
    }

    public void addCollision() {
        counter++;
    }

    public void setNewEvent(Event newE) {
        this.newE = newE;
    }

    public void setLastEvent(Event lastE) {
        this.lastE = lastE;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setVelocityX(double vx){
        this.vx = vx;
    }

    public void setVelocityY(double vy){
        this.vy = vy;
    }

	@Override
	public int compareTo(Particle j) {
        double dx  = j.x - this.x;
        double dy  = j.y - this.y;
		if (Math.sqrt(dx*dx + dy*dy) <= (this.radius*2)) {			
			return 0;
		}
		return this.id - j.id;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		return result;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Particle other = (Particle) obj;
//		if (compareTo(other) != 0)
//			return false;
//		return true;
//	}
	


}
