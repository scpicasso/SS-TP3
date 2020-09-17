package src;

public class Particle {
	
    private int id;
    private double x;
    private double y;
    private double radius;
    private double mass;
    private double vx;
    private double vy;
    private Event lastE;
    private Event newE;
    private int counter;

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
            return -1;
    }

    //checks if it will collide with partition wall or pass through gap
    public double xCollision(double length, double gap) {
         if(x > (length/2 - gap/2) && x < (length/2 + gap/2)) {
            if(vx > 0) {
                return (length - radius - x)/vx;
            }
            if(vx < 0) {
                return (radius - x)/vx;
            }
            else
                return -1;
        }
        else {
            if(vx > 0) {
                return (length/2 - radius - x)/vx;
            }
            if(vx < 0) {
                return (length/2 + radius - x)/vx;
            }
            else
                return -1;
        }
    }

    public double particleCollision(Particle j) {
        deltaX = j.getX() - x;
        deltaY = j.getY() - y;
        deltaVx = j.getVelocityX() - vx;
        deltaVy = j.getVelocityY() - vy;
        deltaVR = (deltaVx * deltaX) + (deltaVy * deltaY);
        dValue = Math.pow(deltaVR, 2) - 
        ((Math.pow(deltaVx,2) + Math.pow(deltaVy,2))*
            (Math.pow(deltaX,2) + Math.pow(deltaY,2) - 
                Math.pow(j.getRadius+radius, 2)));
        if(deltaVR>=0 || dValue<0) {
            return -1;
        }
        else {
            return -((deltaVR + Math.sqrt(dValue))/(Math.pow(deltaVx,2) + Math.pow(deltaVy,2)));
        }
    }

    public void bounceBackX() {
        this.vx = -vx;
    }

    public void bounceBackY() {
        this.vy = -vy;
    }

    public void bounce(Particle b) {
        deltaX = b.getX() - x;
        deltaY = b.getY() - y;
        deltaVx = b.getVelocityX() - vx;
        deltaVy = b.getVelocityY() - vy;
        deltaVR = (deltaVx * deltaX) + (deltaVy * deltaY);
        jValue = 2*mass*b.getMass()*deltaVR/((radius + b.getRadius())*(mass + b.getMass()));
        jX = jValue*deltaX/(radius + b.getRadius);
        jY = jValue*deltaY/(radius + b.getRadius);
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

}
