public class Event implements Comparator<Event>{
	

	//if i is null then its a collision between a horizontal wall and particle j
	//if j is null then its a collision between a vertical wall and particle i
	public Event(Particle i, Particle j, double time) {
        this.i = i;
        this.j = j;
        this.currentJ = 0;
        this.currentI = 0;
        if(i!= null) {
        	this.currentI = i.getCounter();
        }
        if(j!= null) {
        	this.currentJ = j.getCounter();
        }
        this.time = time;  
    }

    public double getTime() {
    	return time;
    }

    public Particle getParticle1() {
    	return i;
    }

    public Particle getParticle2() {
    	return j;
    }

    public boolean wasSuperveningEvent() {
    	if(i != null && j != null) {
    		if(i.getCounter() != currentI || j.getCounter() != currentJ) {
    			return true;
    		}
    	}
    	else
    		return false;
    }

    @Override
    public int compareTo(Event o) {
    	return this.getTime() - o.getTime();
    }
}