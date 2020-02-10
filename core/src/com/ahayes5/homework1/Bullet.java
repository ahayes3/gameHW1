package com.ahayes5.homework1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bullet
{
	public Polygon collider;
	//private Texture texture;
	private float width,maxX,maxY;
	private Vector2 direction;
	private boolean player;
	public boolean offScreen;
	public static Texture texture = new Texture(Gdx.files.internal("new_bullet_red.png"));
	public Bullet(float w,Vector2 p,Vector2 d,float mX,float mY,boolean pl)
	{
		//texture=t;
		direction=d;
		collider=new Polygon(new float[]{0,0,0,w,w,w,w,0});
		width=w;
		collider.setPosition(p.x,p.y);
		player=pl;
		offScreen=false;
		maxX=mX;
		maxY=mY;
	}
	public void move()
	{
		if(collider.getX()< 0-width || collider.getX() > maxX || collider.getY()< 0-width || collider.getY() > maxY)
			offScreen=true;
		else
			collider.translate(direction.x*Gdx.graphics.getDeltaTime(),direction.y*Gdx.graphics.getDeltaTime());
	}
	public boolean fromPlayer()
	{
		return player;
	}
	public Enemy checkCollisions(Array<Enemy> enemies)
	{
		for(Enemy e: enemies)
		{
			if(Intersector.overlapConvexPolygons(this.collider,e.collider))
			{
				System.out.println("HIT");
				return e;
			}
		}
		return null;
	}
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture,collider.getX(),collider.getY(),width,width);
	}
}
