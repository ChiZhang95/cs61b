import java.awt.*;
/* Created by Chi Zhang at UC Berkeley, Feb. 5, 2018
*/

public class NBody {

	public static double readRadius(String text){
		In in = new In(text);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planets = new Planet[N];
		for(int i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgfilename = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgfilename);

		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		In in = new In(filename);
		int N = in.readInt();

		/*Draw the background*/
		StdDraw.clear();
		StdDraw.setScale(-radius, radius);
		String image = "images/starfield.jpg";
		StdDraw.picture(0,0,image);
		

		/*Draw all of planets*/
		for(int i =0; i < N; i++){
			Planet planet = planets[i];
			planet.draw();
		}

//		enableDoubleBuffering();
		
		for(double time = 0; time < T; time += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for(int i = 0; i < N; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < N; i++){
				Planet planet = planets[i];
				planet.update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0,0,image);

			for(int i =0; i < N; i++){
				Planet planet = planets[i];
				planet.draw();
			}

			StdDraw.show(10);
		}
	}
}
