public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double dist = Math.sqrt(dx*dx + dy*dy);
		return dist;
	}

	public double calcForceExertedBy(Planet p){
		double dist = calcDistance(p);
		double Force = G*this.mass*p.mass/(dist*dist);
		return Force;
	}

	public double calcForceExertedByX(Planet p){
		double dist = calcDistance(p);
		double Force = calcForceExertedBy(p);
		double dx = this.xxPos - p.xxPos;
		double Fx = Force*dx/dist;
		if(Fx < 0){
			Fx = -Fx;
		}
		return Fx;
	}

	public double calcForceExertedByY(Planet p){
		double dist = calcDistance(p);
		double Force = calcForceExertedBy(p);
		double dy = this.yyPos - p.yyPos;
		double Fy = Force*dy/dist;
		if(Fy < 0){
			Fy = -Fy;
		}
		return Fy;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double netForceX = 0;
		for(int i = 0; i < ps.length; i++){
			Planet p = ps[i];
			double dist = calcDistance(p);
			if(dist == 0){continue;}
			double Force = calcForceExertedBy(p);
			double dx = this.xxPos - p.xxPos;
			double Fx = Force*dx/dist;
			netForceX += Fx;
		}
		if(netForceX < 0){
			netForceX = -netForceX;
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double netForceY = 0;
		for(int i = 0; i < ps.length; i++){
			Planet p = ps[i];
			double dist = calcDistance(p);
			if(dist == 0){continue;}
			double Force = calcForceExertedBy(p);
			double dy = this.yyPos - p.yyPos;
			double Fy = Force*dy/dist;
			netForceY += Fy;
		}
		if(netForceY < 0){
			netForceY = -netForceY;
		}
		return netForceY;
	}

	public void update(double dt,double fX, double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + ax*dt;
		this.yyVel = this.yyVel + ay*dt;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;
	}

	public void draw(){
		String filename = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,filename);
		StdDraw.show(1000);
	}
}
