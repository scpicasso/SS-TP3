package src;

public class Particle implements Comparable<Particle>{
	
    private int id;
    private double x;
    private double y;
    private double radius;
    private double mass;
    private double vx;
    private double vy;
    static double trajectory;
    private int counter;

    public Particle(int id, double x, double y, double vx, double vy, double mass, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mass = mass;
        this.vx = vx;
        this.vy = vy;
        this.counter = 0;
        this.trajectory = 0;
    }
    
    public double yCollision(double height) {
        if(vy > 0) {
            return ((height - radius - y)/vy);
        }
        if(vy < 0) {
            return ((radius - y)/vy);
        }
        else
            return -1;
    }


    //checks if it will collide with partition wall or pass through gap
    public double xCollision(double length, double height, double gap) {

        if(vx > 0){
            if((y > (height/2 - gap/2) && y < (height/2 + gap/2)) || gap == 0.0)
                return ((length-radius-x)/vx);
            else if(x < length/2)
                return ((length/2 - radius - x)/vx);
            else
                return ((length-radius-x)/vx);
        }

        if(vx < 0){
            if((y > (height/2 - gap/2) && y < (height/2 + gap/2)) || gap == 0.0)
                return ((radius-x)/vx);
            else if(x < length/2)
                return ((radius - x)/vx);
            else
                return ((length/2+radius-x)/vx);

        }

        return -1;
    }

    public double timeToCollision(Particle b) {
    	if (id == b.getId()) {
    		return -1;
    	}
        
        double rads = radius + b.getRadius();

        double deltaX = b.getX() - x;
        double deltaY = b.getY() - y;
        double deltaVx = b.getVelocityX() - vx;
        double deltaVy = b.getVelocityY() - vy;

        double deltaVR = (deltaVx*deltaX) + (deltaVy*deltaY);

    	double dValue = deltaVR*deltaVR - (deltaVx*deltaVx + deltaVy*deltaVy)*(deltaX*deltaX + deltaY*deltaY - 
            rads*rads);
        if(deltaVR >= 0 || dValue < 0) {
            return -1;
        }
        else {
            return -(deltaVR + Math.sqrt(dValue))/
                (deltaVx*deltaVx + deltaVy*deltaVy);
        }
    }    
    
    public void bounceBackX() {
        this.vx = -vx;
    }

    public void bounceBackY() {
        this.vy = -vy;
    }

    public void bounce(Particle b) {
    	double rads = radius + b.getRadius();

        double deltaX = b.getX() - x;
    	double deltaY = b.getY() - y;
        double deltaVx = b.getVelocityX() - vx;
        double deltaVy = b.getVelocityY() - vy;

        double deltaVR = (deltaVx*deltaX) + (deltaVy*deltaY);

        double jValue1 = 2*mass*b.getMass()*deltaVR;
        double jValue2 = (radius + b.getRadius())*(mass + b.getMass());
        double jValue = jValue1/jValue2;

    	double jX = (jValue*deltaX)/rads;
    	double jY = (jValue*deltaY)/rads;

        vx = vx + jX/mass;
        vy = vy + jY/mass;

        b.setVelocityX(b.getVelocityX() - jX/b.getMass());
        b.setVelocityY(b.getVelocityY() - jY/b.getMass());

    }

    public double getXImpulse() {
        return Math.abs(vx)*2*mass;
    }
    
    public double getYImpulse() {
        return Math.abs(vy)*2*mass;
    }

    public void addTrajectory(double new_x, double new_y) {
        trajectory += getDistance(x, new_x, y, new_y);
    }

    public double getTrajectory() {
        return trajectory;
    }


    public double getDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
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

    public int getCounter() {
        return counter;
    }

    public void addCollision() {
        counter++;
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
