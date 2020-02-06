package com.ahayes5.homework1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;

public abstract class Plane
{
	Polygon collider;
	Texture texture;
	boolean exploded;
	public Plane(Texture t, Polygon p)
	{
		texture=t;
		collider=p;
		exploded=false;
	}
	//abstract void move();
	//abstract void checkCollisions();
	public abstract void draw(Batch batch);
	public abstract void explode();

}
